<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<%
	request.setCharacterEncoding("utf-8");
	//세션으로 id 값 받음
	String id = (String) session.getAttribute("id");
	//세션으로 name값 받음
	String name = (String) session.getAttribute("name");
	//세션으로 level 값 받음
	String level = (String) session.getAttribute("level");
%>
</head>
<body>
	<!-- Horizonal navigation -->
	<div id="menu" class="menu mc_gray">
		<!--  <div class="mainImg" style="float: left"></div>-->
		<div class="homeImg" style="float: left">
			<a href="./"><img src="./img/academy.gif"></a>
		</div>		
		<!-- Horizonal navigation -->
		<div style="clear: both"></div>
		<div class="inset">
			<div class="major">
				<!-- class="major + (m1~m12)"-->
				<ul>
					<li class="m1"><a href="#"><span>학원정보</span></a>
						<div class="sub">
							<ul>
								<li><a href="#"><span>강사소개</span></a></li>
								<li><a href="#"><span>찾아오시는 길</span></a></li>
								<li><a href="#"><span>학원소개</span></a></li>
							</ul>
						</div></li>
					<li class="m2"><a href="#"><span>커리큘럼</span></a>
						<div class="sub">
							<ul>
								<li><a href="#"><span>과목소개</span></a></li>
							</ul>
						</div></li>
					<li class="m3"><a href="#"><span>입시정보</span></a>
						<div class="sub">
							<ul>
								<li><a href="./BoardNotice.bo?gid=20"><span>입시뉴스</span></a></li>
								<li><a href="./BoardNotice.bo?gid=21"><span>입시자료</span></a></li>
								<li><a href="./BoardNotice.bo?gid=22"><span>대입 FAQ</span></a></li>
								<li><a href="./BoardNotice.bo?gid=23"><span>수시/입학사정관제</span></a></li>
								<li><a href="./BoardNotice.bo?gid=24"><span>입시 투데이</span></a></li>
								<li><a href="./BoardNotice.bo?gid=25"><span>논술/면접/적성검사</span></a></li>
								<li><a href="./BoardNotice.bo?gid=26"><span>비교과정보</span></a></li>
							</ul>
						</div></li>
					<li class="m4"><a href="#"><span>학습/진로</span></a>
						<div class="sub">
							<ul>
								<li><a href="./BoardNotice.bo?gid=30"><span>진학/인성</span></a></li>
								<li><a href="./BoardNotice.bo?gid=31"><span>학습뉴스/정보</span></a></li>
								<li><a href="./BoardNotice.bo?gid=32"><span>추천도서</span></a></li>
								<li><a href="./BoardNotice.bo?gid=33"><span>유학정보</span></a></li>
								<li><a href="./BoardNotice.bo?gid=34"><span>진로/직업자료실</span></a></li>
								<li><a href="./BoardNotice.bo?gid=35"><span>학습자료실</span></a></li>
								<li><a href="./BoardNotice.bo?gid=36"><span>취업뉴스/정보</span></a></li>
							</ul>
						</div></li>
					<li class="m5"><a href="#"><span>커뮤니티</span> <!-- <span class="i"></span> --></a>
						<div class="sub">
							<ul>
								<li><a href="./QnaList.qa"><span>공지사항</span></a></li>
								<li><a href="./Faq_boardList.fb?name=<%=name%>&id=<%=id%>"><span>Q&A</span></a></li>
								<li><a href="./Faq_boardList.fb?name=<%=name%>&id=<%=id%>"><span>FAQ</span></a></li>
							</ul>
						</div></li>
					<li class="m6"><a href="./sitemap/sitemap.jsp"><span>site
								map</span></a></li>
				</ul>
			</div>
			<span class="gradient"></span>
		</div>
		<span class="shadow"></span>
	</div>
	<!-- //Horizonal navigation -->

	<script type="text/javascript">
		jQuery(function($) {

			// Menu
			var menu = $('div.menu');
			var major = $('div.major');
			var li_list = major.find('>ul>li');

			// Selected
			function onselectmenu() {
				var myclass = [];

				$(this).parent('li').each(function() {
					myclass.push($(this).attr('class'));
				});

				myclass = myclass.join(' ');
				if (!major.hasClass(myclass))
					major.attr('class', 'major').addClass(myclass);
			}

			// Show Menu
			function show_menu() {
				t = $(this);
				li_list.removeClass('active');
				t.parent('li').addClass('active');
				// IE7 or IE7 documentMode bug fix
				if ($.browser.msie) {
					var v = document.documentMode
							|| parseInt($.browser.version);
					if (v == 7) {
						var subWidth = t.next('div.sub').eq(-1).width();
						t.next('div.sub').css('width', subWidth);
					}
				}
			}
			li_list.find('>a').click(onselectmenu).mouseover(show_menu).focus(
					show_menu);

			// Hide Menu
			function hide_menu() {
				li_list.removeClass('active');
			}
			menu.mouseleave(hide_menu);
			li_list.find('div.sub>ul').mouseleave(hide_menu);

			//icon
			major.find('div.sub').prev('a').find('>span').append(
					'<span class="i"></span>');

			// Aside
			var aside_li = $('.menu>.inset>.aside>ul>li');
			var aside_a = $('.menu>.inset>.aside>ul>li>a');

			// Show Aside
			function show_aside() {
				li_list.removeClass('active');
				aside_li.removeClass('active');
				$(this).parent('li').addClass('active');

				// IE7 or IE7 documentMode bug fix
				if ($.browser.msie) {
					var v = document.documentMode
							|| parseInt($.browser.version);

					if (v == 7) {
						var sub = $(this).next('div.sub').eq(-1);
						sub.css('width', '').css('width', sub.width() + 'px');
					}
				}
			}
			aside_a.mouseover(show_aside).focus(show_aside);

			// Hide Aside
			function hide_aside() {
				aside_li.removeClass('active');
			}
			menu.mouseleave(hide_aside);
			aside_li.find('div.sub>ul').mouseleave(hide_aside);

			// Hide Menu & Aside
			$('*:not(".menu *")').focus(hide_menu).focus(hide_aside);

		});
	</script>
	</body>
</html>