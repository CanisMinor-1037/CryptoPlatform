<template>
  <div>
    <div class="header">
      <div class="ipInfo">IP地址：{{ip}}</div>
      <dv-decoration-8 class="header-left-decoration" />
      <dv-decoration-5 class="header-center-decoration" />
      <div class="center-title">{{department}}</div>
     <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#75deef"
        router
      >
        
        <!--<el-menu-item index="/database">数据库</el-menu-item>-->
        <el-menu-item index="/attribute">属性授权</el-menu-item>
        <!--<el-menu-item index="/history">共享历史</el-menu-item>-->
        <el-menu-item index="/order3">摄像头订单</el-menu-item>
        <el-menu-item index="/order2">数据库订单</el-menu-item>
        <el-menu-item index="/order">普通文件订单</el-menu-item>
        <el-menu-item index="/confirmation3">摄像头确权申请</el-menu-item>
        <el-menu-item index="/confirmation2">数据库确权申请</el-menu-item>
        <el-menu-item index="/confirmation">普通文件确权申请</el-menu-item>
        <!--<el-menu-item index="/index">首页</el-menu-item>-->
      </el-menu>
    </div>
    <div class="page">
      <router-view></router-view>
    </div>
  </div>
</template>
<script>
export default {
  name: "Main",
  data() {
    return {
      activeIndex: "/confirmation",
      ip: "10.10.1.100",
      department: "属性授权中心"
    };
  },
  methods: {},
  mounted() {
    this.activeIndex = this.$route.path;
  },
};
</script>
<style lang="less" scoped>
.header {
  position: relative;
  width: 100%;
  height: 80px;
  display: flex;
  justify-content: space-between;
  flex-shrink: 0;
  .header-center-decoration {
    width: 20%;
    height: 60px;
    margin-top: 30px;
  }
  .header-left-decoration,
  .header-right-decoration {
    padding-top: 20px;
    width: 30%;
    height: 60px;
  }
  .center-title {
    position: absolute;
    font-size: 20px;
    font-weight: bold;
    left: 36%;
    top: 15px;
    transform: translateX(-50%);
    color: white;
  }
}
.el-menu {
  margin-top: 10px;
  margin-right: 20px;
  width: 60%;
  background-color: transparent !important;
  .el-menu-item,
  .el-submenu {
    height: 60px;
    margin: 0px 5px 0px 0px;
    padding: 0px 5px;
    background-color: transparent !important;
    border-right: 10px;
    font-size: 15px;
    float: right;
  }
  .el-submenu__title {
    background-color: transparent !important;
  }
}
.el-menu.el-menu--horizontal {
  border: 0px;
}
.page {
  margin-top:1%;
  height: calc(100vh - 100px);
}
.ipInfo {
  color: white;
  position: absolute;
  left: 15%;
  width:20%;
  top: 20%;
}
</style>
