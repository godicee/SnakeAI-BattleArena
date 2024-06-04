import { AcGameObject } from "./AcGameObject";//导入基础刷新类

//这里地图类也需要导出export，因为后面会调用
export class GameMap extends AcGameObject{
    constructor(ctx, parent){//构造函数，ctx 是画布，parent是画布的父元素——用来动态修改画布长宽
        super();//执行基类的构造函数
        
        this.ctx = ctx;
        this.parent = parent;
        this.L = 0;//存储一个单位的格子的绝对距离的长度，地图为 13x13 个格子
        
        this.cows = 13;//对战区域正方形块行数
        this.cols = 13;//列数
    }

    start(){//最开始第一帧执行

    }

    update_size(){//每帧更新地图的边长（每个小正方形块的边长）
        this.L = Math.min(this.parent.clientWidth / this.cols, this.parent.clientHeight / this.cows)
        this.ctx.canvas.width = this.L * this.cols;//对战地图（画布）的宽度
        this.ctx.canvas.height = this.L * this.cows;//对战地图（画布）的高度
    }
    update(){//每帧更新
        this.update_size();
        this.render();
    }

    render(){//渲染，把update的更新画到画布上
        //画地图（格子是绿色的，深浅相间的）
        const color_even = "#AAD751";
        const color_odd = "#A2D149";
        for(let r = 0; r < this.cows; r++){
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