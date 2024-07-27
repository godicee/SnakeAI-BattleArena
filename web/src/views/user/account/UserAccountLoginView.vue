<template>
    <ContentField v-if="!$store.state.user.pulling_info"><!--控制页面暂时不要展示-->
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
        //解决：登录页面不要展示——解决刷新时 内存 token 不见
        //会重新跳转回登录页面获取本地token的问题
        //因而会有一个一闪而过的登录页面的问题
        //let show_content = ref(false);

        const jwt_token = localStorage.getItem("jwt_token");
        if(jwt_token){
            store.commit("updateToken", jwt_token);//用 mutations 中的函数把 localStorage 取出的 token 更新到 state 中
            //验证 token 是否合法，actions 中的 getinfo 函数（在云端验证）
            store.dispatch("getinfo",{//调用两个回调函数
                success(){
                    router.push({name: "home"});//成功跳转到首页
                },
                error(){//验证失败：非法/token 过期，则可以展示登录页面
                    store.commit("updatePullingInfo", false);
                    //show_content.value = "true";
                }
            }) 
        }else{//如果本地没有 jwt_token也需要展示页面
            store.commit("updatePullingInfo", false);//拉取 token 结束，应该是 false
            console.log(store.state.user.pulling_info);
            //show_content.value = "true";
        }

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
                            //console.log(store.state.user);//state中的所有信息

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
            //show_content,
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