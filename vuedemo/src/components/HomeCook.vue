<template>
  <el-container class="mycontainer">
    <el-header class="myheader">
      <div>
        <span>餐厅后厨页面</span>
      </div>
      <el-button type="info" @click="logout()">退出登陆</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px" class="myaside">
        <el-menu
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
            router>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>菜单管理</span>
            </template>
            <el-menu-item index="/menuCook">菜单列表</el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/orderCook">订单列表</el-menu-item>
            <el-menu-item index="/orderCookGet">接单</el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>个人信息</span>
            </template>
            <el-menu-item index="/cookUpdate">修改密码</el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main class="mymain">
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  data(){
    return{
      username:window.sessionStorage.getItem('username')
    }
  },
  methods: {
    logout() {
      alert("登出成功")
      window.sessionStorage.clear()
      this.$router.push('/login')
    }
  }
}
</script>

<style lang="less" scoped>
.myheader {
  background-color: #373d41;
  display: flex;
  justify-content: space-between;
  align-items: center;
  color: #FFF;
  font-size: 20px;

  > div {
    display: flex;
    align-items: center;
  }
}

.myaside {
  background-color: #333744;
}

.mymain {
  background-color: #EAEDF1;
}

.mycontainer {
  height: 100%;
}
</style>
