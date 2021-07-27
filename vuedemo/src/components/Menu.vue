<template>
  <div>
    <el-card>
      <el-row>
        <el-col :span="7">
          <el-input placeholder="请输入搜索内容" v-model="search"
                    clearable @clear="fetchData">
            <el-button slot="append" icon="el-icon-search" @click="searchMenu"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4"></el-col>
      </el-row>
    </el-card>

    <el-table
        :data="tableData"
        style="width: 100%"
        max-height=700px>
      <el-table-column
          fixed
          prop="menuid"
          label="菜品编号"
          width="100">
      </el-table-column>
      <el-table-column
          fixed
          prop="name"
          label="菜名"
          width="150">
      </el-table-column>
      <el-table-column
          prop="details"
          label="信息"
          width="400">
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格"
          width="120">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          width="120">
        <template slot-scope="scope">
          <el-button
              @click="dialogVisible=true;updateForm.menuid=scope.row.menuid"
              type="text"
              size="small">
            修改
          </el-button>
          <el-button
              @click="deleteMenu(scope.row)"
              type="text"
              size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog
        title="修改"
        :visible.sync="dialogVisible"
        width="30%">
      <el-form label-width="80px" class="form" :model="updateForm" :rules="rules">
        <el-form-item label="菜名">
          <el-input v-model="updateForm.name"></el-input>
        </el-form-item>
        <el-form-item label="信息">
          <el-input v-model="updateForm.details"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="updateForm.price"></el-input>
        </el-form-item>
        <el-form-item class="button">
          <el-button type="primary" @click="submitUpdateForm();dialogVisible = false;clearForm()">提交</el-button>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;clearForm()">取 消</el-button>
<!--        <el-button type="primary" @click="dialogVisible = false">确 定</el-button>-->
      </span>
    </el-dialog>

  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      search: '',
      updateForm: {
        menuid: '',
        name: '',
        details: '',
        price: ''
      },
      dialogVisible: false,
      rules: {
        name: [{required:true,message:"请输入",trigger:'blur'}],
        details: [{required:true,message:"请输入",trigger:'blur'}],
        price: [{required:true,message:"请输入",trigger:'blur'}]
      }
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      const {data: res} = await this.$http.get('menu');
      this.tableData = res
    },
    async deleteMenu(row) {
      console.log(row.id)
      const {data: res} = await this.$http.get('deleteMenu/' + row.menuid);
      alert(res)
      await this.fetchData()
    },
    async submitUpdateForm() {
      console.log(this.updateForm)
      const {data: res} = await this.$http.post('updateMenu/',this.updateForm);
      alert(res)
      await this.fetchData()
    },
    clearForm() {
      for (let k in this.updateForm) {
        this.updateForm[k]=''
      }
    },
    async searchMenu() {
      console.log(this.search)
      const {data: res} = await this.$http.get('searchMenu/'+encodeURI(encodeURI(this.search)));
      this.tableData = res
    }
  }
}
</script>
