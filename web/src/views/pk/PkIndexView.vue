<template>
    <div>
        <PlayGround v-if="$store.state.pk.status === 'playing'"> </PlayGround>
        <MatchGround v-if="$store.state.pk.status === 'matching'"></MatchGround>
    </div>
    
</template>

<script>
import PlayGround from "@/components/PlayGround.vue"//对战区域的组件，playground 又引入了gameMap
import MatchGround from "@/components/MatchGround.vue"//匹配区组件
import { onMounted, onUnmounted } from "vue";//组件挂载之后执行的函数、组件被卸载执行的函数
import { useStore } from 'vuex'


export default{
    components:{
        PlayGround,
        MatchGround,
    },
    setup(){
        const store = useStore();
        const socketUrl = `ws://127.0.0.1:3002/websocket/${store.state.user.token}/`;//字符串中有${}表达式操作的话，需要用`，不能用引号

        let socket = null;

        onMounted(() => {//页面成功挂载

            store.commit("updateOpponent", {
                username: "我的对手",
                photo: "https://cdn.acwing.com/media/article/image/2022/08/09/1_1db2488f17-anonymous.png",
            })
            //*begin:这段代码写在 setup中也可，不需要一定写在onMounted中
            socket = new WebSocket(socketUrl);
            store.commit("updateSocket", socket);//建立的ws链接更新到全局变量

            socket.onopen = () => {
                console.log("connected!");
            },
            socket.onmessage = msg => {
                const data = JSON.parse(msg.data);
                if(data.event === "match"){//匹配成功
                    store.commit("updateOpponent",{
                        username: data.opponent_username,
                        photo: data.opponent_photo,
                    }),
                    setTimeout(() => {//匹配成功后2s后再跳转页面
                        store.commit("updateStatus", "playing")
                    },2000)
                    store.commit("updateGamemap", data.gamemap)
                }
            },
            socket.onclose = () => {
                console.log("disconnected");
                store.commit("updateStatus", "matching");
            }
            //* end
        });
        onUnmounted(() =>{
            socket.close();
        })
    }    




}   
</script>




<style scoped>

</style>