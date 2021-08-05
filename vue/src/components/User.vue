<template>
  <div>

  <el-table
      :data="tableData"
      style="width: 100%"
      max-height=700px>
    <el-table-column
        fixed
        prop="username"
        label="用户名"
        width="100">
    </el-table-column>
    <el-table-column
        fixed
        prop="rid"
        label="权限"
        width="100">
    </el-table-column>
    <el-table-column
        prop="active"
        label="状态"
        width="100">
    </el-table-column>
    <el-table-column
        prop="phone"
        label="电话"
        width="120">
    </el-table-column>
    <el-table-column
        prop="email"
        label="邮箱"
        width="180">
    </el-table-column>
    <el-table-column
        label="操作"
        width="200">
      <template slot-scope="scope">
        <el-button
            @click="dialogVisible=true;updateForm.username=scope.row.username"
            type="text"
            size="small">
          修改
        </el-button>
        <el-button
            @click="deleteUser(scope.row.username)"
            type="text"
            size="small">
          删除
        </el-button>
        <el-button
            @click="freezeUser(scope.row.username)"
            type="text"
            size="small">
          冻结
        </el-button>
        <el-button
            @click="releaseUser(scope.row.username)"
            type="text"
            size="small">
          解冻
        </el-button>
      </template>
    </el-table-column>
  </el-table>

    <el-dialog
        title="修改"
        :visible.sync="dialogVisible"
        width="30%">
      <el-form label-width="80px" class="form" :model="updateForm">
        <el-form-item label="权限">
          <el-input v-model="updateForm.rid"></el-input>
        </el-form-item>
        <el-form-item label="电话">
          <el-input v-model="updateForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="updateForm.email"></el-input>
        </el-form-item>
        <el-form-item class="button">
          <el-button type="primary" @click="submitUpdateForm();dialogVisible = false;clearForm()">提交</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;clearForm()">取 消</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "User",
  data() {
    return {
      tableData: [],
      updateForm: {
        username:'',
        rid: '',
        phone: '',
        email: ''
      },
      dialogVisible: false,
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      const {data: res} = await this.$http.get('user/userList');
      this.tableData = res
    },
    async submitUpdateForm() {
      const {data: res} = await this.$http.post('user/updateUser',this.updateForm);
      alert(res)
      await this.fetchData()
    },
    clearForm() {
      for (let k in this.updateForm) {
        this.updateForm[k]=''
      }
    },
    async deleteUser(username) {
      let param = new URLSearchParams()
      param.append("username", username)
      const {data: res} = await this.$http.post('/user/deleteUser', param);
      alert(res)
      await this.fetchData()
    },
    async freezeUser(username) {
      let param = new URLSearchParams()
      param.append("username", username)
      const {data: res} = await this.$http.post('/user/freezeUser', param);
      alert(res)
      await this.fetchData()
    },
    async releaseUser(username) {
      let param = new URLSearchParams()
      param.append("username", username)
      const {data: res} = await this.$http.post('/user/releaseUser', param);
      alert(res)
      await this.fetchData()
    }
  }
}
</script>

<style scoped>

</style>
