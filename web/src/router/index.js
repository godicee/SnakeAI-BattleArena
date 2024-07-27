import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '@/views/error/NotFound'
import PkindexView from '@/views/pk/PkIndexView.vue'
import RanklistIndexView from '@/views/ranklist/RanklistIndexView.vue'
import RecoedIndexView from '@/views/record/RecordIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'
import UserAccountLoginView from '@/views/user/account/UserAccountLoginView.vue'
import UserAccountRegisterView from '@/views/user/account/UserAccountRegisterView.vue'
import store from '@/store/index.js'

const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/pk/',//home 页面会重定向到 pk 页面
    meta: {//存储页面需要的一些额外信息
      requestAuth: true,//是否需要token授权
    },
  },
  {
    path: '/pk/',
    name: 'pk_index',
    component: PkindexView,
    meta: {
      requestAuth: true,
    },
  },
  {
    path: '/record/',
    name: 'record_index',
    component: RecoedIndexView,
    meta: {
      requestAuth: true,
    },
  },
  {
    path: '/ranklist/',
    name: 'ranklist_index',
    component: RanklistIndexView,
    meta: {
      requestAuth: true,
    },
  },
  {
    path: '/user/bot',
    name: 'user_bot_index',
    component: UserBotIndexView ,
    meta: {
      requestAuth: true,
    },
  },
  {
    path: '/user/account/login/',
    name: 'user_account_login',
    component: UserAccountLoginView,
    meta: {
      requestAuth: false,
    }, 
  },
  {
    path: '/user/account/register/',
    name: 'user_account_register',
    component: UserAccountRegisterView,
    meta: {
      requestAuth: false,
    }, 
  },
  {
    path: '/404/',
    name: '404',
    component: NotFound,
    meta: {
      requestAuth: false,
    }, 
  },
  { path: '/:catchALl(.*)',
    redirect: "/404/",
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
//实现大部分页面（在未登录状态）会自动跳转到登录页面
//除了登录、注册、404页面外，其他页面都需要登录
router.beforeEach((to, from, next) => {//每次用 router 调用新页面都会执行这个函数
  if(to.meta.requestAuth && !store.state.user.is_login){//若将去的页面需要授权，且当前不是登录状态
    next({name: 'user_account_login'});
  }else{
    next();//否则则可以继续进行页面跳转
  }
})


export default router
