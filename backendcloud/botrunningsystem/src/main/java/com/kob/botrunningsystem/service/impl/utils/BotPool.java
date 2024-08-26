package com.kob.botrunningsystem.service.impl.utils;


import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// 生产者消费者模型（pv 信号量）：一个消费者不停地从队列头部取出代码执行（执行后放到队尾）
// 一个生产者不停地向队列尾部添加代码
public class BotPool extends Thread{
    private final ReentrantLock lock = new ReentrantLock();  // 锁
    private final Condition condition = lock.newCondition();  // 信号量
    private final Queue<Bot> bots = new LinkedList<>();  // 等待队列

    public void addBot(Integer userId, String botCode, String input){  // 在BotRunningService调用
        lock.lock();
        try{
            bots.add(new Bot(userId, botCode, input));
            condition.signalAll();  // 唤醒所有等待的线程（因为这里只有一个线程，可以signalAll）
        } finally{
            lock.unlock();
        }
    }


    private void consume(Bot bot){
        // 这里使用一个新的线程Consumer：控制用户执行代码的时间——超时就自动断掉
        Consumer consumer = new Consumer();  // 开启新线程完成了 bot 代码的编译和执行
        consumer.startTimeOut(2000, bot);  // 超时后，会自动结束线程

    }

    @Override
    public void run() {  // 本质是实现了一个消息队列
        while(true){
            lock.lock();  // 对队列的互斥操作
            if(bots.isEmpty()){  // 队列已经消化完了
                try{
                    condition.await();  // await的时候会释放锁，其他线程可以使用，本线程等待被唤醒
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else{
                Bot bot = bots.remove();  // 拿出队头的 Bot 运行它的代码
                lock.unlock();
                consume(bot);  //  执行拿出 Bot 的代码（编译执行），比较消耗时间，必须加在unlock后
            }
        }
    }
}
