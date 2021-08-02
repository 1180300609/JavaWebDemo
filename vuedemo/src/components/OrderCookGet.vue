<template>
  <div>
    <el-table
        :data="tableData"
        style="width: 100%">
      <el-table-column type="expand">
        <template scope="scope">
          <el-table
              :data="scope.row.items"
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
          </el-table>
        </template>
      </el-table-column>
      <el-table-column
          label="订单编号"
          prop="id">
      </el-table-column>
      <el-table-column
          label="顾客"
          prop="customer">
      </el-table-column>
      <el-table-column
          label="厨师"
          prop="cook">
      </el-table-column>
      <el-table-column label="是否完成">
        <template slot-scope="scope">
          <span v-if="scope.row.isdone===0">未完成</span>
          <span v-else>已完成</span>
        </template>
      </el-table-column>
      <el-table-column
          label="总价"
          prop="total">
      </el-table-column>
      <el-table-column
          fixed="right"
          label="操作"
          align="center"
          width="180">
        <template slot-scope="scope">
          <el-button
              @click="orderID=scope.row.id;submitCookName()"
              type="text"
              size="small">
            接单
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>


<script>
export default {
  data() {
    return {
      tableData: [],
      dialogVisible: false,
      cookList: [],
      orderID:'',
      cookName:''
    }
  },
  created() {
    this.fetchData()
  },
  methods: {
    async fetchData() {
      const {data: res} = await this.$http.get('order/list');
      this.tableData = res
    },
    async submitCookName() {
      let param = new URLSearchParams()
      param.append("orderID", this.orderID)
      param.append("cookName", window.sessionStorage.getItem('username'))
      const {data: res} = await this.$http.post('order/setOrderCook',param);
      if (res !== "success") {
        alert("接单失败")
      }else {
        alert("接单成功")
        await this.fetchData()
      }
    }
  }
}
</script>


<style>
.demo-table-expand {
  font-size: 0;
}

.demo-table-expand label {
  width: 90px;
  color: #99a9bf;
}

.demo-table-expand .el-form-item {
  margin-right: 0;
  margin-bottom: 0;
  width: 50%;
}
.mypclass{
  font-weight:bold;
  font-size: larger;
  color:blue;
}
</style>
