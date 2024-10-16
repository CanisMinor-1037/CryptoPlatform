<template>
  <div class="done">
    <!-- 获取列表：无——凯麟 -->
    <div class="searchParams" style="height: 10%">
      <el-row :gutter="5">
        <el-col
          :span="5"
          style="font-weight: bold; margin: auto; font-size: 1.5rem"
        >
          已处理申请
        </el-col>
        <el-col :span="6">
          <el-input
            v-model="appName"
            size="small"
            placeholder="部门名"
            clearable
            @clear="search"
          ></el-input>
        </el-col>
        <el-col :span="9">
          <el-date-picker
            v-model="appDate"
            type="daterange"
            size="small"
            style="width: 100%"
            range-separator="至"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
            format="yyyy-MM-dd"
            value-format="yyyy-MM-dd"
            @change="search"
          >
          </el-date-picker>
        </el-col>
        <el-col :span="4">
          <el-button
            type="primary"
            icon="el-icon-search"
            size="small"
            style="float: right"
            @click="search"
            >搜索</el-button
          >
        </el-col>
      </el-row>
    </div>
    <div class="resultsList" style="height: 90%">
      <el-table
        :data="resultTableData"
        style="width: 100%"
        @row-click="showDoneAppInfo"
        height="95%"
        :row-style="rowStyle"
        :header-cell-style="{
          background: '#1981f6',
          color: 'white',
          height: '40px',
          padding: '0',
        }"
      >
        <!-- row-style设置每一行的样式。header-cell-style设置表头行的样式 -->
        <el-table-column
          type="index"
          width="50"
          label="#"
          header-align="center"
          align="center"
        ></el-table-column>
        <el-table-column
          label="申请日期"
          header-align="center"
          align="center"
          width="120"
          show-overflow-tooltip
        >
        <template slot-scope="scope">
          {{ scope.row.time.split('T')[0] }}
        </template>
        </el-table-column>
        <el-table-column
          prop="applicant"
          label="部门名"
          header-align="center"
          align="center"
          width="120"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="attribute"
          label="申请属性"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="status"
          label="处理结果"
          header-align="center"
          align="center"
          width="100"
          :filters="[
            { text: '拒绝', value: 2 },
            { text: '通过', value: 1 },
          ]"
          :filter-method="filterStatus"
          filter-placement="bottom-end"
        >
          <template slot-scope="scope">
            <el-tag
              :type="scope.row.status === 2 ? 'danger' : 'success'"
              disable-transitions
              effect="dark"
              size="small"
              >{{ scope.row.status === 2 ? "拒绝" : "通过" }}</el-tag
            >
          </template>
        </el-table-column>
      </el-table>
      <!-- <div class="pagination">
        <el-pagination layout="prev, pager, next" :total="50"> </el-pagination>
      </div> -->
    </div>

    <!-- <el-dialog title="申请信息" :visible.sync="dialogVisible" width="30%">
      {{ appInfo }}
    </el-dialog> -->
  </div>
</template>

<script>
export default {
  name: "done",
  data() {
    return {
      dialogVisible: false,
      appName: "",
      appDate: "",
      resultTableData: [],
      tableData: [],
      appInfo: "",
    };
  },
  methods: {
    async getData() {
      const res = await this.$http.get(
        "/api/attributeAuthority/applicationProcessed"
      );
      // console.log(res);
      this.tableData = res.data.data;
      this.search();
    },
    search() {
      var res;
      if (this.appName) {
        res = this.tableData.filter((data) => {
          // 对象的属性不为空才进行模糊匹配，否则会报错
          return (
            data["applicant"].toString().toLowerCase().indexOf(this.appName) > -1
          );
        });
      } else {
        res = this.tableData;
      }

      if (this.appDate) {
        res = res.filter((data) => {
          let curDate = (data["time"].split("T")[0]).split("-");
          let beginDate = this.appDate[0].split("-");
          let endDate = this.appDate[1].split("-");
          return (
            parseInt(curDate[0]) >= parseInt(beginDate[0]) &&
            parseInt(curDate[0]) <= parseInt(endDate[0]) &&
            parseInt(curDate[1]) >= parseInt(beginDate[1]) &&
            parseInt(curDate[1]) <= parseInt(endDate[1]) &&
            parseInt(curDate[2]) >= parseInt(beginDate[2]) &&
            parseInt(curDate[2]) <= parseInt(endDate[2])
          );
        });
      } else {
        res = res; // 如果搜索框没有值，就正常展示表格的所有数据
      }
      this.resultTableData = res;
    },
    showDoneAppInfo(row) {
      this.dialogVisible = true;
      this.appInfo = row;
    },
    rowStyle(row) {
      // var style = {};
      if (row.rowIndex % 2 == 0) {
        return { background: "#091b38" };
      } else {
        return { background: "#012751" };
      }
    },
    filterStatus(value, row) {
      // console.log(row);
      return row.status === value;
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style lang="less" scoped>
.done {
  width: 100%;
  height: 100%;
  .searchParams {
    // height: 6%;
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
</style>
