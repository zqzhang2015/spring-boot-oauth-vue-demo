<template>
  <div class="login-panel">
    <h1>登录</h1>
    <form autocomplete="off" @keyup.enter="login">
      <input type="text" placeholder="请输入账号" autocomplete="off" v-model="username">
      <input type="password" placeholder="请输入密码" autocomplete="off" v-model="password">
      <div class="login-panel-alert" v-if="error">{{errorMsg}}</div>
      <button type="button" class="login-panel-login" @click="login">登录</button>
      <button type="button" class="login-panel-register" @click="register">注册</button>
    </form>
  </div>
</template>

<script>
  import axios from 'axios'

  export default {
    data() {
      return {
        username: '',
        password: '',
        error: false,
        errorMsg: ''
      }
    },
    methods: {
      login() {
        axios({
          url: '/oauth/token',
          method: 'post',
          data: {
            username: this.username,
            password: this.password,
            client_id: 'client_1',
            client_secret: '123456',
            scope: 'read write',
            grant_type: 'password'
          },
          transformRequest: function (data) {
            let formDataArray = [];
            for (let x in data) {
              formDataArray.push(encodeURIComponent(x) + '=' + encodeURIComponent(data[x]));
            }
            return formDataArray.join('&').toString()
          },
          headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
          }
        }).then(response => {
          this.$store.commit('login', response.data['token_type'] + ' ' + response.data['access_token']);
          this.$router.replace(this.$route.query.redirect ? this.$route.query.redirect : '/index');
        }).catch(error => {
          this.error = true;
          this.errorMsg = '用户名或密码错误！';
        });
      },
      register() {

      },
    },
    created() {
    }
  }
</script>

<style scoped>

  .login-panel {
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    text-align: center;
    color: #555;
    margin: 170px auto 0 auto;
  }

  .login-panel h1 {
    color: #555;
    font-size: 30px;
    font-weight: 700;
    text-shadow: 0 1px 4px rgba(0, 0, 0, .2);
  }

  .login-panel form {
    position: relative;
    width: 305px;
    margin: 15px auto 0 auto;
    text-align: center;
  }

  .login-panel input {
    width: 270px;
    height: 42px;
    margin-top: 25px;
    padding: 0 15px;
    background: #2d2d2d; /* browsers that don't support rgba */
    background: rgba(45, 45, 45, .15);
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    border: 1px solid #3d3d3d; /* browsers that don't support rgba */
    border: 1px solid rgba(255, 255, 255, .15);
    -moz-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
    -webkit-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
    box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset;
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 16px;
    color: black;
    text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
  }

  .login-panel input:-moz-placeholder {
    color: #fff;
  }

  .login-panel input:-ms-input-placeholder {
    color: #fff;
  }

  .login-panel input::-webkit-input-placeholder {
    color: #fff;
  }

  .login-panel input:focus {
    outline: none;
    -moz-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    -webkit-box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    box-shadow: 0 2px 3px 0 rgba(0, 0, 0, .1) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
  }

  .login-panel-alert {
    width: 270px;
    height: 42px;
    font-size: 16px;
    padding-top: 8px;
    margin-left: 20px;
    margin-top: 25px;
    color: #ff543c;
  }

  .login-panel button {
    cursor: pointer;
    width: 150px;
    height: 44px;
    margin-top: 25px;
    padding: 0;
    -moz-border-radius: 6px;
    -webkit-border-radius: 6px;
    border-radius: 6px;
    -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .25) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    font-family: 'PT Sans', Helvetica, Arial, sans-serif;
    font-size: 14px;
    font-weight: 700;
    color: #fff;
    text-shadow: 0 1px 2px rgba(0, 0, 0, .1);
    -o-transition: all .2s;
    -moz-transition: all .2s;
    -webkit-transition: all .2s;
    -ms-transition: all .2s;
  }

  .login-panel button:hover {
    -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
  }

  .login-panel button:active {
    -moz-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    -webkit-box-shadow: 0 15px 30px 0 rgba(255, 255, 255, .15) inset,
    0 2px 7px 0 rgba(0, 0, 0, .2);
    box-shadow: 0 5px 8px 0 rgba(0, 0, 0, .1) inset,
    0 1px 4px 0 rgba(0, 0, 0, .1);

    border: 0px solid #ef4300;
  }

  .login-panel-login {
    background: #ef4300;
    border: 1px solid #ff730e;
  }

  .login-panel-register {
    background: none repeat scroll 0 0 #539FFF;
    border: 1px solid #82bad8;
    /*background: none repeat scroll 0 0 #86d8d4;*/
    /*border: 1px solid #86d8d4;*/
  }

</style>
