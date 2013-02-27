<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List teacherlist = (List) request.getAttribute("tList");
	if (teacherlist == null) {
		response.sendRedirect("./index.jsp");
	}
	String subject = "x";
	if ((String) request.getAttribute("sub") != null) {
		subject = (String) request.getAttribute("sub");
	}
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<script src="./js/calendar.js"></script>
<title>반 생성</title>
<script>
	function sub_change(val) {
		location.href = "./CreateClass.master?subject_sel=" + val;
	}
</script>
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
				<form action="./createClassAction.master" id="classForm"
					method="post">
					<table>
						<tr>
							<td>과목 :<select id="subject_sel" name="sub_sel"
								onchange="sub_change(value)">
									<option value="x">과목을 선택하세요.</option>
									<option value="국어" <%if (subject.equals("국어")) {%> selected
										<%}%>>국어</option>
									<option value="영어" <%if (subject.equals("영어")) {%> selected
										<%}%>>영어</option>
									<option value="수학" <%if (subject.equals("수학")) {%> selected
										<%}%>>수학</option>
									<option value="사회" <%if (subject.equals("사회")) {%> selected
										<%}%>>사회</option>
									<option value="과학" <%if (subject.equals("과학")) {%> selected
										<%}%>>과학</option>
							</select></td>
							<td><select name="sub_name">
									<option value="x">반 이름을 선택하세</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
							</select></td>
							<td>담당 선생 선택 : <select id="teacher_sel" name="teacher_sel">
									<option value="x">선택하세요.</option>
									<%
										for (int i = 0; i < teacherlist.size(); i++) {
											List tList = (List) teacherlist.get(i);
											if (tList.get(2).equals(subject)) {
									%>
									<option value="<%=tList.get(0)%>"><%=tList.get(1)%></option>
									<%
										}
										}
									%>
							</select></td>
							<td>시작일<input type="text" name="startdate"
								readonly="readonly" onclick="datePicker(event,'startdate',0)">
							</td>
							<td>종료일<input type="text" name="enddate" readonly="readonly"
								onclick="datePicker(event,'enddate',0)"> <!--  문자열 잘라서 날짜 비 -->
							</td>
						</tr>
						<tr>
							<td><select name="level" onchange="sch_change(value);">
									<option>레벨 선택</option>
									<option value="e">초등</option>
									<option value="m">중</option>
									<option value="h">고등</option>
							</select>학교</td>
							<td><select name="level2" id="lev">
									<option>학년을 선택하세요.</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4" id="hide">4</option>
									<option value="5" id="hide">5</option>
									<option value="6" id="hide">6</option>
							</select></td>
							<td><select name="half">
									<option>학기를 선택하세요</option>
									<option value="1">1학기</option>
									<option value="2">2학기</option>
							</select></td>
						</tr>
						<tr>
							<td><input type="submit"><input type="reset"
								value="다시작성"><input type="button" value="목록가기"></td>
						</tr>
					</table>
				</form>
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
</body>
</html>