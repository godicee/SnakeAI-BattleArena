export class Cell{//坐标转换的处理
    constructor(r, c){
        this.r = r;
        this.c = c;
        this.x = c + 0.5;
        this.y = r + 0.5;
    }
}