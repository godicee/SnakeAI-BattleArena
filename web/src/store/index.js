import { createStore } from 'vuex'
import ModuleUser from './user'//ModuleUser是随便起的名字
import ModulePk from './pk'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {//注册user模块到 store 中
    user: ModuleUser,
    pk: ModulePk,
  }
})
