<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	String id = (String)request.getAttribute("id");
	String at_memo = (String)request.getAttribute("msg");
%>
<body>
	<form action="./EmployeeAttitudeAddMemoAction.em" method="post">
		<table>
			<tr><th>메모</th></tr>
			<tr>
				<td>
					<textarea rows="5" cols="40" name="at_memo">
						<%if (at_memo != null) %><%=at_memo %>
					</textarea>
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