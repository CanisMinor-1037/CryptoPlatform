<template>
  <div class="page4">
    <el-row :gutter="10">
      <el-col :span="24">
        <div class="left">   
            <dv-border-box-11 title="数据要素共享信息">
              <file-histroy></file-histroy>
            </dv-border-box-11>
        </div>
      </el-col>
    </el-row>
    
  </div>
</template>

<script>
import fileHistroy from "../components/page4/fileHistroy";
export default {
  name: "page4",
  components: {
    fileHistroy,
  },
};
</script>

<style lang="less" scoped>
.page4 {
  color: white;
  height: 100%;
  width: 100%;
  padding: 0px 20px 10px 20px;
  overflow: hidden;
  .el-row {
    height: 100%;
    .el-col {
      height: 100%;
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
      .left, .right {
        height: 100%;
        width: 100%;
        .dv-border-box-11{
          padding: 60px 15px 20px 15px;
        }
      }
    }
  }
}
</style>
