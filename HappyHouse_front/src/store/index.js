import Vue from "vue";
import Vuex from "vuex";
import axios from "../util/axios-common.js";

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    boardlist: [],
    searchlist: [],
    noticelist: [],
    gulist: [],
    donglist: [],
    deallist: [],
    infolist: [],
    newslist: [],
    board: null,
    member: null,
    notice: null,
    info: null,
    id: null,
  },
  getters: {
    boardlist(state) {
      //todolist를 return해주는 함수
      return state.boardlist;
    },
    board(state) {
      return state.board;
    },
  },
  actions: {
    ALLBOARD: (store) => {
      axios
        .get("/qboard/qboardlist")
        .then((response) => {
          store.commit("ALLBOARD", { boardlist: response.data });
        })
        .catch((response) => {
          console.log(response);
        });
    },
    DELETEBOARD: (store, payload) => {
      axios
        .delete("/qboard/qboardlist/" + payload)
        .then(() => {
          console.log("삭제성공");
          store.dispatch("ALLBOARD");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    SHOWBOARD: (store, payload) => {
      return axios
        .get("/qboard/qboardlist/" + payload.no)
        .then((response) => {
          console.log("조회성공");
          store.commit("SHOWBOARD", { board: response.data });
        })
        .catch((response) => {
          console.log(response);
        });
    },
    SEARCHBOARD: (store, payload) => {
      store.commit("SEARCHBOARD", payload);
    },
    ADDBOARD: (store, payload) => {
      axios
        .post("/qboard/qboardlist", { title: payload.title, content: payload.content })
        .then(() => {
          console.log("추가성공");
          store.dispatch("ALLBOARD");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    EDITBOARD: (store, payload) => {
      axios
        .put("/qboard/qboardlist", payload)
        .then(() => {
          console.log("수정성공");
          store.dispatch("ALLBOARD");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    LOGINPROCESS: (store, payload) => {
      axios
        .post("/member/loginProcess", payload)
        .then((response) => {
          console.log("로그인 프로세스");
          console.log(response.status);
          if (response.status == 200) {
            alert("로그인 성공");
            self.sessionStorage.setItem("id", response.data["userid"]);
            self.location.href = "/";
          } else {
            alert("로그인 실패");
          }
        })
        .catch((response) => {
          console.log(response);
        });
    },
    GETMEMBER: (store) => {
      return axios.get("/member/getMember").then((response) => {
        console.log("storestore");
        console.log(response.data);
        store.commit("GETMEMBER", response.data);
      });
    },
    EDITMEMBER: (store, payload) => {
      axios
        .put("/member/modifyProcess", payload)
        .then((response) => {
          if (response.status == 200) {
            alert("수정성공");
            store.dispatch("GETMEMBER");
          } else {
            alert("수정실패");
          }
        })
        .catch((response) => {
          console.log(response.status);
        });
    },
    DELMEMBER: (store) => {
      axios.delete("/member/deleteProcess").then((response) => {
        if (response.status == 200) {
          alert("삭제성공");
          store.commit("DELID");
          self.sessionStorage.removeItem("id");
          self.location.href = "/";
        } else {
          alert("삭제실패");
        }
      });
    },
    REGISTERMEMBER: (store, payload) => {
      console.log(payload);
      axios.post("/member/registerProcess", payload).then((response) => {
        if (response.status == 200) {
          alert("회원가입 성공");
        } else {
          alert("회원가입 실패");
        }
      });
    },
    LOGOUT: (store) => {
      axios.get("/member/logout").then((response) => {
        if (response.status == 200) {
          console.log("로그아웃 성공");
          store.commit("DELID");
          self.sessionStorage.removeItem("id");
          self.location.href = "/";
        } else {
          console.log("로그아웃 실패");
        }
      });
    },
    GETSESSION: (store) => {
      store.commit("GETSESSION", self.sessionStorage.getItem("id"));
    },
    ALLNOTICE: (store) => {
      axios
        .get("/notice/noticelist")
        .then((response) => {
          store.commit("ALLNOTICE", { noticelist: response.data });
        })
        .catch((response) => {
          console.log(response);
        });
    },
    DELETENOTICE: (store, payload) => {
      axios
        .delete("/notice/noticelist/" + payload)
        .then(() => {
          console.log("삭제성공");
          store.dispatch("ALLNOTICE");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    SHOWNOTICE: (store, payload) => {
      return axios
        .get("/notice/noticelist/" + payload.no)
        .then((response) => {
          console.log("조회성공");
          store.commit("SHOWNOTICE", { notice: response.data });
        })
        .catch((response) => {
          console.log(response);
        });
    },
    ADDNOTICE: (store, payload) => {
      axios
        .post("/notice/noticelist", { title: payload.title, content: payload.content })
        .then(() => {
          console.log("추가성공");
          store.dispatch("ALLNOTICE");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    EDITNOTICE: (store, payload) => {
      axios
        .put("/notice/noticelist", payload)
        .then(() => {
          console.log("수정성공");
          store.dispatch("ALLNOTICE");
        })
        .catch((response) => {
          console.log(response);
        });
    },
    GETGU: (store) => {
      return axios.get("/city/gugun/1100000000").then((response) => {
        store.commit("GETGU", response.data);
      });
    },
    GETDONG: (store, payload) => {
      axios.get("/city/dong/" + payload).then((response) => {
        store.commit("GETDONG", response.data);
      });
    },
    SEARCHDONG: async (store, payload) => {
      await axios.post("/city/searchByDong", payload).then((response) => {
        store.commit("SEARCHDONG", response.data);
      });
      await axios.post("/city/infoByDong", payload).then((response) => {
        store.commit("INFODONG", response.data);
      });
    },
    SEARCHNAME: async (store, payload) => {
      await axios.post("/city/searchByName", payload).then((response) => {
        store.commit("SEARCHDONG", response.data);
      });
      await axios.post("/city/infoByName", payload).then((response) => {
        store.commit("INFODONG", response.data);
      });
    },
    ALLNEWS: (store) => {
      return axios
        .get("/news/newslist")
        .then((response) => {
          store.commit("ALLNEWS", { newslist: response.data });
        })
        .catch((response) => {
          console.log(response);
        });
    },
    SEARCHDETAIL: (store, payload) => {
      return axios.post("/city/infoByName", { name: payload }).then((response) => {
        store.commit("SEARCHDETAIL", response.data);
      });
    },
  },
  mutations: {
    ALLBOARD: (state, payload) => {
      state.boardlist = payload.boardlist;
      state.searchlist = payload.boardlist;
    },
    SHOWBOARD: (state, payload) => {
      state.board = payload.board;
    },
    SEARCHBOARD: (state, payload) => {
      state.searchlist = state.boardlist.filter(
        (board) => board.title.indexOf(payload) != -1 || board.content.indexOf(payload) != -1
      );
    },
    GETMEMBER: (state, payload) => {
      state.member = payload;
    },
    GETSESSION: (state, payload) => {
      state.id = payload;
    },
    DELID: (state) => {
      state.id = null;
    },
    ALLNOTICE: (state, payload) => {
      state.noticelist = payload.noticelist;
    },
    SHOWNOTICE: (state, payload) => {
      state.notice = payload.notice;
    },
    GETGU: (state, payload) => {
      state.gulist = payload;
    },
    GETDONG: (state, payload) => {
      state.donglist = payload;
    },
    SEARCHDONG: (state, payload) => {
      state.deallist = payload;
    },
    INFODONG: (state, payload) => {
      state.infolist = payload;
    },
    ALLNEWS: (state, payload) => {
      state.newslist = payload.newslist;
    },
    SEARCHDETAIL: (state, payload) => {
      state.info = payload[0];
    },
  },
});
