<template>
  <div class="card">
    <div class="card-body">
      <header class="row">
        <img :src="user.avatar_url" class="avatar" alt="" />
        <div class="field-container">
          <div>
            <h3>{{ user.name || '이름 없음' }}</h3>
            <a :href="user.html_url" style="float:right;padding-right:20px;">
              <button class="btn btn-outline-dark">팔로우</button>
            </a>
          </div>
          <p>{{ user.bio || '자기 소개 없음' }}</p>
          <hr class="intermediaire" />
          <div class="field-important">
            <p>
              <span class="label bold"> 회사&nbsp;: </span
              ><span class="bold">
                {{ user.company || '없음' }}
              </span>
            </p>
            <p>
              <span class="label bold">팔로워&nbsp;:</span>&nbsp;<span class="counter">{{ user.followers || 0 }}</span>
            </p>
            <p>
              <span class="label bold">팔로잉&nbsp;:</span>&nbsp;<span class="counter">{{ user.following || 0 }}</span>
            </p>
            <p>
              <span class="label bold">만든 날짜&nbsp;:</span>&nbsp;<span>{{ user.created_at.format('LL') }}</span>
            </p>
          </div>
        </div>
      </header>
    </div>
    <hr />
    <div class="card-body row menu-container">
      <div :class="{ active: activeMenu === 0 }" @click="activeMenu = 0">
        <span>Last repositories</span>
      </div>
      <div :class="{ active: activeMenu === 1 }" @click="activeMenu = 1">
        <span>Last stars</span>
      </div>
      <div :class="{ active: activeMenu === 2 }" @click="activeMenu = 2">
        <span>Last commits</span>
      </div>
    </div>
    <hr />
    <div class="card-body row container row-container">
      <ReposList v-if="activeMenu === 0" :repos="user.repos" :user="user" style="width:100%;"/>
      <ReposList v-if="activeMenu === 1" :repos="user.starred" :user="user" type="star" style="width:100%;"/>
      <EventsList v-if="activeMenu === 2" :events="user.events" :user="user" style="width:100%;"/>
    </div>
  </div>
</template>

<script>
import ReposList from '../item/ReposList/ReposList'
import EventsList from '../item/EventsList/EventsList'

export default {
  components: {
    ReposList,
    EventsList
  },
  props: {
    user: {
      type: Object,
      default: () => {}
    }
  },
  data () {
    return {
      activeMenu: 0
    }
  }
}
</script>

<style scoped>
.card {
  background: #ffffff;
  margin: 20px 0;
  padding-top: 10px;
  padding-left: 15px;
  padding-right: 15px;
  border: 2px solid #cecece;
}

.card-body {
  background: #ffffff;
  overflow: hidden;
  padding: 10px;
}

.menu-container {
  display: flex;
}

.menu-container > div {
  flex: 1;
  padding: 5px;
  text-align: center;
}

hr:not(.intermediaire) {
  margin: 0 !important;
  border: 1px solid #cecece;
}

hr {
  margin-top: 1.5rem;
  margin-bottom: 1.5rem;
}

.field-container {
  flex: 1;
  margin-left: 20px;
}

header {
  display: flex;
  height: 270px;
}

.field-important p {
  margin-bottom: 10px;
}

p {
  margin: 0;
}

.counter {
  display: inline-block;
  padding: 2px 5px;
  font-size: 12px;
  font-weight: 600;
  line-height: 1;
  color: #586069;
  background-color: rgba(27, 31, 35, 0.08);
  border-radius: 20px;
}

.menu-container > div span {
  color: #586069;
  opacity: 0.7;
  cursor: default;
}
.menu-container > div.active span {
  color: #000 !important;
  opacity: 1;
}

header img.avatar {
  height: 100%;
  border-radius: 6px !important;
}

.avatar {
  display: flex;
}

@media only screen and (max-width: 768px) {
  .avatar {
    display: none;
  }
}
</style>
