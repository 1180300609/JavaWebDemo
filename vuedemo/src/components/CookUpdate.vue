<template>
  <div class="login_container">
    <div class="login_box">
      <h1 align="center">修改密码</h1>
      <el-form label-width="80px" class="form" :model="form" :rules="rules">
        <el-form-item label="新密码" prop="newPasswd">
          <el-input v-model="form.newPasswd" type="password"></el-input>
        </el-form-item>
        <el-form-item label="再次输入" prop="passwdAgain">
          <el-input v-model="form.passwdAgain" type="password"></el-input>
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
        newPasswd : '',
        passwdAgain : ''
      },
      rules: {
        newPasswd: [{required:true,message:"请输入",trigger:'blur'}],
        passwdAgain: [{required:true,message:"请输入",trigger:'blur'}],
      }
    }
  },
  methods:{
    async submitForm() {
      if (this.form.newPasswd !== this.form.passwdAgain) {
        alert("两次输入的密码不一致!!!");
        return;
      }
      let param = new URLSearchParams()
      param.append("username", window.sessionStorage.getItem("username"))
      param.append("password", this.form.newPasswd)
      const {data: res} = await this.$http.post('user/updatePasswd',param);
      console.log(res)
      if (res !== "success") {
        alert("修改失败")
      }else {
        alert("修改成功")
      }
    },
    clearForm() {
      for (let k in this.form) {
        this.form[k]=''
      }
    }
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
