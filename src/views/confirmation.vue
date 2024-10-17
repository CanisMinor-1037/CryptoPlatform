<template>
  <div class="page2">
    <el-row :gutter="10" class="top">
      <el-col :span="8" class="wrapper1">
        <double-line></double-line>
      </el-col>
      <el-col :span="8" class="wrapper1">
        <upload style="margin-left: 5px"></upload>
      </el-col>
      <el-col :span="8" class="wrapper1">
        <attributes></attributes>
      </el-col>
    </el-row>
    <el-row :gutter="10" class="bottom">
      <el-col :span="13" class="wrapper1">
        <others-files></others-files>
      </el-col>
      <el-col :span="11" class="wrapper1">
        <my-files></my-files>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import doubleLine from "../components/confirmation/doubleLine";
import myFiles from "../components/confirmation/myFiles";
import othersFiles from "../components/confirmation/othersFiles";
import upload from "../components/confirmation/upload";
import attributes from "../components/confirmation/attributes.vue";
export default {
  name: "confirmation",
  components: {
    doubleLine,
    myFiles,
    othersFiles,
    upload,
    attributes,
  },
};
</script>

<style lang="less" scoped>
.page2 {
  color: white;
  height: 100%;
  width: 100%;
  padding: 0px 20px 10px 20px;
  overflow: hidden;
  .top {
    height: 30%;
    width: 100%;
    .wrapper1 {
      box-sizing: border-box;
      height: 100%;
      overflow: hidden;
      box-shadow: 0 0 3px blue;
      display: flex;
      flex-direction: column;
      background-color: rgba(6, 30, 93, 0.5);
      border-top: 2px solid rgba(1, 153, 209, 0.5);
      padding: 0px 10px;
    }
  }
  .bottom {
    margin-top: 10px;
    height: 69%;
    width: 100%;
    .el-col {
      height: 100%;
    }
    .wrapper1 {
      box-sizing: border-box;
      height: 100%;
      overflow: hidden;
      box-shadow: 0 0 3px blue;
      display: flex;
      flex-direction: column;
      background-color: rgba(6, 30, 93, 0.5);
      border-top: 2px solid rgba(1, 153, 209, 0.5);
      padding: 0px 10px;
    }
  }
}
</style>
