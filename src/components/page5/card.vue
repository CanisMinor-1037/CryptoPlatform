<template>
  <div class="card">
    <div class="header">
      <el-row :gutter="10" style="height: 40%">
        <el-col :span="12"
          ><span style="font-weight: bold; font-size: 1.5rem; margin: auto"
            >属性列表</span
          ></el-col
        >
      </el-row>
    </div>
    <div class="body">
      <!-- <el-card class="box-card">
        <div slot="header" class="clearfix">
          <span>卡片名称</span>
          <el-button style="float: right; padding: 3px 0" type="text"
            >操作按钮</el-button
          >
        </div>
        <div v-for="o in 4" :key="o" class="text item">
          {{ "列表内容 " + o }}
        </div>
      </el-card> -->
      <el-tag
        type="warning"
        v-for="(attribute, i) in attributes"
        :key="i"
        class="text item"
        style="margin-left: 10px"
      >
        {{ attribute }}
      </el-tag>
    </div>

    <!-- <el-dialog title="增加属性" :visible.sync="dialogVisible" width="30%">
      <el-form ref="form" :model="form" :rules="rules" label-width="100px">
        <el-form-item label="属性名称">
          <el-input v-model="form.attributename"></el-input>
        </el-form-item>
        <el-form-item label="活动性质">
          <el-checkbox-group v-model="form.departments">
            <el-checkbox
              v-for="(department, i) in departments"
              :key="i"
              :label="department"
              name="departments"
            ></el-checkbox>
          </el-checkbox-group>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog> -->
  </div>
</template>

<script>
export default {
  data() {
    return {
      dialogVisible: false,
      form: {
        attributename: "",
        departments: [],
      },
      departments: [],
      defaultProps: {
        children: "children",
        label: "label",
      },
      rules: {
        attributename: [
          { required: true, message: "请输入活动名称", trigger: "blur" },
        ],
      },
      attributes: [],
    };
  },

  methods: {
    addAttribute() {
      this.dialogVisible = true;
      this.form.attributes = this.$refs.tree.getCheckedNodes();
      this.form.attributes = this.form.attributes.map((element) => {
        return element.label;
      });
    },
    getAttributesList() {
      this.$http.get("/api/attributeAuthority/attributeList").then((res) => {
        this.attributes = res.data.data;
      });
    },
  },
  mounted() {
    this.getAttributesList();
  },
};
</script>

<style lang="less" scoped>
.card {
  width: 100%;
  height: 100%;
  .header {
    height: 40%;
  }
  .body {
    height: 60%;
  }
}
/deep/.el-form-item__label {
  color: white;
}
</style>
