<template>
  <div class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <table class="table table-bordered">
      <tbody>
        <tr>
          <th>글 번호</th>
          <td>
            {{ notice.no }}
          </td>
          <th>작성자</th>
          <td>
            {{ notice.userid }}
          </td>
          <th>등록일</th>
          <td>
            {{ notice.write_date }}
          </td>
        </tr>
        <tr>
          <th>제목</th>
          <td colspan="5">
            {{ notice.title }}
          </td>
        </tr>
        <tr>
          <th style="vertical-align: middle">내용</th>
          <td height="500" valign="top" colspan="5" style="text-align: left">
            {{ notice.content }}
          </td>
        </tr>
      </tbody>
    </table>
    <button
      v-if="id == 'admin'"
      class="btn btn-group btn-dark btn-animated"
      @click="editNotice"
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
      notice: "",
    };
  },
  created() {
    //action call
    var no = this.$route.params.no; //이전 화면에서 파라미터 받아옴
    this.$store.dispatch("SHOWNOTICE", { no: no }).then(() => {
      this.notice = this.$store.state.notice;
    });
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
  },
  methods: {
    editNotice() {
      console.log("메소드출력");
      this.$router.push("/noticeedit");
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
