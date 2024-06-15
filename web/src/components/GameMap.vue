<template>
    <div ref="parent" class="gamemap">
        <canvas ref="canvas" tabindex="0"></canvas>
    </div>
</template>


<script>//被PlayGround调用
import { GameMap } from '@/assets/scripts/GameMap';//引入创建地图的脚本
import { ref, onMounted } from 'vue' //ref是定义变量操作，onMounted是挂载函数（挂载完成后需要执行的操作）

export default{
    setup(){
        let parent = ref(null);//画布父类
        let canvas = ref(null);//画布

        onMounted(() => {
            new GameMap(canvas.value.getContext("2d"), parent.value)     
        });

        return{
        parent,
        canvas
    }
    }

}

</script>




<style scoped>
div.gamemap{
    width: 100%;/* 和父元素等长 */
    height: 100%;
    display: flex;/* 弹性布局(居中) */
    justify-content: center;/* 水平居中 */
    align-items: center;/* 竖直居中 */
}
</style>