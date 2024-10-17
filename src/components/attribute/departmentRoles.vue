<template>
  <div class="userRoles">
    <!-- 获取用户列表：无——凯麟 -->
    <!-- <div style="font-weight: bold">用户列表</div> -->
    <div class="searchParams">
      <el-row :gutter="5">
        <el-col :span="12" style="font-weight: bold; font-size: 1.5rem"
          >部门列表</el-col
        >
        <el-col :span="8">
          <el-input
            v-model="userName"
            size="small"
            placeholder="部门名"
            clearable
            style="float: right"
            @clear="search"
          ></el-input>
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
    <div class="resultsList">
      <el-table
        :data="resultTableData"
        style="width: 100%"
        @row-click="showUserInfo"
        height="90%"
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
        <!--<el-table-column
          prop="username"
          label="用户名"
          header-align="center"
          align="center"
          width="120"
          show-overflow-tooltip
        >
        </el-table-column>-->
        <el-table-column
          prop="name"
          label="部门名"
          header-align="center"
          align="center"
          width="200"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="attribute"
          label="拥有属性"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
      </el-table>
      <!-- <div class="pagination">
        <el-pagination layout="prev, pager, next" :total="50"> </el-pagination>
      </div> -->
    </div>

    <!-- <el-dialog title="用户信息" :visible.sync="dialogVisible" width="30%">
      {{ userInfo }}
    </el-dialog> -->
  </div>
</template>

<script>
export default {
  name: "userRoles",
  data() {
    return {
      dialogVisible: false,
      userName: "",
      resultTableData: [],
      tableData: [],
      userInfo: "",
    };
  },
  methods: {
    async getData() {
      const res = await this.$http.get("/api/attributeAuthority/departmentList");
      this.tableData = res.data.data;
      this.search();
    },
    search() {
      var res;
      if (this.userName) {
        res = this.tableData.filter((data) => {
          return (
            data["name"].toString().toLowerCase().indexOf(this.userName) >
            -1
          );
        });
      } else {
        res = this.tableData;
      }
      this.resultTableData = res;
    },
    showUserInfo(row) {
      this.dialogVisible = true;
      this.userInfo = row;
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
      return row.status === value;
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style lang="less" scoped>
.userRoles {
  width: 100%;
  height: 100%;
  .searchParams {
    height: 7%;
    // margin-top: 1%;
    .el-row {
      .el-range-separator {
        color: white;
      }
    }
  }
  .resultsList {
    height: 102%;
    // margin-top: 1.5%;
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
