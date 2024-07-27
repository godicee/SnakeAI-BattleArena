<template>
    <ContentField>
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form @submit.prevent="register"><!--提交的触发函数，在 css 中定义-->
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="mb-3">
                        <label for="confirmedPassword" class="form-label">确认密码</label>
                        <input v-model="confirmedPassword" type="password" class="form-control" id="confirmedPassword" placeholder="请再次输入密码">
                    </div>
                    <div class="error-message"> {{ error_message }}</div>
                    <button type="submit" class="btn btn-info">注册</button>
                </form>
            </div><!--中间三格-->
        
        </div>
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import { ref } from 'vue'
import router from '@/router/index.js'
import $ from 'jquery'

export default{
    components:{
        ContentField
    },
    setup(){
        let username = ref("");
        let password = ref("");
        let confirmedPassword = ref("");
        let error_message = ref("");

        const register = () =>{//html中 register的触发函数
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/register/",
                type:"post",
                data: {
                    username: username.value,//username.value是页面绑定到 ref 的username 动态变化的，第一个 username 是传给后端的数据
                    password: password.value,
                    confirmedPassword: confirmedPassword.value,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        router.push({name: 'user_account_login'});
                    }else{
                        //这里从后端取回错误信息，绑定到 ref 变量 error_message中
                        //且在下面 return，即可导入绑定到 html 中
                        error_message.value = resp.error_message;//后端返回的信息存在 resp 中
                    }
                },
            });
        }


        return{//返回，方便绑定到 html
            username,
            password,
            confirmedPassword,
            error_message,
            register,
        }
    }
}   
</script>

<style scoped>
button{
    width: 100%;
}
div.error-message{
    color: red;
    display: flex;
    justify-content: center; /* 水平居中 */
    align-items: center;     /* 垂直居中 */
    height: 1.5rem; 
}
</style>