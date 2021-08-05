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
      <el-table-column label="图片">
        <template width="90" slot-scope="scope">
          <img style="width:80px;height:80px;border:none;" :src="getImgUrl(scope.row.menuid)">
        </template>
      </el-table-column>
      <el-table-column
          prop="price"
          label="价格"
          width="120">
      </el-table-column>
    </el-table>
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
    clearForm() {
      for (let k in this.updateForm) {
        this.updateForm[k]=''
      }
    },
    async searchMenu() {
      console.log(this.search)
      const {data: res} = await this.$http.get('searchMenu/'+encodeURI(encodeURI(this.search)));
      this.tableData = res
    },
    getImgUrl(id) {
      return "https://127.0.0.1:8443/img/"+id+".webp"
    }
  }
}
</script>
