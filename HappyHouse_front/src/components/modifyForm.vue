<template>
  <div class="container p-3 border" style="margin-top: 100px; margin-bottom: 40px">
    <br />
    <h1 align="center">회원정보</h1>
    <br />
    <form action="modifyProcess" method="post">
      <div class="form-group has-feedback row">
        <label for="inputId" class="col-md-4 control-label text-md-right col-form-label"
          >아이디
          <span class="text-danger small">*</span>
        </label>
        <div class="col-md-5">
          <input
            type="text"
            class="form-control"
            id="inputId"
            v-model="userid"
            required
            name="userid"
          />
        </div>
      </div>
      <div class="form-group has-feedback row">
        <label for="inputPW" class="col-md-4 control-label text-md-right col-form-label"
          >비밀번호
          <span class="text-danger small">*</span>
        </label>
        <div class="col-md-5">
          <input
            type="password"
            class="form-control"
            id="inputPW"
            v-model="userpwd"
            placeholder="****"
            required
            name="userpwd"
          />
        </div>
      </div>
      <div class="form-group has-feedback row">
        <label for="inputUserName" class="col-md-4 control-label text-md-right col-form-label"
          >이름
          <span class="text-danger small">*</span>
        </label>
        <div class="col-md-5">
          <input
            type="text"
            class="form-control"
            id="inputUserName"
            v-model="username"
            required
            name="username"
          />
        </div>
      </div>
      <div class="form-group has-feedback row">
        <label for="inputEmail" class="col-md-4 control-label text-md-right col-form-label"
          >주소
          <span class="text-danger small">*</span>
        </label>
        <div class="col-md-5">
          <input
            type="text"
            class="form-control"
            id="inputEmail"
            v-model="address"
            required
            name="address"
          />
        </div>
      </div>
      <div class="form-group has-feedback row">
        <label for="inputTel" class="col-md-4 control-label text-md-right col-form-label"
          >이메일
          <span class="text-danger small">*</span>
        </label>
        <div class="col-md-5">
          <input
            type="text"
            class="form-control"
            id="inputTel"
            v-model="email"
            required
            name="email"
          />
        </div>
      </div>
      <div class="form-group row">
        <div class="ml-md-auto col-md-4">
          <button
            @click="modifyProcess"
            id="confirmBtn"
            class="btn btn-group btn-dark btn-animated"
            style="margin-right: 5px"
          >
            수정
          </button>

          <button id="deleteBtn" class="btn btn-group btn-dark btn-animated" @click="deleteProcess">
            탈퇴
          </button>
        </div>
      </div>
    </form>
  </div>
</template>

<script>
export default {
  data() {
    return {
      userid: "",
      userpwd: "",
      username: "",
      address: "",
      email: "",
    };
  },
  created() {
    this.$store.dispatch("GETMEMBER").then(() => {
      this.userid = this.$store.state.member.userid;
      this.userpwd = this.$store.state.member.userpwd;
      this.username = this.$store.state.member.username;
      this.address = this.$store.state.member.address;
      this.email = this.$store.state.member.email;
    });
  },
  methods: {
    modifyProcess() {
      this.$store.dispatch("EDITMEMBER", {
        userid: this.userid,
        userpwd: this.userpwd,
        username: this.username,
        address: this.address,
        email: this.email,
      });
      this.$router.push("/");
    },
    deleteProcess() {
      this.$store.dispatch("DELMEMBER");
      this.$router.push("/");
    },
    getmember() {
    },
  },
};
</script>

<style></style>
