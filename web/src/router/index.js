import { createRouter, createWebHistory } from 'vue-router'
import NotFound from '@/views/error/NotFound'
import PkindexView from '@/views/pk/PkIndexView.vue'
import RanklistIndexView from '@/views/ranklist/RanklistIndexView.vue'
import RecoedIndexView from '@/views/record/RecordIndexView.vue'
import UserBotIndexView from '@/views/user/bot/UserBotIndexView.vue'


const routes = [
  {
    path: '/',
    name: 'home',
    redirect: '/pk/',
  },
  {
    path: '/pk/',
    name: 'pk_index',
    component: PkindexView,
  },
  {
    path: '/record/',
    name: 'record_index',
    component: RecoedIndexView,
  },
  {
    path: '/ranklist/',
    name: 'ranklist_index',
    component: RanklistIndexView,
  },
  {
    path: '/user/bot',
    name: 'user_bot_index',
    component: UserBotIndexView ,
  },
  {
    path: '/404/',
    name: '404',
    component: NotFound,
  },
  { path: '/:catchALl(.*)',
    redirect: "/404/",
  },
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
