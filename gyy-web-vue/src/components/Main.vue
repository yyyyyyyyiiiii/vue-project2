<script>
import axios from "axios";
import th from "element-ui/src/locale/lang/th";

export default {
  components: {},
  data() {
    return {
      tableData: [],
      pageSize:10,
      page:1,
      total:0,
      name:null,
      sex:null,
      sexs:[{value:0,label:'男'},{value:1,label:'女'}],
      phone:null,
      centerDialogVisible:false,
      form:{
        name:null,sex:null,age:null,phone:null,account:null,password:null,roleId:null,id:null
      },
      centerDialogVisible2:false,
    }
  },
  methods:{
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
      this.pageSize = val;
      this.loadGet2();
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
      this.page = val;
      this.loadGet2();
    },
    loadGet2(){
      axios.get(`http://localhost:8080/user/list1`,{
        params:{
          name:this.name,
          pageSize:this.pageSize,
          page:this.page,
          sex: this.sex,
          phone:this.phone,
        }
      }).then(res=>res.data).then(res=>res.data).then(res=>{
        console.log(res);
        this.tableData = res.rows;
        this.total = res.total;
      })
      },
    reset(){
      this.name = null;this.phone = null;this.sex = null;
      this.loadGet2();
    },
    add(){
      this.centerDialogVisible = true;
    },
    addUser(){
      axios.post(`http://localhost:8080/user/add`, {
        name: this.form.name,
        account: this.form.account,
        password: this.form.password,
        age: this.form.age,
        sex: this.form.sex,
        roleId: this.form.sex,
        phone: this.form.phone,
      }).then(res=>{
          this.loadGet2();
          this.centerDialogVisible = false;
          this.$message({
            message: '新增成功',
            type: 'success'
          });
        })
    },
    del(row) {
      axios.delete(`http://localhost:8080/user/delete/${row.id}`).then(res=>res.data).then(res=>{
          this.$message({
            message: '删除成功',
            type: 'success'
          });
        this.loadGet2();
      });

    },
    modify(row){
      this.centerDialogVisible2 = true;
      this.form.name = row.name;
      this.form.account = row.account;
      this.form.age = row.age;
      this.form.password = row.password;
      this.form.phone = row.phone;
      this.form.roleId = row.roleId + '';
      this.form.sex = row.sex + '';
      this.form.id = row.id;
    },
    modUser(){
      axios.put(`http://localhost:8080/user/update`,{
        name: this.form.name,
        account: this.form.account,
        password: this.form.password,
        age: this.form.age,
        sex: this.form.sex,
        roleId: this.form.sex,
        phone: this.form.phone,
        id:this.form.id
      }).then(res=>res.data).then(res=>res.data).then(res=>{
        this.centerDialogVisible2 = false;
        this.$message({
          message: '修改成功',
          type: 'success'
        });
        this.loadGet2();
        })
    }
  },
  beforeMount() {
    //this.loadGet();
    this.loadGet2();
  }
};
</script>

<template>
  <div>
    <div style="margin-bottom: 5px">
      <el-input placeholder = "请输入名称" suffix-icon="el-icon-search" style="width: 200px" v-model="name" @keyup.enter.native="loadGet2"></el-input>
      <el-select v-model="sex" filterable placeholder="请选择性别" style="margin-left: 50px">
        <el-option
            v-for="item in sexs"
            :key="item.value"
            :label="item.label"
            :value="item.value">
        </el-option>
      </el-select>
      <el-input placeholder = "请输入电话号码" suffix-icon="el-icon-search" style="width: 200px;margin-left: 50px" v-model="phone" @keyup.enter.native="loadGet2"></el-input>
      <el-button style="margin-left: 100px" type="primary" @click="loadGet2">查询</el-button>
      <el-button style="margin-left: 10px" type="success" @click="reset">重置</el-button>
      <el-button style="margin-left: 100px;" type="primary" @click="add">新增员工</el-button>
    </div>
  <el-table :data="tableData" width="100%;">
    <el-table-column prop="id" label="ID" width="60">
    </el-table-column>
    <el-table-column prop="account" label="账号" width="180">
    </el-table-column>
    <el-table-column prop="name" label="姓名" width="120">
    </el-table-column>
    <el-table-column prop="age" label="age" width="90">
    </el-table-column>
    <el-table-column prop="sex" label="性别" width="90">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.sex === 0 ? 'primary' : 'success'"
            disable-transitions>{{scope.row.sex === 0 ? '男' : '女'}}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="phone" label="电话号码" width="160">
    </el-table-column>
    <el-table-column prop="roleId" label="角色" width="160">
      <template slot-scope="scope">
        <el-tag
            :type="scope.row.roleId === 0 ? 'primary' : 'success'"
            disable-transitions>{{scope.row.roleId === 0 ? '管理员' : '用户'}}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column prop="image" label="头像" width = "200">
    </el-table-column>
    <el-table-column prop="operate" label="操作" align="center" width="200">
      <template slot-scope="scope">
        <el-button size="small" type="success" @click="modify(scope.row)">编辑</el-button>
        <el-button size="small" type="danger" @click="del(scope.row)">删除</el-button>
      </template>
    </el-table-column>
  </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="page"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
    </el-pagination>
    <el-dialog
        title="新增员工"
        :visible.sync="centerDialogVisible"
        width="30%"
        center>

      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="员工姓名">
          <el-col span=11>
          <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="员工账号">
          <el-input v-model="form.account"></el-input>
        </el-form-item>
        <el-form-item label="员工密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="员工年龄">
          <el-col span=11>
          <el-input v-model="form.age"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="0">男</el-radio>
            <el-radio label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="用户权限">
          <el-radio-group v-model="form.roleId">
            <el-radio label="0">管理员</el-radio>
            <el-radio label="1">用户</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="addUser">确 定</el-button>
  </span>
    </el-dialog>

    <el-dialog
        title="修改员工"
        :visible.sync="centerDialogVisible2"
        width="30%"
        center>

      <el-form ref="form" :model="form" label-width="80px">
        <el-form-item label="员工姓名">
          <el-col span=11>
            <el-input v-model="form.name"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="员工账号">
          <el-input v-model="form.account"></el-input>
        </el-form-item>
        <el-form-item label="员工密码">
          <el-input v-model="form.password"></el-input>
        </el-form-item>
        <el-form-item label="手机号码">
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="员工年龄">
          <el-col span=11>
            <el-input v-model="form.age"></el-input>
          </el-col>
        </el-form-item>
        <el-form-item label="性别">
          <el-radio-group v-model="form.sex">
            <el-radio label="0">男</el-radio>
            <el-radio label="1">女</el-radio>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="用户权限">
          <el-radio-group v-model="form.roleId">
            <el-radio label="0">管理员</el-radio>
            <el-radio label="1">用户</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible2 = false">取 消</el-button>
    <el-button type="primary" @click="modUser">确 定</el-button>
  </span>
    </el-dialog>

  </div>
</template>

<style scoped>

</style>