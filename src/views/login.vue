<template>
  <div class="login-body">
    <div class="login-container">
    <div class="logo-container">
      <img src="../assets/images/logo.png" alt="Logo" class="logo">
    </div>
    <div class="title">
      <h1>区块链安全平台</h1>
    </div>
    <div class="login-form">
      <h2>Blockchain Security platform</h2>
      <input type="text" id="username" placeholder="用户名" v-model="username">
      <input type="password" id="password" placeholder="密码" v-model="password">
      <div class="captcha-container">
        <input type="text" id="captchaInput" placeholder="输入验证码" v-model="captchaInput">
        <div class="captcha-text" id="captchaText" >{{ captchaText }}</div>
      </div>
      <div class="remember-me">
        <input type="checkbox" id="rememberMe" v-model="remeberMe">
        <label for="rememberMe">记住账号</label>
      </div>
      <button @click="login">登录</button>
    </div>
  </div>
  </div>

</template>
<script>
export default {
  name: "login",
  data() {
    return {
      username:"",
      password:"",
      captchaInput:"",
      captchaText:"",
      remeberMe:false
    };
  },
  methods: {
    generateCaptcha() {
      const characters ='0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz';
      let result = '';
      for (let i = 0; i < 4; i++) {
        result += characters.charAt(Math.floor(Math.random() * characters.length));
      }
      return result;
    },
    updateCaptcha() {
      this.captchaText = this.generateCaptcha();
    },
    async login() {
      if (this.captchaInput !== this.captchaText) {
        alert('验证码错误，请重试。');
        this.updateCaptcha();
        return;
      }
      console.log('用户名:', this.username);
      console.log('密码:', this.password);
      let formData=new FormData();
      formData.append("username",this.username);
      formData.append("password",this.password);
      const res = await this.$http.post(
        "/api/login",
        formData,
        {  
          headers: {  
            'Content-Type': 'multipart/form-data'  
          }  
        }
      );
      if(res.data.code=="200"){
        alert('登录成功！');
        this.$router.push('/confirmation');
      }
      else{
        alert('登录失败！,错误原因为'+res.data.data);
      }
    }
  },
  mounted(){
    this.updateCaptcha();
  }
};
</script>
<style scoped>
.login-body {
    margin: 0;
    padding: 0;
    background: url(@/assets/images/background.png) no-repeat center center fixed;
    background-size: cover;
    font-family: 'Arial', sans-serif;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
    overflow: hidden;
  }

  .login-container {
    background: rgba(255, 255, 255, 0.1);
    padding: 40px;
    border-radius: 15px;
    box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
    width: 300px;
    text-align: center;
    position: relative;
    left: calc(25%);
    z-index: 1;
  }

  .title h1 {
    color: #ffffff;
    margin-top: 25px;
    margin-bottom: 15px;
    font-size: 22px;
    text-transform: uppercase;
  }

  .login-form {
    width: 100%;
  }

  .login-form h2 {
    color: #ffffff;
    margin-top: 10px;
    margin-bottom: 10px;
    font-size: 13px;
  }

  .logo-container {
    position: absolute;
    top: 10px;
    left: 15px;
    width: auto;
    height: auto;
  }

  .logo {
    width: 100%;
    height: auto;
    max-width: 180px;
  }

  input {
    /* width: calc(100% - 22px); */
    padding: 10px;
    margin: 5px 0;
    border: none;
    border-radius: 5px;
    background: rgba(255, 255, 255, 0.2);
    color: #ffffff;
    font-size: 16px;
    outline: none;
    transition: background 0.3s ease;
  }

  input[type="text" i] {
    width: calc(100% - 10px);
  }

  input[type="password" i] {
    width: calc(100% - 10px);
  }

  input:focus {
    background: rgba(255, 255, 255, 0.3);
  }

  .remember-me {
    display: flex;
    align-items: center;
    justify-content: left;
    margin-top: 5px;
    margin-bottom: 10px;
    color: #ffffff;
  }

  .remember-me label {
    cursor: pointer;
    margin-left: 5px;
    font-size: 13px;
  }

  .captcha-container {
    display: flex;
    justify-content: space-between;
    align-items: center;
    width: calc(100% - 2px);
    margin-top: 5px;
  }

  .captcha-container input {
    width: calc(50% - 5px);
  }

  .captcha-text {
    width: calc(50% - 5px);
    padding: 10px;
    background: rgba(255, 255, 255, 0.2);
    border-radius: 5px;
    color: #ffffff;
    font-size: 16px;
    text-align: center;
    cursor: not-allowed;
  }

  button {
    width: 50%;
    padding: 10px;
    border: none;
    border-radius: 10px;
    background: #00bff3;
    color: #000000;
    font-size: 16px;
    cursor: pointer;
    transition: background 0.3s ease;
    -webkit-border-radius: 10px;
    -moz-border-radius: 10px;
    -ms-border-radius: 10px;
    -o-border-radius: 10px;
  }

  button:hover {
    background: #0096cc;
  }

  button:active {
    background: #007399;
  }

  @media (max-width: 600px) {
    form {
      max-width: 100%;
      padding: 10px;
    }

    .logo-container img {
      max-width: 80px;
    }
  }
</style>