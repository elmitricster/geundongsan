<template>
  <div>
    <section class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
      <h1>공지사항</h1>
      <transition-group name="list" tag="ul">
        <li v-for="notice in noticelist" :key="notice.no" class="shadow">
          <span @click="showNotice(notice.no)">{{ notice.no }}. {{ notice.title }}</span>
          <span
            v-if="id == 'admin'"
            class="removeBtn"
            type="button"
            @click="removeNotice(notice.no)"
          >
            <i class="far fa-trash-alt" aria-hidden="true"></i>
          </span>
        </li>
      </transition-group>
      <span v-if="id == 'admin'" class="addContainer" @click="addNotice">
        <i class="addBtn fas fa-plus" aria-hidden="true"></i>
      </span>
    </section>
  </div>
</template>

<script>
import { mapActions } from "vuex";

export default {
  created() {
    this.$store.dispatch("ALLNOTICE");
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
    noticelist() {
      return this.$store.state.noticelist;
    },
  },
  methods: {
    ...mapActions({ removeNotice: "DELETENOTICE" }),
    showNotice(no) {
      this.$router.push("/noticedetail/" + no);
    },
    addNotice() {
      this.$router.push("/noticeinput");
    },
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
