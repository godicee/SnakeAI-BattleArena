<template>
    <ContentField>
        <table class="table table-striped table-hover" style="text-align: center;">
            <thead><!--表头-->
                <tr><!--每一行-->
                    <th>PlayerA</th><!--表头单元格-->
                    <th>PlayerB</th>
                    <th>对战结果</th>
                    <th>对战时间</th>
                    <th>对局录像</th>
                </tr>
            </thead>
            <tbody><!--表体-->
                <tr v-for="record in records" :key="record.record.id"><!--循环写法：必须绑定一个唯一的元素-->
                    <td> 
                        <img :src="record.a_photo" alt="" class = "record-user-photo">
                        &nbsp;
                        <span class="record-user-username">{{ record.a_username }}</span>
                    </td><!--表格数据单元格-->
                    <td>
                        <img :src="record.b_photo" alt="" class = "record-user-photo">
                        &nbsp;
                        <span class="record-user-username">{{ record.b_username }}</span>
                    </td>
                    <td> {{ record.result }}</td>
                    <td> {{ record.record.createtime }} </td>
                    <td><!--修改、删除按钮-->
                        <button @click="open_record_content(record.record.id)" type="button" class="btn btn-secondary">播放录像</button>
                    </td>
                </tr>
            </tbody>
        </table>
        <nav aria-label="...">
            <ul class="pagination" style="float: right">
                <li class="page-item" @click="click_page(-4)">
                    <span class="page-link">首页</span>
                </li>
                <li class="page-item" @click="click_page(-2)">
                    <span class="page-link">上页</span>
                </li>
                <li :class="'page-item ' + page.is_active" v-for="page in pages" :key="page.number" @click="click_page(page.number)">
                    <a class="page-link" href="#">  {{ page.number }}   </a>
                </li>
                <li class="page-item" @click="click_page(-1)">
                    <a class="page-link" href="#">下页</a>
                </li>
                <li class="page-item" @click="click_page(-3)">
                    <span class="page-link">最后一页</span>
                </li>
            </ul>
        </nav>
    </ContentField>
</template>

<script>
import ContentField from "@/components/ContentField.vue"
import { useStore } from "vuex"
import { ref } from "vue";
import $ from "jquery"
import router from "@/router/index";

export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let records = ref([]);  // （对应页的所有）对局记录
        let current_page = 1;
        let total_records = 0;
        let pages = ref([]);

        const click_page = (page) =>{  //点击页面触发的函数
            let max_pages = parseInt(Math.ceil(total_records / 10));
            if(page === -2) page = current_page - 1;
            else if(page === -1) page = current_page + 1;
            else if(page === -3) page = max_pages;
            else if(page === -4) page = 1;

            if(page >= 1 && page <= max_pages)
                pull_page(page);
        }
        
        const update_pages = () =>{  // 从后端拉取后触发，会生成页码
            let max_pages = parseInt(Math.ceil(total_records / 10));  // 向上取整
            let new_pages = [];
            for(let i = current_page - 2; i <= current_page + 2; ++i){
                if(i >= 1 && i <= max_pages){
                    new_pages.push({
                        number: i,
                        is_active: i === current_page ? "active" : "",  // 高亮当前页面
                    });
                }
            }
            pages.value = new_pages;
        }
        
        console.log(total_records);

        const pull_page = page =>{
            current_page = page;
            $.ajax({
                url: "https://www.godice.cn/api/record/getlist/",
                data:{
                    page,
                },
                type: "get",
                headers:{
                    Authorization: "Bearer " + store.state.user.token,
                }, 
                success(resp){
                    records.value = resp.records;
                    total_records = resp.records_count;
                    update_pages();
                },
                error(resp){
                    console.log(resp);  
                }
            })
        }
        pull_page(current_page);

        const stringTo2D = map =>{  // 获取地图信息：record.record.map中的字符串转为二维数组
            let g = [];
            let k = 0;
            for(let i = 0; i < 13; ++i){
                let line = [];
                for(let j = 0; j < 14; ++j){
                    if(map[k++] === '1') line.push(1);
                    else line.push(0);
                }
                g.push(line);
            }
            return g;
        }
        const open_record_content = recordId =>{
            for(const record of records.value){
                if(record.record.id === recordId){
                    store.commit("updateIsRecord", true);
                    // console.log(record);
                    store.commit("updateGame", {
                        map: stringTo2D(record.record.map),
                        a_id: record.record.aid,
                        a_sx: record.record.asx,
                        a_sy: record.record.asy,
                        b_id: record.record.bid,
                        b_sx: record.record.bsx,
                        b_sy: record.record.bsy,
                    });
                    store.commit("updateSteps",{
                        a_steps: record.record.asteps,
                        b_steps: record.record.bsteps,                  
                    });
                    store.commit("updateRecordLoser", record.record.loser);
                    router.push({
                        name: "record_content",
                        params:{
                            recordId: recordId
                        }
                    });
                    break;
                }
            }
        }
        
        return{
            records,
            open_record_content,
            pages,
            click_page,
        }
    }
}   
</script>


<style scoped>
img.record-user-photo{
    width: 6vh;
    border-radius: 50%;
}

</style>