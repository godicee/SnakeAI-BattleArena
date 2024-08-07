package com.kob.backend.Consumer.Utils;

import java.util.Random;

public class Game {
    final private Integer rows;
    final private Integer cols;
    final private Integer inner_walls_count;
    final private int[][] g;
    final private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};

    public Game(Integer rows, Integer cols, Integer inner_walls_count){
        this.rows = rows;
        this.cols = cols;
        this.inner_walls_count = inner_walls_count;
        this.g = new int[rows][cols];//
    }

    public int[][] getG(){//返回棋盘
        return g;
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
}
