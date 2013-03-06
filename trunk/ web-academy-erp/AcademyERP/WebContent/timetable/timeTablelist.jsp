<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List timetable = (List) request.getAttribute("timetable");
	List grouplist = (List) request.getAttribute("grouplist");
	String gp_idx = "";
	String idx = (String) request.getAttribute("gp_idx");
	if (idx != null) {
		gp_idx = idx;
	}
	String groupsName = "";
	if (request.getAttribute("groupsName") != null) {
		groupsName = (String) request.getAttribute("groupsName");
	}
%>
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
	function popup(row, col, gp_idx) {
		window
				.open("./InsertSubject.time?day=" + col + "&lesson=" + row
						+ "&gp_idx=" + gp_idx, "",
						"height=400,width=300,toolbar=no,status=no,linemenubar=no,scrollbars=no");
		return false;
	}
	function selGp(value) {
		location.href = "./TimeTableList.time?gp_idx=" + value.split(",")[0]
				+ "&groupsName=" + value.split(",")[1];
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
					<form action="TimetableList.time" method="post">
						<table>
							<tr>
								<td><input type="text" placeholder="찾고자하는 반을 적으세요."></td>
								<td><select onchange="selGp(value)"><option
											value="x">반을 선택하세요.</option>
										<%
											for (int i = 0; i < grouplist.size(); i++) {
												List groups = (List) grouplist.get(i);
												String level = "";
												if (groups.get(6).equals("2") == false) {
													if (groups.get(3).equals("e")) {
														level = "초";
													} else if (groups.get(3).equals("m")) {
														level = "중";
													} else if (groups.get(3).equals("h")) {
														level = "고";
													}
										%>
										<option
											value="<%=groups.get(0)%>,<%=level%>-<%=groups.get(1)%>(<%=groups.get(7)%>:
											정원<%=groups.get(5)%>)"
											<%if (gp_idx.equals(groups.get(0))) {%> selected <%}%>><%=level%>-<%=groups.get(1)%>(<%=groups.get(7)%>:
											정원<%=groups.get(5)%>)
										</option>
										<%
											}
											}
										%>
								</select></td>
							</tr>
						</table>
					</form>
				</div>
				<div id="timetableSection">
					<div>
						<h1><%=groupsName%></h1>
					</div>
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
							<td onclick="popup('<%=timeindex%>',1,'<%=gp_idx%>')"></td>
							<td onclick="popup('<%=timeindex%>',2,'<%=gp_idx%>')"></td>
							<td onclick="popup('<%=timeindex%>',3,'<%=gp_idx%>')"></td>
							<td onclick="popup('<%=timeindex%>',4,'<%=gp_idx%>')"></td>
							<td onclick="popup('<%=timeindex%>',5,'<%=gp_idx%>')"></td>
							<td onclick="popup('<%=timeindex%>',6,'<%=gp_idx%>')"></td>
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