<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-light fixed-top">
    <div class="container">
      <a class="navbar-brand ml-2" href="/">근동산</a>
      <div class="header">
        <ul v-if="id == null" class="navbar-nav ml-auto">
          <li class="nav-item">
            <span class="nav-link" @click="registerForm">sign up</span>
          </li>
          <li class="nav-item">
            <span class="nav-link" @click="loginForm">login</span>
          </li>
        </ul>

        <ul v-else class="navbar-nav ml-auto">
          <li class="nav-item">
            <span class="nav-link" @click="modifyForm">member info</span>
          </li>
          <li class="nav-item">
            <span class="nav-link" @click="logout">logout</span>
          </li>
        </ul>
      </div>
      <button
        class="navbar-toggler"
        type="button"
        data-toggle="collapse"
        data-target="#navbarResponsive"
        aria-controls="navbarResponsive"
        aria-expanded="false"
        aria-label="Toggle navigation"
        @click="searchForm"
      >
        <span><i class="fas fa-search-location"></i></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarResponsive">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item">
            <span class="nav-link" @click="noticelist">공지사항</span>
          </li>
          <li class="nav-item">
            <span class="nav-link" @click="newslist">오늘의 뉴스</span>
          </li>
          <li class="nav-item">
            <span class="nav-link" @click="happylist">Q&A</span>
          </li>
          <li class="nav-item">
            <span class="nav-link" @click="searchForm"> 검색하기</span>
          </li>
        </ul>
      </div>
    </div>
  </nav>
</template>

<script>
export default {
  created() {
    this.$store.dispatch("GETSESSION");
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
  },
  methods: {
    registerForm() {
      this.$router.push("/registerForm");
    },
    loginForm() {
      this.$router.push("/loginForm");
    },
    modifyForm() {
      this.$router.push("/modifyForm");
    },
    logout() {
      this.$store.dispatch("LOGOUT");
      this.$router.push("/");
    },
    happylist() {
      this.$router.push("/happylist");
    },
    newslist() {
      this.$router.push("/newslist");
    },
    searchForm() {
      this.$router.push("/searchForm");
    },
    noticelist() {
      this.$router.push("/noticelist");
    },
  },
};
</script>

<style scoped></style>
