<template>
  <div id="cards">
    <div class="card-title">区块链数据要素可信存储</div>
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
      config:{}
    };
  },
  methods: {
    getRollData(){
      this.$http.get("/api/index/storage").then((res) => {
      this.config = {
        rowNum: 4,
        header: ["描述","所属机构","确权时间"],
        data: res.data.data
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
      background-color: rgb(0,30,132) !important;
      
    }
/deep/ .header-item:nth-child(1){
  width: 35% !important;
}
/deep/ .header-item:nth-child(2){
  width: 30% !important;
}
/deep/ .header-item:nth-child(3){
  width: 40% !important;
}
/deep/ .ceil:nth-child(1){
  width: 35% !important;
}
/deep/ .ceil:nth-child(2){
  width: 30% !important;
}
/deep/ .ceil:nth-child(3){
  width: 40% !important;
}
/deep/ .ceil{
  font-size:75% !important;
}
#cards {
  margin-top: 0px;
  justify-content: space-between;
  height: 35%;
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
