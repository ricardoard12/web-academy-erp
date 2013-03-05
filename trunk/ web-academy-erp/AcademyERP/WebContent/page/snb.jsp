<%@page import="academy.member.db.MemberBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	MemberBean member = (MemberBean) session.getAttribute("sMember");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>


	<!-- Layer Info -->
	<div id="info_wrap" style="top: 20px; left: 20px; width: 200px">
		<div id="info_content">
			<!-- Layer Content -->

			<%
				//세션으로 id 값 받음
				String id = (String) session.getAttribute("id");
				//세션으로 name값 받음
				String name = (String) session.getAttribute("name");
				//세션으로 level 값 받음
				String level = (String) session.getAttribute("level");
			%>
			<form action="" method="post">
				<h4 class="ly_header">회원 로그인</h4>
				<fieldset>
					<dl class="ly_body">

						<%
							if (name != null) {
						%>
						<dt>
							<strong><%=name%></strong>님 반갑습니다
						</dt>

						<dt>
							<strong> <%
 	if (level.equals("1")) {
 %> 학생 <%
 	} else if (level.equals("2")) {
 %> 부모님 <%
 	} else if (level.equals("3")) {
 %> 선생님 <%
 	} else if (level.equals("4")) {
 %> 중간관리자 <%
 	} else if (level.equals("5")) {
 %> 원장님 <%
 	} else if (level.equals("6")) {
 %> 영호(신) <%
 	}
 %>
							</strong>입니다
						</dt>

						<%
							}
						%>


						<dd>
							<%
								if (name != null) {
							%>
							<label> 회원정보수정</label>
							<%
								}
							%>
							<%
								if (name == null) {
							%>
							<label>반갑습니다 <br> 로그인하세요
							</label> <label><a href="#login" accesskey="L"
								title="Accesskey(L)" id="login_anchor" class="login_btn_sml"><span>로그인</span></a></label>
							<%
								} else {
							%>
							<label><a href="./MemberLogout.me" class="btn_sml"><span
									onclick="">로그아웃</span></a></label>
							<%
								}
							%>
						</dd>

					</dl>

				</fieldset>
			</form>
			<!-- //Layer Content -->


			<div class="mw_login">
				<div class="bg"></div>
				<div id="login" class="g_login">
					<a href="#login_anchor" title="로그인 레이어 닫기" class="close">X</a>
					<form action="./MemberLogin.me" id="g_login" class="g_login"
						method="post">
						<fieldset>
							<legend>Login</legend>
							<div class="item">
								<label for="uid" class="i_label"
									style="position: absolute; visibility: visible;">ID</label> <input
									name="mm_id" type="text" id="uid" class="i_text uid">
							</div>
							<div class="item">
								<label for="upw" class="i_label"
									style="position: absolute; visibility: visible;">Password</label>
								<input name="mm_passwd" type="password" id="upw"
									class="i_text upw">
							</div>
							<p class="keeping">
								<input name="" type="checkbox" value="" id="keepid"
									class="i_check"> <label for="keepid">로그인 유지</label>
							</p>
							<p class="warning">
								브라우저를 닫더라도 로그인이 계속 유지될 수 있습니다.<br> 로그인 유지 기능을 사용할 경우 다음
								접속부터는 로그인을 하실 필요가 없습니다.<br> 단, 게임방, 학교 등 공공장소에서 이용 시 개인정보가
								유출될 수 있으니 꼭 로그아웃을 해주세요.
							</p>
							<span class="btn_login"><input name="" type="submit"
								value="로그인"></span>
							<ul class="help">
								<li class="first"><a href="#">아이디/비밀번호 찾기</a></li>
							</ul>
						</fieldset>
					</form>
					<a href="#login_anchor" title="로그인 레이어 닫기" class="close">X</a>
				</div>
			</div>
			<!-- //로그인 끝 -->


		</div>
		<span class="shadow shadow2"></span><span class="shadow shadow3"></span><span
			class="shadow shadow4"></span>
	</div>
	<!-- //Layer Info -->

	<!-- Vertical navigation -->
	<div id="menu_v" class="menu_v">
		<ul>
			<%
				if (name != null) { /*로그인 안하면 메뉴 안보임 시작  */

					/* 로그인후 레벨에 따라 메뉴 보임 시작 */
					if (level.equals("1") || level.equals("2") || level.equals("3")
							|| level.equals("4") || level.equals("5")
							|| level.equals("6")) {
			%>


			<li><a><span>학부모/학생용</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="#"><span>학생정보조회</span></a></li>
					<li><a href="#"><span>회비내역조회</span></a></li>
					<li><a href="#"><span>시간표조회</span></a></li>
					<li><a href="#"><span>성적조회</span></a></li>
					<li><a href="#"><span>출결상황조회</span></a></li>
					<li><a href="#"><span>강의계획서열람</span></a></li>
					<li><a href="#"><span>학원차량위치조회(운행시간)</span></a></li>
					<li><a href="#"><span>학생위치조회(학부모전용)</span></a></li>
				</ul></li>



			<%
				}
					if (level.equals("3") || level.equals("4") || level.equals("5")
							|| level.equals("6")) {
			%>

			<li>
				<!-- class="active" --> <a><span>원생관리</span><span class="i"></span></a>

				<ul style="display: none;">
					<li>
						<!-- class="active" --> <a href="./StudentGroups.st"><span>신규등록</span></a>
					</li>
					<li><a href="./StudentList.st"><span>수강생관리</span></a></li>
					<li><a href="./StudentOffList.st"><span>휴원생관리</span></a></li>
					<li><a href="./StudentOutList.st"><span>퇴원생관리</span></a></li>

				</ul>
			</li>

			<%
				int lev = Integer.parseInt(level);
						if (lev >= 3) {
			%>
			<li><a><span>학급관리</span><span class="i"></span></a>
				<ul style="display: none;">
					<%
						if (lev >= 4) {
					%>
					<li><a href="./ClassList.master"><span>학급생성</span></a></li>
					<%
						}
					%>
					<li><a href="./ClassInfo.gp"><span>학급정보</span></a></li>
					<li><a href="#"><span>학급별시험정보</span></a></li>
					<li><a href="./GroupsAttitudeListAction.gp"><span>학급별출결현황</span></a></li>
					<li><a href="#"><span>학급별상담내역</span></a></li>
					<li><a href="#"><span>학급진도관리</span></a></li>
				</ul></li>
			<%
				}
			%>

			<li><a><span>성적관리</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="./GradeJoin.gr"><span> - 학원/학교 시험등록</span></a></li>

					<li><a href="./GradeAcademyTesting.gr"><span> - 학원
								시험진행</span></a></li>

					<li><a href="./GradeAcademyTested.gr"><span> - 학원
								시험완료</span></a></li>

					<li><a href="./GradeSchoolTested.gr"><span> - 학교 성적
						</span></a></li>

				</ul></li>

			<li><a><span>시간표관리</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="./timetable/timetableset.jsp"><span>전체시간표</span></a></li>
					<li><a href="#"><span>강사별시간표</span></a></li>

					<li><a href="#"><span>학급별시간표</span></a></li>
				</ul></li>
			<!-- 선생님용 메뉴 -->

			<%
				if (level.equals("3") || level.equals("4")
								|| level.equals("5") || level.equals("6")) {
			%>
			<li><a><span>게시판</span><span class="i"></span></a> <!-- Level에 따른 열람 제한 설정 -->

				<ul style="display: none;">
					<!-- 공지사항은 계시판 10 -->
					<li><a href="./BoardNotice.bo?gid=10"><span>공지사항</span></a></li>
					<!-- 업무일지는 11 -->
					<li><a href="./BusinessNotice.bl?level=<%=level%>"><span>업무일지</span></a></li>
					<!-- 강의계획서는 12 -->
					<li><a href="./LessonListAction.le?level=<%=level%>&id=<%=id%>&name=<%=name%>"><span>강의계획서</span></a></li>
					<!-- 수업자료실 13 -->
					<li><a href="./BoardNotice.bo?gid=13"><span>수업자료실</span></a></li>
					<!-- 직원 자료실은 14 -->
					<li><a href="./BoardNotice.bo?gid=14"><span>직원자료실</span></a></li>
					<!-- 직원 계시판은 15 -->
					<li><a href="./BoardNotice.bo?gid=15"><span>직원게시판</span></a></li>
				</ul></li>
			<%
				}
			%>
			<%
				}
					if (level.equals("4") || level.equals("5") || level.equals("6")) {
			%>

			<li><a><span>직원관리</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="./EmployeeJoin.em"><span>신규등록</span></a></li>

					<li><a href="./EmployeeListAction.em"><span>직원현황</span></a></li>
					<li><a href="./EmployeeAttitudeListAction.em"><span>근태관리</span></a></li>
					<li><a href="#"><span>급여관리</span></a></li>
					<li><a href="./EmployeeOutgoingListAction.em"><span>퇴직자관리</span></a></li>
				</ul></li>

			<%
				}
					if (level.equals("5") || level.equals("6")) {
			%>
			<li><a><span>회계관리</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="./AccountingJoin.ac"><span>회계등록</span></a></li>
					<li><a href="./AccountingList.ac?kind=fee"><span>회비현황</span></a></li>
					<li><a href="./AccountingList.ac?kind=in"><span>수입현황</span></a></li>
					<li><a href="./AccountingList.ac?kind=out"><span>지출현황</span></a></li>
					<li><a href="#"><span>계좌관리</span></a></li>
					<li><a href="./AccountingList.ac?kind=list"><span>전체조회</span></a></li>
				</ul></li>


			<li><a><span>홈페이지관리(Master전용)</span><span class="i"></span></a>
				<ul style="display: none;">
					<li><a href="./LevelList.master"><span>레벨/권한 설정</span></a></li>
					<li><a href="#"><span>공지사항 관리</span></a></li>
					<li><a href="./AllMemberList.master"><span>전체 회원 관리</span></a></li>
				</ul></li>
			<%
				}
			%>
			<!--레벨별 메뉴 설정 끝  -->
			<%
				}
			%><!--로그인 안하면 아무것도 안보임 끝  -->
		</ul>
	</div>
	<!-- //Vertical navigation  -->



	<script type="text/javascript">
		jQuery(function($) {
			var loginWindow = $('.mw_login');
			var login = $('#login');
			var uid = $('.i_text.uid');
			var upw = $('.i_text.upw');
			var oid = $('.i_text.oid');

			// Show Hide
			$('.login_btn_sml').click(function() {
				loginWindow.addClass('open');
			});
			$('#login .close').click(function() {
				loginWindow.removeClass('open');
			});
			// o_login
			$('.o_anchor').click(function() {
				login.removeClass('g_login');
				login.addClass('o_login');
			});
			// g_login
			$('.g_anchor').click(function() {
				login.removeClass('o_login');
				login.addClass('g_login');
			});
			// Warning
			$('#keepid').change(function() {
				if ($('#keepid[checked]')) {
					$('.warning').toggleClass('open');
				}
				;
			});
			// Input Clear
			var i_text = $('.item>.i_label').next('.i_text');
			$('.item>.i_label').css('position', 'absolute');
			i_text.focus(function() {
				$(this).prev('.i_label').css('visibility', 'hidden');
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).prev('.i_label').css('visibility', 'visible');
				} else {
					$(this).prev('.i_label').css('visibility', 'hidden');
				}
			}).change(function() {
				if ($(this).val() == '') {
					$(this).prev('.i_label').css('visibility', 'visible');
				} else {
					$(this).prev('.i_label').css('visibility', 'hidden');
				}
			}).blur();
			// Validation
			$('#login>.g_login input[type=submit]').click(function() {
				if (uid.val() == '' && upw.val() == '') {
					alert('ID와 PASSWORD를 입력하세요!');
					return false;
				} else if (uid.val() == '') {
					alert('ID를 입력하세요!');
					return false;
				} else if (upw.val() == '') {
					alert('PASSWORD를 입력하세요!');
					return false;
				}
			});
			$('#login>.o_login input[type=submit]').click(function() {
				if (oid.val() == '') {
					alert('Open ID를 입력하세요!');
					return false;
				}
			});
			// ESC Event
			$(document).keydown(function(event) {
				if (event.keyCode != 27)
					return true;
				if (loginWindow.hasClass('open')) {
					loginWindow.removeClass('open');
				}
				return false;
			});
			// Hide Window
			loginWindow.find('>.bg').mousedown(function(event) {
				loginWindow.removeClass('open');
				return false;
			});
		});

		jQuery(function($) {

			// Side Menu
			var menu_v = $('div.menu_v');
			var sItem = menu_v.find('>ul>li');
			var ssItem = menu_v.find('>ul>li>ul>li');
			var lastEvent = null;

			sItem.find('>ul').css('display', 'none');
			menu_v.find('>ul>li>ul>li[class=active]').parents('li').attr(
					'class', 'active');
			menu_v.find('>ul>li[class=active]').find('>ul').css('display',
					'block');

			function menu_vToggle(event) {
				var t = $(this);

				if (this == lastEvent)
					return false;
				lastEvent = this;
				setTimeout(function() {
					lastEvent = null
				}, 200);

				if (t.next('ul').is(':hidden')) {
					sItem.find('>ul').slideUp(100);
					t.next('ul').slideDown(100);
				} else if (!t.next('ul').length) {
					sItem.find('>ul').slideUp(100);
				} else {
					t.next('ul').slideUp(100);
				}

				if (t.parent('li').hasClass('active')) {
					t.parent('li').removeClass('active');
				} else {
					sItem.removeClass('active');
					t.parent('li').addClass('active');
				}
			}
			sItem.find('>a').click(menu_vToggle).focus(menu_vToggle);

			function subMenuActive() {
				ssItem.removeClass('active');
				$(this).parent(ssItem).addClass('active');
			}
			;
			ssItem.find('>a').click(subMenuActive).focus(subMenuActive);

			//icon
			menu_v.find('>ul>li>ul').prev('a')
					.append('<span class="i"></span>');
		});
	</script>
</body>
</html>