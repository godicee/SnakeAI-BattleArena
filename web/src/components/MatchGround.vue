<template>
    <div class="matchground">
        <div class="row">
            <div class="col-6">
                <div class="user-photo" style="text-align: center;">
                    <img :src="$store.state.user.photo" alt="">
                </div>

                <div class="user-username" >
                    {{ $store.state.user.username }}
                </div>
            </div>


            <div class="col-6">
                <div class="user-photo" style="text-align: center;">
                    <img :src="$store.state.pk.opponent_photo" alt="">
                </div>

                <div class="user-username" >
                    {{ $store.state.pk.opponent_username }}
                </div>
            </div>
            <div class="col-12" style="text-align: center; margin-top: 13%;">
                <button @click="click_match_btn_event" type="button" :class="'btn ' + match_btn_color + ' btn-lg ' + 'rounded-pill'" style=" width: 20% ;">
                <i class="fas fa-sync-alt">{{ match_btn_info }}</i> 
                </button> 
            </div>  


        </div>
    </div>
</template>

<script>
import {ref} from "vue"
import { useStore } from "vuex"

export default {
    setup(){
        const store = useStore();
        let match_btn_info = ref("开始匹配");
        let match_btn_color = ref("btn-outline-light");
        const click_match_btn_event = () =>{
            if(match_btn_info.value === "开始匹配"){
                match_btn_info.value = "取消匹配";
                match_btn_color.value = "btn-outline-danger";
                store.state.pk.socket.send(JSON.stringify({
                    event: "start-matching",
                }));
            }else{
                match_btn_info.value = "开始匹配";
                match_btn_color.value = "btn-outline-light";
                store.state.pk.socket.send(JSON.stringify({
                    event: "stop-matching",
                })); 
            }
        }
        return{
            click_match_btn_event,
            match_btn_info,
            match_btn_color,
        }
    },
    

};
</script>



<style scoped>
div.matchground{
    width:50vw;
    height: 70vh;
    margin: 40px auto;
    background-color: rgba(220, 202, 202, 0.5);
}

div.user-photo{
    text-align: center;
}

div.user-photo > img{
    border-radius: 50%;
    width: 20vh;
    margin-top: 20%;
}

div.user-username{
    margin-top: 5%;
    text-align: center;
    font-size: 24px;
    color: white;
    font-weight: 800;
    text-shadow: 2px 2px 4px rgba(177, 49, 130, 0.5);
}



</style>