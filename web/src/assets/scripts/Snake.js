import { AcGameObject } from "./AcGameObject";
import { Cell } from "./Cell";//坐标的处理

export class Snake extends AcGameObject{
    constructor(info, gamemap){
        super();

        this.id = info.id;//记录蛇的标号，用于区分是哪条蛇
        this.color = info.color;//蛇的颜色
        this.gamemap = gamemap;
        
        this.cells = [new Cell(info.r, info.c)];//cells存蛇身体，cells[0]存放蛇头
        this.next_cell = null;//蛇下一步移动的目标位置
  
        this.speed = 5;//蛇每秒走 5 个格子
        //回合制游戏，蛇只有双方都输入指令以后才会移动（输入可以是人可以是代码）
        this.direction = -1;//方向：-1没有指令,0、1、2、3分别代表上、右、下、左
        this.status = "idle";//idle表示停止，move 表示正在移动，die表示死亡
        //蛇的状态判断逻辑放到了gamemap中的 check_ready()函数中

        //偏移量：上右下左——对应direction[0,1,2,3]
        this.dr = [-1, 0, 1, 0];//行
        this.dc = [0, 1, 0, -1];//列
        
        this.step = 0;//记录回合数：前1——10 每回合蛇身变长 1，之后每 3 回合蛇身变长 1
        this.eps = 1e-2;//允许的坐标误差，当两个点的坐标误差相差 0.01，就认为它们已经重合
    }

    start(){

    }

    set_direction(d){//设置方向(获取本类中方向的函数)
        this.direction = d;
    }
    
    check_tail_increasing(){//检测当前回合是否需要变长  
        if(this.step <= 10) return true;
        if(this.step % 3 === 1) return true;
        return false;
    }
    
    next_step(){//蛇的状态变为走下一步：上右下左(-1,0)(0,1)(1,0)(0,-1)
        const d = this.direction;
        this.next_cell = new Cell(this.cells[0].r + this.dr[d], this.cells[0].c + this.dc[d]);//Cell 是存坐标信息的类
        this.direction = -1;//清空方向操作
        this.status = "move";//调整蛇的状态为移动中
        this.step ++ ;

        const k = this.cells.length;
        for(let i = k; i > 0; i--){//在头部抛出新球，每个小求向后移动一位
            this.cells[i] = JSON.parse(JSON.stringify(this.cells[i - 1]));
        }

        if(!this.gamemap.check_valid(this.next_cell)){//下一步蛇头的撞墙/身体的非法检测
            this.status = "die";
        }
    }
    
    update_move(){//移动         
        const dx = this.next_cell.x - this.cells[0].x;//x方向的偏移量
        const dy = this.next_cell.y - this.cells[0].y;//y方向的偏移量
        const distance = Math.sqrt(dx * dx + dy * dy);

        if(distance < this.eps){//如果误差范围内到了目标位置，则停下来
            this.cells[0] = this.next_cell;//头结点抛出的圆作为新的头
            this.next_cell = null;
            this.status = "idle";//调整蛇的状态为停止

            //蛇尾到达目标点后蛇尾逻辑
            if(!this.check_tail_increasing())//蛇尾不变长，则砍掉蛇尾
                this.cells.pop();
            
        }else{//如果还没到，继续移动
            const move_distance = this.speed * this.timedelta / 1000;//每帧移动的距离：timedelta是两帧之间的时间间隔，单位是毫秒，要转换成秒，所以要除以 1000
            this.cells[0].x += move_distance * dx / distance;//余弦计算
            this.cells[0].y += move_distance * dy / distance;//正弦计算

            //尾部的移动
            if(!this.check_tail_increasing()){//蛇尾不变长：蛇尾需要向前移动（移动完成后需要删除尾部）
                const k = this.cells.length;
                const tail = this.cells[k - 1], tail_target = this.cells[k - 2];//尾部和头部的距离
                const tail_dx = tail_target.x - tail.x;
                const tail_dy = tail_target.y - tail.y;
                tail.x += move_distance * (tail_dx / distance);
                tail.y += move_distance * (tail_dy / distance);//每帧x方向移动的距离
            }//如果蛇尾需要边长的话：就不需要移动蛇尾（不需要额外逻辑）
        }
    }

    update(){//每帧执行一次 
        if(this.status === 'move'){
            this.update_move(); 
        } 
        this.render(); 
        
    }

    render(){
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx;
        
        ctx.fillStyle = this.color; 
        if(this.status === "die"){
            ctx.fillStyle = "white";
        }
        for(const cell of this.cells){
            ctx.beginPath();//开启一个路径
            ctx.arc(cell.x * L, cell.y * L, L / 2 * 0.8, 0, Math.PI * 2)//前两个坐标是小圆的中点，后一个坐标是半径，最后两个是圆弧的起始和终止角度 
            ctx.fill();
        }
        for(let i = 1; i < this.cells.length; ++i){//给蛇身画线
            const a = this.cells[i], b = this.cells[i - 1];
            if(Math.abs(a.x - b.x) < this.eps && Math.abs(a.y - b.y) < this.eps){//两个圆很接近则不画线（适用于尾部移动的边界判断）
                continue;
            }
            //两种情况进行画线（横向和纵向的坐标计算）
            if(Math.abs(a.x - b.x) < this.eps){//纵向排列
                ctx.fillRect((a.x - 0.4) * L, Math.min(a.y, b.y) * L, L * 0.8, Math.abs(a.y - b.y) * L);//(左上角横坐标，左上角纵坐标，宽度，长度)
            }else{//横向排列
                ctx.fillRect(Math.min(a.x, b.x) * L, (a.y - 0.4) * L, Math.abs(a.x - b.x) * L, L * 0.8);
            }
        }
    }
}