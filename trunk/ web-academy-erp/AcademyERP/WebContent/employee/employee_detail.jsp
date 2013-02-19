<%@page import="org.apache.tomcat.jni.Mmap"%>
<%@page import="academy.member.db.MemberBean"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">PE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
	function selectDomain() { // 도메인 선택
		document.detailEmployeeForm.mm_email2.value = document.joinEmployeeForm.select_domain.value;
	}
	
	function checkForm() { // 폼 입력 체크
		if (document.detailEmployeeForm.mm_name.value.length == 0) {
			alert("이름을 입력하세요.");
			document.detailEmployeeForm.mm_name.focus();
			return false;
		}
		if (document.detailEmployeeForm.mm_jumin1.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.detailEmployeeForm.mm_jumin1.focus();
			return false;
		}
		if (document.detailEmployeeForm.mm_jumin2.value.length == 0) {
			alert("주민등록번호를 입력하세요.");
			document.detailEmployeeForm.mm_jumin2.focus();
			return false;
		}
		if (document.detailEmployeeForm.mm_manager_id.value.length == 0) {
			alert("상위관리자를 입력하세요.");
			document.detailEmployeeForm.mm_manager_id.focus();
			return false;
		}
		
		if (confirm("수정하시겠습니까?") == true) {
			return true;
		} else {
			return false;
		}
	}
</script>
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	MemberBean member = (MemberBean)request.getAttribute("member");
	EmployeeBean employee = (EmployeeBean)request.getAttribute("employee");
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

				<!-- 직원 상세 정보 조회 시작 -->

				<form action="./EmployeeModifyAction.em" method="post" name="detailEmployeeForm" onsubmit="return checkForm()">
					<fieldset>
						<legend>직원 상세 정보</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="직원 상세정보">
								<tbody>
									<tr>
										<th scope="row">이름</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">이름
													입력</label> <input type="text" name="mm_name" id="temp_input"
													class="i_text" style="width: 300px" value="<%=member.getMm_name() %>">
											</div>
										</td>
									</tr>
									
									<tr>
										<th scope="row">아이디</th>
										<td>
											<div class="item">
												<%=member.getMm_id() %>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">주민등록번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_jumin1" title="주민등록번호 입력"
													class="i_text" value="<%=member.getMm_jumin1() %>"> - <input type="password"
													name="mm_jumin2" title="레이블 텍스트" class="i_text" value="<%=member.getMm_jumin2() %>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">전화번호</th>
										<td>
											<div class="item">
												<select name="mm_telDDD">
													<option value="02" <%if (member.getMm_tel().split("-")[0].equals("02")) %> selected>02</option>
													<option value="031" <%if (member.getMm_tel().split("-")[0].equals("031")) %> selected>031</option>
													<option value="051" <%if (member.getMm_tel().split("-")[0].equals("051")) %> selected>051</option>
													<option value="052" <%if (member.getMm_tel().split("-")[0].equals("052")) %> selected>052</option>
													<option value="053" <%if (member.getMm_tel().split("-")[0].equals("053")) %> selected>053</option>
													<option value="055" <%if (member.getMm_tel().split("-")[0].equals("055")) %> selected>055</option>
												</select> 
												<input type="text" name="mm_tel1" title="전화번호"
													class="i_text" value="<%=member.getMm_tel().split("-")[1]%>"> - 
												<input type="text" name="mm_tel2" title="전화번호"
													class="i_text" value="<%=member.getMm_tel().split("-")[2]%>">	
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">휴대폰번호</th>
										<td>
											<div class="item">
												<select name="mm_phoneDDD">
													<option value="010" <%if (member.getMm_phone().split("-")[0].equals("010")) %> selected>010</option>
													<option value="011" <%if (member.getMm_phone().split("-")[0].equals("011")) %> selected>011</option>
													<option value="016" <%if (member.getMm_phone().split("-")[0].equals("016")) %> selected>016</option>
													<option value="017" <%if (member.getMm_phone().split("-")[0].equals("017")) %> selected>017</option>
												</select> 
												<input type="text" name="mm_phone1" title="휴대폰"
													class="i_text" value="<%=member.getMm_phone().split("-")[1]%>"> - 
												<input type="text" name="mm_phone2" title="휴대폰"
													class="i_text" value="<%=member.getMm_phone().split("-")[2]%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">우편번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_zipcode1" title="우편번호1"
													class="i_text" value="<%=member.getMm_zipcode().split("-")[0] %>"> - <input type="text"
													name="mm_zipcode2" title="우편번호2" class="i_text" value="<%=member.getMm_zipcode().split("-")[1] %>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr1" title="주소" class="i_text" value="<%=member.getMm_addr1() %>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상세주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr2" title="상세주소"
													class="i_text" value="<%=member.getMm_addr2() %>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">이메일 주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_email1" title="이메일"
													class="i_text" value="<%=member.getMm_email().split("@")[0] %>">@ <input type="text"
													name="mm_email2" title="도메인" class="i_text" value="<%=member.getMm_email().split("@")[1] %>"> 
												<select	name="select_domain" onchange="selectDomain()">
													<option value="">직접 입력</option>
													<option value="nate.com" <%if (member.getMm_email().split("@")[1].equals("nate.com")) %> selected>nate.com</option>
													<option value="naver.com" <%if (member.getMm_email().split("@")[1].equals("naver.com")) %> selected>naver.com</option>
													<option value="gmail.com" <%if (member.getMm_email().split("@")[1].equals("gmail.com")) %> selected>gmail.com</option>
													<option value="hanmail.net" <%if (member.getMm_email().split("@")[1].equals("hanmail.com")) %> selected>hanmail.net</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">회원 등급</th>
										<td>
											<div class="item">
												<%=member.getMm_level() %>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상위 관리자 ID</th>
										<td>
											<div class="item">
												<input type="text" name="mm_manager_id" title="상위 관리자 ID"
													class="i_text" value="<%=member.getMm_manager_id()%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당부서</th>
										<td>
											<div class="item">
												<input type="text" name="ep_department" title="담당부서"
													class="i_text" value="<%=employee.getEp_department()%>">
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">직급</th>
										<td>
											<div class="item">
												<input type="text" name="ep_position" title="직급"
													class="i_text" value="<%=employee.getEp_position()%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당과목</th>
										<td>
											<div class="item">
												<select name="ep_subject_name">
													<option value="국어" <%if (employee.getEp_subject_name().equals("국어")) %> selected>국어</option>
													<option value="영어" <%if (employee.getEp_subject_name().equals("영어")) %> selected>영어</option>
													<option value="수학" <%if (employee.getEp_subject_name().equals("수학")) %> selected>수학</option>
													<option value="사회" <%if (employee.getEp_subject_name().equals("사회")) %> selected>사회</option>
													<option value="과학" <%if (employee.getEp_subject_name().equals("과학")) %> selected>과학</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당학급</th>
										<td>
											<div class="item">
												<input type="text" name="ep_group_id" title="담당학급"
													class="i_text" value="<%=employee.getEp_group_id()%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">연봉</th>
										<td>
											<div class="item">
												<input type="text" name="ep_salary" title="연봉"
													class="i_text" value="<%=employee.getEp_salary()%>">원
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">은행명</th>
										<td>
											<div class="item">
												<input type="text" name="ep_bank_name" title="은행명"
													class="i_text" value="<%=employee.getEp_bank_name()%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">계좌번호</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_num" title="계좌번호"
													class="i_text" value="<%=employee.getEp_account_num()%>">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">예금주</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_name" title="예금주"
													class="i_text" value="<%=employee.getEp_account_name()%>">
											</div>
										</td>
									</tr>

									<!-- 수정 버튼 -->
									<tr align="right">
										<td></td>
										<td align="left">
											<div class="item">
												<input type="submit" value="수정"> 
												<input type="button" value="목록" onclick="location.href='./EmployeeListAction.em'">
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</fieldset>
				</form>

				<!-- 직원 상세정보 조회 끝 -->

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