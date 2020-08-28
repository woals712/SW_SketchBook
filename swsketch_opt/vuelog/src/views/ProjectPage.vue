<template>
  <keep-alive>
    <div class="home">
      <p class="plusB btn btn-danger" v-if="!tempname && wantuser && !currentUrl">{{ wantuser }} 아이디 열람 모드</p>
      <button class="plusB btn btn-primary" v-if="tempname && !currentUrl"><router-link :to="{ name: 'createProject' }" class="text-white">프로젝트 생성</router-link></button>
      <div class="title" style="padding-bottom:30px;padding-top:20px" v-if="(tempname || wantuser) && !currentUrl">
        <img src="../assets/img/projectlogo.png">
      </div>

      <PortFolioGrid :events="events" @buttonClicked="eventHandler" v-if="(tempname || wantuser)"/>
    </div>
  </keep-alive>
</template>

<script>
import PortFolioGrid from "@/components/PortFolioGrid"
import axios from 'axios'
import { mapState } from 'vuex'

export default {
  name: "project",
  components: {
    PortFolioGrid,
  },
  data: function () {
    return {
      events: [],
      currentUrl: window.location.href.indexOf('event') + 1,
    }
  },
  computed: {
    ...mapState(['username']),
    ...mapState(['tempname']),
    ...mapState(['wantuser'])
  },
  methods: {
    eventHandler: function () {
      // console.log('button clicked')
    },
    makePO: function () {
      this.$router.push('/projectcreate')
    }
  },
  created () {
    if (this.$store.state.tempname) {
      axios.get(`/${this.$store.state.tempname}/portfolio`)
        .then(res => {
          // console.log(this.events)
          this.events = res.data.PortfolioList
          if (!Object.keys(this.events).length) {
            this.makePO()
          }
        })
    } else if (this.$store.state.wantuser) {
      axios.get(`/${this.$store.state.wantuser}/portfolio`)
        .then(res => {
          // console.log(this.events)
          this.events = res.data.PortfolioList
        })
        .catch((ex) => {
          this.$swal({
            icon: "error",
            title: this.$store.state.wantuser + "는 없는 유저 입니다. 다른 유저를 검색해 주세요",
          })
          this.$router.push('/')
        })
    }
  }
}
</script>

<style scoped>
.title {
  color: white;
  text-align: center;
  font-size: 5vw;
  font-weight: 700;
  padding: 30px 0;
}

.btn-primary {
  margin-top: 10px;
  margin-right: 20px;
  top: 25;
  right: 0;
}

@media only screen and (max-width: 768px) {
  .title {
    display: none;
    font-size: 10vw;
    text-align: center;
  }
}
</style>
