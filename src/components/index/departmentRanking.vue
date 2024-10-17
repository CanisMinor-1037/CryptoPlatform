<template>
  <div id="cards">
    <div class="card-title">机构排行</div>
    <hr>
    <div style="width: 100%; height: 50%;">
      <div ref="applyChart" style="width: 100%; height: 150%; position:relative; bottom:30%;"></div><div class="rank1">申请排行</div>
    </div>
    <div style="width: 100%; height: 50%;">
      <div ref="appliedChart" style="width: 100%; height: 150%; position:relative; bottom:45%;"></div><div class="rank2">被申请排行</div>
    </div>
  </div>
</template>

<script>
import * as echarts from 'echarts'; 
export default {
  name: "",
  data() {
    return {
      order:{
        lastWeek: 1,
        thisyear: 2,
        total: 3,
        consitution: 4
      },
      applyData:{
        "department": ['武汉市交通局', '武汉市环保局', '武汉市数据局', '武汉市农业局', '武汉市统计局'],
        "times": [7, 9, 16, 17, 20]
       },
      appliedData:{
        "department": ['武汉市环保局', '武汉市统计局', '武汉市农业局', '武汉市公安局', '武汉市数据局'] ,
        "times": [5, 7, 9, 13, 25]
      }
    };
  },
  methods: {
    drawChart() {  
      let chartDom = this.$refs.applyChart;  
      let chartDom2 = this.$refs.appliedChart;  
      let myChart = echarts.init(chartDom);
      let myChart2 = echarts.init(chartDom2);
      let option = {  
        color: '#0062ff',
        tooltip: {},  
        textStyle: {
          color: "white"
        },
        xAxis: {  
          type: 'value',

        },  
        yAxis: {
          type: 'category',
          data: this.applyData.department, 
        },  
        series: [{   
          type: 'bar',  
          data: this.applyData.times,
        }] ,
        grid: {   
          left: 105, 
        }
         
      };    
      let option2 = {  
        color: '#0062ff',
        tooltip: {},  
        textStyle: {
          color: "white"
        },
        xAxis: {  
          type: 'value',

        },  
        yAxis: {
          type: 'category',
          data: this.appliedData.department 
        },  
        series: [{   
          type: 'bar',  
          data: this.appliedData.times
        }] ,
        grid: {   
          left: 105, 
        }
      };  
      myChart.setOption(option); 
      myChart2.setOption(option2); 
    },
    getData() {
      this.$http.get("/api/index/departmentRanking").then((res)=>{
        this.applyData = res.data.data[0];
        this.appliedData = res.data.data[1];
        this.drawChart();
      })
    }
  },
  mounted() {
    this.getData();
  },
};
</script>

<style lang="less" scoped>
#cards {
  .rank1{
    position: relative;
    bottom: 80%;
    left:100%;
    font-size: 90%;
  }
  .rank2{
    position: relative;
    bottom: 100%;
    left:100%;
    font-size: 90%;
  }
  margin-top: 28px;
  justify-content: space-between;
  height: 50%;
  width: 80%;
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
