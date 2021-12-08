<template>
  <section class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <h1>Q&A</h1>
    <happy-search></happy-search>
    <transition-group name="list" tag="ul">
      <li v-for="board in searchlist" :key="board.no" class="shadow">
        <span @click="showBoard(board.no)">{{ board.no }}. {{ board.title }}</span>

        <span
          v-if="id == board.userid || id == 'admin'"
          class="removeBtn"
          type="button"
          @click="removeBoard(board.no)"
        >
          <i class="far fa-trash-alt" aria-hidden="true"></i>
        </span>
      </li>
    </transition-group>
  </section>
</template>

<script>
import { mapActions } from "vuex";
import HappySearch from "./HappySearch.vue";

export default {
  computed: {
    searchlist() {
      return this.$store.state.searchlist;
    },
    id() {
      return this.$store.state.id;
    },
  },
  created() {
    this.$store.dispatch("ALLBOARD");
  },
  methods: {
    ...mapActions({ removeBoard: "DELETEBOARD" }),
    showBoard(no) {
      this.$router.push("/happydetail/" + no);
    },
  },
  components: {
    HappySearch,
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
