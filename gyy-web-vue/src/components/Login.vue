<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2 class="login-title">登录</h2>
      <el-form :model="loginForm" :rules="rules" ref="loginForm" label-width="80px">
        <el-form-item label="账号" prop="account">
          <el-input v-model="loginForm.account" placeholder="账号"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="loginForm.password" placeholder="密码" type="password"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleLogin">登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import axios from 'axios';
import Index from "@/components/Index.vue";



export default {
  data() {
    return {
      loginForm: {
        account: '',
        password: ''
      },
      rules: {
        account: [
          { required: true, message: '请输入账号', trigger: 'blur' }
        ],
        password: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate((valid) => {
        if (valid) {
          axios.post(`http://localhost:8080/user/login`, {
            account: this.loginForm.account,
            password: this.loginForm.password
          }).then(res=>res.data)
              .then(response => {
                if (response.data !=null) {
                  this.$message({
                    message: '登录成功',
                    type: 'success'
                  });
                  // 这里可以添加跳转逻辑，例如跳转到主页
                  console.log(response);
                  this.$router.replace('/Index').then()
                  console.log("成功")
                  sessionStorage.setItem('CurUser',JSON.stringify(response.data))

                } else {
                  this.$message({
                    message: '登录失败: ' + response.message,
                    type: 'error'
                  });
                }
              })
              .catch(error => {
                this.$message({
                  message: '登录失败: ' + error.message,
                  type: 'error'
                });
              });
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    }
  },
  components:{Index}
};
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background-color: #f5f5f5;
}

.login-card {
  width: 400px;
  padding: 20px;
}

.login-title {
  text-align: center;
  margin-bottom: 20px;
}
</style>
