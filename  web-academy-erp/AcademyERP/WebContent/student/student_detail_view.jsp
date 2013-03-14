<%@page import="academy.groups.db.GroupsBean"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.tomcat.jni.Mmap"%>
<%@page import="academy.student.db.StudentBean"%>
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
<title>Insert title here</title>
</head>
<%
	StudentBean studentbean = (StudentBean) request
			.getAttribute("studentbean");// detail에서 넘긴 값을 받아옴 
	String check = (String) request.getAttribute("check");
%>
<body>
	<!-- UI Object -->
	<!-- content -->
<!-- 	<div id="content"> -->

		<!-- 학생 회원가입시작 -->

		<form action="./StudentModifyAction.st" method="post">
			<fieldset>
				<legend>학생 상세 정보</legend>
				<div class="form_table">
					<table border="1" cellspacing="0" summary="회원가입">
						<tbody>
							<tr>
								<th scope="row">회원이름</th>
								<td>
									<div class="item">
										<label for="temp_input" class="i_label"
											style="position: absolute; visibility: visible;"></label> 
											<%=studentbean.getMm_name()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">회원아이디</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_id()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">주민등록번호</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_jumin1()%> - <%=studentbean.getMm_jumin2()%>
									</div>
								</td>
							</tr>


							<tr>
								<th scope="row">전화번호</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_tel().split("-")[0]%> - <%=studentbean.getMm_tel().split("-")[1]%> - <%=studentbean.getMm_tel().split("-")[2]%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">휴대폰번호</th>
								<td>
									<div class="item">
									<%=studentbean.getMm_phone().split("-")[0]%> - <%=studentbean.getMm_phone().split("-")[1]%> - <%=studentbean.getMm_phone().split("-")[2]%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">우편번호</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_zipcode().split("-")[0]%> - <%=studentbean.getMm_zipcode().split("-")[1]%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">주소</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_addr1()%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">상세주소</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_addr2()%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">이메일 주소</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_email().split("@")[0]%>@<%=studentbean.getMm_email().split("@")[1]%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">학교명</th>
								<td>
									<%=studentbean.getSt_school_name()%>
								</td>
							</tr>

							<tr>
								<th scope="row">학년</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_school_grade()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">소속학급</th>
								<td>
									<div class="item">
										<%=studentbean.getGp_name()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">학부모 이름</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_parent_name()%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">학부모 연락처</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_parent_mobile().split("-")[0]%> - <%=studentbean.getSt_parent_mobile().split("-")[1]%> - <%=studentbean.getSt_parent_mobile().split("-")[2]%>
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">학부모 아이디</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_parent_id()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">재학상태</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_status()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">등급</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_level()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">수강료</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_tuition()%>원
									</div>
								</td>
							</tr>

							<tr>
								<th scope="row">납부여부</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_tuition_state()%>
									</div>
								</td>
							</tr>


							<tr>
								<th scope="row">메모</th>
								<td>
									<div class="item">
										<%=studentbean.getSt_memo()%>
									</div>
								</td>
							</tr>
							<tr>
								<th scope="row">담임</th>
								<td>
									<div class="item">
										<%=studentbean.getMm_manager_id()%>
									</div>
								</td>
							</tr>
							<!--  버튼 -->
							<tr>
								<td align="center" colspan="2">
									<div class="item">
										<input type="button" value="닫기" onclick="window.close()">
									</div>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</fieldset>
		</form>

		<!-- 회원가입 끝 -->

<!-- 	</div> -->
	<!-- //content -->
</body>
</html>