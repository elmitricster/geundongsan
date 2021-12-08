<template>
  <div class="container p-3 border" style="margin-top: 100px; margin-bottom: 40px">
    <br />
    <h1 align="center">회원가입</h1>
    <br />
    <form action="#" id="f" method="post">
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
            placeholder=""
            required
            name="userid"
            v-model="userid"
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
            placeholder="영문 숫자 포함 6자리 이상"
            v-model="userpwd"
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
            placeholder="User Name"
            required
            name="username"
            v-model="username"
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
            placeholder="address"
            required
            name="address"
            v-model="address"
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
            type="email"
            class="form-control"
            id="inputTel"
            placeholder="***@***"
            required
            name="email"
            v-model="email"
          />
        </div>
      </div>
      <div class="form-group row">
        <div class="ml-md-auto col-md-4">
          <button class="btn btn-group btn-dark btn-animated" id="loginBtn" @click="registermember">
            회원가입
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
  methods: {
    registermember() {
      this.$store.dispatch("REGISTERMEMBER", {
        userid: this.userid,
        userpwd: this.userpwd,
        username: this.username,
        address: this.address,
        email: this.email,
      });
      this.$router.push("/");
    },
  },
};
</script>

<style></style>
