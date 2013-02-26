<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List searchlist = (List)request.getAttribute("searchlist");
%>
<body>
	<form action="./GradeSearch.gr" method="post">
		<table>
			<tr><th>리스트</th></tr>
			
			<% if(searchlist == null){%>
				<tr><td>다시 입력해주세요</td></tr>
			<%} %>
			
			<tr>
				<td></td>
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