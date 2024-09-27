<template>
  <NavBar/>
  <router-view> </router-view><!--自动根据网址改变路由，需要去 router 中定义-->
</template>

<script>
import NavBar from "./components/NavBar.vue"
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.js'
// import "bootstrap/dist/css/bootstrap.min.css"
// import "bootstrap/dist/js/bootstrap"
// import $ from 'jquery'

export default{
  components: {
    NavBar
  },
  setup(){
    // $.ajax({
    //   url: "http://127.0.0.1:3002/user/account/register/",
    //   type: "post",
    //   data:{
    //     username: "godice2",
    //     password: "pgodice2",
    //     confirmedPassword: "pgodice2",
    //   },
    //   success(resp){
    //     console.log(resp);
    //   },
    //   error(resp){
    //     console.log(resp);
    //   }
    // })
  }
}

// background-image: url("@/assets/images/background.png");/*@这里是根目录，也可以用相对目录*/

</script>


 
<style>
body {
  background-image: url("@/assets/images/IMG_3137.JPG");/*@这里是根目录，也可以用相对目录*/
  background-size: cover;/*背景图缩放*/
}

</style>
