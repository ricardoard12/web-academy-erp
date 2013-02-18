<%@page import="java.util.Vector"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*session 값 확인 mm_level >= 3*/
	request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<!-- jquery code -->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
<script src="http://code.jquery.com/ui/1.10.1/jquery-ui.js"></script>
<!-- jquery code -->
<title>시간표 설정</title>
<script>

function popup(){
	$("#dialog").dialog({
		height:140,
		modal:true
		});



</script>
<style>
</style>
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
				<!-- time table start -->
				<div id="timetable">
					<table>
						<tr>
							<th></th>
							<th>Mo</th>
							<th>Tu</th>
							<th>We</th>
							<th>Th</th>
							<th>Fr</th>
							<th>Se</th>
						</tr>
						<%
							for (int i = 0; i < 8; i++) {
						%>
						<tr>
							<td><%=i + 1%></td>
							<%
								for (int j = 0; j < 6; j++) {
							%>
							<td onclick="popup()">(<%=i%>,<%=j%>)
							</td>							
							<%
								}
							%>
						</tr>
						<%
							}
						%>
					</table>
				</div>
			<div id="dialog" title="dooooooo dialog">이 글이 다이알 로그로 나오면 성공 </div>
				<!-- end of time table -->
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