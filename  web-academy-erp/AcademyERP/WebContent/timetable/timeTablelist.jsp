<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<link href="./css/timetable.css" rel="stylesheet" type="text/css">
<script src="./js/calendar.js"></script>
<script src="./js/jquery-1.9.1.js"></script>
<title>시간표 작성</title>
<script>
	// 	$(function() {
	// 	$(".timetable td").bind("click", function() {
	// 			alert($(".timetable td").);
	// 		});
	function popup(row, col) {
		window
				.open("./InsertSubject.time?day="+col+"&lesson="+row, "",
						"height=400,width=300,toolbar=no,status=no,linemenubar=no,scrollbars=no");
		return false;
	}

	// 	});
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
				<div id="classSelectSection" style="min-height: 70">
					<table>
						<tr>
							<td>여기에 원하는 리스트 메뉴 삽입</td>
						</tr>
					</table>
				</div>
				<div id="timetableSection">
					<table class="timetable">

						<tr class="timetablehead">
							<th></th>
							<th>月</th>
							<th>火</th>
							<th>水</th>
							<th>木</th>
							<th>金</th>
							<th>土</th>
						</tr>
						<%
							for (int timeindex = 1; timeindex < 8; timeindex++) {
						%>
						<tr>
							<th><%=timeindex%></th>
							<td onclick="popup('<%=timeindex%>',1)"></td>
							<td onclick="popup('<%=timeindex%>',2)"></td>
							<td onclick="popup('<%=timeindex%>',3)"></td>
							<td onclick="popup('<%=timeindex%>',4)"></td>
							<td onclick="popup('<%=timeindex%>',5)"></td>
							<td onclick="popup('<%=timeindex%>',6)"></td>
						</tr>
						<%
							}
						%>
					</table>
				</div>
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