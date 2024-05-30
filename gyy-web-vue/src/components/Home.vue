<template>
  <div class="user-center">
    <el-card class="box-card">
      <div slot="header" class="clearfix">
        <span>用户个人中心</span>
      </div>
      <div class="user-info">
        <el-row>
          <el-col :span="6"><strong>用户ID:</strong></el-col>
          <el-col :span="18">{{ userInfo.id }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>姓名:</strong></el-col>
          <el-col :span="18">{{ userInfo.name }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>年龄:</strong></el-col>
          <el-col :span="18">{{ userInfo.age }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>电话号码:</strong></el-col>
          <el-col :span="18">{{ userInfo.phone }}</el-col>
        </el-row>
        <el-row>
          <el-col :span="6"><strong>性别:</strong></el-col>
          <el-col :span="18">{{stringSex}}</el-col>
        </el-row>
        <!-- 添加更多需要显示的用户信息 -->
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'UserCenter',
  data() {
    return {
      userInfo: {},
      stringSex:null
    };
  },
  created() {
    this.loadUserInfo();
    this.sex();
  },
  methods: {
    loadUserInfo() {
      const userData = sessionStorage.getItem('CurUser');
      if (userData) {
        this.userInfo = JSON.parse(userData);
      } else {
        this.$message.error('User data not found!');
      }
    },
    sex(){
      if (this.userInfo.sex === 0)
        this.stringSex = '男'
      else
        this.stringSex = '女'
    }
  }
};
</script>

<style scoped>
.user-center {
  width: 60%;
  margin: 50px auto;
}

.user-info {
  margin-top: 20px;
}

.el-row {
  margin-bottom: 10px;
}
</style>