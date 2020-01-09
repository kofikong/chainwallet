<template>
    <el-menu
      :default-active="'/wallet'"
      router
      mode="horizontal"
      background-color="white"
      text-color="#222"
      active-text-color="red"
      style="min-width: 1300px">
      <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      <i class="el-icon-switch-button" v-on:click="logout" style="float:right;font-size: 40px;color: #222;padding: 10px"></i>
      <a href="#nowhere" style="color: #222;float: right;padding: 20px;">More</a>
      <i class="el-icon-menu" style="float:right;font-size: 45px;color: #222;padding-top: 8px"></i>
      <span style="position: absolute;padding-top: 20px;right: 43%;font-size: 20px;font-weight: bold">Chain Wallet</span>
    </el-menu>
</template>

<script>
export default {
  name: 'NavMenu',
  data () {
    return {
      navList: [
        {name: '/wallet', navItem: 'Wallets'},
        {name: '/receive', navItem: 'Receive'},
        {name: '/send', navItem: 'Send'},
        {name: '/shop', navItem: 'Shop'},
        {name: '/setting', navItem: 'Settings'}
      ]
    }
  },
  methods: {
    logout () {
      var _this = this
      this.$axios.get('/api/logout').then(resp => {
        if (resp.status === 200) {
          _this.$store.commit('logout')
          _this.$router.replace('/login')
        }
      })
    }
  }
}
</script>

<style scoped>
  a{
    text-decoration: none;
  }
  span {
    pointer-events: none;
  }
  .el-icon-switch-button {
    cursor: pointer;
    outline:0;
  }
</style>
