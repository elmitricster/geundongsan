import Vue from "vue";
import VueRouter from "vue-router";

import HMain from "../components/HMain.vue";
import HappyList from "../components/HappyList.vue";
import HappyDetail from "../components/HappyDetail.vue";
import HappyInput from "../components/HappyInput.vue";
import HappyEdit from "../components/HappyEdit.vue";
import MemberLogin from "../components/MemberLogin.vue";
import MemberRegister from "../components/MemberRegister.vue";
import SearchPrice from "../components/SearchPrice.vue";
import modifyForm from "../components/modifyForm.vue";
import noticelist from "../components/noticelist.vue";
import noticeedit from "../components/noticeedit.vue";
import noticedetail from "../components/noticedetail.vue";
import noticeinput from "../components/noticeinput.vue";
import newslist from "../components/newslist.vue";
import searchdetail from "../components/SearchDetail.vue";

Vue.use(VueRouter);

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes: [
    { path: "/", component: HMain },
    { path: "/error", component: HMain },
    { path: "/happylist", component: HappyList },
    { path: "/happyinput", component: HappyInput },
    { path: "/happydetail/:no", component: HappyDetail },
    { path: "/happyedit", component: HappyEdit },
    { path: "/loginForm", component: MemberLogin },
    { path: "/modifyForm", component: modifyForm },
    { path: "/registerForm", component: MemberRegister },
    { path: "/searchForm", component: SearchPrice },
    { path: "/noticelist", component: noticelist },
    { path: "/noticeedit", component: noticeedit },
    { path: "/noticedetail/:no", component: noticedetail },
    { path: "/noticeinput", component: noticeinput },
    { path: "/newslist", component: newslist },
    { path: "/searchDetail/:name", component: searchdetail },
  ],
});

export default router;
