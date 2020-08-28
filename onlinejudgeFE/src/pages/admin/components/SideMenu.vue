<template>
  <el-menu class="vertical_menu"
           :router="true" :default-active="currentPath">
    <div class="logo">
      <img :src="`../static/profile.png`" alt="oj admin" v-on:click="hLink"/>
    </div>
    <el-menu-item index="/"><i class="el-icon-menu"></i>{{$t('m.User')}} 관리</el-menu-item>
    <el-submenu index="problem" v-if="hasProblemPermission">
      <template slot="title"><i class="el-icon-fa-bars"></i>{{$t('m.Problem')}}</template>
      <el-menu-item index="/problems">{{$t('m.Problem_List')}}</el-menu-item>
      <el-menu-item index="/problem/create">{{$t('m.Create_Problem')}}</el-menu-item>
    </el-submenu>
  </el-menu>
</template>

<script>
  import {mapGetters} from 'vuex'

  export default {
    name: 'SideMenu',
    data () {
      return {
        currentPath: ''
      }
    },
    mounted () {
      this.currentPath = this.$route.path
    },
    methods: {
      hLink: function () {
        window.open('/', '_self')
      }
    },
    computed: {
      ...mapGetters(['user', 'isSuperAdmin', 'hasProblemPermission'])
    }
  }
</script>

<style scoped lang="less">
  .vertical_menu {
    overflow: auto;
    width: 205px;
    height: 100%;
    position: fixed !important;
    z-index: 100;
    top: 0;
    bottom: 0;
    left: 0;
    .logo {
      margin: 20px 0;
      text-align: center;
      cursor: pointer;
      img {
        background-color: #fff;
        border-radius: 50%;
        border: 3px solid #fff;
        width: 75px;
        height: 75px;
      }
    }
  }
</style>
