<template>
  <div class="container text-center" style="margin-top: 100px; margin-bottom: 40px">
    <!-- Heading Row -->
    <div class="row align-items-center my-3">
      <div class="col-lg-6">
        <h1 class="font-weight-light">동별 실거래가 검색</h1>
        <div class="form-inline justify-content-center" id="frm" action="#" method="post">
          <div class="form-group md-1">
            <select
              class="form-control"
              name="gugun"
              id="gugun"
              v-model="gugun"
              @change="getdong"
              style="width: 128px"
            >
              <option value="all" selected disabled hidden>구</option>
              <option v-for="gu in gulist" :value="gu.gugun_name" :key="gu.gugun_name">
                {{ gu.gugun_name }}
              </option>
            </select>
          </div>
          <div class="form-group md-1">
            <select
              class="form-control"
              name="dong"
              id="dong"
              v-model="dongname"
              style="width: 128px"
            >
              <option value="all" selected disabled hidden>동</option>
              <option v-for="dong in donglist" :key="dong.dong" :value="dong.dong">
                {{ dong.dong }}
              </option>
            </select>
          </div>
          <div class="form-group md-1 m-1">
            <button class="btn btn-primary" id="query" @click="searchDong">검색</button>
          </div>
        </div>
      </div>
      <!-- /.col-lg-8 -->
      <div class="col-lg-6">
        <h1 class="font-weight-light">아파트별 실거래가 검색</h1>
        <div action="#" method="post">
          <div class="input-group justify-content-center">
            <div class="form-inline">
              <input
                type="search"
                name="name"
                id="form1"
                class="form-control"
                v-model="name"
                placeholder="검색할 단어를 입력하세요"
              />
              <label class="form-label" for="form1"></label>
              <button class="btn btn-primary ml-1" id="query" @click="searchName">검색</button>
            </div>
          </div>
        </div>
      </div>
      <!-- /.col-md-4 -->
    </div>
    <!-- 그림 다음 -->
    <div class="m-4">
      <h2 class="m-2">거래 정보</h2>
      <div class="masthead-content d-flex">
        <div class="col-8" id="map"></div>
        <div class="col-4" id="info" style="border: 2px solid #464f41; padding: 0px; margin: 15px">
          <div class="list-group d-flex overflow-auto" style="height: 500px">
            <div
              v-for="deal in deallist"
              :key="deal.no"
              @click="SEARCHDETAIL(deal.aptName)"
              class="
                list-group-item list-group-item-action
                flex-column
                align-items-start
                active
                mb-1
              "
              id="link1"
              @mouseenter="moveMap(deal)"
            >
              <div class="d-flex w-100 justify-content-between">
                <h5 class="mb-1">{{ deal.aptName }}</h5>
              </div>
              <p class="mb-1" id="priceInfo">{{ deal.dealAmount }} (만원)</p>
              <p class="mb-1" id="sizeInfo">전용면적 : {{ deal.area }} m<sup>2</sup></p>
              <p class="mb-1" id="typeInfo">
                거래구분: <span v-if="deal.type == 1">아파트 매매</span>
              </p>
              <small id="dateInfo"
                >{{ deal.dealYear }}년 {{ deal.dealMonth }}월 {{ deal.dealDay }}일</small
              >
            </div>
            <div v-if="deallist.length == 0">
              <center>
                <h5 class="m-5">현재 거래 정보가 없습니다.</h5>
              </center>
            </div>
          </div>
        </div>
      </div>
      <!-- google map -div -->
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      gugun: "all",
      dongname: "all",
      name: "",
      gulist: [],
    };
  },
  computed: {
    donglist() {
      return this.$store.state.donglist;
    },
    deallist() {
      return this.$store.state.deallist;
    },
    infolist() {
      return this.$store.state.infolist;
    },
  },
  created() {
    this.$store.dispatch("GETGU").then(() => {
      this.gulist = this.$store.state.gulist;
    });
  },
  mounted() {
    if (window.kakao && window.kakao.maps) {
      this.initMap();
    } else {
      const script = document.createElement("script");
      /* global kakao */
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=700de7c3f8c234200cecfb764ce8ec5d";
      document.head.appendChild(script);
    }
  },
  watch: {
    infolist() {
      this.printMarker();
    },
  },
  methods: {
    moveMap(deal) {
      for (let index = 0; index < this.infolist.length; index++) {
        if (deal.aptName == this.infolist[index].aptName) {
          var mapContainer = document.getElementById("map"), // 지도를 표시할 div
            mapOption = {
              center: new kakao.maps.LatLng(this.infolist[index].lat, this.infolist[index].lng), // 지도의 중심좌표
              level: 3, // 지도의 확대 레벨
            };
          var map = new kakao.maps.Map(mapContainer, mapOption);
          // 마커가 표시될 위치입니다
          var markerPosition = new kakao.maps.LatLng(
            this.infolist[index].lat,
            this.infolist[index].lng
          );
          // 마커를 생성합니다
          var marker = new kakao.maps.Marker({
            position: markerPosition,
          });
          // 마커가 지도 위에 표시되도록 설정합니다
          marker.setMap(map);

          // 마커 이미지의 이미지 주소입니다
          var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

          for (var i = 0; i < this.infolist.length; i++) {
            if (i == index) continue;
            // 마커 이미지의 이미지 크기 입니다
            var imageSize = new kakao.maps.Size(24, 35);

            // 마커 이미지를 생성합니다
            var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

            // 마커를 생성합니다
            marker = new kakao.maps.Marker({
              map: this.map, // 마커를 표시할 지도
              position: new kakao.maps.LatLng(this.infolist[i].lat, this.infolist[i].lng), // 마커를 표시할 위치
              title: this.infolist[i].aptName, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
              image: markerImage, // 마커 이미지
            });
            marker.setMap(map);
          }
          break;
        }
      }
    },
    SEARCHDETAIL(name) {
      this.$router.push("/searchDetail/" + name);
    },
    getdong() {
      this.$store.dispatch("GETDONG", this.gugun);
    },
    searchDong() {
      this.$store.dispatch("SEARCHDONG", { dong: this.dongname });
    },
    searchName() {
      this.$store.dispatch("SEARCHNAME", { name: this.name });
    },
    initMap() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.566675761763825, 126.97840912217265), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(37.566675761763825, 126.97840912217265);
      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        position: markerPosition,
      });
      // 마커가 지도 위에 표시되도록 설정합니다
      marker.setMap(map);
    },
    printMarker() {
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.infolist[0].lat, this.infolist[0].lng), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 마커를 표시할 위치와 title 객체 배열입니다
      var positions = [];
      for (let index = 0; index < this.infolist.length; index++) {
        positions.push({
          title: this.infolist[index].AptName,
          latlng: new kakao.maps.LatLng(this.infolist[index].lat, this.infolist[index].lng),
        });
      }
      // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
      // LatLngBounds 객체에 좌표를 추가합니다
      var bounds = new kakao.maps.LatLngBounds();

      for (let index = 0; index < this.infolist.length; index++) {
        bounds.extend(new kakao.maps.LatLng(this.infolist[index].lat, this.infolist[index].lng));
      }

      // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
      map.setBounds(bounds);

      // 마커 이미지의 이미지 주소입니다
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      for (var i = 0; i < positions.length; i++) {
        // 마커 이미지의 이미지 크기 입니다
        var imageSize = new kakao.maps.Size(24, 35);

        // 마커 이미지를 생성합니다
        var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

        // 마커를 생성합니다
        var marker = new kakao.maps.Marker({
          map: this.map, // 마커를 표시할 지도
          position: positions[i].latlng, // 마커를 표시할 위치
          title: positions[i].title, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
          image: markerImage, // 마커 이미지
        });
        marker.setMap(map);
      }
    },
  },
};
</script>

<style scoped>
#link1:hover {
  background-color: #bfcba8;
}
</style>
