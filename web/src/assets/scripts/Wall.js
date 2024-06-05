//放置障碍物
import { AcGameObject } from "./AcGameObject";

export class Wall extends AcGameObject{
    constructor(r, c ,gamemap){
        super();
        
        this.r = r;//行——y
        this.c = c;//列——x
        this.gamemap = gamemap;
        this.color = "#B37226";
    }

    update(){
        this.render();
    }

    render(){
        const L = this.gamemap.L;
        const ctx = this.gamemap.ctx; 

        ctx.fillStyle = this.color;
        ctx.fillRect(this.c * L, this.r * L, L, L);//画位于(x,y)的的一个墙的方块
    }

}