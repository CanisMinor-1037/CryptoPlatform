<template>
  <div id="cards">
    <div class="card-title">数据要素流通次数排行</div>
    <dv-border-box-8 :dur="6" style="padding: 5px 10px 5px 10px">
      <dv-scroll-ranking-board :config="config"/>
    </dv-border-box-8>
  </div>
</template>
<script>
import * as echarts from 'echarts'; 
export default {
  name: "",
  data() {
    return {
      config:{},
    };
  },
  methods: {
    getRollData(){
      this.$http.get("/api/index/timesRanking").then((res) => {
      this.config={
        oddRowBGC:"transparent",
        evenRowBGC:"transparent",
        width:"50%",
        rowNum:4,
        data: res.data.data
      }
      }
    )
    },
    drawChart() {  
      let chartDom = this.$refs.chart;  
      let myChart = echarts.init(chartDom);  
      let option = {  
        color: '#0062ff',
        title: {  
          text: '订单量' 
        },  
        tooltip: {},  
        xAxis: {
    type: 'value',
    boundaryGap: [0, 0.01]
  },
  yAxis: {
    type: 'category',
    data: ['Brazil', 'Indonesia', 'USA', 'India', 'China', 'World']
  },
  series: [
    {
      name: '2011',
      type: 'bar',
      data: [18203, 23489, 29034, 104970, 131744, 630230]
    },
    {
      name: '2012',
      type: 'bar',
      data: [19325, 23438, 31000, 121594, 134141, 681807]
    }
  ] 
      };    
      myChart.setOption(option); 
    },
  },
  mounted() {
    this.getRollData();
  },
};
</script>
<style lang="less" scoped>
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 30%;
  width: 95%;
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
