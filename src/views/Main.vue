<template>
  <div>
    <div class="header">
      <dv-decoration-8 class="header-left-decoration" />
      <dv-decoration-5 class="header-center-decoration" />
      <div class="center-title">数据要素平台</div>

     <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#75deef"
        router
      >
        <el-menu-item index="/page6">摄像头</el-menu-item> 
        <el-menu-item index="/page5">属性授权</el-menu-item> 
        <el-menu-item index="/page4">共享历史</el-menu-item>
        <el-menu-item index="/page3">订单</el-menu-item>
        <el-menu-item index="/page2">确权申请</el-menu-item>
        <el-menu-item index="/page1">首页</el-menu-item>
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
      activeIndex: "/page1",
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
    font-size: 30px;
    font-weight: bold;
    left: 50%;
    top: 15px;
    transform: translateX(-50%);
    color: white;
  }
}

.el-menu {
  margin-top: 10px;
  margin-right: 20px;
  width: 30%;
  background-color: transparent !important;
  .el-menu-item,
  .el-submenu {
    height: 60px;
    margin: 0px 5px 0px 0px;
    padding: 0px 5px;
    background-color: transparent !important;
    border-right: 10px;
    font-size: 15px;
    // 会让元素靠右对齐，但是会变成逆序
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
</style>
