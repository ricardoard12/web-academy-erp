<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/login.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
</head>
<body>


<!-- Layer Info -->
<div id="info_wrap" style="top:20px;left:20px;width:200px">
	<div id="info_content">
		<!-- Layer Content -->
		<form action="" method="post">
			<h4 class="ly_header">회원 로그인</h4>
			<fieldset>
				<dl class="ly_body">
				<dt><strong>박한빛</strong>님 반갑습니다</dt>
				<dd>	
					<label> 회원정보수정</label>
					<label><a href="#login" accesskey="L" title="Accesskey(L)" id="login_anchor" class="login_btn_sml"><span>로그인</span></a></label>
					<label><a href="#" class="btn_sml"><span>로그아웃</span></a></label>
						</dd>
				</dl>
				
					<!-- <div id="login_footer">
					<input name="" type="image" src="http://static.naver.com/common/btn/btn_confirm2.gif" alt="확인">
					<a href="#"><img src="http://static.naver.com/common/btn/btn_cancel2.gif" alt="취소" width="38" height="21"></a>
				</div> -->
			</fieldset>
		</form>
		<!-- //Layer Content -->


			<div class="mw_login">
				<div class="bg"></div>
				<div id="login" class="g_login">
					<a href="#login_anchor" title="로그인 레이어 닫기" class="close">X</a>
					<form action="" id="g_login" class="g_login">
						<fieldset>
							<legend>Login</legend>
							<div class="item">
								<label for="uid" class="i_label"
									style="position: absolute; visibility: visible;">ID</label><input
									name="" type="text" value="" id="uid" class="i_text uid">
							</div>
							<div class="item">
								<label for="upw" class="i_label"
									style="position: absolute; visibility: visible;">PASSWORD</label><input
									name="" type="password" value="" id="upw" class="i_text upw">
							</div>
							<p class="keeping">
								<input name="" type="checkbox" value="" id="keepid"
									class="i_check"><label for="keepid">로그인 유지</label>
							</p>
							<p class="warning">브라우저를 닫더라도 로그인이 계속 유지될 수 있습니다. 로그인 유지 기능을
								사용할 경우 다음 접속부터는 로그인을 하실 필요가 없습니다. 단, 게임방, 학교 등 공공장소에서 이용 시 개인정보가
								유출될 수 있으니 꼭 로그아웃을 해주세요.</p>
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
	<span class="shadow shadow2"></span><span class="shadow shadow3"></span><span class="shadow shadow4"></span>
</div>
<!-- //Layer Info -->

<!-- Vertical navigation -->
<div id="menu_v" class="menu_v">
	<ul>
	
	<li><a href="#"><span>학부모/학생용</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>학생정보조회</span></a></li>
		<li><a href="#"><span>회비내역조회</span></a></li>
		<li><a href="#"><span>시간표조회</span></a></li>
		<li><a href="#"><span>성적조회</span></a></li>
		<li><a href="#"><span>출결상황조회</span></a></li>
		<li><a href="#"><span>강의계획서열람</span></a></li>
		<li><a href="#"><span>학원차량위치조회(운행시간)</span></a></li>
		<li><a href="#"><span>학생위치조회(학부모전용)</span></a></li>
		</ul>
	</li>
	
	<li> <!-- class="active" --> <a href="#"><span>원생관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li> <!-- class="active" --><a href="./StudentJoin.st"><span>신규등록</span></a></li>
		<li><a href="./StudentListAction.st"><span>수강생관리</span></a></li>
		<li><a href="#"><span>휴원생관리</span></a></li>
		<li><a href="#"><span>퇴원생관리</span></a></li>
		
		</ul>
	</li>
	
	<li><a href="#"><span>학급관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>학급별시간표</span></a></li>
		<li><a href="#"><span>학급정보</span></a></li>
		<li><a href="#"><span>학급별시험정보</span></a></li>
		<li><a href="#"><span>학급별출결현황</span></a></li>
		<li><a href="#"><span>학급별상담내역</span></a></li>
		<li><a href="#"><span>학급진도관리</span></a></li>
		</ul>
	</li>
	
	<li><a href="#"><span>성적관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>학교성적관리</span></a></li>
		<li><a href="#"><span>학원시험관리</span></a></li>
		
		<li><a href="#"><span> - 시험등록</span></a></li>
		<li><a href="#"><span> - 진행중시험</span></a></li>
		<li><a href="#"><span> - 완료된시험</span></a></li>
		</ul>
	</li>
	
	<li><a href="#"><span>시간표관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>전체시간표</span></a></li>
		<li><a href="#"><span>강사별시간표</span></a></li>
		
		<li><a href="#"><span>학급별시간표</span></a></li>
		</ul>
	</li>
	<li><a href="#"><span>게시판</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>공지사항</span></a></li>
		<li><a href="#"><span>업무일지</span></a></li>
		<li><a href="#"><span>강의계획서</span></a></li>
		<li><a href="#"><span>수업자료실</span></a></li>
		
		<li><a href="#"><span>직원자료실</span></a></li>
		<li><a href="#"><span>직원게시판</span></a></li>
		</ul>
	</li>	
	<li><a href="#"><span>직원관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="./EmployeeAdd.em"><span>신규등록</span></a></li>
		
		<li><a href="./EmployeeListAction.em"><span>직원현황</span></a></li>
		<li><a href="./EmployeeAttitudeListAction.em"><span>근태관리</span></a></li>
		<li><a href="#"><span>급여관리</span></a></li>
		</ul>
	</li>
	<li><a href="#"><span>회계관리</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>회비현황</span></a></li>
		<li><a href="#"><span>수입현황</span></a></li>
		<li><a href="#"><span>지출현황</span></a></li>
		<li><a href="#"><span>계좌관리</span></a></li>
		<li><a href="#"><span>전체조회</span></a></li>
		</ul>
	</li>
	<li><a href="#"><span>홈페이지관리(Master전용)</span><span class="i"></span></a>
		<ul style="display:none;">
		<li><a href="#"><span>레벨/권한 설정</span></a></li>
		<li><a href="#"><span>공지사항 관리</span></a></li>
		<li><a href="#"><span>전체 회원 관리</span></a></li>
		
		</ul>
	</li>
	
	</ul>
</div>
<!-- //Vertical navigation  -->



<script type="text/javascript">
jQuery(function($){
	var loginWindow = $('.mw_login');
	var login = $('#login');
	var uid = $('.i_text.uid');
	var upw = $('.i_text.upw');
	var oid = $('.i_text.oid');
	
	// Show Hide
	$('.login_btn_sml').click(function(){
		loginWindow.addClass('open');
	});
	$('#login .close').click(function(){
		loginWindow.removeClass('open');
	});
	// o_login
	$('.o_anchor').click(function(){
		login.removeClass('g_login');
		login.addClass('o_login');
	});
	// g_login
	$('.g_anchor').click(function(){
		login.removeClass('o_login');
		login.addClass('g_login');
	});
	// Warning
	$('#keepid').change(function(){
		if($('#keepid[checked]')){
			$('.warning').toggleClass('open');
		};
	});
	// Input Clear
	var i_text = $('.item>.i_label').next('.i_text');
	$('.item>.i_label').css('position','absolute');
	i_text
		.focus(function(){
			$(this).prev('.i_label').css('visibility','hidden');
		})
		.blur(function(){
			if($(this).val() == ''){
				$(this).prev('.i_label').css('visibility','visible');
			} else {
				$(this).prev('.i_label').css('visibility','hidden');
			}
		})
		.change(function(){
			if($(this).val() == ''){
				$(this).prev('.i_label').css('visibility','visible');
			} else {
				$(this).prev('.i_label').css('visibility','hidden');
			}
		})
		.blur();
	// Validation
	$('#login>.g_login input[type=submit]').click(function(){
		if(uid.val() == '' && upw.val() == ''){
			alert('ID와 PASSWORD를 입력하세요!');
			return false;
		}
		else if(uid.val() == ''){
			alert('ID를 입력하세요!');
			return false;
		}
		else if(upw.val() == ''){
			alert('PASSWORD를 입력하세요!');
			return false;
		}
	});
	$('#login>.o_login input[type=submit]').click(function(){
		if(oid.val() == ''){
			alert('Open ID를 입력하세요!');
			return false;
		}
	});
	// ESC Event
	$(document).keydown(function(event){
		if(event.keyCode != 27) return true;
		if (loginWindow.hasClass('open')) {
			loginWindow.removeClass('open');
		}
		return false;
	});
	// Hide Window
	loginWindow.find('>.bg').mousedown(function(event){
		loginWindow.removeClass('open');
		return false;
	});
});


jQuery(function($){
	
	// Side Menu
	var menu_v = $('div.menu_v');
	var sItem = menu_v.find('>ul>li');
	var ssItem = menu_v.find('>ul>li>ul>li');
	var lastEvent = null;
	
	sItem.find('>ul').css('display','none');
	menu_v.find('>ul>li>ul>li[class=active]').parents('li').attr('class','active');
	menu_v.find('>ul>li[class=active]').find('>ul').css('display','block');

	function menu_vToggle(event){
		var t = $(this);
		
		if (this == lastEvent) return false;
		lastEvent = this;
		setTimeout(function(){ lastEvent=null }, 200);
		
		if (t.next('ul').is(':hidden')) {
			sItem.find('>ul').slideUp(100);
			t.next('ul').slideDown(100);
		} else if(!t.next('ul').length) {
			sItem.find('>ul').slideUp(100);
		} else {
			t.next('ul').slideUp(100);
		}
		
		if (t.parent('li').hasClass('active')){
			t.parent('li').removeClass('active');
		} else {
			sItem.removeClass('active');
			t.parent('li').addClass('active');
		}
	}
	sItem.find('>a').click(menu_vToggle).focus(menu_vToggle);
	
	function subMenuActive(){
		ssItem.removeClass('active');
		$(this).parent(ssItem).addClass('active');
	}; 
	ssItem.find('>a').click(subMenuActive).focus(subMenuActive);
	
	//icon
	menu_v.find('>ul>li>ul').prev('a').append('<span class="i"></span>');
});
</script>
</body>
</html>