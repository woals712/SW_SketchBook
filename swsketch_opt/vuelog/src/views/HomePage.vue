<template>
  <div class="home">

    <div class="title" style="padding-bottom:30px;padding-top:20px">
      <img class="hideContent" src="../assets/img/titlelogo.png">
    </div>

    <div class="VueGridLayout-container margin-right">
      <GridLayout :layout="layoutLeft" :col-num="6" :colWidth="BASE_LEN" :row-height="BASE_LEN" :is-draggable="true" :is-resizable="false" :is-mirrored="false" :vertical-compact="true" :margin="[0, 0]" :use-css-transforms="true">
        <GridItem v-for="item in layoutLeft" :x="item.x" :y="item.y" :w="item.w" :h="item.h" :i="item.i" :key="item.i">
          <div class="container">
            <MetroTile :width="item.width" :height="item.height" :length="item.length" :rotateX="item.rotateX" :faceStyle="item.faceStyle" @click="handleClick1(item.i)">
              <template v-if="item.i === '0'">
                <div slot="front">
                  <img src="../assets/img/vue-user.png" class="image" />
                  <div class="tile-label">
                    자기 소개
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '1'">
                <div slot="front">
                  <img src="../assets/img/vue-project.png" class="image" />
                  <div class="tile-label">
                    프로젝트 소개
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '2'">
                <div slot="front">
                  <img src="../assets/img/vue-algorithm.png" class="image" />
                  <div class="tile-label">
                    알고리즘
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '3'">
                <div slot="front">
                  <img src="../assets/img/vue-spring.png" class="image" />
                  <div class="tile-label">
                    Spring
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '4'">
                <div slot="front">
                  <img src="../assets/img/vue-docker.png" class="image" />
                  <div class="tile-label">
                    Docker
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '5'">
                <div slot="front">
                  <img src="../assets/img/vue-mariadb.png" class="image" />
                  <div class="tile-label">
                    Maria-DB
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '6'">
                <div slot="front">
                  <div class="calendar">
                    <div class="calendar-week-day">
                      {{weekDay}}
                    </div>
                    <div class="calendar-month-day">
                      {{monthDay}}
                    </div>
                  </div>
                </div>
              </template>
              <template v-else-if="item.i === '7'">
                <div slot="front">
                  <div class="tile-label">
                    STUDY 게시판
                  </div>
                  <img src="../assets/img/vue-study1.png" class="store-cover-img" />
                  <div class="store-title">
                    STUDY 게시판
                  </div>
                  <div class="store-subtitle">
                    공부 한 내용 업로드&#8314;
                  </div>
                </div>
                <div slot="top">
                  <div class="tile-label">
                    STUDY 게시판
                  </div>
                  <img src="../assets/img/vue-study2.png" class="store-cover-img" />
                  <div class="store-title">
                    피드백 확인
                  </div>
                  <div class="store-subtitle">
                    댓글 기능&#8314;
                  </div>
                </div>
                <div slot="bottom">
                  <div class="tile-label">
                    STUDY 게시판
                  </div>
                  <img src="../assets/img/vue-tag.png" class="store-cover-img" />
                  <div class="store-title">
                    검색 기능
                  </div>
                  <div class="store-subtitle">
                    검색어로도 검색 가능&#8314;
                  </div>
                </div>
              </template>
            </MetroTile>
          </div>
        </GridItem>
      </GridLayout>
    </div>

    <div class="VueGridLayout-container margin-left">
      <GridLayout :layout="layoutRight" :col-num="6" :colWidth="BASE_LEN" :row-height="BASE_LEN" :is-draggable="true" :is-resizable="false" :is-mirrored="false" :vertical-compact="true" :margin="[0, 0]" :use-css-transforms="true">
        <GridItem v-for="item in layoutRight" :x="item.x" :y="item.y" :w="item.w" :h="item.h" :i="item.i" :key="item.i">
          <div class="container">
            <MetroTile
              @click="handleClick2(item.i)"
              :width="item.width"
              :height="item.height"
              :length="item.length"
              :rotateX="item.rotateX"
              :faceStyle="item.faceStyle"
              :frontStyle="item.frontStyle"
              :topStyle="item.topStyle"
              :backStyle="item.backStyle"
              :bottomStyle="item.bottomStyle"
              :perspective="item.perspective"
              v-on="item.i === '9' ? {click: redirect} : {}"
            >
              <!-- custom face for each tile in right layout-->
              <template v-if="item.i === '0'">
                <div slot="front">
                </div>
              </template>
              <template v-else-if="item.i === '1'">
                <div slot="front">
                </div>
              </template>
              <template v-else-if="item.i === '2'">
                <div slot="front">
                </div>
              </template>
              <template v-else-if="item.i === '3'">
                <div slot="front">
                </div>
              </template>
              <template v-else-if="item.i === '4'">
                <div slot="front">
                  <div class="tile-label">
                    SSAFY
                  </div>
                  <img src="../assets/img/vue-ssafy.png" class="image" />
                </div>
              </template>
              <template v-else-if="item.i === '5'">
                <div slot="front">
                  <div class="tile-label">
                    사용자 검색
                  </div>
                  <img src="../assets/img/vue-chatbot.png" class="image" />
                </div>
              </template>
              <template v-else-if="item.i === '6'">
                <div slot="front">
                  <div class="tile-label">
                    Vue
                  </div>
                  <img src="https://user-images.githubusercontent.com/6414178/45696077-51b2e580-bb95-11e8-91ab-097cc1d1b89d.png" class="image" />
                </div>
                <div slot="top">
                  <div class="tile-label">
                    Bootstrap
                  </div>
                  <img src="../assets/img/vue-bootstrap.png" class="image" />
                </div>
              </template>
              <template v-else-if="item.i === '7'">
                <div slot="front">
                  <div class="tile-label">
                    Open API
                  </div>
                  <img src="../assets/img/vue-api.png" class="image" />
                </div>
              </template>
              <template v-else-if="item.i === '8'">
                <div slot="front">
                  <div class="tile-label">
                    AWS EC2
                  </div>
                  <img src="../assets/img/vue-aws.png" class="image" />
                </div>
              </template>
              <template v-else-if="item.i === '9'">
                <div slot="front">
                  <div class="tile-label">
                    GitHub
                  </div>
                  <img src="../assets/img/vue-github.png" class="image" />
                </div>
              </template>
            </MetroTile>
          </div>
        </GridItem>
      </GridLayout>
    </div>
  </div>
</template>

<script>
import MetroTile from 'vue-metro-tile'
import VueGridLayout from 'vue-grid-layout'
import axios from 'axios'
const BASE_LEN = 65
const MARGIN = 3
const REPO_URL = 'https://github.com/'

export default {
  name: 'app',
  components: {
    MetroTile,
    GridLayout: VueGridLayout.GridLayout,
    GridItem: VueGridLayout.GridItem,
  },
  data: () => ({
    BASE_LEN,
    layoutLeft: [
      {
        x: 0,
        y: 0,
        w: 4,
        h: 2,
        i: "0",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 2,
        y: 2,
        w: 4,
        h: 2,
        i: "1",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#228b22'
        }
      },
      {
        x: 0,
        y: 4,
        w: 4,
        h: 2,
        i: "2",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#ff4500'
        }
      },
      {
        x: 4,
        y: 0,
        w: 2,
        h: 2,
        i: "3",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#ffa07a'
        }
      },
      {
        x: 4,
        y: 4,
        w: 2,
        h: 2,
        i: "4",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#9400d3'
        }
      },
      {
        x: 0,
        y: 2,
        w: 2,
        h: 2,
        i: "5",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 0,
        y: 6,
        w: 2,
        h: 2,
        i: "6",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 2,
        y: 6,
        w: 4,
        h: 2,
        i: "7",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      }
    ],
    layoutRight: [
      {
        x: 0,
        y: 0,
        w: 1,
        h: 1,
        i: "0",
        width: BASE_LEN - MARGIN * 2,
        height: BASE_LEN - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 1,
        y: 0,
        w: 1,
        h: 1,
        i: "1",
        width: BASE_LEN - MARGIN * 2,
        height: BASE_LEN - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 1,
        y: 1,
        w: 1,
        h: 1,
        i: "2",
        width: BASE_LEN - MARGIN * 2,
        height: BASE_LEN - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 0,
        y: 1,
        w: 1,
        h: 1,
        i: "3",
        width: BASE_LEN - MARGIN * 2,
        height: BASE_LEN - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#1E90FF'
        }
      },
      {
        x: 2,
        y: 0,
        w: 4,
        h: 2,
        i: "4",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#3cb371'
        }
      },
      {
        x: 0,
        y: 2,
        w: 4,
        h: 4,
        i: "5",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 4 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#cd5c5c'
        }
      },
      {
        x: 4,
        y: 2,
        w: 2,
        h: 2,
        i: "6",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        frontStyle: {
          'background-color': '#8a2be2'
        },
        topStyle: {
          'background-color': '#1E90FF'
        },
        backStyle: {
          'background-color': 'yellow'
        },
        bottomStyle: {
          'background-color': 'green'
        },
        perspective: 0
      },
      {
        x: 4,
        y: 4,
        w: 2,
        h: 2,
        i: "7",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#4169e1'
        }
      },
      {
        x: 0,
        y: 6,
        w: 4,
        h: 2,
        i: "8",
        width: BASE_LEN * 4 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#4169e1'
        }
      },
      {
        x: 4,
        y: 6,
        w: 2,
        h: 2,
        i: "9",
        width: BASE_LEN * 2 - MARGIN * 2,
        height: BASE_LEN * 2 - MARGIN * 2,
        rotateX: 0,
        faceStyle: {
          'background-color': '#000'
        }
      }
    ],
    weekDay: (() => {
      const weekDayMap = {
        0: '일요일',
        1: '월요일',
        2: '화요일',
        3: '수요일',
        4: '목요일',
        5: '금요일',
        6: '토요일'
      }
      return weekDayMap[new Date().getDay()]
    })(),
    monthDay: (() => {
      return new Date().getDate()
    })(),
    rotateIndex1: 0,
    rotateArray1: [-90, 90, 90, -90],
    rotateIndex2: 0,
    rotateArray2: [-90, 90],
  }),
  methods: {
    setRotateX (layoutItem, rotateIndexName, rotateArray) {
      layoutItem.rotateX += rotateArray[this[rotateIndexName] % rotateArray.length]
      this[rotateIndexName] += 1
    },
    handleClick1 (event) {
      if (Number(event) === 0) {
        this.$router.push('/introduce')
      } else if (Number(event) === 1) {
        this.$router.push('/project')
      } else if (Number(event) === 2) {
        window.open(`http://i3a210.p.ssafy.io:8081/`, '_self')
      } else if (Number(event) === 7) {
        this.$router.push('/study')
      }
    },
    handleClick2 (event) {
      if (Number(event) === 5) {
        this.$router.push('/logsearch')
      }
    },
    redirect () {
      window.open(REPO_URL, '_blank')
    }
  },
  created () {
    axios.get('/me')
      .then(res => {
        this.$store.state.username = res.data.user.name
      })
  },
  mounted () {
    this.interval1 = setInterval(
      () => {
        this.setRotateX(this.layoutLeft[7], 'rotateIndex1', this.rotateArray1)
      }, 3000)
    this.interval2 = setInterval(
      () => {
        this.setRotateX(this.layoutRight[6], 'rotateIndex2', this.rotateArray2)
      }, 5000)
  },
  beforeDestroy () {
    clearInterval(this.interval1)
    clearInterval(this.interval2)
  }
}
</script>

<style lang="stylus" scoped>
  .home
    text-align center
</style>

<style scoped>
.home {
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  width: 100vw;
  padding-right: 7%;
  text-align: center;
}
.backup  {
  background: #fff;
  width: 1050px;
  border-radius: 10px;
  -o-border-radius: 10px;
  -ms-border-radius: 10px;
  -moz-border-radius: 10px;
  -webkit-border-radius: 10px;
  box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -o-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -ms-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -moz-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  -webkit-box-shadow: 0px 8px 20px 0px rgba(0, 0, 0, 0.15);
  margin: 30px 0;
  position: relative;
  display: flex;
  display: -webkit-flex;
}
.title {
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  font-size: 20px;
  font-weight: 500;
  padding: 0 0 10px 0;
  color: darkblue;
}
.subtitle {
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  font-size: 17px;
  font-weight: 500;
  padding-bottom: 30px;
  color: #fff;
}
.VueGridLayout-container {
  display: inline-block;
  width: 390px;
  height: 100%;
}
.margin-right {
  margin-right: 17px
}
.container {
  margin: 3px;
  width: calc(100% - 6px);
  height: calc(100% - 6px);
}
.icon {
  position: absolute;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
}
.image {
  position: absolute;
  width: 50px;
  height: 50px;
  left: 0;
  right: 0;
  top: 0;
  bottom: 0;
  margin: auto;
}
.store-icon-right {
  position: absolute;
  right: 5px;
  bottom: 5px;
  margin: auto;
}
.store-cover-img {
  position: absolute;
  left: 8px;
  top: 10px;
  height: 50px;
  width: 50px;
}
.store-title {
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  font-size: 17px;
  font-weight: 400;
  color: #fff;
  position: absolute;
  top: 10px;
  left: 65px;
}
.store-subtitle {
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  font-size: 14px;
  font-weight: 400;
  color: rgba(255, 255, 255, 0.5);
  position: absolute;
  top: 35px;
  left: 65px;
}
.tile-label {
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  font-size: 14px;
  font-weight: 500;
  color: #fff;
  position: absolute;
  left: 8px;
  bottom: 5px;
}
.vue-GridItem.vue-grid-placeholder {
  background-color: transparent !important;
}
.calendar {
  width: 100%;
  height: 100%;
  position: absolute;
  text-align: center;
  font-family: "Segoe UI", Frutiger, "Frutiger Linotype", "Dejavu Sans", "Helvetica Neue", Arial, sans-serif;
  color: #fff;
}
.calendar .calendar-week-day {
  margin-top: 20px;
  font-size: 14px;
  font-weight: 500;
}
.calendar .calendar-month-day {
  margin-top: 15px;
  font-size: 30px;
  transform: scale(1.75);
}

.margin-left {
  display: inline-block;
}
.hideContent {
  display: inline-block;
}

@media only screen and (max-width: 768px) {
  .margin-left {
    display: none;
  }
  .hideContent {
    display: none;
  }
}
</style>
