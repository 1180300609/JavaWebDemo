<template>
  <div class="login_container">
    <div class="login_box">
      <h1 align="center">添加用户</h1>
      <el-form label-width="80px" class="form" :model="form" :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item label="权限" prop="rid">
          <el-input v-model="form.rid" ></el-input>
        </el-form-item>
        <el-form-item label="手机">
          <el-input v-model="form.phone" ></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.email" ></el-input>
        </el-form-item>
        <el-form-item class="button">
          <el-button type="primary"  @click="submitForm();clearForm()">提交</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "AddMenu",
  data() {
    return{
      form:{
        username : '',
        password: '',
        rid: '',
        phone: '',
        email:''
      },
      rules: {
        username: [{required:true,message:"请输入",trigger:'blur'}],
        password: [{required:true,message:"请输入",trigger:'blur'}],
        rid: [{required:true,message:"请输入",trigger:'blur'}]
      }
    }
  },
  methods:{
    async submitForm() {
      console.log(this.form)
      const {data: res} = await this.$http.post('user/addUser', this.form)
      console.log(res)
      if (res !== "success") {
        alert("添加失败")
      }else {
        alert("添加成功")
      }
    },
    clearForm() {
      for (let k in this.form) {
        this.form[k]=''
      }
    },
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #EAEDF1;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 450px;
  background-color: aliceblue;
  border-radius: 3px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
}

.form{
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}

.button{
  display: flex;
  justify-content: center;
}
</style>
