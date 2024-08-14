package com.kob.backend.Consumer;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.Consumer.Utils.Game;
import com.kob.backend.Consumer.Utils.JwtAuthentication;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
//映射到哪个链接
@ServerEndpoint("/websocket/{token}")  // 注意不要以'/'结尾
public class WebSocketServer {
    //存储userid 和websocket 链接的映射，用于匹配成功后向对应前端返回信息
    //ConcurrentMap是线程安全的哈希表，存储所有链接的映射
    //static是所有实例可见的全局变量
    final public static ConcurrentMap<Integer, WebSocketServer> users = new ConcurrentHashMap<>();
    //CopyOnWriteArraySet线程安全
    //final 声明为常量，（引用地址不可以改，值可以改）进一步保证线程安全
    final private static CopyOnWriteArraySet<User>  matchpool = new CopyOnWriteArraySet<>();//对战页面点击开始匹配后——用户进入匹配池

    private User user;//user对象信息需要存到 session 中
    private Session session = null;

    //websocket不是 spring 标准组件、注入需要特殊处理，不能简单Autowired
    //websocket 不是单例模式（线程安全：每个类同一时间只能有一个实例）
    private static UserMapper userMapper;
    public static RecordMapper recordMapper;
    private Game game = null;

    @Autowired
    public void setUserMapper(UserMapper userMapper){
        WebSocketServer.userMapper = userMapper;
    }//静态变量访问需要用类名

    @Autowired
    public void setRecordMapper(RecordMapper recordMapper){
        WebSocketServer.recordMapper = recordMapper;
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
            matchpool.remove(this.user);
        }
    }

    private void startMatching(){
        System.out.println(this.user.getUsername() + " start matching!");
        matchpool.add(this.user);
        System.out.println("matchpool" + matchpool);

        while(matchpool.size() >= 2){
            Iterator<User> it = matchpool.iterator();
            User a = it.next(), b = it.next();
            matchpool.remove(a);
            matchpool.remove(b);


            Game game = new Game(13, 14, 20, a.getId(), b.getId());
            game.createMap();
            users.get(a.getId()).game = game;//获取 a 的 ws 链接，再由链接获game实例
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
            users.get(a.getId()).sendMessage(respA.toJSONString());

            JSONObject respB = new JSONObject();
            respB.put("event", "match");
            respB.put("opponent_username", a.getUsername());
            respB.put("opponent_photo", a.getPhoto());
            respB.put("game", respGame);
            //向前端返回信息
            users.get(b.getId()).sendMessage(respB.toJSONString());
            System.out.println("恭喜"+ a.getUsername() + " & " + b.getUsername() +    "成功匹配");
        }
    }
    private void stopMatching(){
        System.out.println("stop matching");
        matchpool.remove(this.user);
        System.out.println("matchpool" + matchpool);
    }

    private void move(int direction){
        if(game.getPlayerA().getId().equals(user.getId())){
            game.setNextStepA(direction);
        }else if(game.getPlayerB().getId().equals(user.getId())){
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
            startMatching();
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