export default{
    state:{
        status: "matching",//matching匹配界面，playing对战界面
        socket: null,//建立的websocket链接
        opponent_username: "",//对手的名字
        opponent_photo: "",//对手的头像
        gamemap: null,//后端生成的对战地图
        //匹配成功的对战玩家信息
        a_id: 0,
        a_sx: 0,
        a_sy: 0,
        b_id: 0,
        b_sx: 0,
        b_sy: 0,
        gameObject: null,
        loser: "none",//all,A,B
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
        updateGame(state, game){
            state.gamemap = game.map;
            state.a_id = game.a_id;
            state.a_sx = game.a_sx;
            state.a_sy = game.a_sy;
            state.b_id = game.b_id;
            state.b_sx = game.b_sx;
            state.b_sy = game.b_sy;
        },
        updateGameObject(state, gameObject){
            state.gameObject = gameObject;
        },

        updateLoser(state, loser){
            state.loser = loser;
        }
    },
    actions:{

    },
    modules:{

    }
}