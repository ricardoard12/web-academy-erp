<%@page import="academy.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	List receiverList = (List) request.getAttribute("receiverList");
	String receiverID = "";
	String receiverName = "";
	String receiverPhone = "";
%>
</head>
<body>
        <form action="./SendSmsAction.sms" method="post">
        <div align="center">
	        <table border="1">
			<tr><th>송신자번호</th><td><input type="text" name="senderPhone"></td></tr>
	                <tr><th>내용</th><td><textarea rows="5" cols="14" name="message"></textarea> </td></tr>
			<tr><th>수신자</th><th>수신자번호</th></tr>
			<%	
				for (int i = 0; i < receiverList.size(); i++) {
					MemberBean member = (MemberBean)receiverList.get(i);
					String phone = member.getMm_phone().split("-")[0] + member.getMm_phone().split("-")[1] + member.getMm_phone().split("-")[2];
					receiverID += member.getMm_id() + ",";
					receiverName += member.getMm_name() + ",";
					receiverPhone += phone + ",";
			%>
	                <tr><td><%=member.getMm_name()%></td><td><%=phone%></td></tr>
	        	<%} %>
	        	<input type="hidden" name="receiverID" value="<%=receiverID %>">
		        <input type="hidden" name="receiverName" value="<%=receiverName %>">
		        <input type="hidden" name="receiverPhone" value="<%=receiverPhone %>">
	        </table>
		<input type="submit" value="문자발송"><input type="button" value="취소" onclick="window.close()">
	</div>	        
        </form>
</body>
</html>