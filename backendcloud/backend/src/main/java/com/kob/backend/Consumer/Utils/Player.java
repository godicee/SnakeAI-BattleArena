package com.kob.backend.Consumer.Utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Player {//记录每个玩家的位置信息
    private Integer id;
    private Integer botId;  // -1表示人工操作，否则表示出战的 ai id
    private String botCode;
    private Integer sx;
    private Integer sy;
    private List<Integer> steps;//记录玩家的移动序列（每回合走动的方向）


    private boolean check_tail_increasing(int steps){
        if(steps <= 10) return true;
        return steps % 3 == 1;
    }

    public List<Cell> getCells(){ //获取蛇当前身体的位置
        List<Cell> res = new ArrayList<>();
        //正常坐标int dx[] = {0, 1, 0, -1}, dy[] = {-1, 0, 1, 0};
        int dr[] = {-1, 0, 1, 0}, dc[] = {0, 1, 0, -1};//行列左边：上右下左
        int x = sx, y = sy;
        int step = 0;
        res.add(new Cell(x, y));
        for(int d: steps){
            x += dr[d];
            y += dc[d];
            res.add(new Cell(x, y));
            if(!check_tail_increasing(++ step)) //如果不是蛇的长度增加，那么蛇尾的位置就删除
                res.remove(0);
        }
        return res;
    }

    public String getStepsString(){//把list的 steps转为字符串
        StringBuilder res = new StringBuilder();
        for(int d: steps){
            res.append(d);
        }
        return res.toString();
    }
}
