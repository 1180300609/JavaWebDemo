<template>
  <div class="login_container">
    <div class="login_box">
      <h1 align="center">增加菜单</h1>
      <el-form label-width="80px" class="form" :model="form" :rules="rules">
        <el-form-item label="菜名" prop="name">
          <el-input v-model="form.name"></el-input>
        </el-form-item>
        <el-form-item label="信息" prop="details">
          <el-input v-model="form.details" ></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="price">
          <el-input v-model="form.price" ></el-input>
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
        name : '',
        details: '',
        price:''
      },
      rules: {
        name: [{required:true,message:"请输入",trigger:'blur'}],
        details: [{required:true,message:"请输入",trigger:'blur'}],
        price: [{required:true,message:"请输入",trigger:'blur'}]
      }
    }
  },
  methods:{
    async submitForm() {
      console.log(this.form)
      const {data: res} = await this.$http.post('addMenu', this.form)
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
