package com.kob.botrunningsystem.utils;


import java.util.ArrayList;
import java.util.List;

// 这里用做编写 Bot的 ai 测试代码，不影响程序，编写后写入 bot 即可
public class Bot implements com.kob.botrunningsystem.utils.BotInterface{

    static class Cell{  // 蛇头坐标
        public int x, y;
        public Cell(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    private boolean check_tail_increasing(int steps){
        if(steps <= 10) return true;
        return steps % 3 == 1;
    }

    public List<Cell> getCells(int sx, int sy, String steps){ //获取蛇当前身体的位置，传入的steps是操作序列
        steps = steps.substring(1, steps.length() -1);  // 去掉编码中：玩家操作的的左右括号
        List<Cell> res = new ArrayList<>();
        //正常坐标int dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};//行列左边：上右下左
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for(int i = 0; i < steps.length(); ++i){
            int d = steps.charAt(i) - '0';
            x += dr[d];
            y += dc[d];
            res.add(new Cell(x, y));
            if(!check_tail_increasing(++ step)) //如果不是蛇的长度增加，那么蛇尾的位置就删除
                res.remove(0);
        }
        return res;
    }


    @Override
    public Integer nextMove(String input) {  // input是当前的地图信息（障碍物和蛇位置）
        String[] strs = input.split("#");  // 解码出来(地图、mesx,mesy,me操作,opsx,opsy,op操作)
        int[][] g = new int[13][14];
        for(int i = 0, k = 0; i < 13; ++i){  // 取出地图
            for(int j = 0; j < 14; ++j, ++k){
                if(strs[0].charAt(k) == '1'){
                    g[i][j] = 1;
                }
            }
        }

        // 计算身体位置
        int aSx = Integer.parseInt(strs[1]), aSy = Integer.parseInt(strs[2]);
        int bSx = Integer.parseInt(strs[4]), bSy = Integer.parseInt(strs[5]);

        List<Cell> aCells = getCells(aSx, aSy, strs[3]);
        List<Cell> bCells = getCells(bSx, bSy, strs[6]);

        // 把两条蛇身体也添加到障碍物中
        for(Cell c: aCells) g[c.x][c.y] = 1;
        for(Cell c: bCells) g[c.x][c.y] = 1;

        // 蛇下一步操作搜索
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};  // 行列
        int aim_position[] = {0, 0, 0, 0};  // 记录下一步可走的位置，这个位置的下一步可以走的位置的数量(搜索两个位置)
        for(int i = 0; i < 4; ++i){
            int x = aCells.get(aCells.size() - 1).x + dr[i];  // 蛇头的下一步可能的 x 坐标
            int y = aCells.get(aCells.size() - 1).y + dc[i];
            // 判断下一步是否合法
            if(x >= 0 && x < 13 && y >= 0 && y < 14 && g[x][y] == 0){
                g[x][y] = 1;
                for(int j = 0; j < 4; ++j){
                    int xx = x + dr[j];  // 下下步可能的位置
                    int yy = y + dc[j];
                    if(xx >= 0 && xx < 13 && yy >= 0 && yy < 14 && g[xx][yy] == 0){
                        aim_position[i]++;
                    }
                }
                g[x][y] = 0;
            } else{
                aim_position[i] = -1;
            }
        }
        int max = 0;  // 可能位置的最大值
        int pos = 0;  // 最大值的位置
        for(int i = 0; i < 4; ++i){
            if(aim_position[i] > max){
                max = aim_position[i];
                pos = i;
            }
        }
        if(aim_position[pos] != -1)
            return pos;
        for(int i = 0; i < 4; ++i){
            if(aim_position[i] != -1)
                return i;
        }
        return 0;
    }
}
