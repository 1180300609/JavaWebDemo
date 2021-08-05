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
              @click="dialogVisible=true;orderID=scope.row.id"
              type="text"
              size="small">
            厨师接单
          </el-button>
          <el-button
              @click="doneOrder(scope.row.id)"
              type="text"
              size="small">
            完成订单
          </el-button>
          <el-button
              @click="deleteOrder(scope.row.id)"
              type="text"
              size="small">
            删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>


    <el-dialog
        title="厨师接单"
        :visible.sync="dialogVisible">
      <p v-if="cookName!==''" class="mypclass">已选中:{{cookName}}</p>
        <li v-for="item in cookList">
          <ul @click="cookName=item">
            {{ item }}
          </ul>
          <el-button @click="cookName=item" size="small" type="primary">选择</el-button>
        </li>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;submitCookName()" type="primary">确定</el-button>
        </span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false;cookName='';orderID=''" >取 消</el-button>
        </span>
    </el-dialog>

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
    this.fetchCookList()
  },
  methods: {
    async fetchData() {
      const {data: res} = await this.$http.get('order/list');
      this.tableData = res
    },
    async fetchCookList() {
      const {data: res} = await this.$http.get('cookList');
      this.cookList = res
      console.log(this.cookList)
    },
    async submitCookName() {
      console.log(this.orderID)
      console.log(this.cookName)
      let param = new URLSearchParams()
      param.append("orderID", this.orderID)
      param.append("cookName", this.cookName)
      const {data: res} = await this.$http.post('order/setOrderCook',param);
      if (res !== "success") {
        alert("接单失败")
      }else {
        alert("接单成功")
        await this.fetchData()
      }
    },
    async doneOrder(orderid) {
      let param = new URLSearchParams()
      param.append("orderID",orderid)
      const {data: res} = await this.$http.post('order/doneOrder', param);
      if (res !== "success") {
        alert("完成订单失败")
      }else {
        alert("完成订单成功")
        await this.fetchData()
      }
    },
    async deleteOrder(orderID) {
      let param = new URLSearchParams()
      param.append("orderID", orderID)
      const {data: res} = await this.$http.post('order/deleteOrder', param);
      if (res !== "success") {
        alert("删除订单失败")
      } else {
        alert("删除订单成功")
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
