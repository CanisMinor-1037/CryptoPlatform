<template>
  <div class="page1">
    <!--<el-row id="row1" style="display:none;"> <digitalFlop /> </el-row>-->
    <el-row :gutter="5" id="row2">
      <el-col :span="7">
        <div class="left">
          <census class="leftTop" />
          <cards class="leftMiddle" />
          <timesRanking class="leftBottom" />
        </div>
      </el-col>
      <el-col :span="1"></el-col>
      <!-- 中列 -->
      <el-col :span="9">
        <dv-border-box-13 class="middle">
          <!--<wuhan-map></wuhan-map>-->
          <china-map></china-map>
        </dv-border-box-13>
        <realTime class="middleBottom" />
      </el-col>
      <el-col :span="1"></el-col>
      <el-col :span="6">
        <peerDistribution class="rightTop" />
        <departmentRanking class="rightMiddle" />
        <storage class="rightBottom" />
      </el-col>
    </el-row>
  </div>
</template>

<script>
import cards from "../components/page1/cards";
import chinaMap from "../components/page1/chinaMap";
import census from "../components/page1/census";
import timesRanking from "../components/page1/timesRanking";
import realTime from "../components/page1/realTime";
import storage from "../components/page1/storage";
import peerDistribution from"../components/page1/peerDistribution"
import departmentRanking from "../components/page1/departmentRanking"
export default {
  name: "page1",
  components: {
    cards,
    chinaMap,
    census,
    timesRanking,
    realTime,
    storage,
    peerDistribution,
    departmentRanking
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
  height: 20%;
}
#row2 {
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
