<template>
  <div class="chinaMap"></div>
</template>
<script>
import "echarts/map/js/china.js";
export default {
  name: "",
  data() {
    return {
      departmentOrderCount:[],
      recentDepartmentOrder:[]
    };
  },
  methods: {
    async setMap() {
      const res1 = await this.$http.get("/api/index/departmentOrderCount");
      this.departmentOrderCount = res1.data.data;
      const res2 = await this.$http.get("/api/index/recentDepartmentOrderExists");
      this.recentDepartmentOrder = res2.data.data;
      var data = [
        { name: "鄂尔多斯", value: 0, online: true, peerName:"org2", departmentName:"公安局"},
        { name: "广州", value: 0, online: true, peerName:"orderer", departmentName:"orderer" },
        { name: "贵阳", value: 0, online: true, peerName:"org3", departmentName:"医院" },
        { name: "武汉", value: 0, online: true, peerName:"org1", departmentName:"属性授权中心" },
      ];
      for(let i = 0; i < this.departmentOrderCount.length; i++){
        for(let j = 0 ; j < data.length; j++){
          if(this.departmentOrderCount[i].name == data[j].departmentName){
            data[j].value = this.departmentOrderCount[i].value;
          }
        }
      }
      var geoCoordMap = {
        鄂尔多斯: [109.781327, 39.608266],
        广州: [113.23, 23.16],
        贵阳: [106.71, 26.57],
        武汉: [114.31, 30.52]
      };
      var BJData = [];
      for(let i = 0; i < this.recentDepartmentOrder.length; i++){
        BJData.push([]);
        for(let j = 0; j < data.length; j++){
          if(this.recentDepartmentOrder[i].owner == data[j].departmentName){
            BJData[i].push({ name : data[j].name});
          }
        }
        for(let j = 0; j < data.length; j++){
          if(this.recentDepartmentOrder[i].applicant == data[j].departmentName){
            BJData[i].push({ name : data[j].name});
          }
        }
      }
      var convertData = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var geoCoord = geoCoordMap[data[i].name];
          if (geoCoord) {
            res.push({
              name: data[i].name,
              value: geoCoord.concat(data[i].value).concat(data[i].online).concat(data[i].peerName).concat(data[i].departmentName),
            });
          }
        }
        return res;
      };
      var convertData1 = function (data) {
        var res = [];
        for (var i = 0; i < data.length; i++) {
          var dataItem = data[i];
          var fromCoord = geoCoordMap[dataItem[0].name];
          var toCoord = geoCoordMap[dataItem[1].name];
          if (fromCoord && toCoord) {
            res.push({
              fromName: dataItem[0].name,
              toName: dataItem[1].name,
              coords: [fromCoord, toCoord],
            });
          }
        }
        return res;
      };
      let option = {
        title: [
          {
            text: "节点信息",
            top: "2%",
            left: "2%",
            textStyle: {
              color: "#fff",
              fontSize: 20,
              fontWeight: "bold",
            },
          },
        ],
        tooltip: {
          trigger: "item",
          position: "right",
          formatter: function (params) {
            const status = params.data.value[3] === true ? "在线" : "离线";
            return (
              params.marker +
              status +
              "&nbsp;" +
              params.seriesName +
              "</br>" + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + params.data.value[5] + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;" + +
              params.data.value[2]
            );
          },
          backgroundColor: "#11367a",
          textStyle: {
            color: "#6dd0e3",
            fontSize: 10,
          },
        },
        geo: {
          map: "china",
          label: {
            emphasis: {
              show: false,
            },
          },
          roam: false,
          itemStyle: {
            normal: {
              areaColor: "#2043AA",
              borderColor: "#111",
            },
            emphasis: {
              areaColor: "#2a333d",
            },
          },
        },
        series: [
          {
            type: "lines",
            coordinateSystem: "geo",
            lineStyle: {
              normal: {
                show: true,
                width: 1, 
                opacity: 1, 
                curveness: 0.3, 
                color: "#fcdd6e", 
              },
              color: "#fcdd6e",
            },
            effect: {
              show: true,
              period: 6,
              trailLength: 0,
              symbol: "arrow",
              symbolSize: 8,
              color: "#fcdd6e",
            },
            data: convertData1(BJData),
          },
          {
            name: "数据要素流通次数",
            type: "effectScatter",
            coordinateSystem: "geo",
            data: convertData(
              data
                .sort(function (a, b) {
                  return b.value - a.value;
                })
                .slice(0, 10)
            ),
            symbolSize: function (val) {
              return 10;
            },
            showEffectOn: "render",
            rippleEffect: {
              period: 4,
              brushType: "stroke", 
              scale: 4,
            },
            hoverAnimation: true,
            label: {
              normal: {
                formatter: "{b}",
                position: "right",
                show: false,
              },
            },
            itemStyle: {
              normal: {
                color: function (params) {
                  if (params.data.value[3] === true) {
                    return "skyblue";
                  } else {
                    return "red";
                  }
                },
                shadowBlur: 10,
              },
            },
          },
        ],
      };
      let myChart = this.$echarts.init(this.$el);
      myChart.clear();
      myChart.resize();
      myChart.setOption(option);
    },
  },
  mounted() {
    this.setMap();
    window.addEventListener("resize", () => {
      this.setMap();
    });
  },
};
</script>
<style lang="less" scoped>
.chinaMap {
  height: 100%;
}
</style>
