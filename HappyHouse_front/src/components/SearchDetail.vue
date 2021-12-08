<template>
  <center class="container" style="margin-top: 100px; margin-bottom: 40px">
    <h1
      style="background-color: #bfcba8; color: white; margin: 0px; padding: 20px; font-weight: bold"
    >
      주변 지역 헬스장 정보
    </h1>
    <div style="background-color: #dfe5d3; padding: 20px">
      <div style="text-align: left; width: 96%">
        <p>
          <img
            draggable="false"
            src="https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png"
          />
          선택하신 아파트의 위치
          <img
            src="http://t1.daumcdn.net/localimg/localimages/07/2018/pc/img/marker_spot.png"
            draggable="false"
            style="width: 27px; height: 35px; margin-left: 10px"
          />
          주변 헬스장의 위치 (누르면 '상세정보'와 '길찾기'를 확인할 수 있습니다.)
        </p>
      </div>
      <div id="map" style="width: 96%; height: 1000px"></div>
    </div>

    <div>
      <!-- The modal -->
      <b-modal id="my-modal" size="xl">
        <center>
          <button class="btn btn-primary" @click="changeFlag(1)">상세정보</button>
          <button class="btn btn-primary ml-3" @click="changeFlag(2)">길찾기</button>
          <div class="mt-4">
            <iframe v-if="flag == 1" :src="place.place_url" width="1000px" height="1000px"></iframe>
            <iframe v-if="flag == 2" :src="street" width="1000px" height="1000px"></iframe>
          </div>
        </center>
        <template #modal-footer="{ ok }">
          <b-button size="sm" variant="primary" @click="ok()"> OK </b-button>
        </template>
      </b-modal>
    </div>
  </center>
</template>

<script>
import axios from "axios";

export default {
  created() {},
  data() {
    return {
      name: "",
      place: "",
      flag: 2,
      street: "",
      rtx1: "",
      rtx2: "",
      rty1: "",
      rty2: "",
    };
  },
  computed: {
    info() {
      return this.$store.state.info;
    },
  },
  mounted() {
    this.name = this.$route.params.name;
    this.$store.dispatch("SEARCHDETAIL", this.name).then(() => {
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");
        /* global kakao */
        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=700de7c3f8c234200cecfb764ce8ec5d&libraries=services,clusterer,drawing";
        document.head.appendChild(script);
      }
    });
  },
  methods: {
    changeFlag(no) {
      this.flag = no;
    },
    showModal() {
      this.$bvModal.show("my-modal");
    },
    initMap() {
      // 마커를 클릭하면 장소명을 표출할 인포윈도우 입니다
      // var infowindow = new kakao.maps.InfoWindow({ zIndex: 1 });
      // 커스텀 오버레이를 생성합니다
      var customOverlay = new kakao.maps.CustomOverlay({
        xAnchor: 0.5,
        yAnchor: 1.2,
      });
      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(this.info.lat, this.info.lng), // 지도의 중심좌표
          level: 5, // 지도의 확대 레벨
        };
      var map = new kakao.maps.Map(mapContainer, mapOption);
      // 마커가 표시될 위치입니다
      var markerPosition = new kakao.maps.LatLng(this.info.lat, this.info.lng);

      // 마커 이미지의 이미지 주소입니다
      var imageSrc = "https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/markerStar.png";

      // 마커 이미지의 이미지 크기 입니다
      var imageSize = new kakao.maps.Size(24, 35);

      // 마커 이미지를 생성합니다
      var markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize);

      // 마커를 생성합니다
      var marker = new kakao.maps.Marker({
        map: this.map, // 마커를 표시할 지도
        position: markerPosition, // 마커를 표시할 위치
        title: this.info.name, // 마커의 타이틀, 마커에 마우스를 올리면 타이틀이 표시됩니다
        image: markerImage, // 마커 이미지
      });
      marker.setMap(map);

      let url =
        "https://dapi.kakao.com/v2/local/search/keyword?query=서울특별시 " +
        this.info.dong +
        " 헬스장" +
        "&x=" +
        this.info.lng +
        "&y=" +
        this.info.lat;
      axios
        .get(url, { headers: { Authorization: "KakaoAK ec59fa3365a5dbe94375da05c74f8e47" } })
        .then((response) => {
          if (response.status === 200) {
            // 검색된 장소 위치를 기준으로 지도 범위를 재설정하기위해
            // LatLngBounds 객체에 좌표를 추가합니다
            var bounds = new kakao.maps.LatLngBounds();

            for (var i = 0; i < response.data.documents.length; i++) {
              displayMarker(response.data.documents[i]);
              bounds.extend(
                new kakao.maps.LatLng(response.data.documents[i].y, response.data.documents[i].x)
              );
            }

            // 검색된 장소 위치를 기준으로 지도 범위를 재설정합니다
            map.setBounds(bounds);
          }
        })
        .catch((response) => {
          console.log(response);
        });

      var doc = this;

      // 지도에 마커를 표시하는 함수입니다
      function displayMarker(place) {
        // 마커를 생성하고 지도에 표시합니다
        var marker = new kakao.maps.Marker({
          map: map,
          position: new kakao.maps.LatLng(place.y, place.x),
        });

        // 마커에 클릭이벤트를 등록합니다
        kakao.maps.event.addListener(marker, "click", function () {
          doc.place = place;
          doc.showModal();

          let url =
            "https://dapi.kakao.com/v2/local/geo/transcoord.json?x=" +
            place.x +
            "&y=" +
            place.y +
            "&input_coord=WGS84&output_coord=WCONGNAMUL";
          axios
            .get(url, { headers: { Authorization: "KakaoAK ec59fa3365a5dbe94375da05c74f8e47" } })
            .then((response) => {
              doc.rtx2 = response.data.documents[0].x;
              doc.rty2 = response.data.documents[0].y;
              url =
                "https://dapi.kakao.com/v2/local/geo/transcoord.json?x=" +
                doc.info.lng +
                "&y=" +
                doc.info.lat +
                "&input_coord=WGS84&output_coord=WCONGNAMUL";
              axios
                .get(url, {
                  headers: { Authorization: "KakaoAK ec59fa3365a5dbe94375da05c74f8e47" },
                })
                .then((response) => {
                  doc.rtx1 = response.data.documents[0].x;
                  doc.rty1 = response.data.documents[0].y;
                  doc.street =
                    "https://map.kakao.com/?map_type=TYPE_MAP&target=walk&rt=" +
                    doc.rtx1 +
                    "," +
                    doc.rty1 +
                    "," +
                    doc.rtx2 +
                    "," +
                    doc.rty2 +
                    "&rt1=" +
                    doc.info.aptName +
                    "&rt2=" +
                    place.place_name +
                    "&rtIds=,&rtTypes=,";
                })
                .catch((response) => {
                  console.log(response);
                });
            })
            .catch((response) => {
              console.log(response);
            });

          marker.setMap(map);
        });

        kakao.maps.event.addListener(marker, "mouseover", function () {
          // 마커를 클릭하면 장소명이 인포윈도우에 표출됩니다
          var content =
            `<div 
            style="background-color: black; 
                  width: auto;
                  height: auto;
                  color: white;
                  text-align: left;
                  padding: 20px;
                  opacity: 0.7;
                  ">` +
            `    <div class="mb-2">[` +
            place.place_name +
            `]</div>` +
            `        <div>` +
            `            <span >전화번호 : </span>` +
            `            <span >` +
            place.phone +
            `</span>` +
            `        </div>` +
            `       <div>` +
            `            <span >지번 주소 : </span>` +
            `            <span >` +
            place.address_name +
            `</span>` +
            `        </div>` +
            `        <div>` +
            `           <span >도로명 주소 : </span>` +
            `            <span >` +
            place.road_address_name +
            `</span>` +
            `        </div>` +
            `        <div>` +
            `            <span >직선거리 : </span>` +
            `            <span >` +
            place.distance +
            `(m)</span>` +
            `        </div>` +
            `</div>`;
          customOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
          customOverlay.setContent(content);

          // 커스텀 오버레이를 지도에 표시합니다
          customOverlay.setMap(map);
        });

        kakao.maps.event.addListener(marker, "mouseout", function () {
          doc.flag = 2;
          customOverlay.setMap(null);
        });
      }
    },
  },
};
</script>

<style scoped>
.overlaybox {
  position: relative;
  width: 360px;
  height: 350px;
  padding: 15px 10px;
  /* background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/box_movie.png")
      no-repeat; */
  background-color: black;
}
.overlaybox div,
ul {
  overflow: hidden;
  margin: 0;
  padding: 0;
}
.overlaybox li {
  list-style: none;
}
.overlaybox .boxtitle {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png")
    no-repeat right 120px center;
  margin-bottom: 8px;
}
.overlaybox .first {
  position: relative;
  width: 247px;
  height: 136px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/thumb.png") no-repeat;
  margin-bottom: 8px;
}
.first .text {
  color: #fff;
  font-weight: bold;
}
.first .triangle {
  position: absolute;
  width: 48px;
  height: 48px;
  top: 0;
  left: 0;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/triangle.png") no-repeat;
  padding: 6px;
  font-size: 18px;
}
.first .movietitle {
  position: absolute;
  width: 100%;
  bottom: 0;
  background: rgba(0, 0, 0, 0.4);
  padding: 7px 15px;
  font-size: 14px;
}
.overlaybox ul {
  width: 247px;
}
.overlaybox li {
  position: relative;
  margin-bottom: 2px;
  background: #2b2d36;
  padding: 5px 10px;
  color: #aaabaf;
  line-height: 1;
}
.overlaybox li span {
  display: inline-block;
}
.overlaybox li .number {
  font-size: 16px;
  font-weight: bold;
}
.overlaybox li .title {
  font-size: 13px;
}
.overlaybox ul .arrow {
  position: absolute;
  margin-top: 8px;
  right: 25px;
  width: 5px;
  height: 3px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/updown.png") no-repeat;
}
.overlaybox li .up {
  background-position: 0 -40px;
}
.overlaybox li .down {
  background-position: 0 -60px;
}
.overlaybox li .count {
  position: absolute;
  margin-top: 5px;
  right: 15px;
  font-size: 10px;
}
.overlaybox li:hover {
  color: #fff;
  background: #d24545;
}
.overlaybox li:hover .up {
  background-position: 0 0px;
}
.overlaybox li:hover .down {
  background-position: 0 -20px;
}
</style>
