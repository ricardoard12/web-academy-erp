<%@page import="java.util.List"%>
<%@page import="academy.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List notiList = (List) request.getAttribute("notiList");
	List libIb = (List) request.getAttribute("libIb");
	List libSu = (List) request.getAttribute("libSu");
	List newIb = (List) request.getAttribute("newIb");
	int i;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/main.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>

<title>Welcome to the Academy WebSite</title>
<style>
.banner {
	margin-top: 15px;
	margin-bottom: 15px;
}

.row {
	border: solid 1px;
	height: 150px;
	width: 800px;
	float: left;
}

.row a {
	text-decoration: none;
}

.row .col ul li {
	list-style: none;
}

.row .col ul li a {
	text-decoration: none;
}

.row .col {
	list-style: none;
	float: left;
	min-width:350px;
}
.clear{
clear:both;
}
</style>
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
				<!-- banner -->
				<div class="banner">
					<img src="./img/banner.jpeg" width="800px">
				</div>
				<!-- banner -->
				<!-- board list -->
				<div class="row">
					<ul >
						<li class="col">
							<h5>
								<a href="./BoardNotice.bo?gid=10">공지사항</a>
							</h5>
							<ul>
								<%
									if (notiList.size() < 1) {
								%>
								<li class="more">공지사항이 없습니다.</li>
								<%
									} else {
										for (i = 0; i < notiList.size(); i++) {
											List noti = (List) notiList.get(i);
								%>
								<li><a href="#"><%=noti.get(3)%></a></li>
								<%
									}
									}
								%>
							</ul>

						</li>
						<li class="col">
							<h5>입시자료</h5>
							<ul>
								<%
									if (libIb.size() < 1) {
								%>
								<li class="more">입시자료가 없습니다.</li>
								<%
									} else {
										for (i = 0; i < libIb.size(); i++) {
											List lib = (List) libIb.get(i);
								%>
								<li><a href="#"><%=lib.get(3)%></a></li>
								<%
									}
									}
								%>
							</ul>
						</li>
					</ul>
					<div class="row">
						<ul>
							<li class="col">
								<h5>수업자료실</h5>
								<ul>
									<%
										if (libSu.size() < 1) {
									%>
									<li class="more">수업자료가 없습니다.</li>
									<%
										} else {
											for (i = 0; i < libSu.size(); i++) {
												List lib = (List) libSu.get(i);
									%>
									<li><a href="#"><%=lib.get(3)%></a></li>
									<%
										}
										}
									%>
								</ul>
							</li>
							<li class="col">
								<h5>입시뉴스</h5>
								<ul>
									<%
										if (newIb.size() < 1) {
									%>
									<li class="more">입시 뉴스가 없습니다.</li>
									<%
										} else {
											for (i = 0; i < newIb.size(); i++) {
												List lib = (List) newIb.get(i);
									%>
									<li><a href="#"><%=lib.get(3)%></a></li>
									<%
										}
										}
									%>
								</ul>
							</li>
						</ul>
					</div>
					<div class="clear"></div>
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