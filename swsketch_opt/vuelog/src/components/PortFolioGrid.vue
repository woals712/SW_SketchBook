<template>
  <transition-group name="fade" mode="out-in" tag="div" class="main-container">
    <div class="event-container" :class="{ 'full-container': (full && selected == event.title) }" v-for="event in events" :key="event.title">
      <PortFolio
        :event="event"
        :buttonText="buttonText"
        :hideTitle="hideTitle"
        :hideButton="hideButton"
        @onOpen="onOpen"
        @onClose="onClose"
        @buttonClicked="$emit('buttonClicked')"
        v-if="(!full) || (full && selected == event.title)"
      />
    </div>
  </transition-group>
</template>

<script>
import PortFolio from './PortFolio'

export default {
  components: {
    PortFolio
  },
  props: {
    events: Array,
    buttonText: {
      type: String,
      default: 'Register'
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
      selected: "",
      full: false,
    }
  },
  methods: {
    onOpen: function (value) {
      if (!this.full) {
        this.full = true
        this.selected = value
        this.$router.push({ path: "/project", query: { event: value } })
      }
    },
    onClose: function () {
      setTimeout(() => {
        this.full = false
      }, 400)
      this.$router.push({ path: "/project", query: {} })
    }
  },
  created: function () {
    if (this.$route.query.event) {
      this.full = true
      this.selected = this.$route.query.event
    }
  }
}
</script>

<style scoped>
.main-container {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}
.full-container {
  position: fixed;
  height: 99vh;
  width: 100vw;
  padding-top: 20px;
  padding-left: 20px;
  padding-right: 20px;
  padding-bottom: 20px;

  overflow-y: scroll;
  overflow: -moz-scrollbars-none;
  -ms-overflow-style: none;
  transition: all 1s ease;
}
.full-container::-webkit-scrollbar { width: 0 !important }

.fade-move {
  transition: transform 800ms ease;
}
</style>
