<template>
  <el-container style="height: 1200px; border: 1px solid #eee">
    <el-aside width="200px" style="background-color: rgb(238, 241, 246) ">
      <Aside></Aside>
    </el-aside>
    
    <el-container>
      <el-header style="text-align: right; font-size: 12px;border-bottom: #333333 1px solid;">
        <Header></Header>
      </el-header>
      
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
  </template>
  
  <style>
    .el-header {
      background-color: silver;
      color: #333;
      line-height: 60px;
    }
    
    .el-aside {
      color: #333;
    }
  </style>
  
  <script>
    import Aside from "@/components/Aside.vue";
    import Header from "@/components/Header.vue";
    import Main from "@/components/Main.vue";
    export default {
      components: {Header, Aside,Main},
      data() {

        return {

        }
      },
      created() {
        this.$router.replace('/Home');
      }
    };
  </script>
