<template>
  <el-menu
    class="categories"
    default-active="btc"
    @select="handleSelect"
    active-text-color="red">
    <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
      <i class="el-icon-menu"></i>
      <span slot="title">{{ item.navItem }}</span>
    </el-menu-item>
  </el-menu>
</template>

<script>
export default {
  name: 'SideMenu',
  data () {
    return {
      cid: '',
      navList: [
        {name: 'btc', navItem: 'BTC'},
        {name: 'bch', navItem: 'BCH'}
      ]
    }
  },
  methods: {
    // handleOpen (key, keyPath) {
    //   console.log(key, keyPath)
    // },
    handleSelect (key, keyPath) {
      this.cid = key
      this.$emit('indexSelect')
    }
  }
}
</script>

<style scoped>
  .categories {
    position: fixed;
    margin-left: 50%;
    left: -600px;
    top: 100px;
    width: 150px;
  }
</style>
