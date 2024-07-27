<template>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container">  
    <router-link class="navbar-brand" :to="{name: 'home'}">King of Bots(Snake)</router-link>
    <div class="collapse navbar-collapse" id="navbarText">
      <ul class="navbar-nav me-auto mb-2 mb-lg-0">
        <li class="nav-item">
          <router-link :class="route_name == 'pk_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'pk_index'} ">游戏对战</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'record_index' ? 'nav-link active' : 'nav-link'" :to="{name: 'record_index'}">对局列表</router-link>
        </li>
        <li class="nav-item">
          <router-link :class="route_name == 'ranklist_index' ? 'nav-link active' : 'nav-link' " :to="{name: 'ranklist_index'}">排行榜</router-link>
        </li>
      </ul>
      <!--登录状态：导航栏右上角的显示用户名-->
      <ul class="navbar-nav" v-if="$store.state.user.token">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ $store.state.user.username }}
          </a>
          <ul class="dropdown-menu">
            <li><router-link class="dropdown-item" :to="{name: 'user_bot_index'}">我的bot</router-link></li>
            <li><hr class="dropdown-divider"></li>
            <li><router-link class="dropdown-item" :to="{name: 'home'}" @click="logout">退出</router-link></li>
          </ul>
        </li>
      </ul>
      <!--未登录：导航栏右上角显示登录、注册-->
      <ul class="navbar-nav" v-else-if="!$store.state.user.pulling_info">
        <li class="nav-item">
          <router-link class="nav-link" :to="{name: 'user_account_login'}" role="button">
            登录
          </router-link>
        </li>
        <li class="nav-item dropdown">
          <router-link class="nav-link" :to="{name: 'user_account_register'}" role="button">
            注册
          </router-link>
        </li>
      </ul>

    </div>
  </div>
</nav>
</template>

<script>
import { useRoute } from 'vue-router'
import { computed } from 'vue'
import { useStore } from "vuex" 

export default{
  setup(){
    const store = useStore();
    const route = useRoute();
    let route_name = computed(()=> route.name);

    //退出登录的触发事件
    const logout = () =>{
      store.dispatch("logout");//函数在下面 return 中返回
    }

    return {
      route_name,
      logout,//退出登录的触发事件，直接嵌入到 html 中即可
    }
  }
}

</script>

<style scoped>

</style>