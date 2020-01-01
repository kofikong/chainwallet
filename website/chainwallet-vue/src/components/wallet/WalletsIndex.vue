<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <!-- <div>Receive!</div> -->
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <!--<books></books>-->
      <wallet class="wallet-area"></wallet>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from '../common/SideMenu'
import Wallet from './Wallet'
export default {
  name: 'WalletsIndex',
  components: {SideMenu, Wallet},
  methods: {
    listByCategory () {
    //   var _this = this
      var cid = this.$refs.sideMenu.cid
      console.log(cid)
    //   var url = 'categories/' + cid + '/books'
    //   this.$axios.get(url).then(resp => {
    //     if (resp && resp.status === 200) {
    //       _this.$refs.booksArea.books = resp.data
    //     }
    //   })
    }
  }
}
</script>

<style scoped>
.wallet-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
