<template>
  <div class="container p-3 border" style="margin-top: 100px; margin-bottom: 40px">
    <br />
    <h1 align="center">로그인</h1>
    <br />
    <div>
      <div class="form-group has-feedback row">
        <label for="inputId" class="col-md-4 control-label text-md-right col-form-label"
          >아이디</label
        >
        <div class="col-md-5">
          <input
            type="text"
            class="form-control"
            id="inputId"
            placeholder=""
            required
            name="id"
            v-model="mid"
          />
        </div>
      </div>
      <div class="form-group has-feedback row">
        <label for="inputPW" class="col-md-4 control-label text-md-right col-form-label"
          >비밀번호
        </label>
        <div class="col-md-5">
          <input
            type="password"
            class="form-control"
            id="inputPW"
            required
            name="pw"
            v-model="mpw"
          />
        </div>
      </div>
      <div class="form-group row">
        <div class="ml-md-auto col-md-4">
          <input
            class="btn btn-group btn-dark btn-animated"
            @click="LOGINPROCESS"
            id="loginBtn"
            value="로그인"
            type="submit"
          />
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      mid: null,
      mpw: null,
    };
  },

  methods: {
    LOGINPROCESS() {
      this.$store.dispatch("LOGINPROCESS", { userid: this.mid, userpwd: this.mpw });
    },
  },
};
</script>

<style></style>
