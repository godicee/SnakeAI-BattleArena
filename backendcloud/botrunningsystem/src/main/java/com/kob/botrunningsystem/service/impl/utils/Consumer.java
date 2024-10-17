package com.kob.botrunningsystem.service.impl.utils;

import com.kob.botrunningsystem.utils.BotInterface;
import org.joor.Reflect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.UUID;
import java.util.function.Supplier;

@Component
public class Consumer extends Thread{
    private Bot bot;
    private static RestTemplate restTemplate;
    private final static String receiveBotMoveUrl = "http://127.0.0.1:3002/pk/receive/bot/move/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        Consumer.restTemplate = restTemplate;
    }

    public void startTimeOut(long timeout, Bot bot){
        this.bot = bot;
        this.start();  // 当前线程执行start后会开一个新的线程去执行run()函数，然后当前线程继续执行后面的代码

        try{                     // 当前线程继续执行到join,当前线程会阻塞timeout秒，再执行后面的操作
            this.join(timeout);  // 如果新开线程的run函数执行完毕，本线程会跳过阻塞，继续执行后面
        } catch (InterruptedException e){
            e.printStackTrace();
        } finally {
            this.interrupt();  //经过timeout秒，run如果还没执行完毕，就终结本线程
        }
    }

    private String addUid(String code, String uid){  // 在 code中的bot类名后面加上 uid
        int k = code.indexOf(" implements java.util.function.Supplier<Integer>");  // 获取插入位置
        return code.substring(0, k) + uid + code.substring(k);  // 在bot类名后面加上 uid
    }

    // run方法是线程执行的主体。当创建一个Thread对象并调用它的start方法时，JVM会在一个新的线程中执行这个对象的run方法
    @Override
    public void run() {
        UUID uuid = UUID.randomUUID();
        String uid = uuid.toString().substring(0, 8);  // 取前 8 位


        // botInterface获取了一个：编译java字符串代码的类的实例（相当于new一个类的实例：只不过这个类是字符串定义的）
        Supplier<Integer> botInterface = Reflect.compile(  // 编译代码的接口joor-java-8，接受文件名和代码内容两个参数
                "com.kob.botrunningsystem.utils.Bot" + uid,  // 重名类只会编译一次，（不同用户的bot代码不一样）所以类名后面需要加一个随机字符串
                addUid(bot.getBotCode(), uid)  // 传入了前端的 bot 代码，同时包名和类名都需要加上相同的 uid
        ).create().get();  // Reflect.compile编译传入的字符串形式 java 代码、并返回一个类，create和get创建并获取编译后的类的实例

        File file = new File("input.txt");
        try(PrintWriter fout = new PrintWriter(file)){
            fout.println(bot.getInput());
            fout.flush();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        Integer direction = botInterface.get();
        System.out.println("move-direction: userid" + bot.getUserId() + " direction is" + direction);

        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("user_id", bot.getUserId().toString());
        data.add("direction", direction.toString());
        restTemplate.postForObject(receiveBotMoveUrl, data, String.class);
//        package com.kob.botrunningsystem.utils.Bot;  // 这里需要加上 uid
//
//        public class Bot implements com.kob.botrunningsystem.utils.BotInterface{  // 这里的 Bot 后面也需要加上 uid
//            @Override
//            public Integer nextMove(String input) {
//                return 0;
//            }
//        }
    }
}
