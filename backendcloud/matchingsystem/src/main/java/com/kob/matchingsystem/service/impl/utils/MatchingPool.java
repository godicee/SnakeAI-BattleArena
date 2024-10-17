package com.kob.matchingsystem.service.impl.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

@Component  // 为了可以注入 spring中的 bean
public class MatchingPool extends Thread{
    private static List<Player> players = new ArrayList<>();//存匹配池中的玩家
    private final ReentrantLock lock = new ReentrantLock();
    private static RestTemplate restTemplate;
    private final static String startGameUrl = "http://127.0.0.1:3002/pk/start/game/";

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate){
        MatchingPool.restTemplate = restTemplate;
    }

    public void addPlayer(Integer userId, Integer rating, Integer botId){
        lock.lock();
        try{
            players.add(new Player(userId, rating, botId ,0));
        } finally{
            lock.unlock();
        }
    }

    public void removePlayer(Integer userId){
        lock.lock();
        try{
            List<Player> newPlayers = new ArrayList<>();
            for(Player player: players){
                if(!player.getUserId().equals(userId)){
                    newPlayers.add(player);
                }
            }
            players = newPlayers;
        } finally {
            lock.unlock();
        }
    }

    private void increaseWaitingTime(){  // 所有玩家的等待时间+1s
        for(Player player: players){
            player.setWaitingTime(player.getWaitingTime() + 1);
        }
    }

    private boolean checkMatched(Player a, Player b){  // 判断两名玩家是否匹配
        int ratingDelta = Math.abs(a.getRating() - b.getRating());
        int waitingTime = Math.min(a.getWaitingTime(), b.getWaitingTime());  // 两个玩家的等待时间都满足匹配条件才匹配
        return ratingDelta <= waitingTime * 10;
    }

    private void sendResult(Player a, Player b) {  // 向两个匹配成功的玩家发送匹配结果
        System.out.println("send result " + a.getUserId() + " " + b.getUserId());
        MultiValueMap<String, String> data = new LinkedMultiValueMap<>();
        data.add("a_id", a.getUserId().toString());
        data.add("a_bot_id", a.getBotId().toString());
        data.add("b_id", b.getUserId().toString());
        data.add("b_bot_id", b.getBotId().toString());
        restTemplate.postForObject(startGameUrl, data, String.class);
    }

    private void matchPlayers(){  // 尝试匹配所有玩家
        // System.out.println("match players: " + players.toString());
        boolean[] used = new boolean[players.size()];  // 存储哪些玩家已经匹配成功的玩家

        // 优先匹配等待时间长的玩家，因为是 list.add，所以老玩家在列表最前面
        for(int i = 0; i < players.size(); ++i){
            if(used[i]) continue;
            for(int j = i + 1; j < players.size(); ++j){
                if(used[j]) continue;
                Player a = players.get(i), b = players.get(j);
                if(checkMatched(a, b)){
                    used[i] = used[j] = true;
                    sendResult(a, b);
                    break;
                }
            }
        }

        // 删除已匹配的玩家
        List<Player> newPlayers = new ArrayList<>();
        for(int i = 0; i < players.size(); ++i){
            if(!used[i]){
                newPlayers.add(players.get(i));
            }
        }
        players = newPlayers;
    }

    // 匹配逻辑：rating 分在 10以内的玩家可以匹配到一起，每 s 轮询一次，每过 1 s 可以匹配的 rating 分差值+10
    @Override
    public void run() {//线程启动需要执行的方法
        while(true){
            try {
                Thread.sleep(1000);
                lock.lock();
                try{
                    increaseWaitingTime();
                    matchPlayers();
                } finally {
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}
