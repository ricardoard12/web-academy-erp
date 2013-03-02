<%@page import="academy.student.db.StudentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	String gp_name = (String)request.getAttribute("gp_name");
	List groupsList = (List)request.getAttribute("groupsList");
	String chkValue = (String)request.getAttribute("chkValue"); // 체크박스 값 결합시킨 채로 그대로 전달
%>
<body>
	<form action="./GroupsMoveStudentAction.gp" method="post">
<%-- 		<input type="hidden" name="studentList" value="<%=studentList %>"> --%>
		<input type="hidden" name="chkValue" value="<%=chkValue %>">
		<table border="1">
			<tr><th colspan="3"><h1>학급 선택</h1></th></tr>
			<th>현재 학급</th><th rowspan="2"> → </th><th>대상 학급</th>
			<tr>
				<td><%=gp_name %></td>
			<%if (groupsList.size() == 0) {%>
				<td colspan="3">이동시킬 수 있는 학급이 없습니다.</td></tr>
			<%} else {%>
				<td>
					<select name="gp_name" >
			<%
				for (int i = 0; i < groupsList.size(); i++) { // 이동시킬 학급 목록 출력
					out.println("size : " + groupsList.size());
			%>		
						<%if (!groupsList.get(i).equals(gp_name)) {%>
						<option value="<%=groupsList.get(i)%>"><%=groupsList.get(i)%></option>
						<%} %>
					
			<%
				} 
			%>	
					</select>
				</td>
			<%}%>
			<tr>
				<td align="center" colspan="3">
					<input type="submit" value="이동">
					<input type="button" value="취소" onclick="window.close()">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>