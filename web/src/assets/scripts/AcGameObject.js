const AC_GAME_OBJECTS = [];//定义一个对象数组

export class AcGameObject {//定义基类
    constructor(){//构造函数，把基类对象添加到数组中
        AC_GAME_OBJECTS.push(this);
        this.timedelta = 0;//两帧之间的时间间隔，用于速度的计算
        this.has_called_start = false;//变量记录：是否执行过start函数
    }
    

    start(){//只在初始化时执行一次的函数

    }

    update(){//每帧执行一次，除了第一帧

    }

    on_destroy(){//在删除前执行一次

    }
    destroy(){//从数组中删除本对象
        this.on_destroy();//销毁前执行的一些操作
        
        for(let i in AC_GAME_OBJECTS){//用 in 遍历的是下标，of 遍历的值
            const obj = AC_GAME_OBJECTS[i];
            if(obj === this){
                AC_GAME_OBJECTS.splice(i);
                break; 
            }
        }
    }
}

let last_timestamp;//上一次执行的时间
const step = timestamp => {//传入当前帧的时间
    for(let obj of AC_GAME_OBJECTS){//of 是遍历值
        if(!obj.has_called_start){//如果没执行过初始化，则初始化（判断这是不是第一帧）
            obj.has_called_start = true;
            obj.start();//执行初始化
        }else{//如果执行过初始化，则执行 update
            obj.timedelta = timestamp - last_timestamp;//计算时间间隔
            obj.update();//执行更新函数
        }
    }
    last_timestamp = timestamp;//更新上一帧的时间戳
    requestAnimationFrame(step);//递归调用 step——》每帧刷新都会执行 step
}


requestAnimationFrame(step)//浏览器的下一次刷新，调用 step 这个函数