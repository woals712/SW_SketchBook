<template>
    <div class="card" :class="{ 'full': full }" @mouseover="hover = true" @mouseleave="hover = false" @click="goToEvent">
      <div class="card-image"
      :style="{ 'background-image': 'url(' + getImgUrl(event.outerImage) + ')' }"
      >
      </div>
      <div class="card-body">
        <div class="close-btn mx-2" @click.stop="goBack" v-if="full">&#10006;</div>
        <div class="title" v-if="full || !hideTitle">{{ event.title.toUpperCase() }}</div>
        <div class="footer">
          <transition name="slide-right">
            <div class="extra" v-if="hover || full">
              <div v-html="computedDetail"></div>
              <p v-if="full">프로젝트 기간 : {{ event.date.split(',')[0] }}</p>
            </div>
          </transition>
          <p class="date" v-if="!full">
            {{ event.date.split(',')[0] }}
          </p>
        </div>

        <transition name="slide-left" tag="div" v-if="full">
          <div class="info">
            <div class="text">
              <br><h2 class="text-title">프로젝트 설명</h2>
              <br><span v-if="event.content" v-html="event.content"></span>
            </div>

            <div class="poster">
              <div class="video-container" v-if="getVideoOn">
                <iframe width="560" height="315" :src="'https://' + event.youtubeLink" frameborder="0" allowfullscreen></iframe>
              </div>
              <img v-if="!getVideoOn" :src="getImgUrl(event.innerImage)" :alt="event.title" class="w-100">
            </div>
          </div>
        </transition>
        <br>
        <button class="yButton" v-if="full && event.youtubeLink" v-on:click="yLink(event.youtubeLink)"><img :src="getImgUrl('assets/img/youtube.png')"></button>
        <button class="gButton" v-if="full && event.githubLink" v-on:click="gLink(event.githubLink)"><img :src="getImgUrl('assets/img/github.png')"></button>
        <button class="eButton" v-if="full && this.$store.state.isLogin && this.$store.state.tempname"><router-link :to="{ name: 'updateProject', params: { id: event.id } }" class="text-white"><img :src="getImgUrl('assets/img/edit.png')"></router-link></button>
        <button class="dButton" v-if="full && this.$store.state.isLogin && this.$store.state.tempname" @click="deleteProject"><img :src="getImgUrl('assets/img/remove.png')"></button>
      </div>
    </div>
</template>

<script>
import axios from 'axios'

export default {
  props: {
    event: Object,
    buttonText: {
      type: String
    },
    hideTitle: {
      type: Boolean,
      default: false
    },
    hideButton: {
      type: Boolean,
      default: false
    }
  },
  data: function () {
    return {
      full: false,
      hover: false,
    }
  },
  computed: {
    computedDetail () {
      return this.event.details.split('\n').join('<br />')
    },
    getVideoOn: function () {
      if (this.event.youtubeLink) {
        return true
      }
      return false
    },
  },
  created: function () {
    if (this.event.title === this.$route.query.event) {
      this.full = true
    }
  },
  methods: {
    deleteProject () {
      this.$swal({
        title: '정말 삭제하시겠습니까?',
        text: "더 이상 복구할 수 없습니다",
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#3085d6',
        cancelButtonColor: '#d33',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'
      }).then((result) => {
        if (result.value) {
          this.$swal(
            '삭제되었습니다!',
            '파일이 삭제되었습니다',
            'success'
          )
          axios.delete(`/portfolio/${this.event.id}`)
            .then(res => {
              this.$router.push('/project')
            })
        }
      })
    },
    goToEvent: function () {
      this.full = true
      this.$emit('onOpen', this.event.title)
    },
    goBack: function () {
      this.full = false
      this.$emit('onClose')
    },
    getImgUrl: function (img) {
      if (img.startsWith('https://') || img.startsWith('http://')) {
        return img
      }
      return require('@/' + img)
    },
    onButtonClick: function () {
      this.$emit('buttonClicked')
    },
    yLink: function (vd) {
      var temp = vd.split('/').reverse()
      window.open("https://www.youtube.com/embed/" + temp[0], "_blank")
    },
    yReturn: function (vd) {
      var temp = vd.split('/').reverse()
      return "https://www.youtube.com/embed/" + temp[0]
    },
    gLink: function (vd) {
      if (vd.startsWith('https://') || vd.startsWith('http://')) {
        window.open(vd, "_blank")
        return
      }
      window.open("https://" + this.event.githubLink, "_blank")
    }
  }
}
</script>

<style scoped>
.card {
  position: relative;
  height: 400px;
  width: 300px;
  box-shadow: 0 5px 20px rgba(120, 120, 120, 0.4);
  border: none;
  border-radius: 20px;
  margin: 20px;
  cursor: pointer;
  transition: all 500ms ease-out;
}
.card:hover {
  transform: scale(1.02);
  box-shadow: 0 10px 50px rgba(120, 120, 120, 0.4);
}
.card:hover .card-image::after {
  background: rgba(0, 0, 0, 0.6);
}
.card:hover .title {
  font-size: 1.5em;
}
.card:hover .title::after {
  bottom: -15px;
}
.card:hover .date {
  font-size: 1.6em;
  opacity: 1;
}
.card-image {
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  background-position: center;
  background-size: cover;
}
.card-image::after {
  content: '';
  position: absolute;
  width: 100%;
  height: 100%;
  border-radius: 20px;
  background: rgba(0, 0, 0, 0.2);
  transition: all 800ms ease-out;
}

.card-body {
  color: rgba(252, 253, 253, 0.692);
  font-weight: bold;
  padding: 30px;
  z-index: 2;
}
.title {
  position: relative;
  text-align: center;
  color: white;
  font-weight: 700;
  font-size: 1.4em;
  margin-top: 30px;
  transition: all 200ms ease-out;
}
.title::after {
  content: '';
  position: absolute;
  left: 50%;
  transform: translateX(-50%);
  bottom: -30px;
  width: 20%;
  height: 3px;
  background: white;
  transition: all 200ms ease-out;
}
.footer {
  position: absolute;
  bottom: 20px;
  background: transparent;
}
.extra {
  margin: 20px 0;
  font-size: 1em;
}
.date {
  font-size: 1.2em;
  color: white;
  transition: font-size 200ms ease-out;
}
.yButton {
  margin-right: 5px;
  background: white;
}
.yButton:hover {
  background: darkred;
}
.gButton {
  margin-right: 5px;
}
.gButton:hover {
  background: rgba(252, 253, 253, 0.692);
}
.eButton {
  margin-right: 5px;
  background: yellow;
}
.eButton:hover {
  background: darkorange;
}
.dButton {
  margin-right: 5px;
  background: red;
}
.dButton:hover {
  background: darkred;
}

/* Animations */
.slide-right-enter-active, .slide-right-leave-active {
  transition: all 300ms ease-out;
}
.slide-right-enter, .slide-right-leave-to {
  transform: translateX(-30px);
  opacity: 0;
}
.slide-left-enter-active {
  transition: all 400ms ease 500ms;
}
.slide-left-leave-active {
  transition: all 50ms;
}
.slide-left-enter {
  transform: translateX(100px);
  opacity: 0;
}
.slide-left-leave-to {
  opacity: 0;
}

/* Full screen card */
.card.full {
  position: relative;
  /* top: 0;
  left: 0; */
  min-height: 100vh;
  height: fit-content;
  width: 100%;
  box-shadow: 0 0 0 white;
  border-radius: 0;
  margin: 0;
  cursor: auto;
  z-index: 10;
  transition: all 800ms ease;
}
.card.full:hover {
  transform: scale(1);
  box-shadow: 0 0 0 white;
}
.card.full .card-image, .card.full .card-image::after {
  border-radius: 0;
}
.card.full .card-image::after {
  background: rgba(0, 0, 0, 0.90);
}
.card.full .title {
  text-align: left;
  font-size: 1.5em;
  transition: all 500ms ease-out;
}
.card.full .title::after {
  left: 0;
  transform: translateX(0);
  bottom: -15px;
  transition: all 500ms ease-out;
}
.card.full .footer {
  position: relative;
  margin-top: 60px;
}
.card.full .date {
  font-size: 1.6em;
}

.info {
  display: flex;
  color: rgba(252, 253, 253, 0.692);
}
.info .text-title {
  color: white;
}
.text {
  order: 2;
  width: 50vw;
}
.poster {
  width: 40vw;
  height: fit-content;
  border: 1px solid white;
  margin-top: -20vh;
  margin-left: auto;
  margin-right: 30px;
  order: 3;
}

.poster img {
  width: 100%;
}

.btn {
  color: white;
  font-weight: bold;
  padding: 10px 50px;
  transition: background-color 200ms ease-out;
  border-radius: 50px;
  box-shadow: 1px 1px 6px rgba(0, 0, 0, 0.20);
  cursor: pointer;
}
.close-btn {
  position: absolute;
  top: 10px;
  right: 30px;
  margin: 10px;
  font-size: 1.4em;
  color: white;
  cursor: pointer;
}
.close-btn:hover {
  color: red;
}
.register {
  position: relative;
  margin: 20px 0;
  border-radius: 5px;
  padding: 20px 50px;
  background: black;
  border: 1px solid blue;
  box-shadow: 0 0 15px rgba(0, 0, 255, 0.5);
}
.register:hover {
  color: white;
  background: blue;
  border: 1px solid blue;
}
.video-container { margin: 0;padding-bottom: 75%; max-width: 100%; height: 0; position: relative;overflow: hidden;}
.video-container iframe,
.video-container object,
.video-container embed { margin: 0;padding: 0; width: 100%; height: 95%;position: absolute; top: 0;left: 0; }

@media screen and (max-width: 768px) {
  .card {
    width: 80vw;
  }
  .info {
    flex-direction: column;
  }
  .poster {
    margin: 0 auto;
    margin-bottom: 30px;
    width: 80vw;
    order: 1;
  }
  .text {
    width: 80vw;
  }
  .register {
    margin: 30px 0;
  }
}
</style>
