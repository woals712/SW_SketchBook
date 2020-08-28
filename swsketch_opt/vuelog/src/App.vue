<template>
  <div class="content-head">
    <transition name="fade" mode="out-in" appear>
      <header-page></header-page>
    </transition>
    <div class="content-body">
      <transition name="fade" mode="out-in" @before-leave="closeSideMenu" @before-enter="resetScroll" appear>
        <router-view :key="$route.fullPath"></router-view>
      </transition>
    </div>
    <transition name="fade" mode="out-in" appear>
      <footer-page></footer-page>
    </transition>
  </div>
</template>

<script>
import HeaderPage from './components/HeaderPage'
import FooterPage from './components/FooterPage'

export default {
  name: 'app',

  components: {
    HeaderPage,
    FooterPage
  },

  created () {
    this.$bus.$on('myDataFetched', myData => {
      // Initializing the real time connection
      this.$rt.init(myData.settings.realTimeServerUrl, myData.user.token)
    })
    // window.onbeforeunload = function () {
    //   localStorage.clear()
    // }
  },

  methods: {
    closeSideMenu () {
      this.$store.dispatch('sideMenu', false)
    },

    resetScroll () {
      window.scrollTo(0, 0)
    }
  }
}
</script>

<style lang="stylus" scoped>
  .content-head
    min-height 100%
    display flex
    flex-direction column
    align-items center

  .content-body
    flex 1
    display flex
    flex-direction column
    padding 100px 30px 15px
    width 100%

  @media screen and (max-width: 999px)
    .content-body
      padding-top 50px
      padding-bottom 15px
</style>
