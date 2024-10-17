<template>
  <div>
    <el-row :gutter="10" class="top">
      <el-col :span="8" :offset="2">
        <dv-border-box-12 style="padding: 10px">
          <div style="color: white;font-size: 30px;">数据库基本信息</div>
          <div style="color: white;font-size: large;">数据库交易号</div>
          <div style="color: white;font-size: large;">{{info.assetFabricId}}</div>
          <div style="color: white;font-size: large;">数据库地址</div>
          <div style="color: white;font-size: large;">{{info.url}}</div>
          <div style="color: white;font-size: large;">数据库用户名</div>
          <div style="color: white;font-size: large;">{{info.username}}</div>
          <div style="color: white;font-size: large;">数据库密码</div>
          <div style="color: white;font-size: large;">{{info.password}}</div>
          <el-button type="primary" size="large" @click="testConnect">测试连接</el-button>
          <!--<el-form :model="info" label-width="50px">
            <el-form-item label="数据库交易号">
              <el-input v-model="info.assetFabricId" disabled></el-input>
            </el-form-item>
            <el-form-item label="数据库地址">
              <el-input v-model="info.url" disabled></el-input>
            </el-form-item>
            <el-form-item label="用户名">
              <el-input v-model="info.username" disabled></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input v-model="info.password" disabled></el-input>
            </el-form-item>
          </el-form>-->
        </dv-border-box-12>

      </el-col>
      <el-col :span="12" >
        <dv-border-box-12 style="padding: 10px">
          <el-input placeholder="请输入sql语句" v-model="sql"></el-input>
          <el-button type="primary" size="large" @click="executeQuery">查询</el-button>
          <el-table :data="sqlResult">
            <el-table-column v-for="(item,index) in sqlColumns" :key="index" :prop="item">
              <template slot="header">
                {{item}}
              </template>
            </el-table-column>
          </el-table>
        </dv-border-box-12>
      </el-col>
    </el-row>
  </div>
</template>
<script>
export default{
  name:"database",
  data() {
    return {
      info:{
        username:"",
        password:"",
        url:"",
        assetFabricId:""
      },
      sql:"",
      sqlResult:[],
      sqlColumns:[],
      sqlValues:[]
    };
  },
  methods: {
    testConnect(){
      const formData = new FormData;
      formData.append("url",this.info.url);
      formData.append("username",this.info.username);
      formData.append("password",this.info.password);
      this.$http.post("/api/database/testConnect",formData, {  
          headers: {  
            'Content-Type': 'multipart/form-data'  
          }  
        })
      .then((response)=>{
        if(response.data.code == 200){
          this.$notify.success("连接成功");
        }
        else{
          this.$notify.warning("连接失败");
        }
      })
    },
    executeQuery(){
      const formData = new FormData;
      formData.append("url",this.info.url);
      formData.append("username",this.info.username);
      formData.append("password",this.info.password); 
      formData.append("sql",this.sql);
      formData.append("assetFabricId",this.info.assetFabricId);
      this.$http.post("/api/database/executeQuery",formData, {  
          headers: {  
            'Content-Type': 'multipart/form-data'  
          }  
        })
      .then((response)=>{
        if(response.data.data){
          this.sqlResult=JSON.parse(response.data.data);
          this.sqlColumns=Object.keys(this.sqlResult[0]);
          /*console.log(this.sqlResult);
          for(let key in this.sqlResult[0]){
            this.sqlColumns.append(key);
          }
          console.log(this.sqlColumns);
          console.log(this.sqlResult);*/
        }
        else{
          this.$notify.warning("未能查询到结果");
        }
      })
      .catch(error=>{
        this.$notify.warning("未能查询到结果");
      })
    }
  },
  mounted(){
    this.info=this.$store.getters.getDatabaseInfo;
  }
}
</script>
<style lang="less" scoped></style>