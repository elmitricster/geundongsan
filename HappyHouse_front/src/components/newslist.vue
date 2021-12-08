<template>
  <section class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <h1 style="margin-bottom: 30px">주택 관련 오늘의 뉴스</h1>
    <transition-group name="list" tag="ul">
      <li v-for="news in newslist" :key="news.no" class="shadow">
        <a v-bind:href="news.url" target="_blank">{{ news.no }}. {{ news.title }}</a>
      </li>
    </transition-group>
  </section>
</template>

<script>
export default {
  data() {
    return {
      newslist: [],
    };
  },
  created() {
    this.$store.dispatch("ALLNEWS").then(() => {
      this.newslist = this.$store.state.newslist;
    });
  },
};
</script>

<style scoped>
.del {
  text-decoration: line-through;
}
ul {
  list-style-type: none;
  padding-left: 0px;
  margin-top: 0;
  text-align: left;
}
li {
  display: flex;
  min-height: 50px;
  height: 50px;
  line-height: 50px;
  margin: 0.5rem 0;
  padding: 0 0.9rem;
  background: white;
  border-radius: 5px;
  margin-bottom: 20px;
}
.checkBtn {
  line-height: 45px;
  color: #62acde;
  margin-right: 5px;
}
.removeBtn {
  margin-left: auto;
  color: #de4343;
}

.list-enter-active,
.list-leave-active {
  transition: all 1s;
}
.list-enter,
.list-leave-to {
  opacity: 0;
  transform: translateY(30px);
}
</style>
