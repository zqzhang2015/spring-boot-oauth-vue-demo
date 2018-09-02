<template>
  <el-container>
    <el-header>
      管理界面
      <el-button type="danger" size="small" @click="logout" style="float: right;">退出</el-button>
    </el-header>
    <el-container>
      <el-aside width="200px">
        <app-menu></app-menu>
      </el-aside>
      <el-container>
        <el-main>
          <router-view/>
        </el-main>
        <el-footer>
        </el-footer>
      </el-container>
    </el-container>
  </el-container>
</template>

<script>
  import AppMenu from '@/components/AppMenu.vue'

  export default {
    components: {AppMenu},
    methods: {
      logout: function () {
        this.$store.commit('logout');
        this.$router.push({
          path: '/login',
          query: {redirect:this.$route.fullPath}
        });
      }
    }
  }
</script>
