<template>
  <el-menu :default-active="$route.path"
           router>
    <el-submenu v-for="nav in navs" :index="nav.index" :key="nav.index">
      <template slot="title">
        <span>{{nav.title}}</span>
      </template>
      <el-menu-item v-for="item in nav.items" :index="item.index" :key="item.index">{{item.title}}</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
    export default {
      data() {
        return {
          navs: [
            {
              title: '首页',
              index:'0',
              items: [
                {index: '/welcome', title: '欢迎'}
              ]
            },
            {
              title: '数据管理',
              index:'1',
              items: [
                {index: '/user', title: '用户信息'}
              ]
            }
          ]
        }
      },
    }
</script>

<style scoped>

</style>
