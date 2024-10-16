<template>
  <div class="fileHistroy">
      <el-row :gutter="5">
        <el-col :span="6">
            <el-input
              v-model="applicant"
              placeholder="请输入申请方"
              clearable
              @clear="search"
            />
        </el-col>
        <el-col :span="6">
            <el-input
              v-model="owner"
              placeholder="请输入权属方"
              clearable
              @clear="search"
            />
        </el-col>
        <el-col :span="6">
            <el-input
              v-model="name"
              placeholder="请输入数据要素名"
              clearable
              @clear="search"
            />
        </el-col>
        <el-col :span="6">
            <el-button type="primary" icon="el-icon-search" size="mini" @click="search">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="reset">重置</el-button>
        </el-col>
      </el-row>
      <br>
    <div class="resultsList">
      <el-table
        :data="resultTableData"
        style="width: 100%"
        height="90%"
        :row-style="rowStyle"
        :header-cell-style="{
          background: '#1981f6',
          color: 'white',
          height: '40px',
          padding: '0',
        }"
      >
      <el-table-column type="index" label="#" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="fabricId" />
      <el-table-column label="申请方" align="center" prop="applicant" />
      <el-table-column label="权属方" align="center" prop="owner" />
      <el-table-column label="数据要素名称" align="center" prop="name" />
      <el-table-column label="属性集" align="center" prop="policy" />
      <el-table-column
          prop="status"
          label="状态"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        <template slot-scope="scope">
            <el-tag
              :class="{
                step1:scope.row.status === 1,
                step2:scope.row.status === 2,
                step3:scope.row.status === 3,
                step4:scope.row.status === 4
              }"
              disable-transitions
              effect="dark"
              size="small"
              >{{
                scope.row.status === 1
                  ? "未处理"
                  : scope.row.status === 2
                  ? "已拒绝"
                  : scope.row.status === 3
                  ? "传输中"
                  : scope.row.status === 4
                  ? "传输完成":""
              }}</el-tag
            >
          </template>
      </el-table-column>
      <el-table-column label="时间" align="center" prop="time" />
      </el-table>
    </div>

  </div>
</template>

<script>
export default {
  name: "fileHistroy",
  data() {
    return {
      dialogVisible: false,
      traceNo: "",
      fileName: "",
      fileDate: "",
      resultTableData: [],
      tableData: [],
      fileInfo: [],
      applicant:"",
      owner:"",
      name:"",
    };
  },
  methods: {
    reset(){
      this.applicant = "";
      this.owner = "";
      this.name = "";
      this.search();
    },
    async getData() {
      const res = await this.$http.get(
        "/api/sharingHistory/allFile"
      );
      this.tableData = res.data.data;
      this.search();
    },
    search() {
      var res;
      if (this.applicant!="") {
        res = this.tableData.filter((data) => {
          // 对象的属性不为空才进行模糊匹配，否则会报错
          return (
            data["applicant"].toString().toLowerCase().indexOf(this.applicant) > -1
          );
        });
      } else {
        res = this.tableData;
      }
      if (this.owner!="") {
        res = res.filter((data) => {
          // 对象的属性不为空才进行模糊匹配，否则会报错
          return (
            data["owner"].toString().toLowerCase().indexOf(this.owner) > -1
          );
        });
      } else {
        res = res
      }
      if (this.name!="") {
        res = res.filter((data) => {
          // 对象的属性不为空才进行模糊匹配，否则会报错
          return (
            data["name"].toString().toLowerCase().indexOf(this.name) > -1
          );
        });
      } else {
        res = res;
      }
      this.resultTableData = res;
    },
    rowStyle(row) {
      if (row.rowIndex % 2 == 0) {
        return { background: "#091b38" };
      } else {
        return { background: "#012751" };
      }
    },
    filterStatus(value, row) {
      return row.status === value;
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style lang="less" scoped>
.step4 {
  background-color: purple;
}
.step3 {
  background-color: blue;
}
.step1 {
  background-color: green;
}
.step2{
  background-color: red;
}
.fileHistroy {
  width: 100%;
  height: 100%;
  .searchParams {
    height: 7%;
    .el-row {
      .el-range-separator {
        color: white;
      }
    }
  }
  .resultsList {
    height: 102%;
    // margin-top: 1%;
    .el-table {
      color: white;
      border: 0px;
    }
    .el-table::before {
      /* 去除下边框 */
      height: 0;
    }
    // .pagination {
    //   height: 10%;
    //   text-align: center;
    // }
  }
}
// 改变input里的字体颜色
/deep/input::-webkit-input-placeholder {
  // color: #17a1e5;
  color: white;
  font-size: 13px;
}
// 改变日期选择器输入框里的字体颜色
/deep/input.el-range-input::-webkit-input-placeholder {
  color: white;
  font-size: 13px;
}

// 改变input框背景颜色
/deep/.el-input__inner {
  background-color: transparent !important;
  border: 1px solid #1296db;
  color: white;
}
// 改变日期选择器输入框背景颜色
/deep/.el-range-input {
  background-color: transparent !important;
  // border: 1px solid #1296db;
  color: white;
}
// 改变日期选择器中的“至”的颜色
/deep/.el-range-separator {
  color: #17a1e5;
  font-size: 15px;
  // background-color: transparent !important;
  // border: 1px solid #1296db;
}
// 去掉表格线
/deep/.el-table__row > td {
  /* 去除表格线 */
  border: none;
}
// 去掉表格上边框，注意不是表头
/deep/.el-table th.is-leaf {
  border: none;
}
/*填充上未被填充的右侧表头空白*/
/deep/th.gutter {
  background-color: #1981f6 !important;
}
// 筛选时空着的地方的背景色
/deep/.el-table__body-wrapper {
  background: #02367a;
}
.el-input{
  position: relative;
  left: 10%;
}
.el-button{
  position: relative;
  left: 50%;
}
</style>
