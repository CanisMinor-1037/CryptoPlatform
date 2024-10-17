<template>
  <div class="page1">
    <el-row :gutter="15" id="row1">
      <el-col :span="7">
        <div class="left">
          <census class="leftTop" />
          <br>
          <cards class="leftMiddle" />
          <timesRanking class="leftBottom" />
        </div>
      </el-col>
      <el-col :span="11">
        <dv-border-box-13 class="middle">
          <chinaMap></chinaMap>
        </dv-border-box-13>
        <realTime class="middleBottom" />
      </el-col>
      <el-col :span="6">
        <peerDistribution class="rightTop" />
        <departmentRanking class="rightMiddle" />
        <storage class="rightBottom" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import cards from "../components/index/cards";
import chinaMap from "../components/index/chinaMap";
import census from "../components/index/census";
import timesRanking from "../components/index/timesRanking";
import realTime from "../components/index/realTime";
import storage from "../components/index/storage";
import peerDistribution from"../components/index/peerDistribution"
import departmentRanking from "../components/index/departmentRanking"
export default {
  name: "index",
  components: {
    cards,
    chinaMap,
    census,
    timesRanking,
    realTime,
    storage,
    peerDistribution,
    departmentRanking,
  }
};
</script>

<style lang="less" scoped>
.page1 {
  color: white;
  height: 100%;
  width: 100%;
  padding: 0px 20px 10px 20px;
  overflow: hidden;
}

.el-row {
  margin: 0;
  padding: 0;
}
#row1 {
  height: 80%;
}

.el-col {
  height: 100%;
}

.dv-border-box-13 {
  padding: 15px;
}

.left {
  height: 85%;
  .leftTop {
    width: 100%;
    height: 40%;
  }
  .leftBottom {
    width: 100%;
    height: 40%;
    position: relative;
    top: 5%;
  }
}
.middle {
  height: 80%;
}
.middleBottom {
  height: 20%;
}
.right {
  height: 100%;
  .rightTop {
    height: 50%;
    width: 100%;
  }
  .rightMiddle {
    height: 50%;
    width: 100%;
  }
  .rightBottom {
    height: 50%;
    width: 100%;
  }
}
</style>
