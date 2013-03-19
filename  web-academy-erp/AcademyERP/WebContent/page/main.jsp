<%@page import="academy.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

<title>Insert title here</title>
</head>
<body>
	<!-- UI Object -->
	<div id="wrap">
		<!-- header -->
		<div id="header">
			<jsp:include page="../page/header.jsp"></jsp:include>
		</div>
		<!-- //header -->
		<!-- container -->
		<div id="container">
			<!-- snb -->
			<div class="snb">
				<jsp:include page="snb.jsp"></jsp:include>
			</div>
			<!-- //snb -->
			<!-- content -->
			<div id="content">

				<!-- tab -->
				<div class="tab_list m1" style="height: 107px;">
					<ul>
						<li class="m1"><a href="#"><span>공지사항</span></a>
							<ul>
								<li>› <a href="#">이것은 첫 번째 탭의 공지사항 목록 입니다.</a></li>
								<li>› <a href="#">이것은 첫 번째 탭의 공지사항 목록 입니다.</a></li>
								<li>› <a href="#">이것은 첫 번째 탭의 공지사항 목록 입니다.</a></li>
								<li class="more">› <a href="#">공지사항 더보기</a></li>
							</ul></li>
						<li class="m2"><a href="#"><span>보도자료</span></a>
							<ul>
								<li>› <a href="#">이것은 두 번째 탭의 보도자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 두 번째 탭의 보도자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 두 번째 탭의 보도자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 두 번째 탭의 보도자료 목록 입니다.</a></li>
								<li class="more">› <a href="#">보도자료 더보기</a></li>
							</ul></li>
						<li class="m3"><a href="#"><span>해명자료</span></a>
							<ul>
								<li>› <a href="#">이것은 세 번째 탭의 해명자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 세 번째 탭의 해명자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 세 번째 탭의 해명자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 세 번째 탭의 해명자료 목록 입니다.</a></li>
								<li>› <a href="#">이것은 세 번째 탭의 해명자료 목록 입니다.</a></li>
								<li class="more">› <a href="#">해명자료 더보기</a></li>
							</ul></li>
					</ul>
				</div>
				<!-- //tab -->

			</div>
			<!-- //content -->
		</div>
		<!-- //container -->
		<!-- footer -->
		<div id="footer">
			<jsp:include page="../page/footer.jsp"></jsp:include>
		</div>
		<!-- //footer -->
	</div>
	<!-- //UI Object -->

	<script type="text/javascript">
		jQuery(function($) {
			var tab = $('.tab_list');
			tab.removeClass('js_off');
			tab.css('height', tab.find('>ul>li>ul:visible').height() + 40);
			function onSelectTab() {
				var t = $(this);
				var myClass = t.parent('li').attr('class');
				t.parents('.tab_list:first').attr('class',
						'tab_list ' + myClass);
				tab.css('height', t.next('ul').height() + 40);
			}
			tab.find('>ul>li>a').click(onSelectTab).focus(onSelectTab);
		});
	</script>

</body>
</html>