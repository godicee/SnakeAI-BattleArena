package com.kob.matchingsystem;

import com.kob.matchingsystem.service.MatchingService;
import com.kob.matchingsystem.service.impl.MatchingServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MatchingSystemApplication {
    public static void main(String[] args) {
        //每两个玩家的对局匹配都是用一个线程处理的
        //不然需要等第一对匹配上，其他才能继续匹配
        MatchingServiceImpl.matchingPool.start();  // 启动匹配线程
        SpringApplication.run(MatchingSystemApplication.class, args);
    }
}