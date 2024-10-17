import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state() {
    return {
      database:{
        username:"",
        password:"",
        url:"",
        assetFabricId:""
      },
      videoUrl:""
    }
  },
  getters: {
    getDatabaseInfo(state){
      return state.database;
    },
    getVideoUrl(state){
      return state.videoUrl;
    }
  },
  mutations: {
    setDatabaseInfo(state,info){
      state.database.url=info.url;
      state.database.username=info.username;
      state.database.password=info.password;
      state.database.assetFabricId=info.assetFabricId;
    },
    setVideoUrl(state,url){
      state.videoUrl=url;
    }
  },
  actions: {
  },
  modules: {
  }
})
