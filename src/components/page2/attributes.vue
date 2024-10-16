<template>
  <div class="attributes">
    <div class="header-title">拥有属性</div>
    <el-button class="appButton" type="primary" size="large" @click="showDialog"
      >申请属性</el-button
    >
    <div class="attributes-list">
      <el-tag
        type="warning"
        v-for="(attribute, i) in attributes"
        :key="i"
        class="text item"
        style="margin-left: 10px; margin-bottom: 10px"
        effect="light"
      >
        {{ attribute }}
      </el-tag>
    </div>

    <el-dialog title="申请属性" :visible.sync="dialogVisible" width="30%">
      <el-tree
        ref="tree"
        :data="toApplyAttributes"
        show-checkbox
        :props="defaultProps"
        node-key="label"
      >
      </el-tree>
      <span slot="footer">
        <el-button
          type="primary"
          @click="submit"
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
      attributes: [],
      toApplyAttributes: [],
      applyAttributes: [],
      defaultProps: {
        children: "children",
        label: "label",
      },
      dialogVisible: false,
    };
  },
  methods: {
    getAttributesList() {
      this.$http
        .get("/api/confirmation/getAllAttributes")
        .then((res) => {
          let allAttrs = res.data.data[0];
          if (
            res.data.data[1] === "no state found" ||
            res.data.data[1] === ""
          ) {
            this.attributes = [];
          } else {
            this.attributes = res.data.data[1];
            let newAttrs = this.attributes.join(",");
            sessionStorage.setItem("attributes", newAttrs);
          }
          allAttrs.forEach((element) => {
            if (this.attributes.indexOf(element) == -1) {
              this.toApplyAttributes.push({ label: element, children: [] });
            }
          });
        });
    },
    showDialog() {
      this.dialogVisible = true;
    },
    submit() {
      this.applyAttributes = this.$refs.tree.getCheckedNodes();
      this.applyAttributes = this.applyAttributes.map((element) => {
        return element.label;
      });
      console.log(this.applyAttributes);
      let attrString = this.applyAttributes.join(" ");
      this.$http
        .post("/api/confirmation/applyAttributes", {
          attributes: attrString
        })
        .then((res) => {
          if(res.data.code == 200){
            this.$notify.success("已向区块链发送申请");
          }
          else{
            this.$notify.warning(res.data.data);
          }
        })
      this.$refs.tree.setCheckedKeys([]);
      this.dialogVisible = false;
    }
  },
  mounted() {
    this.getAttributesList();
  },
};
</script>

<style lang="less" scoped>
.attributes {
  padding-top: 1%;
  height: 100%;
  .header-title {
    font-weight: bold;
    font-size: 18px;
    line-height: 1.2;
  }
  .appButton {
    position: absolute;
    right: 10px;
    top: 10px;
  }
  .downloadButton {
    position: absolute;
    right: 100px;
    top: 10px;
  }
  .attributes-list {
    padding: 1%;
    height: calc(100vh-22px);
    position: relative;
    top: 10%;
  }
}

/deep/.el-tree {
  background: #071332;
  color: white;
}
// 鼠标悬浮于选项上时的背景色
/deep/.el-tree-node__content:hover {
  background-color: #333 !important;
}
/deep/.el-tree-node:focus > .el-tree-node__content {
  background-color: #333 !important;
}
// 选项文字的颜色
/deep/.el-tree-node__label {
  color: rgb(119, 225, 245);
}
</style>
