<template>
    <div class="playground">
       <GameMap></GameMap>
    </div>
    <div class="button-refresh d-flex justify-content-center align-items-center">
      <button type="button" class="btn btn-outline-light btn-lg rounded-pill" @click="refreshPage" style="background-color: aqua;">
        <i class="fas fa-sync-alt"></i> 刷新
      </button>
    </div>
</template>

<script>
import GameMap from "../components/GameMap.vue";

export default {
    components: {
        GameMap
    },
    methods: {
        refreshPage() {
            // 刷新页面
            location.reload();
        }
    }
};
</script>

<style scoped>
div.playground{
    width:50vw;
    height: 70vh;
    /* background:beige; */
    margin: 40px auto;
}
</style>