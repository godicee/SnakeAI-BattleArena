export default{
    state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_username: "",//对手的名字
        opponent_photo: "",//对手的头像
        gamemap: "",//后端生成的对战地图
    },
    getters:{

    },
    mutations:{
        //前端成功创建链接后，需要把信息更新到state中
        updateSocket(state, socket){
            state.socket = socket;
        },
        updateOpponent(state, opponent){
            state.opponent_username = opponent.username;
            state.opponent_photo = opponent.photo;
        },
        updateStatus(state, status){
            state.status = status;
        },
        updateGamemap(state, gamemap){
            state.gamemap = gamemap;
        }

    },
    actions:{

    },
    modules:{

    }
}