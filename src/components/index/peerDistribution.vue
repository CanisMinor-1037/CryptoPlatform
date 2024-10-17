<template>
  <div id="cards">
    <div class="card-title">网络节点分布</div>
    <dv-border-box-8 :dur="6">
      <dv-scroll-board :config="config" style="width:100%;height:100%" />
    </dv-border-box-8>

  </div>
</template>

<script>
export default {
  name: "",
  data() {
    return {
      config:{
        
      },
    };
  },
  methods: {
    getRollData(){
      this.config={
        rowNum: 3,
        header: ["节点名","节点类型","运行状态"],
        data: [["排序组织(orderer)","排序","正常"],["属性授权中心(org1)","对等","正常"],["公安局(org2)","对等","正常"],["医院(org3)","对等","正常"]],
        headerHeight: 30,
      }
    },
  },
  mounted() {
    this.getRollData();
  },
};
</script>

<style lang="less" scoped>
/deep/ .dv-scroll-board .header {
      background-color: rgb(0,30,132) !important;
    }
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 25%;
  width: 100%;
  .card-title {
      font-size: 18px;
      font-weight: bold;
      padding-left: 10px;
      background: linear-gradient(to right, rgb(0, 100, 255),rgb(0, 0, 0)) ;
      border-left: 2px solid rgb(77,255,255);
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
