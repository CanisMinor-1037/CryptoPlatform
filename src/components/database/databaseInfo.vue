<template>
  <div>

  </div>
</template>
<script>
export default {
  name:"databaseInfo",
  data() {
    return {
      info:{
        username:"",
        password:"",
        url:"",
        assetFabricId:""
      }
    };
  },
  methods: {

  },
  mounted(){
    this.info=this.$store.getters.getDatabaseInfo;
    alert(this.info.username);
    alert(this.info.password);
    alert(this.info.url);
    alert(this.info.assetFabricId);
  }
}
</script>
<style lang="less" scoped>
</style>