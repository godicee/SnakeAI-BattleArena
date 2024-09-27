export default{
    state:{
        is_record: false,  //记录是否是录像（区分录像和实时对战）
        a_steps: "",
        b_steps: "",
        record_loser: "",
    },
    getters:{

    },
    mutations:{
        //前端成功创建链接后，需要把信息更新到state中
        updateIsRecord(state, is_record){
            state.is_record = is_record;
        },
        updateSteps(state, data){  //update 只能传一个参数（多个参数需要放到字典中）
            state.a_steps = data.a_steps;
            state.b_steps = data.b_steps;
        },
        updateRecordLoser(state, record_loser){
            state.record_loser = record_loser;
        }
    },
    actions:{

    },
    modules:{

    }
}