<template>
  <footer class="footer">
    <span class="copyright">&copy; 2020 SSAFY 2반 10팀 공동제작</span>
    <ul class="footer-links list-inline float-right">
      <li class="list-inline-item"><a href="http://edu.ssafy.com/edu/main/index.do" target="_blank">ssafy page 이동</a></li>
    </ul>
  </footer>
</template>

<script>
export default {
  name: 'PageFooter'
}
</script>

<style lang="scss" scoped>
.footer {
  width: 100%;
  font-size: 13px;
  color: #666;
  line-height: 40px;
  border-top: 1px solid #ddd;
  margin-top: 50px;

  .copyright {
    margin-left: 130px;
  }

  .list-inline-item {
    margin-right: 150px;
  }

  a {
    color: #666;
  }
}
</style>
