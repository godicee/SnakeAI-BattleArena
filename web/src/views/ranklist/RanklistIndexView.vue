<template>
    <ContentField>
        <table class="table table-striped table-hover" style="text-align: center;">
            <thead><!--表头-->
                <tr><!--每一行-->
                    <th>玩家</th><!--表头单元格-->
                    <th>天梯分</th>
                </tr>
            </thead>
            <tbody><!--表体-->
                <tr v-for="user in users" :key="user.id"><!--循环写法：必须绑定一个唯一的元素-->
                    <td> 
                        <img :src="user.photo" alt="" class = "record-user-photo">
                        &nbsp;
                        <span class="record-user-username">{{ user.username }}</span>
                    </td><!--表格数据单元格-->
                    <td> {{ user.rating }}</td>
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
import { ref } from "vue"
import $ from "jquery"

export default{
    components:{
        ContentField
    },
    setup(){
        const store = useStore();
        let users = ref([]);  // （对应页的所有）对局记录
        let current_page = 1;
        let total_users = 0;
        let pages = ref([]);

        const click_page = (page) =>{  //点击页面触发的函数
            let max_pages = parseInt(Math.ceil(total_users / 10));
            if(page === -2) page = current_page - 1;
            else if(page === -1) page = current_page + 1;
            else if(page === -3) page = max_pages;
            else if(page === -4) page = 1;

            if(page >= 1 && page <= max_pages)
                pull_page(page);
        }
        
        const update_pages = () =>{  // 从后端拉取后触发，会生成页码
            let max_pages = parseInt(Math.ceil(total_users / 10));  // 向上取整
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

        const pull_page = page =>{
            current_page = page;
            $.ajax({
                url: "https://www.godice.cn/api/ranlist/getlist/",
                data:{
                    page,
                },
                type: "get",
                headers:{
                    Authorization: "Bearer " + store.state.user.token,
                }, 
                success(resp){
                    users.value = resp.users;
                    total_users = resp.users_count;
                    update_pages();
                },
                error(resp){
                    console.log(resp);  
                }
            })
        }
        pull_page(current_page);

        return{
            users   ,
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