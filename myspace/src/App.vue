<template>
  <NavBar />
  <router-view/>
</template>

<script>
import 'bootstrap/dist/css/bootstrap.css';
import 'bootstrap/dist/js/bootstrap';
import NavBar from './components/NavBar.vue';

export default{
  name: "App",
  components:{//一个对象
    NavBar
  }
}
</script>



<style>

</style>
