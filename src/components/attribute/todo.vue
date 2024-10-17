<template>
  <div class="todo">
    <div style="font-weight: bold; height: 10%; font-size: 1.5rem">
      未处理申请
    </div>
    <div class="resultsList">
      <el-table
        :data="tableData"
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
          label="操作"
          header-align="center"
          align="center"
          width="150"
        >
          <template slot-scope="scope">
            <el-button
              type="primary"
              @click.native.stop="handleClick(scope.row, true)"
              size="small"
              >同意</el-button
            >
            <el-button
              type="danger"
              @click.native.stop="handleClick(scope.row, false)"
              size="small"
              >拒绝</el-button
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
  name: "todo",
  data() {
    return {
      dialogVisible: false,
      tableData: [],
      appInfo: "",
    };
  },
  methods: {
    async getData() {
      const res = await this.$http.get(
        "/api/attributeAuthority/applicationUnprocessed"
      );
      this.tableData = res.data.data;
    },
    showAppInfo(row) {
      this.dialogVisible = true;
      this.appInfo = row;
    },
    rowStyle(row) {
      if (row.rowIndex % 2 == 0) {
        return { background: "#091b38" };
      } else {
        return { background: "#012751" };
      }
    },
    handleClick(row, isAgreed) {
      this.tableData.splice(this.tableData.indexOf(row), 1);
      if(isAgreed){
        this.$http
        .post("/api/attributeAuthority/agree", row)
        .then((res) => {
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送同意信息");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
      }
      else{
        this.$http
        .post("/api/attributeAuthority/refuse", row)
        .then((res) => {
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送拒绝信息");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
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
.todo {
  width: 100%;
  height: 100%;
  .resultsList {
    height: 99%;
    .el-table {
      color: white;
      border: 0px;
    }
    .el-table::before {
      height: 0;
    }
  }
}
/deep/input::-webkit-input-placeholder {
  color: white;
  font-size: 13px;
}
/deep/input.el-range-input::-webkit-input-placeholder {
  color: white;
  font-size: 13px;
}
/deep/.el-input__inner {
  background-color: transparent !important;
  border: 1px solid #1296db;
  color: white;
}
/deep/.el-range-input {
  background-color: transparent !important;
  color: white;
}
/deep/.el-range-separator {
  color: #17a1e5;
  font-size: 15px;
}
/deep/.el-table__row > td {
  border: none;
}
/deep/.el-table th.is-leaf {
  border: none;
}
/deep/th.gutter {
  background-color: #1981f6 !important;
}
/deep/.el-table__body-wrapper {
  background: #02367a;
}
</style>
