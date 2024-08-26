<template>
    <div class="result-board">

        <div class="result-board-text" v-if="$store.state.pk.loser === 'all'">
            Draw
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.pk.a_id === parseInt($store.state.user.id)">
            Lose
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'A' && $store.state.pk.a_id !== parseInt($store.state.user.id)">
            Win
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.pk.b_id === parseInt($store.state.user.id)">
            Lose
        </div>
        <div class="result-board-text" v-else-if="$store.state.pk.loser === 'B' && $store.state.pk.b_id !== parseInt($store.state.user.id)">
            Win
        </div>
        <!-- <div class="result-board-text" v-else>
            Draw
        </div> -->
        

        <div class="result-board-btn" @click="restart" style="text-align: center; padding-top: 25%;">
            <button type="button" class="btn btn-outline-light btn-lg rounded-pill" style="background-color: orange;">
            <i class="fas fa-sync-alt">再来!</i> 
            </button>
        </div>
    </div>
</template>




<script>
import { useStore } from 'vuex';

export default{
    setup(){
    const store = useStore();

    const restart = () =>{
        store.commit("updateLoser", "none");
        store.commit("updateStatus", "matching");
        store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
    }


    return{
        restart,
    };
}

}




</script>


<style scoped>
div.result-board{
    width: 30vh;
    height: 30vh;
    background-color: rgba(199, 189, 189, 0.5);
    position: absolute;
    left: 42%;
    top: 29%;
    border-radius: 35%;
}

div.result-board-text{
    text-align: center;
    color:white; 
    font-size: 30px; 
    font-weight: 800; 
    font-style: italic; 
    padding-top: 15%;
}


</style>