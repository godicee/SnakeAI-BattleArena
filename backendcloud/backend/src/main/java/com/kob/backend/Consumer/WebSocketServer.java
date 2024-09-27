package com.kob.backend.Consumer;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.Consumer.Utils.Game;
import com.kob.backend.Consumer.Utils.JwtAuthentication;
import com.kob.backend.mapper.BotMapper;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component//为了可以注入spring 中的 bean
//映射到哪个链接
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    //存储userid 和websocket 链接的映射，用于匹配成功后向对应前端返回信息
    //ConcurrentMap是线程安全的哈希表，存储所有链接的映射
    //static是所有实例可见的全局变量
    final public static ConcurrentMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    //CopyOnWriteArraySet线程安全
    //final 声明为常量，（引用地址不可以改，值可以改）进一步保证线程安全

    private User user;//user对象信息需要存到 session 中
    private Session session = null;

    //websocket不是 spring 标准组件、注入需要特殊处理，不能简单Autowired
    //websocket 不是单例模式（线程安全：每个类同一时间只能有一个实例）
    public static UserMapper userMapper;//用户数据库
    public static RecordMapper recordMapper;//对局记录数据库
    private static BotMapper botMapper;  // bot 数据库注入
    public static RestTemplate restTemplate;//RestTemplate用于发送 http，在两个服务器间通信
    public Game game = null;
    //暂时用于服务器通信的常量
    private final static String addPlayerUrl = "http://127.0.0.1:3001/player/add/";
    private final static String removePlayerUrl = "http://127.0.0.1:3001/player/remove/";

    //注意上面定义的都是静态变量，静态变量的访问需要用类名
    //多线程的独特注入方式
    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }
    @Autowired
    public void setRecordMapper(RecordMapper recordMapper){
        WebSocketServer.recordMapper = recordMapper;
    }

    @Autowired
    public void setBotMapper(BotMapper botMapper){
        WebSocketServer.botMapper = botMapper;
    }

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        WebSocketServer.restTemplate = restTemplate;
    }

    @OnOpen
    public void onOpen(Session session, @PathParam("token") String token) throws IOException {
        // 建立连接
        this.session = session;
        System.out.println("Connected!");
        Integer userId = JwtAuthentication.getUserId(token);
        this.user = userMapper.selectById(userId);//获取新建立链接的用户 id

        if(this.user != null){
            users.put(userId, this);//新建立链接的 userId 和 websocket链接的映射
            System.out.println(users);
        }else{
            this.session.close();
            System.out.println("Invalid token!");
        }

    }

    @OnClose
    public void onClose() {
        // 关闭链接
        System.out.println("Disconnected!");
        //断开连接需要删除链接映射
        if(this.user == null){
            users.remove(this.user.getId());
        }
    }

    public static void startGame(Integer aId, Integer aBotId, Integer bId, Integer bBotId){//匹配成功后开启游戏
        User a = userMapper.selectById(aId), b = userMapper.selectById(bId);
        Bot botA = botMapper.selectById(aBotId), botB = botMapper.selectById(bBotId);

        Game game = new Game(
                13,
                14,
                20,
                a.getId(),
                botA,
                b.getId(),
                botB
        );
        game.createMap();
        if(users.get(a.getId()) != null)
            users.get(a.getId()).game = game;//获取 a 的 ws 链接，再由链接获game实例
        if(users.get(b.getId()) != null)
            users.get(b.getId()).game = game;
        game.start();//Tread的 api，进入一个新线程开始执行接下来的函数

        //地图信息（蛇的位置）
        JSONObject respGame = new JSONObject();
        respGame.put("a_id", game.getPlayerA().getId());
        respGame.put("a_sx", game.getPlayerA().getSx());
        respGame.put("a_sy", game.getPlayerA().getSy());
        respGame.put("b_id", game.getPlayerB().getId());
        respGame.put("b_sx", game.getPlayerB().getSx());
        respGame.put("b_sy", game.getPlayerB().getSy());
        respGame.put("map", game.getG());

        //对手信息
        JSONObject respA = new JSONObject();
        respA.put("event", "match");
        respA.put("opponent_username", b.getUsername());
        respA.put("opponent_photo", b.getPhoto());
        respA.put("game", respGame);
        //向前端返回信息
        if(users.get(a.getId()) != null)
            users.get(a.getId()).sendMessage(respA.toJSONString());

        JSONObject respB = new JSONObject();
        respB.put("event", "match");
        respB.put("opponent_username", a.getUsername());
        respB.put("opponent_photo", a.getPhoto());
        respB.put("game", respGame);
        //向前端返回信息
        if(users.get(b.getId()) != null)
            users.get(b.getId()).sendMessage(respB.toJSONString());
        System.out.println("恭喜"+ a.getUsername() + " & " + b.getUsername() +    "成功匹配");
    }

    private void startMatching(Integer botId){//开启匹配，向匹配系统发请求开始匹配(add)
        System.out.println(this.user.getUsername() + " start matching!");
        //向匹配系统（微服务）的后端发请求：向匹配池加入一个玩家
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        data.add("rating", this.user.getRating().toString());
        data.add("bot_id", botId.toString());
        restTemplate.postForObject(addPlayerUrl, data, String.class);//第三个参数是期望返回的格式，String.class标识返回的是字符串
    }

    private void stopMatching(){//停止匹配，向匹配系统发请求取消匹配（remove）
        System.out.println(this.user.getUsername() + " stop matching");
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", this.user.getId().toString());
        restTemplate.postForObject(removePlayerUrl, data, String.class);
    }

    private void move(int direction){  // 传递人的操作给 game 使玩家移动
        if(game.getPlayerA().getId().equals(user.getId())){
            if(game.getPlayerA().getBotId().equals(-1))  // 亲自出马
                game.setNextStepA(direction);
        }else if(game.getPlayerB().getId().equals(user.getId())){
            if(game.getPlayerB().getBotId().equals(-1))
                game.setNextStepB(direction);
        }
    }

    @OnMessage
    public void onMessage(String message, Session session) {//收到的信息当作路由（交给哪个函数处理）
        // Server从Client接收到消息
        System.out.println("Receive Message!");
        JSONObject data = JSONObject.parseObject(message);
        String event = data.getString("event");
        if("start-matching".equals(event)){
            startMatching(data.getInteger("bot_id"));
        }else if("stop-matching".equals(event)){
            stopMatching();
        }else if("move".equals(event)){
            move(data.getInteger("direction"));
        }
    }

    @OnError
    public void onError(Session session, Throwable error) {
        error.printStackTrace();
    }

    //server 向client 发信息
    public void sendMessage(String message){
        synchronized(this.session){//因为是异步的，所以要加锁（session的同步块：保证多线程访问的安全）
            try{
                this.session.getBasicRemote().sendText(message);
            } catch (IOException e){//需要捕获一个 io 异常
                e.printStackTrace();//输出
            }
        }
    }
}