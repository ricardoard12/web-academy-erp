<%@page import="academy.member.db.MemberBean"%>
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
		document.joinEmployeeForm.mm_email2.value = document.joinEmployeeForm.select_domain.value;
	}
	
	function checkForm() { // 폼 입력 체크
		if (document.joinEmployeeForm.mm_name.value.length == 0) {
			alert("이름을 입력하세요.");
			document.joinEmployeeForm.mm_name.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_id.value.length == 0) {
			alert("아이디를 입력하세요.");
			document.joinEmployeeForm.mm_id.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_passwd.value.length == 0) {
			alert("비밀번호를 입력하세요.");
			document.joinEmployeeForm.mm_passwd.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_passwd2.value.length == 0) {
			alert("비밀번호 확인을 입력하세요.");
			document.joinEmployeeForm.mm_passwd2.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_passwd.value != document.joinEmployeeForm.mm_passwd2.value) {
			alert("입력하신 비밀번호가 서로 다릅니다.");
			document.joinEmployeeForm.mm_passwd.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_jumin1.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.joinEmployeeForm.mm_jumin1.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_jumin2.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.joinEmployeeForm.mm_jumin2.focus();
			return false;
		}
		if (document.joinEmployeeForm.mm_manager_id.value.length == 0) {
			alert("상위관리자를 입력하세요.");
			document.joinEmployeeForm.mm_manager_id.focus();
			return false;
		}
	}
	
	function selManager(value) {
		location.href="./EmployeeJoin.em?level=" + value;
		return null;
	}
</script>
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List managerList = (List)request.getAttribute("managerList"); 
	String level = (String) request.getAttribute("level");
%>
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

				<!-- 직원 회원가입시작 -->

				<form action="./EmployeeJoinAction.em" method="post"
					name="joinEmployeeForm" onsubmit="return checkForm()">
					<fieldset>
						<legend>직원 회원가입</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
								<tbody>
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
													입력</label> <input type="password" name="mm_passwd" id="temp_input"
													class="i_text" style="width: 300px">
											</div> <br>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력 확인</label> <input type="password" name="mm_passwd2"
													id="temp_input" class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">주민등록번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_jumin1" title="주민등록번호 입력"
													class="i_text"> - <input type="password"
													name="mm_jumin2" title="레이블 텍스트" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">전화번호</th>
										<td>
											<div class="item">
												<select name="mm_telDDD">
													<option value="02">02</option>
													<option value="031">031</option>
													<option value="051">051</option>
													<option value="052">052</option>
													<option value="053">053</option>
													<option value="055">055</option>
												</select> 
												<input type="text" name="mm_tel1" title="전화번호" class="i_text"> - 
												<input type="text" name="mm_tel2" title="전화번호" class="i_text">
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
												</select> 
												<input type="text" name="mm_phone1" title="휴대폰" class="i_text"> - 
												<input type="text" name="mm_phone2" title="휴대폰" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">우편번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_zipcode1" title="우편번호1" class="i_text"> - 
													<input type="text" name="mm_zipcode2" title="우편번호2" class="i_text">
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
												<input type="text" name="mm_addr2" title="상세주소"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">이메일 주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_email1" title="이메일" class="i_text"> @ 
												<input type="text" name="mm_email2" title="도메인" class="i_text"> 
												<select name="select_domain" onchange="selectDomain()">
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
										<th scope="row">회원 등급</th>
										<td>
											<div class="item">
												<select name="mm_level" onchange="selManager(value)">
													<option value="">선택하세요</option>
													<option value="3" <%if (level == "3") {%> selected <%} %>>3(강사)</option>
													<option value="4" <%if (level == "4") {%> selected <%} %>>4(관리자)</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상위 관리자 ID</th>
										<td>
											<div class="item">
											<select name="mm_manager_id">
											<option value="없음">선택하세요</option>
											<%for (int i = 0; i < managerList.size(); i++)  {
												MemberBean member = (MemberBean) managerList.get(i);
											%>
												<option value="<%=member.getMm_manager_id()%>"><%=member.getMm_name() %>(<%=member.getMm_manager_id()%>)</option>
											<%} %>
											</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당부서</th>
										<td>
											<div class="item">
												<select name="ep_department"  title="담당부서" class="i_text">
													<option value="없음">선택하세요</option>
													<option value="교육">교육</option>
													<option value="교육">총무</option>
													<option value="교육">상담</option>
												</select>
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">직급</th>
										<td>
											<div class="item">
											<select name="ep_position"  title="직급" class="i_text">
													<option value="없음">선택하세요</option>
													<option value="강사">강사</option>
													<option value="주임">주임</option>
													<option value="팀장">팀장</option>
													<option value="실장">실장</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당과목</th>
										<td>
											<div class="item">
												<select name="ep_subject_name">
													<option value="">없음</option>
													<option value="국어">국어</option>
													<option value="영어">영어</option>
													<option value="수학">수학</option>
													<option value="사회">사회</option>
													<option value="과학">과학</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">연봉</th>
										<td>
											<div class="item">
												<input type="text" name="ep_salary" title="연봉"
													class="i_text">원
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">은행명</th>
										<td>
											<div class="item">
												<input type="text" name="ep_bank_name" title="은행명"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">계좌번호</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_num" title="계좌번호"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">예금주</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_name" title="예금주"
													class="i_text">
											</div>
										</td>
									</tr>

									<!--  가입버튼 -->
									<tr align="right">
										<td></td>
										<td align="left">
											<div class="item">
												<input type="submit" value="가입"> 
												<input type="button" value="취소" onclick="history.back()">
											</div>
										</td>
									</tr>
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