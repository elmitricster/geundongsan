<template>
  <center class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <div class="container">
      <div class="row">
        <form method="post" action="#">
          <table class="table table-bordered" style="text-align: center; border: 1px solid #dddddd">
            <thead>
              <tr>
                <th colspan="2" style="background-color: #bfcba8; color: white; text-align: center">
                  공지사항 게시글 등록하기
                </th>
              </tr>
            </thead>

            <tbody>
              <tr>
                <td>
                  <input
                    type="text"
                    class="form-control"
                    v-model="title"
                    name="bbsTitle"
                    maxlength="50"
                  />
                </td>
              </tr>

              <tr>
                <td>
                  <textarea
                    class="form-control"
                    v-model="content"
                    name="bbsContent"
                    maxlength="2048"
                    style="height: 350px"
                  ></textarea>
                </td>
              </tr>
            </tbody>
          </table>

          <button class="btn btn-group btn-dark btn-animated" @click="addNotice">글쓰기</button>
        </form>
      </div>
    </div>
  </center>
</template>

<script>
export default {
  data() {
    return {
      title: "",
      content: "",
    };
  },
  methods: {
    addNotice() {
      if (this.title != "" && this.content != "") {
        this.$store.dispatch("ADDNOTICE", { title: this.title, content: this.content });
        this.clearInput();
        this.$router.push("/noticelist");
      } else {
        alert("내용을 넣어주세요!");
      }
    },

    clearInput() {
      this.title = "";
      this.content = "";
    },
  },
};
</script>

<style scoped>
table td {
  background-color: #dfe5d3;
}
</style>
