<template>
  <div class="inputBox shadow">
    <input
      type="text"
      v-model="newWord"
      placeholder="검색어를 입력하세요"
      @keyup.enter="searchBoard"
    />
    <button class="btn btn-group btn-outline-dark btn-animated ml-1" @click="searchBoard">
      검색
    </button>
    <button
      v-if="id != null"
      class="addContainer btn btn-group btn-dark btn-animated mr-1"
      @click="addBoard"
    >
      글쓰기
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      newWord: "",
    };
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
  },
  methods: {
    searchBoard() {
      if (this.newWord != "") {
        this.$store.dispatch("SEARCHBOARD", this.newWord);
        this.clearInput();
        // this.$router.push("/happylist");
      } else {
        alert("찾을 값을 입력하세요!");
      }
    },
    addBoard() {
      this.$router.push("/happyinput");
    },
    clearInput() {
      this.newWord = "";
    },
  },
};
</script>

<style scoped>
input {
  outline: none;
  background: gainsboro;
}
.inputBox {
  background: white;
  height: 60px;
  line-height: 40px;
  border-radius: 5px;
  vertical-align: middle;
}
.inputBox input {
  border-style: none;
  font-size: 0.9rem;
  width: 500px;
  margin-top: 10px;
}
.addContainer {
  float: right;
  display: inline-block;
  margin-top: 13px;
}
.addBtn {
  color: white;
  vertical-align: middle;
}
</style>
