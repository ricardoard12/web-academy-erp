<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
int num=Integer.parseInt(request.getParameter("num"));
%>
<body>
<h1>게시판 글삭제</h1>
<form action="./BoardDeleteAction.bo?num=<%=num %>" method="post">
<table border="1">
<tr><td>글비밀번호:</td>
    <td><input type="password" name="board_pass"></td></tr>
<tr><td colspan="2"><input type="submit" value="삭제">
<input type="reset" value="취소"></td></tr>
</table>
</form>
</body>
</html>




