import $ from 'jquery'

export default{
    state: {//存用户全局信息
        id: "",
        username: "",
        photo: "",
        token: "",
        is_login: false,
        pulling_info: "true",//当前是否在获取 token 信息——用于控制获取信息中的页面跳转问题（login）
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
        },
        updatePullingInfo(state, pulling_info){
            state.pulling_info = pulling_info;
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
                        //存储token到本地  
                        localStorage.setItem("jwt_token", resp.token);
                        
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
                        context.commit("updateUser",{//这里其实是一个mutations 的函数调用，{包含的是一个字典信息}
                            ...resp,//将后端取回的信息放到当前的对象里
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
            localStorage.removeItem("jwt_token");//删除浏览器本地的 token
            context.commit("logout");
        }
    },
    modules: {
    }
}