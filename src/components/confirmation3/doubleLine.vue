<template>
  <div class="doubleLine"></div>
</template>

<script>
export default {
  name: "",
  data() {
    return {
    };
  },
  methods: {
    setChart() {
      this.$http
        .get("/api/confirmation/videoApplyAndApplied")
        .then((res) => {
          let option = {
            title: {
            text: "申请与被申请次数",
            textStyle: { color: "#ffffff" },
            top: "3%",
            padding: 0,
          },
        tooltip: {
          trigger: "axis",
        },
        legend: {
          right: "5%",
          top: "5%",
          itemWidth: 7,
          itemHeight: 7,
          textStyle: {
            color: "#5CB1C1",
            fontSize: 10,
          },
        },
        grid: {
          top: "20%",
          left: "10%",
          right: "10%",
          bottom: "10%",
          containLabel: false,
        },

        xAxis: {
          type: "category",
          boundaryGap: false,
          axisLine: {
            symbol: ["none", "arrow"],
            symbolSize: [6, 6],
            symbolOffset: [0, 10],
            lineStyle: {
              color: "#61B9C8",
            },
          },
          axisTick: { show: false },
          axisLabel: {
            color: "#61B9C8",
            fontSize: 9,
          },
          data: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
        },
        yAxis: [
          {
            type: "value",
            splitNumber: 7,
            axisLine: {
              symbol: ["none", "arrow"],
              symbolSize: [6, 6],
              lineStyle: {
                color: "#61B9C8",
              },
            },
            axisLabel: {
              color: "#61B9C8",
              showMaxLabel: false,
              fontSize: 10,
            },
            splitLine: {
              show: false,
            },
          },
          {
            type: "value",
            splitNumber: 7,
            axisLine: {
              symbol: ["none", "arrow"],
              symbolSize: [6, 6],
              lineStyle: {
                color: "#61B9C8",
              },
            },
            axisLabel: {
              color: "#61B9C8",
              showMaxLabel: false,
              fontSize: 10,
            },
            splitLine: {
              show: false,
            },
          },
        ],
        series: [
          {
            name: "申请文件次数",
            yAxisIndex: 0,
            type: "line",
            smooth: true,
            symbol: "none",
            lineStyle: {
              color: "#F39800",
            },
            itemStyle: {
              color: "#F39800",
            },
            data: res.data.data[0],
          },
          {
            name: "被申请文件次数",
            yAxisIndex: 1,
            type: "line",
            smooth: true,
            symbol: "none",
            lineStyle: {
              color: "#BF232A",
            },
            itemStyle: {
              color: "#BF232A",
            },
            data: res.data.data[1],
          },
        ],
      };
      let myChart = this.$echarts.init(this.$el);

      myChart.clear();
      myChart.resize();
      myChart.setOption(option);
        });
      
    },
  },
  mounted() {
    this.setChart();
    window.addEventListener("resize", () => {
      this.setChart();
    });
  },
};
</script>

<style lang="less" scoped>
.doubleLine {
  height: 100%;
  width: 100%;
}
</style>
