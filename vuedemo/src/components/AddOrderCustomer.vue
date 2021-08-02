<template>
  <div>
    <h1>备选菜单</h1>
    <el-table
        :data="tableData"
        style="width: 100%"
        max-height=400px>
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
              @click="addMenu(scope.row)"
              type="text"
              size="small">
            添加
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <h1>已选菜单</h1>
    <p align="right">总价:{{total}}</p>
    <el-table
        :data="chooseTable"
        style="width: 100%"
        max-height=300px>
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
              @click=""
              type="text"
              size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <div align="right">
      <el-button type="primary" @click="submitMenu()">提交订单</el-button>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      tableData: [],
      chooseTable:[],
      menuList:[],
      total:0
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
    addMenu(row){
      console.log(row)
      this.chooseTable.push(row)
      this.menuList.push(row.menuid)
      this.total+=row.price
      console.log(this.total)
      console.log(this.menuList)
    },
    async submitMenu() {
      let param = new URLSearchParams()
      param.append("customer", window.sessionStorage.getItem("username"))
      param.append("menuList", this.menuList)
      const {data: res} = await this.$http.post('order/addOrder',param);
      if (res === "success") {
        alert("提交订单成功")
        this.menuList=[]
        this.chooseTable=[]
      } else {
        alert("提交订单失败")
      }
    }
  }
}
</script>
