//用于地图绘制——绿色、墙
import { AcGameObject } from "./AcGameObject";//导入基础刷新类
import { Wall } from "./Wall";
import { Snake } from "./Snake";
//这里地图类也需要导出export，因为后面会调用

export class GameMap extends AcGameObject{
    constructor(ctx, parent, store){//构造函数，ctx 是画布，parent是画布的父元素——用来动态修改画布长宽
        super();//执行基类的构造函数
        
        this.ctx = ctx;
        this.parent = parent;
        this.store = store;
        this.L = 0;//存储一个单位的格子的绝对距离的长度，地图为 13x13 个格子
        
        this.rows = 13;//对战区域正方形块行数
        this.cols = 14;//列数
        
        this.inner_walls_count = 20;//内部障碍物数量
        this.walls = [];//用于存储墙的数组

        //创建两条蛇
        this.snakes =[
            new Snake({id: 0, color: "#4876EC", r: this.rows - 2, c: 1}, this),
            new Snake({id: 1, color: "#F94848", r: 1, c: this.cols - 2}, this),
        ];  
    }

    // check_connectivity(g, sx, sy, tx, ty){//判断生成的地图是否连通
    //     if(sx == tx && sy == sy) return true;
    //     g[sx][sy] = true;
    //     //偏移数组，x 是行，y 是列（上右下左）
    //     let dx =[-1, 0, 1, 0], dy =[0, 1, 0, -1];
    //     for(let i = 0;i < 4; ++i){//dfs
    //         let x = sx + dx[i], y = sy + dy[i];
    //         if(!g[x][y] && this.check_connectivity(g, x, y ,tx, ty))//如果没有撞墙&&可以搜到终点
    //             return true;
    //     }

    //     return false;
    // }

    create_walls(){
        const g = this.store.state.pk.gamemap;
        //画墙
        for(let r = 0; r < this.rows; ++r){
            for(let c = 0; c < this.cols; ++c){
                if(g[r][c]){
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }
    }

    add_listening_events(){//获取用户输入信息
        // 检测是录像还是直播
        if(this.store.state.record.is_record){
            let k = 0;
            const a_steps = this.store.state.record.a_steps;
            const b_steps = this.store.state.record.b_steps;
            const loser = this.store.state.record.record_loser;
            const [snake0, snake1] = this.snakes;
            const interval_id = setInterval(() => {  // 300ms走一次
                if(k >= a_steps.length - 1){  // 最后一步是死亡，作为终止条件，不用复现
                    if(loser === "all" || loser === 'A'){
                        snake0.status = "die";
                    }
                    if(loser === "all" || loser === 'B'){
                        snake1.status = "die";
                    }
                    // snake0.status = "die";
                    // console.log(snake1.status)
                    // console.log(snake0.status)
                    clearInterval(interval_id);  // 终止循环
                } else{
                    snake0.set_direction(parseInt(a_steps[k]));
                    snake1.set_direction(parseInt(b_steps[k]));
                }
                ++k;
            }, 300);
        } else{
            this.ctx.canvas.focus();//canvas 聚焦，以获取用户输入信息
            this.ctx.canvas.addEventListener("keydown", e => {//获取用户输入信息
                let d = -1;
                if(e.key === 'w' || e.key === "ArrowUp") d = 0;
                else if(e.key === 'd' || e.key === "ArrowRight") d = 1;
                else if(e.key === 's' || e.key === "ArrowDown") d = 2;
                else if(e.key === 'a' || e.key === "ArrowLeft") d = 3;
                
                if(d >= 0){
                    this.store.state.pk.socket.send(JSON.stringify({
                        event: "move",
                        direction: d,
                    }))
                }
            });
        }
        
        
    }
 
    start(){//最开始第一帧执行
        this.create_walls();
        this.add_listening_events();
    }

    update_size(){//每帧更新地图的边长（每个小正方形块的边长）
        //这里parseInt是为了把像素值设为整数，避免边缝
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows))
        this.ctx.canvas.width = this.L * this.cols;//对战地图（画布）的宽度
        this.ctx.canvas.height = this.L * this.rows;//对战地图（画布）的高度
    }
    //回合制游戏：两条蛇是否都准备好了下一步的动作
    check_ready(){//当每条蛇都处于静止（都完成了上一步的移动），且双方都有了下一步的输出，则开始移动
        for(const snake of this.snakes){//遍历两条蛇
            if(snake.status !== "idle") return false;//状态为停止
            if(snake.direction === -1) return false;//没有下一步移动的输入
        }
        return true;
    }

    next_step(){// 让两条蛇进入下一回合
        for(const snake of this.snakes){
            snake.next_step();//这个是调用Snack中的 next_step()函数
        }
    }

    check_valid(cell){//检测蛇头下一步移动位置是否合法：撞墙？撞身体？
        for(const wall of this.walls){//撞墙判断
            if(wall.r === cell.r && wall.c === cell.c){
                return false;
            }
        }

        for(const snake of this.snakes){
            let k = snake.cells.length;
            if(!snake.check_tail_increasing()){//蛇尾不会前进的情况，这个位置就可以走（蛇尾的判断）
                k--;
            }
            for(let i = 0; i < k; ++i){//枚举判断头是否撞到身体
                if(snake.cells[i].r === cell.r && snake.cells[i].c === cell.c){
                    return false;
                }
            }
        }

        return true;
    }
    update(){//每帧更新
        this.update_size();
        if(this.check_ready()){
            this.next_step();
        }
        this.render();
    }

    render(){//渲染，把update的更新画到画布上
        //画地图（格子是绿色的，深浅相间的）
        const color_even = "#AAD751";
        const color_odd = "#A2D149";
        for(let r = 0; r < this.rows; r++){
            for(let c = 0; c < this.cols; c++){
                if((r + c) % 2 == 0 ){
                    this.ctx.fillStyle = color_even;
                }else{  
                    this.ctx.fillStyle = color_odd;
                }
                this.ctx.fillRect(c * this.L, r * this.L, this.L, this.L);
            }
        }      
    }
}