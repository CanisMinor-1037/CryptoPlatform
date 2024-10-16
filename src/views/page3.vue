<template>
  <div class="page3" style="color: white">
    <el-row :gutter="5">
      <el-col :span="12">
        <dv-border-box-12 style="padding: 10px"
          ><myApplicationHistroy></myApplicationHistroy></dv-border-box-12>
      </el-col>
      <el-col :span="12">
        <dv-border-box-12 style="padding: 10px"
          ><othersApplicationHistroy></othersApplicationHistroy></dv-border-box-12>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import myApplicationHistroy from "../components/page3/myApplicationHistroy";
import othersApplicationHistroy from "../components/page3/othersApplicationHistroy";
export default {
  name: "page3",
  components: {
    myApplicationHistroy,
    othersApplicationHistroy,
  },
};
</script>

<style lang="less" scoped>
.page3 {
  height: 100%;
  width: 100%;
  .el-row {
    height: 100%;
    width: 100%;
    .el-col {
      height: 100%;
    }
  }
}
</style>
