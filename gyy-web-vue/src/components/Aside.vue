<template>
  <el-menu style="height: 100%;background-color:#545c64" text-color="#fff" active-text-color="#ffd04b" default-active="/Home" router>
    <el-menu-item index="/Home">
      <i class="el-icon-s-home"></i>
      <span>首页</span>
    </el-menu-item>
    <el-menu-item index="/Main">
      <i class="el-icon-s-data"></i>
      <span>员工</span>
    </el-menu-item>
    <el-menu-item index="/Object">
      <i class="el-icon-s-order"></i>
      <span>物品</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: "Aside"
}
</script>

<style>

</style>