<template>
    <ContentField>
        <div class="row justify-content-center"><!--居中-->
            <div class="col-3">
                <form @submit.prevent="login">
                    <div class="mb-3">
                        <label for="username" class="form-label">用户名</label>
                        <input v-model="username" type="text" class="form-control" id="username" placeholder="请输入用户名">
                    </div>
                    <div class="mb-3">
                        <label for="password" class="form-label">密码</label>
                        <input v-model="password" type="password" class="form-control" id="password" placeholder="请输入密码">
                    </div>
                    <div class="error-message"> {{ error_message }}</div>
                    <button type="submit" class="btn btn-info">登录</button>
                </form>
            </div><!--中间三格-->
        </div>
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContenField.vue"
import {useStore} from 'vuex'//useStore函数返回一个store实例，可以在组件的setup()函数中使用它来访问store的状态、dispatch actions或commit mutations。
import {ref} from 'vue'//可以处理和标签中的变量的更新
import router from "@/router/index.js";
export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let username = ref("");
        let password = ref("");
        let error_message = ref("");

        //登录并跳转
        const login = () =>{//触发登录函数
            error_message.value = "";//每次清空一下
            store.dispatch("login",{
                username: username.value,//页面传到 ref，传到这里，这里调用了user.js中的登录函数
                password: password.value,//登录函数的data由这里传入
                success(){
                    store.dispatch("getinfo",{//获取信息的回调函数，实现在user.js中
                        success(){
                            router.push({name: "home"});//跳转到主页面
                            //console.log(resp);//后端定义返回的信息
                            console.log(store.state.user);//state中的所有信息
                            
                            //router.push ("/");
                        }
                    })
                },
                error(){
                    error_message.value = "用户名或密码错误";
                } 
            })
        }
        return{
            username,
            password,
            error_message,
            login,
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
}
</style>