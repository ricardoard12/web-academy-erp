<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="academy.faq_board.db.Faq_boardDAO"%>

<%@page import="academy.faq_board.db.Faq_boardbean"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

<%
request.setCharacterEncoding("utf-8");
// Faq_boardbean faq_boardbean = (Faq_boardbean) request.getAttribute("faq_boardbean");
// DB검색을 위한 num값 전달
int num = Integer.parseInt(request.getParameter("num"));

out.print("Faq_num값은 : " + num + "입니다.");
%>
<style type="text/css">
.table{
margin-top: 30px; 
/* padding-top: 3000px; */
}
</style>

</head>
<body>
<!-- 비밀번호 체크를 위하여 form에서 FrontController로 checkAction으로 submit으로 값을 전달하도록 한다. -->
<!-- 해당 Action에서 DAO로 기능을 돌린 후 다시 check.html으로 값을 전달하도록 forward값을 설정한다. -->
<!-- 값을 전달받은 check.html은 DB값과 전달받은 값을 받은 Boolean값을 가지고 alert를 띄우도록한다. -->


<form action="./Faq_UserCheckAction.fb" method="get">
<input type="hidden" name="faq_num" value="<%=num %>">

<table border="1" align="center" class="table" cellpadding="0" cellspacing="0">

<th>비밀번호 확인</th>

<tr>
	<td>
		<input type="password" name="faq_passwd">
		<input type="submit" name="submit" value="확인">
	</td>
</tr>
</table>
</form>
</body>
</html>