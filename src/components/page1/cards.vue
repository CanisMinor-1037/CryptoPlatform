<template>
  <div id="cards">
    <div class="card-title">数据要素资产分类</div>
    <div class="card-item">
      <div class="card-header">
      </div>
      <dv-active-ring-chart :config="config1" />
      <div class="card-footer">区域</div>
    </div>
    <div class="card-item">
      <div class="card-header">
      </div>
      <dv-active-ring-chart :config="config2" />
      <div class="card-footer">应用领域</div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Cards",
  data() {
    return {
      config1: {},
      config2: {},
    };
  },
  methods: {
    getData() {
      this.$http.get("/api/index/applicationInfo").then((res) => {
        this.config1 = {
          radius: "75%",
          activeRadius: "80%",
          lineWidth: 20,
          digitalFlopStyle: {
            fontSize: 16,
            fill: "#fff",
          },
          // 显示原始值
          showOriginValue: true,
          data: res.data.data[0],
        };
        this.config2 = {
          radius: "75%",
          activeRadius: "80%",
          lineWidth: 20,
          digitalFlopStyle: {
            fontSize: 16,
            fill: "#fff",
          },
          // 显示原始值
          showOriginValue: true,
          data: res.data.data[1],
        };
      });
    },
  },
  mounted() {
    this.getData();
    // window.addEventListener("resize", () => {
    //   this.getData();
    // });
  },
};
</script>

<style lang="less" scoped>
#cards {
  margin-top: 10px;
  justify-content: space-between;
  height: 30%;
  width: 80%;
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
    width: 50%;
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
