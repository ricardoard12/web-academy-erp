<%@page import="java.util.List"%><%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%><%	request.setCharacterEncoding("utf-8");			List timetable = (List) request.getAttribute("timelist");%><!DOCTYPE><html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><link href="./css/default.css" rel="stylesheet" type="text/css"><link href="./css/board.css" rel="stylesheet" type="text/css"><link href="./css/timetable.css" rel="stylesheet" type="text/css"><script src="./js/calendar.js"></script><script src="./js/jquery-1.9.1.js"></script><title><%=session.getAttribute("name")%>님의 시간표</title><script>	</script><style></style></head><body>	<!-- UI Object -->	<div id="wrap">		<!-- header -->		<div id="header">			<jsp:include page="../page/header.jsp"></jsp:include>		</div>		<!-- //header -->		<!-- container -->		<div id="container">			<!-- snb -->			<div class="snb">				<jsp:include page="../page/snb.jsp"></jsp:include>			</div>			<!-- //snb -->			<!-- content -->			<div id="content">				<div id="classSelectSection"></div>				<div id="timetableSection">					<div>						<h1><%=session.getAttribute("name")%>님의 시간표						</h1>					</div>					<table class="timetable">						<tr class="timetablehead">							<th></th>							<th>月</th>							<th>火</th>							<th>水</th>							<th>木</th>							<th>金</th>							<th>土</th>						</tr>						<%							for (int timeindex = 0; timeindex < 7; timeindex++) {						%>						<tr>							<th><%=timeindex + 1%></th>							<%								for (int dayindex = 0; dayindex < 6; dayindex++) {							%><td>								<%									for (int i = 0; i < timetable.size(); i++) {						List table = (List) timetable.get(i);						if (table.get(2).equals(dayindex + "")								&& table.get(3).equals(timeindex + "")) {								%><%=table.get(4)%><br><%=table.get(5)%> <% 	}					} %>							</td>							<%								}							%>						</tr>						<%							}						%>					</table>				</div>			</div>			<!-- //content -->		</div>		<!-- //container -->		<!-- footer -->		<div id="footer">			<jsp:include page="../page/footer.jsp"></jsp:include>		</div>		<!-- //footer -->	</div>	<!-- //UI Object --></body></html>