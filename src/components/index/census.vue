<template>
  <div id="cards">
    <div class="card-title">数据要素流通统计</div>
      <div class="orderInfo">
      <div class="orderInfoItem">
        <div class="orderValue">{{order.lastWeek}}</div>
        <div class="orderProperty">上周订单</div>
      </div>
      <div class="orderInfoItem">
        <div class="orderValue">{{order.thisYear}}</div>
        <div class="orderProperty">本年订单</div>
      </div>
      <div class="orderInfoItem">
        <div class="orderValue">{{order.total}}</div>
        <div class="orderProperty">总订单</div>
      </div>
      <div class="orderInfoItem">
        <div class="orderValue">{{order.department}}</div>
        <div class="orderProperty">参与机构</div>
      </div>
    </div>
    <hr>
  <div>订单量</div>
    <div ref="chart" style="width: 90%; height: 80%; position: relative; bottom: 20%;left: 10%"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts'; 
export default {
  name: "Census",
  data() {
    return {
      order:{
        lastWeek: 0,
        thisYear: 0,
        total: 0,
        department: 0
      },
      barData: []
    };
  },
  methods: {
    drawChart() {  
      let chartDom = this.$refs.chart;  
      let myChart = echarts.init(chartDom);  
      let option = {  
        textStyle: {
          color: "white"
        },
        color: '#0062ff',
        tooltip: {},  
        xAxis: {  
          data: ['1月', '2月', '3月', '4月', '5月', '6月','7月', '8月', '9月', '10月', '11月', '12月'] , 
        },  
        yAxis: {},  
        series: [{   
          type: 'bar',  
          data: this.barData
        }] ,
      };    
      myChart.setOption(option); 
    },
    getData() {
      this.$http.get("/api/index/orderInfo").then((res) => {
        this.order = res.data.data.order;
        this.barData = res.data.data.barData;
        this.drawChart();
      });
    },
  },
  mounted() {
    this.getData();
  },
};
</script>
<style lang="less" scoped>
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 60%;
  width: 95%;
  .card-title {
      font-size: 18px;
      font-weight: bold;
      padding-left: 10px;
      background: linear-gradient(to right, rgb(0, 100, 255),rgb(0, 0, 0)) ;
      border-left: 2px solid rgb(77,255,255);
    }
  .orderInfo {
    .orderInfoItem {
      display: inline-block;
      width: 50%;
      height: 50%;
      .orderValue {
        color: yellow;
        font-size: 25px;
        text-align: center;
      }
      .orderProperty{
        font-size: 10px;
        text-align: center;
      }
    }
  }
  .card-item {
    background-color: rgba(6, 30, 93, 0.5);
    border-top: 2px solid rgba(1, 153, 209, 0.5);
    width: 50%;
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
