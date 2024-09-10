<template>
  <el-menu :default-openeds="['1', '3']" style="min-height:100%;overflow-x:hidden"
           background-color="rgb(48,65,86)"
           text-color="#fff"
           active-text-color="#ffd04b"
           :collapse-transition="false"
           :collapse="isCollapse"
           router
           @select="handleSelect"
  >
    <div style="height:60px;line-height:60px;text-align:center">
      <img src="../assets/logo.png" alt="" style="width:20px;position:relative;top:5px;margin-right: 5px">
      <b style="color:white" v-show="!isCollapse">北斗形变监测系统</b>
    </div>
    <el-menu-item index="/home">
      <i class="el-icon-house"></i>
      <span slot="title">主页</span>
    </el-menu-item>
    <el-submenu index="2">
      <template slot="title">
        <i class="el-icon-menu"></i>
        <span slot="title">系统管理</span>
      </template>
      <el-menu-item index="/shuju">
        <i class="el-icon-s-data"></i>
        <span slot="title">数据监测</span>
      </el-menu-item>
      <el-menu-item index="/baobiao">
        <i class="el-icon-picture"></i>
        <span slot="title">数据报表</span>
      </el-menu-item>
      <el-menu-item index="/yujing">
        <i class="el-icon-warning-outline"></i>
        <span slot="title">预警管理</span>
      </el-menu-item>
      <el-menu-item index="/file">
        <i class="el-icon-document"></i>
        <span slot="title">文件管理</span>
      </el-menu-item>
      <el-menu-item index="/user">
        <i class="el-icon-s-custom"></i>
        <span slot="title">用户管理</span>
      </el-menu-item>
      <el-menu-item index="/map">
        <i class="el-icon-map-location"></i>
        <span slot="title">电子地图</span>
      </el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
export default {
  name: "Aside",
  props: {
    isCollapse: Boolean,
    logoTextShow:Boolean
  },
  methods:{
    handleSelect(index){

    }
  }
}
</script>

<style scoped>

</style>