<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style lang="less" scoped>
#app {
  overflow: hidden;
  background-color: black;
  // widows: 100%;
  height: 100vh;
  font-family: Arial, Helvetica, sans-serif;
  margin: 0;
  padding: 0;
  /*  背景图定位 / 背景图尺寸  cover 完全铺满容器  contain 完整显示在容器内 */
  background: url(@/assets/images/bg.jpg) no-repeat #000;
  background-size: cover;
  /* 行高是字体1.15倍 */
  line-height: 1.5;
}
</style>
