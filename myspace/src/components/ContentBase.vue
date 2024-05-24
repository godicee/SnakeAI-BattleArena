<!--本文件作为内容组件的模板使用-->
<template>
  <div class="home">
    <div class="container">
      <div class="card">
        <div class="card-body">
          <slot></slot><!--内容占位-->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default{
    name: "ContentBase",
}
</script>

<style scoped>
.container{
  margin-top: 20px;
}

</style>