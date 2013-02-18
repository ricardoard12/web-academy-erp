<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Timestamp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>시간 변경</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	String id = request.getParameter("id");
 	String time = request.getParameter("time");
	String type = request.getParameter("type");
%>
<body>
	<form action="./StudentAttitudeEditTimeAction.st" method="post">
		<input type="hidden" name="id" value="<%=id %>">
		<input type="hidden" name="type" value="<%=type%>">
<%-- 		<input type="hidden" name="time" value="<%=time %>"> --%>
		<table>
		<%if (type.equals("come")) {%>
			<tr><th>출근 시간 변경</th></tr>
		<%} else {%>
			<tr><th>퇴근 시간 변경</th></tr>
		<%} %>	
			<tr>
				<td>
					<select name="hour">
					<%
						for (int i = 1; i <= 24; i++ ) {
							if (i == Integer.parseInt(time.split(":")[0])) {
								%><option selected><%=i %></option><%
							} else {
								%><option><%=i %></option><%
							} 
						}
					%>
					</select>
					<select name="minute">
					<%
						for (int i = 1; i <= 60; i++ ) {
							if (i == Integer.parseInt(time.split(":")[1])) {
								%><option selected><%=i %></option><%
							} else {
								%><option><%=i %></option><%
							} 
						}
					%>
					</select>
				</td>
			</tr>
			<tr>
				<td align="center">
					<input type="submit" value="입력">
					<input type="button" value="취소" onclick="window.close()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>