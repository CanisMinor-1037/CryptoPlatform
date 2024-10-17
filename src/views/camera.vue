<template>
    <div>
        <div style="color: white;">地址为{{ videoUrl }}
        </div>
        <div id="app-container" class="video-box">
      <video
        ref="videoElement"
        :src="videoUrl"
        id="videoElement"
        controls
        muted
        style="width: 300px; height: auto; object-fit: fill"
      ></video>
    </div> 
    </div>
</template>

<script>
import hlsjs from "hls.js";
export default{
  name:"camera",
  data() {
    return {
      videoUrl:"http://videochannel-face.oss-cn-wuhan-lr.aliyuncs.com/test-channel/playlist.m3u8"
    };
  },
  methods:{
    show() {

      this.video = this.$refs.videoElement; //定义挂载点
      console.log(this.video);
      if (hlsjs.isSupported()) {
        this.hlsjs = new hlsjs();
        this.hlsjs.loadSource(this.videoUrl);//设置播放路径
        this.hlsjs.attachMedia(this.video);//解析到video标签上
        console.log(this.hlsjs);
        this.hlsjs.on(hlsjs.Events.MANIFEST_PARSED, () => {
            console.log(this.videoUrl);  
            this.video.play();
          console.log("加载成功");
        });
        this.hlsjs.on(hlsjs.Events.ERROR, (event, data) => {
        console.log(event, data);
          // 监听出错事件
          console.log("加载失败");
        });
      } else {
        this.$message.error("不支持的格式");
        return;
      }
    },
    closeVideo() {
      if (this.hlsjs) {
        this.$refs.videoElement.pause();
        this.video.pause();
        this.hlsjs.destroy();
        this.hlsjs = null;
        this.$emit("closeVideo");
      }
    },
  },
  mounted(){
    //this.videoUrl=this.$store.getters.getVideoUrl;
    this.show();
  }
}
</script>
<style lang="less" scoped></style>