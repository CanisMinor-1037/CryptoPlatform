<template>
  <div class="page5">
    <el-row :gutter="10">
      <el-col :span="12">
        <div class="left">
          <div class="leftTop">
            <dv-border-box-3 style="padding:30px">
              <card></card>
            </dv-border-box-3>
          </div>
          <div class="leftBottom">
            <dv-border-box-13 style="padding: 20px">
              <department-roles></department-roles>
            </dv-border-box-13>
          </div>
        </div>
      </el-col>
      <el-col :span="12">
        <div class="right">
          <div class="rightTop">
            <dv-border-box-8 :dur="6" style="padding: 5px 10px 5px 10px">
              <todo />
            </dv-border-box-8>
          </div>
          <div class="rightBottom">
            <dv-border-box-8 :dur="6" reverse style="padding: 5px 10px 5px 10px">
              <done />
            </dv-border-box-8>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import card from "../components/attribute/card";
import departmentRoles from "../components/attribute/departmentRoles";
import todo from "../components/attribute/todo";
import done from "../components/attribute/done";
export default {
  name: "attribute",
  components: {
    card,
    departmentRoles,
    todo,
    done,
  },
};
</script>

<style lang="less" scoped>
.page5 {
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
      .left,
      .right {
        height: 100%;
        width: 100%;
        .dv-border-box-11 {
          padding: 60px 15px 20px 15px;
        }
        .leftTop {
          height: 30%;
        }
        .leftBottom {
          height: 70%;
        }
        .rightTop,
        .rightBottom {
          height: 50%;
          width: 100%;
        }
      }
    }
  }
}
</style>
