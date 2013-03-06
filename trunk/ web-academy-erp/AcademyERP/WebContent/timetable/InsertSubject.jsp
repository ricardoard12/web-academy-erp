<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String day = request.getParameter("day");
	String lesson = request.getParameter("lesson");
	String gp_idx=request.getParameter("gp_idx");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과목 삽입</title>
</head>
<body>이부분이 나오면 성공<%=day %>,<%=lesson %>,<%=gp_idx %>
</body>
</html>