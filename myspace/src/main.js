import { createApp } from 'vue'
import App from './App.vue'//导入根组件
import store from './store'//vuex（多组件维护同一个数据）
import router from './router'//路由

createApp(App).use(router).use(store).mount('#app')
