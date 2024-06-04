<template>
    <div class="playground"> 
        <GameMap></GameMap>
    </div>
</template>



<script>//被/view/pk/PkIndexView.vue调用 
import GameMap from './GameMap.vue';//在游戏界面中导入地图组件 

export default{
    components: {
        GameMap,
    } 
}
</script>




<style scoped>
div.playground{
    width: 60vw;/*百分之六十的浏览器宽度*/
    height: 70vh;
    background: lightblue; 
    margin: 40px  auto ;
}

</style>