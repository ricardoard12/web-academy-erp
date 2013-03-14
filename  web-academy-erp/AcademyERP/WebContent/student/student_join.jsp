<%@page import="academy.groups.db.GroupsBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
	
	<script type="text/javascript">
	function selectDomain() { // 도메인 선택
		document.StudentJoinForm.mm_email2.value = document.StudentJoinForm.mm_emailDDD.value;
	}
	
	function checkForm() { // 폼 입력 체크
		if (document.StudentJoinForm.mm_name.value.length == 0) {
			alert("이름을 입력하세요.");
			document.StudentJoinForm.mm_name.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_id.value.length == 0) {
			alert("아이디를 입력하세요.");
			document.StudentJoinForm.mm_id.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_pw.value.length == 0) {
			alert("비밀번호를 입력하세요.");
			document.StudentJoinForm.mm_pw.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_pw_check.value.length == 0) {
			alert("비밀번호 확인을 입력하세요.");
			document.StudentJoinForm.mm_pw_check.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_pw.value != document.StudentJoinForm.mm_pw_check.value) {
			alert("입력하신 비밀번호가 서로 다릅니다.");
			document.StudentJoinForm.mm_pw_check.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_jumin1.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.StudentJoinForm.mm_jumin1.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_jumin2.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.StudentJoinForm.mm_jumin2.focus();
			return false;
		}
		if (document.StudentJoinForm.mm_tel1.value.length == 0) {
			alert("전화번호를 입력하세요.");
			document.StudentJoinForm.mm_tel1.focus();
			return false;
		}if (document.StudentJoinForm.mm_tel2.value.length == 0) {
			alert("전화번호를 입력하세요.");
			document.StudentJoinForm.mm_tel2.focus();
			return false;
		}if (document.StudentJoinForm.mm_phone1.value.length == 0) {
			alert("휴대전화 번호를 입력하세요.");
			document.StudentJoinForm.mm_phone1.focus();
			return false;
		}if (document.StudentJoinForm.mm_phone2.value.length == 0) {
			alert("휴대전화 번호를 입력하세요.");
			document.StudentJoinForm.mm_phone2.focus();
			return false;
		}if (document.StudentJoinForm.mm_zipcode1.value.length == 0) {
			alert("우편번호 번호를 입력하세요.");
			document.StudentJoinForm.mm_zipcode1.focus();
			return false;
		}if (document.StudentJoinForm.mm_zipcode2.value.length == 0) {
			alert("우편번호 번호를 입력하세요.");
			document.StudentJoinForm.mm_zipcode2.focus();
			return false;
		}if (document.StudentJoinForm.mm_addr1.value.length == 0) {
			alert("주소 번호를 입력하세요.");
			document.StudentJoinForm.mm_addr1.focus();
			return false;
		}if (document.StudentJoinForm.mm_addr2.value.length == 0) {
			alert("상세주소를 입력하세요.");
			document.StudentJoinForm.mm_addr2.focus();
			return false;
		}if (document.StudentJoinForm.mm_email1.value.length == 0) {
			alert("이메일를 입력하세요.");
			document.StudentJoinForm.mm_email1.focus();
			return false;
		}if (document.StudentJoinForm.mm_email2.value.length == 0) {
			alert("이메일를 입력하세요.");
			document.StudentJoinForm.mm_email2.focus();
			return false;
		}
		
		if (document.StudentJoinForm.st_school_name.value.length == 0) {
			alert("학교이름을  입력하세요.");
			document.StudentJoinForm.st_school_name.focus();
			return false;
		}if (document.StudentJoinForm.st_parent_name.value.length == 0) {
			alert("부모이름을 입력하세요.");
			document.StudentJoinForm.st_parent_name.focus();
			return false;
		}if (document.StudentJoinForm.st_parent_mobile1.value.length == 0) {
			alert("부모연락처을 입력하세요.");
			document.StudentJoinForm.st_parent_mobile1.focus();
			return false;
		}if (document.StudentJoinForm.st_parent_mobile2.value.length == 0) {
			alert("부모연락처을 입력하세요.");
			document.StudentJoinForm.st_parent_mobile2.focus();
			return false;
		}if (document.StudentJoinForm.st_parent_id.value.length == 0) {
			alert("부모아이디을 입력하세요.");
			document.StudentJoinForm.st_parent_id.focus();
			return false;
		}if (document.StudentJoinForm.st_parent_passwd.value.length == 0) {
			alert("비빌번호를  입력하세요.");
			document.StudentJoinForm.st_parent_passwd.focus();
			return false;
		}
		if (document.StudentJoinForm.st_parent_passwd.value != document.StudentJoinForm.st_parent_passwd_check.value) {
			alert("입력하신 비밀번호가 서로 다릅니다.");
			document.StudentJoinForm.st_parent_passwd.focus();
			return false;
		}if (document.StudentJoinForm.st_tuition.value.length == 0) {
			alert("수강료를  입력하세요.");
			document.StudentJoinForm.st_tuition.focus();
			return false;
		}
		
	}
	function ch_inputLen_jumin(date) {    // 주민번호 앞자리 6자리 입력후 자동 넘김
		var obj=document.StudentJoinForm;
		if(date.length==6 ){
			obj.mm_jumin2.focus();
		}
	}
	
	function ch_inputLen_mtel(date) {    // 전화번호 앞자리 3자리 입력후 자동 넘김
		var obj=document.StudentJoinForm;
		if(date.length==3 ){
			obj.mm_tel2.focus();
		}
	}
	function ch_inputLen_mphone(date) {    // 휴폰번호 앞자리4자리 입력후 자동 넘김
		var obj=document.StudentJoinForm;
		if(date.length==4 ){
			obj.mm_phone2.focus();
		}
	}
	function ch_inputLen_zipcode(date) {    // 우편번호 앞자리 3자리 입력후 자동 넘김
		var obj=document.StudentJoinForm;
		if(date.length==3){
			obj.mm_zipcode2.focus();
		}
	}function ch_inputLen_mmoblie(date) {    // 학부모 전화번호 앞자리 5자리 입력후 자동 넘김
		var obj=document.StudentJoinForm;
		if(date.length==4){
			obj.st_parent_mobile2.focus();
		}
	}
	
	
	</script>
<title>Insert title here</title>
</head>
<body>
	<!-- UI Object -->
	<div id="wrap">
		<p>#wrap</p>
		<!-- header -->
		<div id="header">
			<jsp:include page="../page/header.jsp"></jsp:include>
		</div>
		<!-- //header -->
		<!-- container -->
		<div id="container">
			<!-- snb -->
			<div class="snb">
				<jsp:include page="../page/snb.jsp"></jsp:include>
			</div>
			<!-- //snb -->
			<!-- content -->
			<div id="content">

				<!-- 학생 회원가입시작 -->

				<form action="./StudentJoinAction.st" method="post" name="StudentJoinForm" onsubmit="return checkForm()">
					<fieldset>
						<legend>학생 회원가입</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
								<tbody>
									<!-- <tr>
		<th scope="row">회원 이름</th>
		<td>
			<div class="item">
				<input type="text" name="" title="이름입력하세요" class="i_text">
				<button type="button" class="i_help"><span>?</span></button>
				<span class="i_dsc hide">숨겨둔 설명 입니다. 줄 바꿈 없습니다.</span>
			</div>
		</td>
		</tr> 
		<tr>
		<th scope="row">인풋:설명 숨기고 줄 바꿈</th>
		<td>
			<div class="item">
				<input type="text" name="" title="레이블 텍스트" class="i_text">
				<button type="button" class="i_help"><span>?</span></button>
				<p class="i_dsc hide">숨겨둔 설명 입니다. 줄 바꿈 했습니다.</p>
			</div>
		</td>
		</tr> -->
									<!-- <tr>
		<th scope="row">회원 이름</th>
		<td>
			<div class="item">
				<input type="text" name="" title="이름 입력하세요" class="i_text"> 숨겨두지 않은 설명 입니다. 줄 바꿈 없습니다.
			</div>
		</td>
		</tr>-->

									<tr>
										<th scope="row">회원이름</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">이름
													입력</label> <input type="text" name="mm_name" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">회원아이디</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">아이디
													입력</label> <input type="text" name="mm_id" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">비밀번호</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력</label> <input type="password" name="mm_pw" id="temp_input"
													class="i_text" style="width: 300px">
											</div> <br>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력 확인</label> <input type="password" name="mm_pw_check" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									<!-- <tr>
		<th scope="row">인풋:설명 보이고 줄 바꿈</th>
		<td>
			<div class="item">
				<input type="text" name="" title="레이블 텍스트" class="i_text">
				<p class="i_dsc">숨겨두지 않은 설명 입니다. 줄 바꿈 했습니다.</p>
			</div>
		</td>
		</tr>-->
									<tr>
										<th scope="row">주민등록번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_jumin1" title="주민등록번호 입력" class="i_text" maxlength="6" onkeyup="ch_inputLen_jumin(this.value)">
												- <input type="password" name="mm_jumin2" title="레이블 텍스트"
													class="i_text" maxlength="7">
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">전화번호</th>
										<td>
											<div class="item">
												<select name="mm_telDDD">
													<option>02</option>
													<option>031</option>
													<option>051</option>
													<option>052</option>
													<option>053</option>
													<option>055</option>
												</select> 
												<input type="text" name="mm_tel1" title="전화번호" class="i_text" maxlength="3" onkeyup="ch_inputLen_mtel(this.value)">-
												<input type="text" name="mm_tel2" title="전화번호" class="i_text" maxlength="4">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">휴대폰번호</th>
										<td>
											<div class="item">
												<select name="mm_phoneDDD">
													<option>010</option>
													<option>011</option>
													<option>016</option>
													<option>017</option>
													
												</select> <input type="text" name="mm_phone1" title="휴대폰" class="i_text" maxlength="4" onkeyup="ch_inputLen_mphone(this.value)">-
												<input type="text" name="mm_phone2" title="휴대폰" class="i_text" maxlength="4">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">우편번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_zipcode1" title="우편번호1" class="i_text" maxlength="3" onkeyup="ch_inputLen_zipcode(this.value)"> - 
												<input type="text" name="mm_zipcode2" title="우편번호2" class="i_text" maxlength="3">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr1" title="주소" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상세주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr2" title="상세주소" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">이메일 주소</th>
										<td>
											<div class="item">

												<input type="text" name="mm_email1" title="이메일" class="i_text">@
												<input type="text" name="mm_email2" title="직접입력" class="i_text">
												<select name="mm_emailDDD" onchange="selectDomain()">
													<option value="">직접 입력</option>
													<option value="nate.com">nate.com</option>
													<option value="naver.com">naver.com</option>
													<option value="gmail.com">gmail.com</option>
													<option value="hanmail.net">hanmail.net</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학교명</th>
										<td>
											<div class="item">
												<input type="text" name="st_school_name" title="학교명" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학년</th>
										<td>
											<div class="item">

												<select name="st_school_grade">
													<option>1학년</option>
													<option>2학년</option>
													<option>3학년</option>
												</select>
											</div>
										</td>
									</tr>
									<%
										List groups = (List)request.getAttribute("groups");
									%>
										<tr>
										<th scope="row">소속학급</th>
										<td>
											<div class="item">
												<select name="st_school_groups">
												<option>소속학급선택</option>
													<%for(int i =0; i<groups.size(); i++){ 
														GroupsBean group=(GroupsBean)groups.get(i);
													%>
													<option value="<%=group.getGp_name()%>,<%=group.getGp_idx()%>"><%=group.getGp_name()%></option>
													<%} %>
												</select>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">학부모 이름</th>
										<td>
											<div class="item">
												<input type="text" name="st_parent_name" title="학교명" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학부모 연락처</th>
										<td>
											<div class="item">
												<select name="st_parent_mobileDDD">
													<option>010</option>
													<option>011</option>
													<option>016</option>
													<option>017</option>
												</select> <input type="text" name="st_parent_mobile1" title="휴대폰" class="i_text" maxlength="4" onkeyup="ch_inputLen_mmoblie(this.value)">-
												<input type="text" name="st_parent_mobile2" title="휴대폰" class="i_text" maxlength="4">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학부모 아이디</th>
										<td>
											<div class="item">
												<input type="text" name="st_parent_id" title="학교명" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">비밀번호</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력</label> <input type="password" name="st_parent_passwd" id="temp_input"
													class="i_text" style="width: 300px">
											</div> <br>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력 확인</label> <input type="password" name="st_parent_passwd_check" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">수강료</th>
										<td>
											<div class="item">
												<input type="text" name="st_tuition" title="학교명" class="i_text">
												원
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">납부여부</th>
										<td>
											<div class="item">
												<input name="st_tuition_state" type="radio" value="납부" id="c1" class="i_radio"><label
													for="c1">납부</label> <input name="st_tuition_state" type="radio" value="미납"
													id="c2" class="i_radio" checked="checked"><label for="c2">미납</label>
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">메모</th>
										<td>
											<div class="item">
												<textarea name="st_memo" cols="50" rows="5" title="레이블 텍스트"
													class="i_text"></textarea>
											</div>
										</td>
									</tr>

									<!--  가입버튼 -->
									<tr align="right">
										<td></td>
										<td align="left">
											<div class="item">
												<input type="submit" value="가입"> 
												<input type="reset" value="다시쓰기">
												<input type="button" value="목록" onclick="location.href='./StudentList.st'">
											</div>
										</td>
									</tr>



									<!-- <tr>
		<th scope="row">멀티라인 인풋</th>
		<td>
			<div class="item">
				<textarea name="" cols="50" rows="5" title="레이블 텍스트" class="i_text"></textarea>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">멀티라인 인풋:레이블 클리어</th>
		<td>
			<div class="item">
				<label for="temp_textarea" class="i_label" style="position:absolute; visibility:visible;">레이블 텍스트를 멀티라인 인풋과 오버레이</label>
				<textarea name="" cols="50" rows="5" id="temp_textarea" class="i_text"></textarea>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">셀렉트:단일 선택</th>
		<td>
			<div class="item">
				<select name="">
					<option>옵션 하나</option>
					<option>옵션 둘</option>
					<option>옵션 셋</option>
				</select>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">셀렉트:복수 선택</th>
		<td>
			<div class="item">
				<select name="" multiple="multiple">
					<option>옵션 하나</option>
					<option>옵션 둘</option>
					<option>옵션 셋</option>
				</select>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">체크박스 인풋:줄 바꿈 없는</th>
		<td>
			<div class="item">
				<input name="" type="checkbox" value="" id="a1" class="i_check"><label for="a1">레이블</label> <input name="" type="checkbox" value="" id="a2" class="i_check"><label for="a2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">체크박스 인풋:줄 바꿈 있는</th>
		<td>
			<div class="item">
				<input name="" type="checkbox" value="" id="b1" class="i_check"><label for="b1">레이블</label><br>
				<input name="" type="checkbox" value="" id="b2" class="i_check"><label for="b2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">라디오 인풋:줄 바꿈 없는</th>
		<td>
			<div class="item">
				<input name="" type="radio" value="" id="c1" class="i_radio"><label for="c1">레이블</label> <input name="" type="radio" value="" id="c2" class="i_radio"><label for="c2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">라디오 인풋:줄 바꿈 있는</th>
		<td>
			<div class="item">
				<input name="" type="radio" value="" id="d1" class="i_radio"><label for="d1">레이블</label><br>
				<input name="" type="radio" value="" id="d2" class="i_radio"><label for="d2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">파일</th>
		<td>
			<div class="item">
				<input name="" type="file" title="레이블 텍스트">
			</div>
		</td>
		</tr> -->
								</tbody>
							</table>
						</div>
					</fieldset>
				</form>

				<!-- 회원가입 끝 -->

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

	<script type="text/javascript">
		jQuery(function() {
			// Help Toggle
			$('.item>.i_help').click(function() {
				$(this).parent('.item').find('.i_dsc').toggleClass('hide');
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
		});
	</script>
</body>
</html>