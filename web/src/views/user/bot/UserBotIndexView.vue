<template>
    <ContentField>
        我的机器人
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import $ from 'jquery'
import {useStore} from 'vuex'
 
export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();

        $.ajax({
            url: "http://127.0.0.1:3002/user/bot/getlist/",
            type: "get",
            headers: {
                Authorization: "Bearer " + store.state.user.token,
            },
            success(resp){
                console.log(resp);
            },
            error(resp){
                console.log(resp);
            }
        })


        // $.ajax({
        //     url: "http://127.0.0.1:3002/user/bot/update/",
        //     type: "post",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token, 
        //     },
        //     data: {
        //         bot_id: 2,
        //         title: "22号用户的2号Bot",
        //         description: "2号Bot的描述",
        //         content: "2号 Bot 的代码",
        //     },
        //     success(resp){
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp);
        //     }
        // })


        // $.ajax({
        //     url: "http://127.0.0.1:3002/user/bot/add/",
        //     type: "post",
        //     data: {
        //         title : "Bot的标题",
        //         description : "Bot的描述",
        //         content : "Bot的内容",
        //     },
        //     headers:{
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     success(resp){
        //         console.log(resp);
        //     },
        //     error(resp){
        //         console.log(resp); 
        //     }
        // })

        // $.ajax({
        //     url: "http://127.0.0.1:3002/user/bot/remove/",
        //     type: "post",
        //     headers: {
        //         Authorization: "Bearer " + store.state.user.token,
        //     },
        //     data: {
        //         bot_id: 4,
        //     },
        //     success(resp){
        //         console.log(resp);
        //     },
        //     errror(resp){
        //         console.log(resp);
        //     }
        // })
    }
}   
</script>

<style scoped>

</style>