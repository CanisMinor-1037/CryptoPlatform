<template>
  <div id="cards">
    <div class="card-title">数据要素实时流通</div>
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
      config:{},
    };
  },
  methods: {
    getRollData(){
      this.$http.get("/api/index/realTime").then((res) => {
      this.config={
        rowNum: 4,
        header: ["订单号","申请机构","被申请机构","数据要素","发起时间","状态"],
        data: res.data.data.map((item)=>{
          item.fabricId = item.fabricId.substring(14);
          return item;
        })
      }
      }
    )
    },
  },
  mounted() {
    this.getRollData();
  },
};
</script>

<style lang="less" scoped>
/deep/ .dv-scroll-board .header {
      background-color: rgb(0,15,67) !important;
      
    }
/deep/ .header-item:nth-child(4){
  width: 25% !important;
}
/deep/ .header-item:nth-child(5){
  width: 25% !important;
}
/deep/ .header-item{
  width: 20% !important;
  text-align: center;
}
/deep/ .ceil:nth-child(4){
  width: 25% !important;
}
/deep/ .ceil:nth-child(5){
  width: 25% !important;
}
/deep/ .ceil{
  width: 20% !important;
  font-size:75% !important;
  text-align: center;
}
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 35%;
  width: 100%;
  .card-title {
    display: inline-block;
    font-size: 18px;
    font-weight: bold;
    width: 100%;
    background: linear-gradient(to right,rgb(0, 0, 0), rgb(0, 100, 255),rgb(0, 0, 0)) ;
    text-align: center;
    color: rgb(74,255,255);
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
