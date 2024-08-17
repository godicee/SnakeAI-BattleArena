package com.kob.backend.Consumer.Utils;

import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.Consumer.WebSocketServer;
import com.kob.backend.pojo.Record;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Game extends Thread{
    //地图信息
    private final Integer rows;
    private final Integer cols;
    private final Integer inner_walls_count;
    private final int[][] g;
    private final static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    //玩家信息
    private final Player playerA, playerB;//A 左下，B 右上
    //存储玩家下一步操作（0123上下左右）
    private Integer nextStepA = null;//下一步的操作
    private Integer nextStepB = null;
    private ReentrantLock lock = new ReentrantLock();
    private String status = "playing";//游戏状态：playing -> finished
    private String loser = "";//谁输了：all/A/B  平局/a输/b输


    public Game(Integer rows, Integer cols, Integer inner_walls_count, Integer idA, Integer idB){
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];//

        playerA = new Player(idA, rows - 2, 1, new ArrayList<>());
        playerB = new Player(idB, 1, cols - 2, new ArrayList<>());

    }

    public Player getPlayerA() {
        return playerA;
    }

    public Player getPlayerB() {
        return playerB;
    }

    public int[][] getG(){//返回棋盘
        return g;
    }

    //会在WebSocketServer.java中收到前端发的信息后，会调用给变量赋值
    public void setNextStepA(Integer nextStepA){
        lock.lock();
        try{
            this.nextStepA = nextStepA;
        } finally{//报不报异常都解锁，不会死锁
            lock.unlock();
        }
    }

    public void setNextStepB(Integer nextStepB){
        lock.lock();
        try{
            this.nextStepB = nextStepB;
        } finally{
            lock.unlock();
        }
    }

    //DFS 深度优先
    public boolean check_connectivity(int sx, int sy, int tx, int ty){//起点(sx,sy)到终点(tx,ty)是否存在路径
        if(sx == tx && sy == ty) return true;
        g[sx][sy] = 1;//已访问过（同时这里也会被标记为有墙，因为是全局变量，所以后面必须恢复）

        for(int i = 0; i < 4; ++i){//四个方向
            int x = sx + dx[i], y = sy + dy[i];//预访问
            if(x >= 0 && x < this.rows && y >= 0 && y < this.cols && g[x][y] == 0){//预访问点是否在棋盘范围内，且没有障碍物
                if(check_connectivity(x, y, tx, ty)){
                    g[sx][sy] = 0;//重置源点为无墙状态
                    return true;
                }
            }
        }
        g[sx][sy] = 0;//重置为未访问且无墙状态
        return false;
    }
    private boolean draw(){//画地图
        //清空之前生成的
        for(int i = 0; i < this.rows; ++i){
            for(int j = 0; j < this.cols; ++j){
                this.g[i][j] = 0;
            }
        }

        //给四周加上障碍物
        for(int r = 0; r < this.rows; ++r){
            g[r][0] = g[r][this.cols - 1] = 1;
        }
        for(int c = 0; c < this.cols; ++c){
            g[0][c] = g[this.rows - 1][c] = 1;
        }

        //随机生成障碍物
        Random random = new Random();
        for(int i = 0; i <  this.inner_walls_count / 2; ++i){
            for(int j = 0; j < 1000; ++j) {//防止随机到已放过障碍物的色块
                int r = random.nextInt(this.rows);
                int c = random.nextInt(this.cols);

                if(g[r][c] == 1 || g[this.rows - 1 - r][this.cols - 1 - c] == 1)//如果本位置or对称位置存在障碍物，则重新生成
                    continue;
                if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2)//左下右上（蛇生成位置）不能有障碍物
                    continue;

                g[r][c] = g[this.rows - 1 - r][this.cols - 1 - c] = 1;//对称生成
                break;
            }


        }

        return check_connectivity(this.rows - 2, 1, 1, this.cols - 2);
    }

    public void createMap(){//随机生成（合法）地图
        for(int i = 0; i < 1000; ++i){
            if(draw()){
                break;
            }
        }
    }

    //轮询读取玩家下一步操作
    private boolean nextStep(){
        //前端蛇每秒走5个格子，200ms走一格，需要最少 sleep200ms读取一次玩家下一步操作
        //因为后端（如果是 bot 操作）返回操作信息的速度快于200ms/次（返回一个序列）
        //前端读取会被覆盖，只留下序列的最后一个操作，覆盖掉前面的操作
        try{
            Thread.sleep(200);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        for(int i = 0; i < 50; ++i){
            try{
                Thread.sleep(100);
                lock.lock();
                try{
                    if(nextStepA != null && nextStepB != null){
                        playerA.getSteps().add(nextStepA);
                        playerB.getSteps().add(nextStepB);
                        return true;
                    }
                } finally{
                    lock.unlock();
                }

            } catch(InterruptedException e){
                e.printStackTrace();
            }

        }

        return false;
    }

    private boolean check_valid(List<Cell> cellsA, List<Cell> cellsB){//检测A蛇的移动是否合法
        int n = cellsA.size();
        Cell newCell = cellsA.get(n - 1);

        if(g[newCell.x][newCell.y] == 1) return false;//撞墙
        //撞自己
        for(int i = 0; i < n - 1; ++i){//cellA[n-1]处是新的蛇头
            if(newCell.x == cellsA.get(i).x && newCell.y == cellsA.get(i).y)
                return false;
        }
        //撞对手
        for(int i = 0; i < n - 1; ++i){
            if(newCell.x == cellsB.get(i).x && newCell.y == cellsB.get(i).y)
                return false;
        }

        //往回走
        return true;
    }

    private void judge(){//判断两名玩家下一步操作是否合法
        //先取出两条蛇的身体
        List<Cell> cellsA = playerA.getCells();
        List<Cell> cellsB = playerB.getCells();
        boolean valid_a = check_valid(cellsA, cellsB), valid_b = check_valid(cellsB, cellsA);

        if(!valid_a || !valid_b){
            status = "finished";
            if(!valid_a && !valid_b){
                loser = "all";
            }else if(!valid_a){
                loser = "A";
            }else{
                loser = "B";
            }
        }
    }

    private void sendAlLMessage(String message){//向两个client传递信息时调用的
        if(WebSocketServer.users.get(playerA.getId()) != null)
            WebSocketServer.users.get(playerA.getId()).sendMessage(message);
        if(WebSocketServer.users.get(playerB.getId()) != null)
            WebSocketServer.users.get(playerB.getId()).sendMessage(message);
    }

    private void sendMove(){//向两个 client 传递移动信息
        lock.lock();
        try{
            JSONObject resp = new JSONObject();
            resp.put("event", "move");
            resp.put("a_direction", nextStepA);
            resp.put("b_direction", nextStepB);
            sendAlLMessage(resp.toJSONString());
            nextStepA = nextStepB = null;//读取后清空操作
        } finally{
            lock.unlock();
        }

    }

    private String getMapString(){//把地图信息转为一维字符串
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < rows; ++i){
            for(int j = 0; j < cols; ++j){
                res.append(g[i][j]);
            }
        }
        return res.toString();
    }

    private void saveToDatabase() {//保存对局记录到数据库
        Record record = new Record(
                null,//id数据库默认会填
                playerA.getId(),
                playerA.getSx(),
                playerA.getSy(),
                playerB.getId(),
                playerB.getSx(),
                playerB.getSy(),
                playerA.getStepsString(),
                playerB.getStepsString(),
                getMapString(),
                loser,
                new Date()
        );
        WebSocketServer.recordMapper.insert(record);
    }

    private void sendResult(){//向两个Client公布结果
        JSONObject resp = new JSONObject();
        resp.put("event", "result");
        resp.put("loser", loser);
        saveToDatabase();
        sendAlLMessage(resp.toJSONString());
        System.out.println("loser is " + loser);
    }
    @Override
    public void run() {
        //蛇前10步每移1格增加1个身体长度，后面每3格增加1个身体长度，地图 13x14=182格，蛇最长长度为182
        //182x3 = 546，最多600次就可以结束
        for(int i = 0; i < 1000; ++i){
            if(nextStep()){//如果两条蛇的操作都获取到了
                judge();//判断两名玩家下一步操作是否合法
                if("playing".equals(status)){
                    sendMove();//向两个 client 同步移动信息（然后继续下一回合）
                }else{
                    sendResult();//向两个 client 公布对战结果
                    break;//游戏结束了
                }
            }else{//如果任意一条蛇的操作没获取到
                status = "finished";
                lock.lock();
                try{
                    if(nextStepA == null && nextStepB == null){//平局
                        loser = "all";
                    }else if (nextStepA == null){
                        loser = "A";
                    }else if(nextStepB == null){
                        loser = "B";
                    }else{//如果在if判断之后，加锁之前，a&b都刚好发出了结束信息，增加了判断边界
                        loser = "all";
                    }
                } finally{
                    lock.unlock();
                }
                sendResult();
                break;
            }
        }
    }
}
