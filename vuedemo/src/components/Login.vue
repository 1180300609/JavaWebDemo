<template>
  <div class="login_container">
    <div class="login_box">
      <h1 align="center">登陆</h1>
      <el-form label-width="80px" class="form" :model="form" :rules="rules">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" type="password"></el-input>
        </el-form-item>
        <el-form-item class="button">
          <el-button type="primary"  @click="submitForm">登陆</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return{
      form:{
        username : '',
        password: ''
      },
      rules: {
        username: [{required:true,message:"请输入用户名",trigger:'blur'}],
        password: [{required:true,message:"请输入密码",trigger:'blur'}]
      }
    }
  },
  methods:{
    async submitForm() {
      console.log(this.form.username)
      console.log(this.form.password)
      const {data: res} = await this.$http.post('login', this.form)
      if (res.msg !== "success") {
        alert("登陆失败")
      }else {
        alert("登陆成功")
        window.sessionStorage.setItem("token",res.token)
        window.sessionStorage.setItem("username",res.username)
        window.sessionStorage.setItem("rid",res.rid)
        this.$router.push('/home')
      }
    }
  }
}
</script>

<style lang="less" scoped>
.login_container {
  background-color: #2b4b6b;
  height: 100%;
}

.login_box {
  width: 450px;
  height: 300px;
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
