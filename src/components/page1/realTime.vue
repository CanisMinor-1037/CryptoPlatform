<template>
  <div id="cards">
    <!-- 1. 每个部门申请数量占总数量的比重 -->
    <!-- 2. 每个部门申请属性占总属性的比重 -->
    <div class="card-title left-title">数据要素</div>
    <div class="card-title right-title">实时流通</div>
    <dv-border-box-8 :dur="6">
      <dv-scroll-board :config="config1" style="width:100%;height:100%" />
    </dv-border-box-8>

  </div>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      config1:{
        
      },
      config2: {},
    };
  },
  methods: {
    getRollData(){
      this.$http.get("/api/index/realTime").then((res) => {
      this.config1={
        rowNum: 4,
        header: ["订单号","申请机构","被申请机构","数据要素","发起时间","状态"],
        data: res.data.data,
        //align: ['center'],
        headerHeight: 50,
      }
      }
    )
    },
  },
  mounted() {
    //this.drawChart();
    this.getRollData();
    //this.getData();
    // window.addEventListener("resize", () => {
    //   this.getData();
    // });
  },
};
</script>

<style lang="less" scoped>
/deep/ .dv-scroll-board .header {
      background-color: rgb(0,15,67) !important;
      
    }
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 35%;
  width: 100%;
  .left-title {
    display: inline-block;
    font-size: 18px;
    font-weight: bold;
    width: 50%;
    background: linear-gradient(to right,rgb(0, 0, 0), rgb(0, 100, 255)) ;
    text-align: right;
    color: rgb(74,255,255);
    border-left: 2px solid rgb(77,255,255);
    }
  .right-title {
    display: inline-block;
    font-size: 18px;
    font-weight: bold;
    width: 50%;
    background: linear-gradient(to right, rgb(0, 100, 255),rgb(0, 0, 0)) ;
    color: rgb(74,255,255);
  }
  .card-item {
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, 0.5);
    width: 100%;
    height: 100%;
    display: inline-flex;
    flex-direction: column;

    .dv-active-ring-chart {
      width: 100%;
      height: 100%;
      // position:absolute;
      // margin: auto;
    }
  }
  .card-item:last-child {
    margin-right: 0px;
  }

  .card-header {
    display: flex;
    height: 20%;
    align-items: center;
    justify-content: space-between;
    
    .card-header-left {
      font-size: 18px;
      font-weight: bold;
      padding-left: 10px;
      background: linear-gradient(to right, rgb(0, 98, 255),rgb(0,0,0)) ;
    }

    .card-header-right {
      padding-right: 10px;
      font-size: 30px;
      color: #03d3ec;
    }
  }

  // .ring-charts {
  //   height: 55%;
  // }

  .card-footer {
    height: 25%;
    display: flex;
    align-items: center;
    justify-content: space-around;
  }

  .card-footer-item {
    padding: 5px 10px 0px 10px;
    box-sizing: border-box;
    width: 40%;
    background-color: rgba(6, 30, 93, 0.7);
    border-radius: 3px;
  }
}
</style>
