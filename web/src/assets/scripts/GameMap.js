//用于地图绘制——绿色、墙
import { AcGameObject } from "./AcGameObject";//导入基础刷新类
import { Wall } from "./Wall";
//这里地图类也需要导出export，因为后面会调用

export class GameMap extends AcGameObject{
    constructor(ctx, parent){//构造函数，ctx 是画布，parent是画布的父元素——用来动态修改画布长宽
        super();//执行基类的构造函数
        
        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;//存储一个单位的格子的绝对距离的长度，地图为 13x13 个格子
        
        this.rows = 13;//对战区域正方形块行数
        this.cols = 13;//列数
        
        this.inner_walls_count = 20;//内部障碍物数量
        this.walls = [];//用于存储墙的数组
    }

    check_connectivity(g, sx, sy, tx, ty){//判断生成的地图是否连通
        if(sx == tx && sy == sy) return true;
        g[sx][sy] = true;
        //偏移数组，x 是行，y 是列（上右下左）
        let dx =[-1, 0, 1, 0], dy =[0, 1, 0, -1];
        for(let i = 0;i < 4; ++i){//dfs
            let x = sx + dx[i], y = sy + dy[i];
            if(!g[x][y] && this.check_connectivity(g, x, y ,tx, ty))//如果没有撞墙&&可以搜到终点
                return true;
        }

        return false;
    }

    create_walls(){
        const g = [];//布尔数组，记录哪个格子有墙
        //数组初始化全为false
        for(let r = 0; r < this.rows; ++r){
            g[r] = []; 
            for(let c = 0; c < this.cols; ++c){
                g[r][c] = false;
            }
        }
 
        //给四周加上障碍物  
        for(let r = 0; r < this.rows; ++r){
            g[r][0] = g[r][this.cols - 1] = true;
        }
        for(let c = 0; c < this.cols; ++c){
            g[0][c] = g[this.rows - 1][c] = true;
        }
        //创建随机障碍物
        for(let i = 0; i < this.inner_walls_count / 2 ; ++i){
            for(let j = 0; j < 1000; ++j){//防止随机到已放过障碍物的色块
                let r = parseInt(Math.random() * this.rows);
                let c = parseInt(Math.random() * this.rows);
                if(g[r][c] || g[c][r]) continue;
                if(r == this.rows - 2 && c == 1 || r == 1 && c == this.cols - 2)//蛇出生的左上，右下，不能有障碍物
                    continue;
                
                g[r][c] = g[c][r] = true;
                break;
            }
        }
        //深度复制一份对象 g，防止 g 的状态被修改
        const copy_g = JSON.parse(JSON.stringify(g));//深拷贝：二维数组 g 先转为 json 格式字符串，再转回对象
        //如果直接copy_g = g，g两个变量可能会指向同一个引用，导致修改一个，另一个也被修改
        if(!this.check_connectivity(copy_g , this.rows - 2, 1, 1, this.cols - 2))//传入数组&起点&终点坐标
            return false;//不连通则返回 false

        //画墙
        for(let r = 0; r < this.rows; ++r){
            for(let c = 0; c < this.cols; ++c){
                if(g[r][c]){
                    this.walls.push(new Wall(r, c, this));
                }
            }
        }

        return true;//连通的话，返回 true
    }


    start(){//最开始第一帧执行
        for(let i = 0; i < 1000; ++i){//地图连通性的判断逻辑
            if(this.create_walls()){//创建墙（墙类中自己会 update（刷新） 和 render（渲染） 墙）
                break;
            }
        }
        
    }

    update_size(){//每帧更新地图的边长（每个小正方形块的边长）
        //这里parseInt是为了把像素值设为整数，避免边缝
        this.L = parseInt(Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.rows))
        this.ctx.canvas.width = this.L * this.cols;//对战地图（画布）的宽度
        this.ctx.canvas.height = this.L * this.rows;//对战地图（画布）的高度
    }
    update(){//每帧更新
        this.update_size();
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