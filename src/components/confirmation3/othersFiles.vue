<template>
  <div class="othersFiles">
    <div class="searchParams">
      <el-row :gutter="5">
        <el-col :span="7" style="font-size: 1.5rem; font-weight: bold">
          可申请摄像头
        </el-col>
        <el-col :span="6">
          <el-input
            v-model="fileName"
            size="small"
            placeholder="摄像头名"
            clearable
            @change="search"
          ></el-input>
        </el-col>
        <el-col :span="7">
          <el-input
            v-model="departmentName"
            size="small"
            placeholder="所属部门"
            clearable
            @change="search"
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
        height="100%"
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
        >
        </el-table-column>
        <el-table-column
          label="上传日期"
          header-align="center"
          align="center"
          width="120"
          show-overflow-tooltip
        >
        <template slot-scope="scope">
          {{scope.row.createTime.split('T')[0]}}
        </template>
        </el-table-column>
        <el-table-column
          prop="name"
          label="摄像头名"
          header-align="center"
          align="center"
          width="120"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="owner"
          label="所属部门"
          header-align="center"
          align="center"
          width="120"
          filter-placement="bottom-end"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          prop="policy"
          label="属性策略"
          header-align="center"
          align="center"
          show-overflow-tooltip
        >
        </el-table-column>
        <el-table-column
          width="150"
          label="操作"
          header-align="center"
          align="center"
          ><template slot-scope="scope">
            <el-button
              type="primary"
              @click.native.stop="downloadFile(scope.row)"
              size="small"
              >访问</el-button
            >
            <!--<el-button
              type="primary"
              @click.native.stop="downloadFile(scope.row)"
              size="small"
              >跳转</el-button
            >--></template
          >
        </el-table-column>
      </el-table>
    </div>

    <el-dialog title="文件信息" :visible.sync="dialogVisible" width="30%">
      <span style="color: #77e1f5; line-height:1.5rem">文件ID：{{ fileInfo.id }} </span><br />
      <span style="color: #77e1f5; line-height:1.5rem">文件名称：{{ fileInfo.filename }} </span><br />
      <span style="color: #77e1f5; line-height:1.5rem">文件上传日期 ：{{ fileInfo.date }} </span><br />
      <span style="color: #77e1f5; line-height:1.5rem">文件属性策略 ：{{ fileInfo.attributes }} </span
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
      departmentName: "",
      departList: [],
      resultTableData: [],
      tableData: [],
      fileInfo: "",
    };
  },
  methods: {
    downloadFile(row) {
        this.$http
        .post("/api/confirmation/downloadVideoAsset", row,)
        .then((res)=>{
          //alert("地址为"+res.data.data)
          this.$store.commit('setVideoUrl',res.data.data);
          this.$router.push("/camera");
        })
        .catch((error)=>{
          this.$notify.warning("链接解析失败");
        })
    },
    getData() {
      this.$http.post("/api/confirmation/videoAvailable").then((res)=>{
        this.tableData = res.data.data;
        this.tableData.map(item=>{
          let item2={
            text:item.owner,
            value:item.onwer
          }
          return item2;
        }).forEach(item=>this.departList.push(item));
        this.search();
      });
    },
    search() {
      var res;
      if (this.fileName) {
        res = this.tableData.filter((data) => {
          return (
            data["name"].toString().toLowerCase().indexOf(this.fileName) >-1
          );
        });
      } else {
        res = this.tableData;
      }

      if (this.departmentName) {
        res = this.tableData.filter((data) => {
          return (
            data["owner"]
              .toString()
              .toLowerCase()
              .indexOf(this.departmentName) > -1
          );
        });
      } else {
        res = res; 
      }
      this.resultTableData = res;
    },
    showFileInfo(row) {
      this.dialogVisible = true;
      this.fileInfo = row;
    },
    rowStyle(row) {
      if (row.rowIndex % 2 == 0) {
        return { background: "#091b38" };
      } else {
        return { background: "#012751" };
      }
    },
    apply(row){
      this.$http
        .post("/api/confirmation/applyFile", row)
        .then((res)=>{
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送申请");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
    },
    filterDept(value, row) {
      return row.department === value;
    },
  },
  mounted() {
    this.getData();
  },
};
</script>

<style lang="less" scoped>
.othersFiles {
  width: 100%;
  height: 100%;
  .searchParams {
    height: 7%;
    .el-row {
      margin-top: 1%;
      .el-range-separator {
        color: white;
      }
    }
  }
  .resultsList {
    // margin-top: 1%;
    height: 90%;
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
