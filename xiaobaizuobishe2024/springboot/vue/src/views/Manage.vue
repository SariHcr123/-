<template>

    <el-container style="min-height: 100vh">
      <el-aside :width="sideWidth+'px'" style="background-color: rgb(238, 241, 246);">
        <Aside :isCollapse="isCollapse" :logoTextShow="logoTextShow"/>
      </el-aside>

      <el-container>
        <el-header style="border-bottom: 1px solid #ccc;">
        <Header :collapseBtnClass="collapseBtnClass" :collapse="collapse" :user="user"/>

        </el-header>

        <el-main>
<!--          表示当前页面的子路由在<router-view/>里面展示-->
          <router-view @refreshUser="getUser" />
        </el-main>
      </el-container>
    </el-container>

</template>

<script>

import Aside from "@/components/Aside";
import Header from "@/components/Header";

export default {
  name: 'HomeView',

  data(){

    return{
      collapseBtnClass:'el-icon-s-fold',
      isCollapse:false,
      logoTextShow:true,
      sideWidth:200,
      user: {}
    }
  },
  components:{
    Aside,
    Header
  },
  created() {
    //从后台获取最新的User数据
    this.getUser()
  },
  methods:{
    collapse(){
  this.isCollapse=!this.isCollapse
      if(this.isCollapse){
        this.sideWidth=64
        this.collapseBtnClass='el-icon-s-unfold'
      }else{
        this.sideWidth = 200
        this.collapseBtnClass='el-icon-s-fold'
      }
    },
    getUser() {
      let username = localStorage.getItem("user")?JSON.parse(localStorage.getItem("user")).username : ""
      //从后台获取User数据
      this.request.get("/user/username/" + username).then(res => {
        //重新赋值后台的最新User数据
        this.user = res.data
      })
    }
  }
}
</script>

