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
	String subject ="x";
	if((String) request.getAttribute("sub")!=null){
		subject=(String) request.getAttribute("sub");
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
				<form action="" method="post"">
					<tr>
						<td>과목 :<select name="subject_sel"
							onchange="sub_change(value)">
								<option value="x" <%if (subject.equals("x")) {%> selected <%}%>>과목을
									선택하세요.</option>
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
						<td>담당 선생 선택 : <select>
								<option>선택하세요.</option>
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
						<td>시작일<input type="text" name="startdate" readonly="readonly" onclick="datePicker(event,'startdate',0)">
						</td>
						<td>종료일<input type="text" name="enddate" readonly="readonly" onclick="datePicker(event,'enddate',0)">
						<!--  문자열 잘라서 날짜 비 -->
						</td>
					</tr>
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