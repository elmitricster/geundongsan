<template>
  <div class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>글 번호</th>
          <td>
            {{ board.no }}
          </td>
          <th>작성자</th>
          <td>
            {{ board.userid }}
          </td>
          <th>등록일</th>
          <td>
            {{ board.write_date }}
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td colspan="5">
            {{ board.title }}
          </td>
        </tr>
        <tr>
          <th style="vertical-align: middle">내용</th>
          <td height="500" valign="top" colspan="5" style="text-align: left">
            {{ board.content }}
          </td>
        </tr>
      </tbody>
    </table>
    <button
      v-if="id == this.board.userid || id == 'admin'"
      class="btn btn-group btn-dark btn-animated"
      @click="editBoard"
      id="editBtn"
    >
      수정
    </button>
  </div>
</template>

<script>
export default {
  data() {
    return {
      board: "",
    };
  },
  created() {
    //action call
    var no = this.$route.params.no; //이전 화면에서 파라미터 받아옴
    this.$store.dispatch("SHOWBOARD", { no: no }).then(() => {
      this.board = this.$store.state.board;
    });
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
  },
  methods: {
    editBoard() {
      console.log("메소드출력");
      this.$router.push("/happyedit");
    },
  },
};
</script>
<style scoped>
table th {
  background-color: #bfcba8;
  color: white;
  font-size: 1.1em;
  border: 2px solid white;
}
table td {
  background-color: #dfe5d3;
  border: 2px solid white;
}
</style>
