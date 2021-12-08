<template>
  <div data-spy="scroll" data-target="#navbar1" data-offset="60">
    <header class="bg-primary">
      <div class="container h-100">
        <div class="row h-100">
          <div class="col-12">
            <div
              class="text-center m-0 vh-100 d-flex flex-column justify-content-center text-light"
            >
              <h1 class="display-4" style="font-weight: 500">
                <span
                  style="
                    border-bottom: 4px solid white;
                    padding-left: 5px;
                    padding-right: 5px;
                    padding-bottom: 2px;
                  "
                  >근동산</span
                >
              </h1>
              <!-- <p class="lead mt-3">Gym near home at a glance!</p> -->
              <p class="lead mt-4" style="font-size: 14px">GYM NEAR HOME AT A GLANCE!</p>
              <div v-if="id == null" class="row">
                <div class="col-lg-3 col-sm-6 mx-auto">
                  <div class="input-group mb-3 justify-content-center">
                    <button
                      class="btn btn-outline-light btn-lg rounded-right"
                      type="button"
                      @click="registerForm"
                    >
                      Sign-up
                    </button>
                    <button
                      class="btn btn-outline-light btn-lg rounded-right"
                      type="button"
                      @click="loginForm"
                    >
                      Login
                    </button>
                  </div>
                </div>
              </div>
              <div v-else class="row">
                <div class="col-lg-4 col-sm-6 mx-auto">
                  <div class="input-group mb-3 justify-content-center">
                    <button
                      class="btn btn-outline-light btn-lg rounded-right"
                      type="button"
                      @click="searchForm"
                    >
                      Search Page
                    </button>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </header>
    <div class="container" style="margin-top: 40px">
      <div class="row">
        <div class="col-xl-4 col-sm-6 col-12" @click="goNotice">
          <div class="card">
            <div class="card-content">
              <div class="card-body">
                <div class="media d-flex">
                  <div class="align-self-center">
                    <i class="icon-speech primary font-large-2 float-left"></i>
                  </div>
                  <div class="media-body text-right">
                    <h3 class="primary">NOTICE</h3>
                    <span>공지사항</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-4 col-sm-6 col-12" @click="goNews">
          <div class="card">
            <div class="card-content">
              <div class="card-body">
                <div class="media d-flex">
                  <div class="align-self-center">
                    <i class="icon-book-open primary font-large-2 float-left"></i>
                  </div>
                  <div class="media-body text-right">
                    <h3 class="primary">TODAY NEWS</h3>
                    <span>주택 관련 기사</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>

        <div class="col-xl-4 col-sm-6 col-12" @click="goQNA">
          <div class="card">
            <div class="card-content">
              <div class="card-body">
                <div class="media d-flex">
                  <div class="align-self-center">
                    <i class="icon-bubbles primary font-large-2 float-left"></i>
                  </div>
                  <div class="media-body text-right">
                    <h3 class="primary">Q&A</h3>
                    <span>질문과 답변</span>
                  </div>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>

<section class="our-webcoderskull padding-lg">
        <div class="container">
          <div class="row heading heading-icon">
            <h2>Our Team</h2>
          </div>
          <ul class="row" style="padding: 0px">
            <li class="col-12 col-md-6 col-lg-4">
              <div class="cnt-block equal-hight" style="height: 349px">
                <figure>
                  <img src="../assets/jb.jpg" class="img-responsive" alt="" />
                </figure>
                <h3>박 주 빈</h3>
                <p>WEB Frontend Developer</p>
                <ul class="follow-us clearfix" style="padding: 0px">
                  <li>
                    <a class="col-3" href="mailto:dpfmal55@naver.com"
                      ><i class="fas fa-at fa-2x"></i
                    ></a>
                    <a class="col-3" href="https://github.com/elmitricster"
                      ><i class="fab fa-github fa-2x"></i
                    ></a>
                    <a class="col-3" href="https://www.acmicpc.net/user/elmitricster"
                      ><i class="fab fa-bootstrap fa-2x"></i
                    ></a>
                  </li>
                </ul>
              </div>
            </li>
            <li class="col-12 col-md-6 col-lg-4">
              <div class="cnt-block equal-hight" style="height: 349px">
                <figure>
                  <img src="../assets/mush.png" class="img-responsive" alt="" />
                </figure>
                <h3>배신자에겐, 죽음뿐.</h3>
                <p><br>'버섯을 죽인 아크라시아를 부순다.'<br>'처음부터 그 생각뿐이었다.'</p>

              </div>
            </li>
            <li class="col-12 col-md-6 col-lg-4">
              <div class="cnt-block equal-hight" style="height: 349px">
                <figure>
                  <img src="../assets/yj.jpg" class="img-responsive" alt="" />
                </figure>
                <h3>박 예 진</h3>
                <p>WEB Backend Developer</p>
                <ul class="follow-us clearfix" style="padding: 0px">
                  <li>
                    <a class="col-3" href="mailto:yejin_joa@naver.com"
                      ><i class="fas fa-at fa-2x"></i
                    ></a>
                    <a class="col-3" href="https://github.com/16yejinPark"
                      ><i class="fab fa-github fa-2x"></i
                    ></a>
                    <a class="col-3" href="https://www.acmicpc.net/user/melon1124"
                      ><i class="fab fa-bootstrap fa-2x"></i
                    ></a>
                  </li>
                </ul>
              </div>
            </li>
          </ul>
        </div>
      </section>

      
    </div>
  </div>
</template>

<script>
export default {
  created() {
    this.$store.dispatch("GETSESSION");
  },
  computed: {
    id() {
      return this.$store.state.id;
    },
  },
  methods: {
    registerForm() {
      this.$router.push("/registerForm");
    },
    loginForm() {
      this.$router.push("/loginForm");
    },
    goNotice() {
      this.$router.push("/noticelist");
    },
    goQNA() {
      this.$router.push("/happylist");
    },
    goNews() {
      this.$router.push("/newslist");
    },
    searchForm() {
      this.$router.push("/searchForm");
    },
  },
};
</script>

<style>
div.card-body {
  height: 200px;
  padding-top: 65px;
}
.primary {
  color: #5b6d5b;
}
.warning {
  color: #ca8a8b;
}
.row.heading h2 {
  color: #fff;
  font-size: 52.52px;
  line-height: 95px;
  font-weight: 400;
  text-align: center;
  margin: 0 0 40px;
  padding-bottom: 20px;
  text-transform: uppercase;
}
ul {
  margin: 0;
  padding: 0;
  list-style: none;
}
.heading.heading-icon {
  display: block;
}
.padding-lg {
  display: block;
  padding-top: 60px;
  padding-bottom: 60px;
}
.practice-area.padding-lg {
  padding-bottom: 55px;
  padding-top: 55px;
}
.practice-area .inner {
  border: 1px solid #999999;
  text-align: center;
  margin-bottom: 28px;
  padding: 40px 25px;
}
.our-webcoderskull .cnt-block:hover {
  box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.3);
  border: 0;
}
.practice-area .inner h3 {
  color: #3c3c3c;
  font-size: 24px;
  font-weight: 500;
  font-family: "Poppins", sans-serif;
  padding: 10px 0;
}
.practice-area .inner p {
  font-size: 14px;
  line-height: 22px;
  font-weight: 400;
}
.practice-area .inner img {
  display: inline-block;
}

.our-webcoderskull {
  background-color: #bfcba8;
  margin-bottom: 30px;
}
.our-webcoderskull .cnt-block {
  float: left;
  width: 100%;
  background: #fff;
  padding: 30px 20px;
  text-align: center;
  border: 2px solid #d5d5d5;
  margin: 0 0 28px;
}
.our-webcoderskull .cnt-block figure {
  width: 148px;
  height: 148px;
  border-radius: 100%;
  display: inline-block;
  margin-bottom: 15px;
}
.our-webcoderskull .cnt-block img {
  width: 148px;
  height: 148px;
  border-radius: 100%;
}
.our-webcoderskull .cnt-block h3 {
  color: #2a2a2a;
  font-size: 20px;
  font-weight: 500;
  padding: 6px 0;
  text-transform: uppercase;
}
.our-webcoderskull .cnt-block h3 a {
  text-decoration: none;
  color: #2a2a2a;
}
.our-webcoderskull .cnt-block h3 a:hover {
  color: #337ab7;
}
.our-webcoderskull .cnt-block p {
  color: #2a2a2a;
  font-size: 13px;
  line-height: 20px;
  font-weight: 400;
}
.our-webcoderskull .cnt-block .follow-us {
  margin: 20px 0 0;
}
.our-webcoderskull .cnt-block .follow-us li {
  display: inline-block;
  width: auto;
  margin: 0 5px;
}
.our-webcoderskull .cnt-block .follow-us li .fa {
  font-size: 24px;
  color: #767676;
}
.our-webcoderskull .cnt-block .follow-us li .fa:hover {
  color: #025a8e;
}
</style>
