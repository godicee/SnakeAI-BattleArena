<template>
    <div>
        <PlayGround> </PlayGround>
    </div>
    
</template>

<script>
import PlayGround from "@/components/PlayGround.vue"//对战区域的组件，playground 又引入了gameMap

export default{
    components:{
        PlayGround,
    },

}   
</script>




<style scoped>

</style>