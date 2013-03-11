<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function windowClosing() {
		opener.location.reload();
		window.close();
	}
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	List receiverIDList = (List) request.getAttribute("receiverIDList");
	List receiverNameList = (List) request.getAttribute("receiverNameList");
	List receiverPhoneList = (List) request.getAttribute("receiverPhoneList");
	List resultMsgList = (List) request.getAttribute("resultMsgList");
	String senderPhone = (String) request.getAttribute("senderPhone");
	String message = (String) request.getAttribute("message");
%>
<body>
	<div align="center">
		<h1>전송 결과</h1>
		<table border="1">
			<th>수신자</th><th>수신번호</th><th>발신번호</th><th>전송메세지</th><th>전송결과</th>
			<%
			for (int i = 0; i < receiverIDList.size(); i++) {
				String receiverID = (String) receiverIDList.get(i);
				String receiverName = (String) receiverNameList.get(i);
				String receiverPhone = (String) receiverPhoneList.get(i);
				String resultMsg = "";
				
				if (resultMsgList.get(i).equals("00")) {
					resultMsg = "전송 성공";
				} else if (resultMsgList.get(i).equals("20")) {
					resultMsg = "아이디 혹은 패스워드 틀림";
				} else if (resultMsgList.get(i).equals("21")) {
					resultMsg = "전송 결과 조회 실패";
				} else if (resultMsgList.get(i).equals("30")) {
					resultMsg = "사용가능한 문자 잔량이 없음";
				} else {
					resultMsg = "기타 에러 발생(문의 요망)";
				}
				
			%>
				<tr>
					<td><%=receiverName %>(<%=receiverID %>)</td>
					<td><%=receiverPhone %></td>
					<td><%=senderPhone %></td>
					<td><%=message %></td>
					<td><%=resultMsg %></td>
				</tr>	
			<%}%>
		</table>
		<input type="button" value="확인" onclick="windowClosing()">
	</div>

</body>
</html>