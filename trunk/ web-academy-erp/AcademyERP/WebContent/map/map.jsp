<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Hello, World!</title>
<meta name="viewport" content="initial-scale=1.0,user-scalable=no">
<style type="text/css">
html {
	height: 100%
}

body {
	height: 100%;
	margin: 0;
	padding: 0
}

#map {
	width: 100%;
	height: 100%
}
</style>
<script type="text/javascript"
	src="http://apis.daum.net/maps/maps3.js?apikey=793624e33269f57b434a6fcb35ba61ab2b5c1780"></script>
<script type="text/javascript">
	window.onload = function() {

		if (navigator.geolocation) {
			navigator.geolocation.getCurrentPosition(
			// successCallback
			function(pos) {
				x = parseFloat(pos.coords.latitude);
				y = parseFloat(pos.coords.longitude);
				//alert("위도: " + x + "경도: " + y); // 경도 // 위도
				//		 		var position = new daum.maps.LatLng(37.537123, 127.005523);
				var position = new daum.maps.LatLng(pos.coords.latitude,
						pos.coords.longitude);

				var map = new daum.maps.Map(document.getElementById('map'), {
					center : position,
					level : 4,
					mapTypeId : daum.maps.MapTypeId.ROADMAP
				});
				var marker = new daum.maps.Marker({
					position : position
				});
				marker.setMap(map);

				var infowindow = new daum.maps.InfoWindow({
					content : '현재 위치' + x + ',' + y
				});
				infowindow.open(map, marker);
			},
			// errorCallback
			function(error) {
				switch (error.code) {
				case error.TIMEOUT:
					alert('시간 초과');
					break;
				case error.POSITION_UNAVAILABLE:
					alert('위치를 사용할 수 없음 (이 오류는 위치 정보 공급자가 응답)');
					break;
				case error.PERMISSION_DENIED:
					alert('권한 거부');
					break;
				case error.UNKNOWN_ERROR:
					alert('알 수 없는 오류');
					break;
				default:
					alert(error.code);
				}
			});
		} else {
			alert("이 브라우저는 geolcation API를 지원하지 않습니다");
		}

	};
</script>
</head>
<body>
	<div id="map"></div>
	<form></form>
</body>
</html>