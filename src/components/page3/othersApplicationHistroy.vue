<template>
  <div class="myApplicationHistroy">  
    <div style="font-weight: bold; text-align: center; font-size: 1.5rem">
      被申请
    </div>
    <div class="searchParams">
      <el-row :gutter="2">
        <el-col :span="8">
          <el-input
            v-model="orderNo"
            size="small"
            placeholder="订单号"
            clearable
            @clear="search"
          ></el-input>
        </el-col>
        <el-col :span="8">
          <el-input
            v-model="fileName"
            size="small"
            placeholder="数据要素名"
            clearable
            @clear="search"
          ></el-input>
        </el-col>

        <el-col :span="4">
          <el-button
            type="primary"
            icon="el-icon-search"
            size="small"
            style="float: right;position: relative;left:100%"
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
        @row-click="showFileInfo"
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
          width="70"
          label="#"
          header-align="center"
          align="center"
        ></el-table-column>
        <el-table-column
          prop="fabricId"
          label="订单号"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="name"
          label="数据要素名"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="applicant"
          label="申请机构"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="status"
          label="状态"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        <template slot="header" slot-scope="scope">
          <el-dropdown trigger="click">
            <span class="el-dropdown-link" style="font-size:14px;color:white">
              状态<i class="el-icon-arrow-down el-icon--right"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="getStatus(0)">全部</el-dropdown-item>
              <el-dropdown-item @click.native="getStatus(1)">未处理</el-dropdown-item>
              <el-dropdown-item @click.native="getStatus(2)">已拒绝</el-dropdown-item>
              <el-dropdown-item @click.native="getStatus(3)">传输中</el-dropdown-item>
              <el-dropdown-item @click.native="getStatus(4)">传输完成</el-dropdown-item>
            </el-dropdown-menu>
            </el-dropdown>
        </template>
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
      <el-table-column
          label="操作"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        <template  slot-scope="scope">
          <el-select :disabled="scope.row.status!==1" :placeholder="scope.row.status===1?'请选择':scope.row.status===2?'已拒绝':'已同意'" size="mini">
            <el-option @click.native="agree(scope.row)">同意</el-option>
            <el-option @click.native="refuse(scope.row)">拒绝</el-option>
          </el-select>
        </template>
      </el-table-column>
      </el-table>
    </div>

    <el-dialog title="文件信息" :visible.sync="dialogVisible" width="30%">
      <span style="color: #77e1f5">文件名：{{ fileInfo.filename }} </span><br />
      <span style="color: #77e1f5">文件日期 ：{{ fileInfo.date }} </span><br />
      <span style="color: #77e1f5">文件状态 ：{{ fileInfo.status }} </span
      ><br />
      <span slot="footer">
        <el-button
          type="primary"
          @click="dialogVisible = false"
          style="background-color: #77e1f5"
          >确定</el-button
        >
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      fileName: "",
      orderNo: "",
      fileDate: "",
      fileStatus: 0,
      resultTableData: [],
      tableData: [],
      tableData2:[],
      fileInfo: "",
      chart: null,
    };
  },
  methods: {
    agree(row){
        this.$http
        .post("/api/order/agree", row)
        .then((res) => {
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送同意信息");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
    },
    refuse(row){
        this.$http
        .post("/api/order/refuse", row)
        .then((res) => {
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送拒绝信息");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
    },
    getProcess(status){
      if(status===4){
        return 100;
      }
      if(status===3){
        return 60;
      }
      if(status===1){
        return 30;
      }
      if(status===2){
        return 100;
      }  
    },
    getColor(status){
      if(status===4){
        return "green";
      }
      if(status===3){
        return "";
      }
      if(status===1){
        return "darkorange";
      }
      if(status===2){
        return "red";
      }
    },
    getStatus(status){;
      this.fileStatus=status;
      this.search();
    },
    async getData() {
      const res = await this.$http.get(
        "/api/order/fileAppliedHistory"
      );
      this.tableData = res.data.data;
      this.search();
      
    },
    search() {
      this.resultTableData=this.tableData.filter((item)=>{
        if(this.fileName===""){
          return true;
        }
        else{
          return this.fileName==item.name;
        }
      })
      .filter((item)=>{
        if(this.orderNo===""){
          return true;
        }
        else{
          return this.orderNo==item.fabricId;
        }
      })
      .filter((item)=>{
        if(this.fileStatus===0){
          return true;
        }
        else{
          return this.fileStatus==item.status;
        }
      })
    },
    showFileInfo(row) {
    },
    rowStyle(row) {
      // var style = {};
      if (row.rowIndex % 2 == 0) {
        return { background: "#091b38" };
      } else {
        return { background: "#012751" };
      }
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
.myApplicationHistroy {
  width: 100%;
  height: 100%;
  .searchParams {
    height: 6%;
    .el-row {
      margin-top: 10px;
      .el-range-separator {
        color: white;
      }
    }
  }
  .resultsList {
    // margin-top: 1%;
    height: 96%;
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
// 注意不同的浏览器前缀不一样。https://m.php.cn/article/473924.html
// input::-webkit-input-placeholder { /* WebKit, Blink, Edge */
//     color : red;
// }
// :-moz-placeholder { /* Mozilla Firefox 4 to 18 */
//    color : red;
// }
// ::-moz-placeholder { /* Mozilla Firefox 19+ */
//    color : red;
// }
// input:-ms-input-placeholder { /* Internet Explorer 10-11 */
//    color : red;
// }
// input::-ms-input-placeholder { /* Microsoft Edge */
//    color : red;
// }

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

/deep/.el-table__row {
  height: 30px;
}

/*填充上未被填充的右侧表头空白*/
/deep/th.gutter {
  background-color: #1981f6 !important;
}

// 筛选时空着的地方的背景色
/deep/.el-table__body-wrapper {
  background: #02367a;
}
.greenprogress .el-progress__bar__inner{
  background-color: green;
}
.blueprogress .el-progress__bar__inner{
  background-color: blue;
}
.yellowprogress .el-progress__bar__inner{
  background-color: yellow;
}

// /deep/.el-table__header{
//   height: 10%;
// }

// .el-pager li.active {
//   color: #0ba0eb;
//   cursor: default;
// }
// .el-pager li {
//   color: white;
//   cursor: default;
// }
// .el-pager {
//   background-color: transparent !important;
// }
// .el-pagination .el-pager li:hover {
//   color: #0ba0eb;
// }
// .el-pagination .el-pager li:not(.disabled):hover {
//   color: #0ba0eb;
// }
</style>
