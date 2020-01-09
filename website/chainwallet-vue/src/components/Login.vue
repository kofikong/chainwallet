<template>
  <body id="poster">
    <el-form class="login-container" label-position="left" label-width="0px">
      <h3 class="login_title">系统登录</h3>
      <el-form-item>
        <el-input type="text" v-model="loginForm.username" auto-complete="off" placeholder="账号"></el-input>
      </el-form-item>
      <el-form-item>
        <el-input type="password" v-model="loginForm.password" auto-complete="off" placeholder="密码"></el-input>
      </el-form-item>
      <el-form-item style="width:100%">
        <el-button type="primary" style="width:100%;backgtroup:#505458;border: none" v-on:click="login">登录</el-button>
      </el-form-item>
    </el-form>
  </body>
</template>
<style>
  .login-container {
    border-radius: 15px;
    background-clip: padding-box;
    margin: 90px auto;
    width: 350px;
    padding: 35px 35px 15px 35px;
    background: #fff;
    border: 1px solid #eaeaea;
    box-shadow: 0 0 25px #cac6c6;
  }
  .login_title {
    margin: 0px auto 40px auto;
    text-align: center;
    color: #505458;
  }

  #poster {
    background:url("../assets/bg.png") no-repeat;
    background-position: center;
    height: 100%;
    width: 100%;
    background-size: cover;
    position: fixed;
  }
  body{
    margin: 0px;
  }
</style>
<script>

export default {
  name: 'Login',
  data () {
    return {
      loginForm: {
        username: 'admin',
        password: '123456'
      },
      responseResult: []
    }
  },
  methods: {
    login () {
      var _this = this
      console.log(this.$store.state)
      this.$axios
        .post('/api/login', {
          userName: this.loginForm.username,
          password: this.loginForm.password
        })
        .then(resp => {
          if (resp.status === 200) {
            var data = resp.data.data
            _this.$store.commit('login', data)
            _this.$store.commit('token', resp.headers.authorization)
            var path = _this.$route.query.redirect
            _this.$router.replace({path: (path === undefined || path == null) ? '/' : path}, (cc) => {
              console.log(cc)
            }, (ee) => {
              console.log(ee)
            })
          } else {
          }
        })
        .catch(failResponse => {
          console.log(failResponse)
        })
    }
  }
}
</script>
