<template>
  <div class="upload">
    <el-row :gutter="5">
      <div class="header-title">确权</div>
      <el-upload
        class="upload-demo"
        ref="upload"
        :http-request="handleUpload" 
        :file-list="fileList"
        :limit="1"
        :on-success="handleSuccess"
        :on-error="handleError"
        :auto-upload="false"
      >
        <el-button
          slot="trigger"
          size="small"
          type="primary"
          @click="type = false"
          style="display: none;"
        >
          选取文件
        </el-button>
        <el-button size="small" type="primary" @click="videoDialogVisible = true">摄像头信息</el-button>
        <el-button
          style="margin-left: 10px"
          size="small"
          type="warning"
          @click="dialogVisible = true"
          >选择属性策略</el-button
        >
        <el-button size="small" type="primary" @click="locationDialogVisible = true">区域</el-button>
        <el-button size="small" type="primary" @click="fieldDialogVisible = true">应用领域</el-button>
        <el-button
          style="margin-left: 10px"
          size="small"
          type="success"
          :file-list="fileList"
          @click="submitUpload"
          >上传</el-button
        >
        <div slot="tip" class="el-upload__tip" style="color: orange">
          一次只能选择一个文件，请选择需要上传的文件和需要满足的属性
        </div>
      </el-upload>
      <el-dialog title="输入摄像头信息" :visible.sync="videoDialogVisible" width="35%">
        <el-form :model="video">
          <el-form-item label="摄像头名">
            <el-input v-model="video.name"></el-input>
          </el-form-item>
          <el-form-item label="摄像头地址">
            <el-input v-model="video.url"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click ="videoDialogVisible=false">确定</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
      <el-dialog title="选择属性策略" :visible.sync="dialogVisible" width="35%">
        <div class="selectButtons">
          <el-tag
            v-for="(attribute, i) in attributes"
            :key="i"
            size = "large"
            @click = "handleCheck(attribute)"
            style="margin: 5px;background-color: green;color: white;cursor: pointer;">
              {{ attribute }}
          </el-tag>
          <div style="display: flex;">
            <el-input size="mini" style="margin: 5px;" v-model="num1"></el-input>
            <span style="font-size: large;color:white;margin-top: 5px;">o</span>
            <span style="font-size: large;color:white;margin-top: 5px;">f</span>
            <el-input size="mini" style="margin: 5px;" v-model="num2"></el-input>
            <el-button
              type="primary"
              size = "mini"
              style="margin: 5px;float:right;width:50%"
              @click = "handleAdd"
              >添加数量
            </el-button>
            <el-button
              type="warning"
              size = "mini"
              style="margin: 5px;float:right;width:50%"
              @click = "handleDelete"
              >删除
            </el-button>
            <el-button
              type="danger"
              size = "mini"
              style="margin: 5px;float:right;width:50%"
              @click = "handleClear"
              >清空
            </el-button>
          </div>
        </div>
        <span slot="footer">
          <el-button
            type="primary"
            @click="setAttributes"
            style="background-color: #77e1f5"
            >确定</el-button
          >
        </span>
        <br>
        <span style="color: white;">文件属性：{{fileAttributes.map(item=>item[0]).join(' ')}}</span>
        <br>
        <span v-if="validAttributes()" style="color: white;">合法</span>
        <span v-else style="color: red;">不合法(必须设置属性，且需正确构成访问树，不能存在1of1)</span>
      </el-dialog>
      <el-dialog title="选择区域" :visible.sync="locationDialogVisible" width="35%">
        <el-radio-group v-model="location">
            <el-radio label="华东">华东</el-radio>
            <el-radio label="华中">华中</el-radio>
            <el-radio label="华南">华南</el-radio>
            <el-radio label="华北">华北</el-radio>
            <el-radio label="西南">西南</el-radio>
            <el-radio label="西北">西北</el-radio>
            <el-radio label="东北">东北</el-radio>
          </el-radio-group>
            <el-button
            type="primary"
            @click="setLocation"
            style="background-color: #77e1f5"
            >确定</el-button>
      </el-dialog>
      <el-dialog title="选择应用领域" :visible.sync="fieldDialogVisible" width="35%">
        <el-radio-group v-model="field">
            <el-radio label="农业">农业</el-radio>
            <el-radio label="工业">工业</el-radio>
            <el-radio label="服务业">服务业</el-radio>
            <el-radio label="政务界">政务界</el-radio>
            <el-radio label="教育界">教育界</el-radio>
            <el-radio label="医学界">医学界</el-radio>
            <el-radio label="城市建设">城市建设</el-radio>
            <el-radio label="公共安全">公共安全</el-radio>
            <el-radio label="其他">其他</el-radio>
        </el-radio-group>
          <el-button
            type="primary"
            @click="setField"
            style="background-color: #77e1f5"
            >确定</el-button>
      </el-dialog>
    </el-row>
  </div>
</template>

<script>
export default {
  name: "upload",
  data() {
    return {
      type: false, 
      dialogVisible: false,
      locationDialogVisible: false,
      fieldDialogVisible: false,
      videoDialogVisible: false,
      attributes: [],
      defaultProps: {
        children: "children",
        label: "label",
      },
      fileAttributes: [],
      fileList: [],
      fileAttributesString: "",
      num1: 1,
      num2: 1,
      location: "",
      field: "",
      video:{
        name:"",
        url:""
      }
    };
  },
  methods: {
    validAttributes(){
      let num = 0;
      for(let i = 0;i < this.fileAttributes.length;i++){
        if(this.fileAttributes[i][1] == "attribute"){
          num ++;
        }
        else{
          num = num - this.fileAttributes[i][0].split("of")[1] + 1;
          if(num < 0){
            return false;
          }
        }
      }
      return num == 1;
    },
    getAttributes() {
      this.$http.get("/api/confirmation/getAllAttributes").then((res) => {
        this.attributes = res.data.data[0];
      });
    },
    setAttributes() {
      if(this.validAttributes() == false){
        alert("文件属性策略不合法，请重新设置");
        return;
      }
      this.dialogVisible = false;
      this.fileAttributesString = this.fileAttributes.map(item=>item[0]).join(' ');
    },
    setLocation() {
      if(this.location == ""){
        alert("请选择区域");
        return;
      }
      this.locationDialogVisible = false;
    },
    setField() {
      if(this.field == ""){
        alert("请选择应用领域");
        return;
      }
      this.fieldDialogVisible = false;
    },
    handleUpload(file){
      const formData = new FormData();  
      formData.append('field',this.field);
      formData.append('location',this.location);
      formData.append('attribute',this.fileAttributesString);
      formData.append('name',this.video.name);
      formData.append('url',this.video.url);
        this.$http.post("/api/confirmation/uploadVideoAsset", formData, {  
        headers: {  
          'Content-Type': 'multipart/form-data'  
        }  
      }).then(response => {  
        this.handleSuccess(response.data.data);  
      }).catch(error => {  
        this.handleError();
      });  
    },
    submitUpload() {
      if(this.validAttributes() == false){
        alert("文件属性策略不合法，请重新设置");
        return;
      }
      this.fileAttributesString = this.fileAttributes.map(item=>item[0]).join(' ');
      if(this.location === ""){
        this.$notify.warning("请选择区域");
        return;
      }
      if(this.field === ""){
        this.$notify.warning("请选择应用领域");
        return;
      }
      this.handleUpload(this.$refs.upload.uploadFiles[0]);
      return;
    },

    handleSuccess(data) {
      this.$refs.upload.uploadFiles = [];
      this.fileList = [];
      this.fileAttributes = [];
      this.$notify.success("上传成功，用时"+data);
    },
    handleError() {
      this.$refs.upload.uploadFiles = [];
      this.fileList = [];
      this.fileAttributes = [];
      this.$notify.error(`上传失败，请重新上传`);
    },
    handleCheck(attribute){
      this.fileAttributes.push([attribute,"attribute"]);
    },
    handleDelete(){
      if(this.fileAttributes.length>0){
        this.fileAttributes.pop();
      }
    },
    handleClear(){
      this.fileAttributes = [];
    },
    isPositiveInteger(a) {
      return a%1 === 0 && a > 0;
    },
    handleAdd(){
      if(this.isPositiveInteger(this.num1)&&this.isPositiveInteger(this.num2)&&this.num2>=this.num1&&(this.num1+this.num2>2)){
        this.fileAttributes.push([this.num1+"of"+this.num2,"num"]);
      }
    }
  },
  mounted() {
    this.getAttributes();
  },
  
};
</script>

<style lang="less" scoped>
.upload {
  padding-top: 1%;
  .header-title {
    font-weight: bold;
    font-size: 18px;
    line-height: 1.2;
  }
  .upload-demo {
    margin-top: 1%;
    width: 100%;
  }
}
/deep/.el-upload-list__item {
  margin: 0;
}
/deep/.el-upload-list__item-name {
  color: #1296db;
}
/deep/.el-icon-document {
  color: #1296db;
}
/deep/.el-upload-list__item-name:hover {
  color: #1296db !important;
}

</style>
