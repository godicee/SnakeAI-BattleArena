<template>
    <div class="container"><!--container会动态调整内部元素的大小-->
        <div class = "row">
            <div class="col-3">
                <div class="card photo-card">
                    <div class="card-header">
                        我的头像
                    </div>
                    <div class="card-body">
                        <img :src="$store.state.user.photo" alt="" style="width: 100%;"><!--如果 src 是表达式，不是一个字符串，在 src 前面需要加冒号-->
                    </div>
                </div>
            </div>

            <!--列表头部-->
            <div class="col-9">
                <div class="card bot-card">
                    <div class="card-header">
                        <span class="bot-name"> 我的Bot </span>
                        <button type="button" class="btn btn-secondary float-end" data-bs-toggle="modal" data-bs-target="#add-bot-btn">创建Bot</button><!--float-end是右对齐-->
                    </div>

                    <!-- Modal 创建-->
                    <div class="modal fade" id="add-bot-btn" tabindex="-1">
                        <div class="modal-dialog modal-xl">
                            <div class="modal-content">
                            <div class="modal-header">
                                <h1 class="modal-title fs-5">创建Bot</h1><!--标题-->
                                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                            </div>
                            <div class="modal-body">
                                
                                <div class="mb-3">
                                    <label for="add-bot-title" class="form-label">
                                    <span style="color: red;"> * </span>名称(title)
                                    </label>
                                    <input v-model="botadd.title" type="text" class="form-control" id="add-bot-tile" placeholder="请输入Bot名称">
                                </div>
                                <div class="mb-3">
                                    <label for="add-bot-description" class="form-label">简介(description)</label>
                                    <textarea v-model="botadd.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot的简介"></textarea>
                                </div>
                                <div class="mb-3">
                                    <label for="add-bot-code" class="form-label">
                                    <span style="color: red;"> * </span>代码(code)
                                    </label>
                                    <VAceEditor v-model:value="botadd.content" @init="editorInit" lang="c_cpp"
                                        theme="textmate" style="height: 300px" :options="{
                                            enableBasicAutocompletion: true, //启用基本自动完成
                                            enableSnippets: true, // 启用代码段
                                            enableLiveAutocompletion: true, // 启用实时自动完成
                                            fontSize: 18, //设置字号
                                            tabSize: 4, // 标签大小
                                            showPrintMargin: false, //去除编辑器里的竖线
                                            highlightActiveLine: true,
                                        }" />

                                </div>

                            <!--模态框中的两个按钮&告警信息-->
                            </div>
                            <div class="modal-footer">
                                <div class="badge bg-primary text-wrap" style="width: 10rem; color: chocolate; font-size: large; margin-right: 1%;">
                                {{ botadd.error_message  }}
                                </div>
                                <button type="button" class="btn btn-info" @click="add_bot">创建</button>
                                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                            </div>
                            </div>
                        </div>
                    </div>
                    <!--end Modal创建-->


                    <!--列表体-->
                    <div class="card-body">
                        <table class="table table-striped table-hover">
                            <thead><!--表头-->
                                <tr><!--每一行-->
                                    <th>名称</th><!--表头单元格-->
                                    <th>创建时间</th>
                                    <th>修改时间</th>
                                    <th class="float-end" style="margin-right: 23%;">操作</th>
                                </tr>
                            </thead>
                            <tbody><!--表体-->
                                <tr v-for="bot in bots" :key="bot.id"><!--循环写法：必须绑定一个唯一的元素-->
                                    <td> {{ bot.title }}</td><!--表格数据单元格-->
                                    <td> {{ bot.createtime }} </td>
                                    <td> {{ bot.modifytime }} </td>
                                    <td><!--修改、删除按钮-->
                                        <button type="button" class="btn btn-dark  float-end" style="color: rgb(178, 29, 66);" @click="remove_bot(bot)">删除</button>
                                        <button type="button" class="btn btn-info float-end" style="margin-right: 6%;" @click="update_bot(bot)" data-bs-toggle="modal" :data-bs-target="'#update-bot-modal-' + bot.id">修改</button>
                                        <!--这两个是模态框交互的属性（关闭等...）data-bs-toggle="modal" data-bs-target="#add-bot-btn"-->
                                        <!-- Modal 修改-->
                                        <div class="modal fade" :id="'update-bot-modal-' + bot.id" tabindex="-1">
                                            <div class="modal-dialog modal-xl">
                                                <div class="modal-content">
                                                <div class="modal-header">
                                                    <h1 class="modal-title fs-5">创建Bot</h1><!--标题-->
                                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                                </div>
                                                <div class="modal-body">
                                                    <div class="mb-3">
                                                        <label for="add-bot-title" class="form-label">
                                                        <span style="color: red;"> * </span> 名称(title)
                                                        </label>
                                                        <input v-model="bot.title" type="text" class="form-control" id="add-bot-tile" placeholder="请输入Bot名称">
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="add-bot-description" class="form-label">简介(description)</label>
                                                        <textarea v-model="bot.description" class="form-control" id="add-bot-description" rows="3" placeholder="请输入Bot的简介"></textarea>
                                                    </div>
                                                    <div class="mb-3">
                                                        <label for="add-bot-code" class="form-label">
                                                            <span style="color: red;"> * </span>代码(code)
                                                        </label>
                                                        <VAceEditor v-model:value="bot.content" @init="editorInit"
                                                            lang="c_cpp" theme="textmate" style="height: 300px"
                                                            :options="{
                                                                enableBasicAutocompletion: true, //启用基本自动完成
                                                                enableSnippets: true, // 启用代码段
                                                                enableLiveAutocompletion: true, // 启用实时自动完成
                                                                fontSize: 18, //设置字号
                                                                tabSize: 4, // 标签大小
                                                                showPrintMargin: false, //去除编辑器里的竖线
                                                                highlightActiveLine: true,
                                                                
                                                            }" />
                                                    </div>

                                                <!--模态框中的两个按钮&告警信息-->
                                                </div>
                                                <div class="modal-footer">
                                                    <div class="badge bg-primary text-wrap" style="width: 10rem; color: chocolate; font-size: large; margin-right: 1%;">
                                                    {{ bot.error_message  }}
                                                    </div>
                                                    <button type="button" class="btn btn-info" @click="update_bot(bot)">保存修改</button>
                                                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">取消</button>
                                                </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!--end Modal修改-->
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                    </div>  
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { useStore } from "vuex"
import $ from 'jquery'
import { ref, reactive } from 'vue'
import { Modal } from "bootstrap/dist/js/bootstrap"//关闭模态框
//导入编辑器模块
import { VAceEditor } from 'vue3-ace-editor'
import ace from 'ace-builds'
import 'ace-builds/src-noconflict/mode-c_cpp';
import 'ace-builds/src-noconflict/mode-json';
import 'ace-builds/src-noconflict/theme-chrome';
import 'ace-builds/src-noconflict/ext-language_tools';
 
export default{
    components:{
        VAceEditor
    },  
    setup(){
        //编辑器配置
        ace.config.set(
            "basePath",
            "https://cdn.jsdelivr.net/npm/ace-builds@" +
            require("ace-builds").version +
            "/src-noconflict/")


        const store = useStore();
        let bots = ref([]);
        const botadd = reactive({
            title: "",
            description: "",
            content: "//这里需要输入控制 bot 行动的程序代码",
            error_message: "",
        })

        const refresh_bots = () =>{
            $.ajax({
                url: "https://www.godice.cn/api/user/bot/getlist/",
                type: "get",
                headers: {
                    Authorization: "Bearer " + store.state.user.token
                },
                success(resp){
                    bots.value = resp;
                }
            })
        }

        const add_bot = () =>{
            botadd.error_message = "";//先清空上一次的报错
            $.ajax({
                url: "https://www.godice.cn/api/user/bot/add/",
                type: "post",
                data:{
                    bot_id: botadd.id,
                    title: botadd.title,
                    content: botadd.content,
                    description: botadd.description,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        botadd.title = "";//成功后要清空信息，防止污染下一次开启按钮
                        botadd.content = "";
                        botadd.description = "";
                        Modal.getInstance("#add-bot-btn").hide();
                        refresh_bots();
                    }else{
                        botadd.error_message = resp.error_message;
                    }
                },
            })
        }

        const update_bot = (bot) =>{//从按钮中获取
            botadd.error_message = "";//先清空上一次的报错
            $.ajax({
                url: "https://www.godice.cn/api/user/bot/update/",
                type: "post",
                data:{
                    bot_id: bot.id,
                    title: bot.title,
                    content: bot.content,
                    description: bot.description,
                },
                headers: {
                    Authorization: "Bearer " + store.state.user.token,
                },
                success(resp){
                    if(resp.error_message === 'success'){
                        Modal.getInstance('#update-bot-modal-' + bot.id).hide();
                        refresh_bots();
                    }else{
                        botadd.error_message = resp.error_message;
                    }
                },
            })
        }

        const remove_bot = (bot) =>{
            $.ajax({
                url: "https://www.godice.cn/api/user/bot/remove/",
                type: "post",
                headers:{
                    Authorization: "Bearer " + store.state.user.token,
                },
                data:{
                    bot_id: bot.id,
                },
                success(resp){
                    if(resp.error_message === "success"){
                        refresh_bots();
                    }
                }
            })
        }


        refresh_bots();

        return{
            bots,
            botadd,
            add_bot,  
            remove_bot,  
            update_bot,
        }
    }
}   
</script>




<style scoped>
div.card{
    margin-top: 50px;
}
div.photo-card .card-header {
    text-align:center;
    padding-bottom: 0; /* 减少底部填充 */
    border-bottom: none; /* 移除底部边框 */
    color: rgb(129, 125, 136);
    font-size: 120%;
}

div.photo-card .card-body {
    padding-top: 0; /* 减少顶部填充 */
}

div.bot-card .card-header{
    text-align:left;
    color: rgb(154, 154, 166);
}

div.bot-card .card-header .bot-name{
    font-size: 135%;
}


</style>