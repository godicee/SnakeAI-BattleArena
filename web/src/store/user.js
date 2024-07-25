import $ from 'jquery'

export default{
    state: {//存用户全局信息
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
    },
    getters: {//一般用不到
    },
    mutations: {//修改数据，所以第一个参数默认是 state，后面传入的 payload 默认是第二个参数
        updateUser(state, user){
            state.id = user.id;
            state.username = user.username;
            state.photo = user.photo;
            state.is_login = user.is_login;
        },
        updateToken(state, token){
            state.token = token;
        },
        logout(state){//token存在浏览器本地，jwt验证，服务器没有存，直接本地删除即可退出登录
            state.id = "";
            state.username = "";
            state.photo = "";
            state.token = "";
            state.is_login = false;
        }
    },
    actions: {//修改 state 的函数
        login(context, data){//这两个参数中 vuex 中的自带的参数
            //context可以提交 mutation、dispatch触发另一个 action 等
            //data 是 action 被调用时传进来的数据
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/token/",
                type: "post",
                data:{
                  username: data.username,
                  password: data.password,
                },
                success(resp){
                    if(resp.error_message === 'success'){//error_message和token都是在后端自定义的
                        context.commit("updateToken", resp.token);//action中调用 mutations 中的函数，需要加 commit
                        data.success(resp);//可以使用回调：更新 ui、重定向页面、存储 token 等
                    }else{
                        data.error(resp);//这里的回调可以返回错误信息、帮助调试问题等等
                    }
                },
                error(resp){
                    data.error(resp);
                }
            });          
        },
        //通过 token 获取信息后,进行用户信息的更新
        getinfo(context, data){
            $.ajax({
                url: "http://127.0.0.1:3002/user/account/info/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + context.state.token,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        context.commit("updateUser",{
                            ...resp,//将 resp 的键值对信息放到当前的对象里（id、username、photo、token、isLogin）
                            is_login: true,//修改isLogin拼接后的信息更新到 store 中存储的信用信息
                        });
                        data.success(resp);
                    }else{
                        data.error(resp);
                    }
                },
                error(resp){
                    data.error(resp);
                }
            })
        },
        logout(context){
            context.commit("logout");
        }
    },
    modules: {
    }
}