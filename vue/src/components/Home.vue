<template>
  <el-container class="mycontainer">
    <el-header class="myheader">
      <div>
        <span>餐厅后台管理系统</span>
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
            <el-menu-item index="/menu">菜单列表</el-menu-item>
            <el-menu-item index="/addMenu">添加菜品</el-menu-item>
            <el-menu-item index="/uploadImage">上传图片</el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>订单管理</span>
            </template>
            <el-menu-item index="/order">订单列表</el-menu-item>
            <el-menu-item index="/addOrder">添加订单</el-menu-item>
          </el-submenu>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-location"></i>
              <span>用户管理</span>
            </template>
            <el-menu-item index="/user">用户列表</el-menu-item>
            <el-menu-item index="/addUser">添加用户</el-menu-item>
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
