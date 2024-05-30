<script>
import th from "element-ui/src/locale/lang/th";

export default {
  name: "Header",
  data(){
    return{
      user: JSON.parse(sessionStorage.getItem('CurUser'))
    }
  },
  methods:{
    toUser(){
      console.log("toUser");
      this.$router.replace('/Home')
    },
    logOut(){
      console.log("退出")
      this.$router.replace('/')
      sessionStorage.clear();
    }
  }
}
</script>

<template>
<div style="display: flex;line-height: 60px">
  <div>
    <i class="el-icon-s-fold" style="font-size: 20px"></i>
  </div>
  <div style="flex: 1;text-align: center;font-size: 34px">
    <span>欢迎来到仓库管理系统</span>
  </div>
  <el-dropdown>
    <span>{{user.name}}</span><i class="el-icon-arrow-down" style="margin-right:15px"></i>
    <el-dropdown-menu slot="dropdown">
      <el-dropdown-item @click.native="toUser">个人中心</el-dropdown-item>
      <el-dropdown-item @click.native="logOut">退出登录</el-dropdown-item>
    </el-dropdown-menu>
  </el-dropdown>
</div>
</template>

<style scoped>

</style>