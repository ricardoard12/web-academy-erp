<%@page import="java.util.StringTokenizer"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Zipcode Searching</title>
<script type="text/javascript">
	function setZipcode(mType, zip1, zip2, addr) {
		if (mType == "employee") {
			opener.document.joinEmployeeForm.mm_zipcode1.value = zip1;
			opener.document.joinEmployeeForm.mm_zipcode2.value = zip2;
			opener.document.joinEmployeeForm.mm_addr1.value = addr;
			window.close();
		} else if (mType == "student") {
			opener.document.joinForm.member_zipcode1.value = zip1;
			opener.document.joinForm.member_zipcode2.value = zip2;
			opener.document.joinForm.member_addr1.value = addr;
			window.close();			
		}
	}
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	String addr = "";
	String zipcode = "";
	String zip1 = "";
	String zip2 = "";
	String mType = request.getParameter("mType");
//         out.println(mType);
	List zipcodeList = (ArrayList)request.getAttribute("zipcodeList");
%>
<body>
	<div align="center">
		<h1>- 우편번호 검색 -</h1><br><br>
		<form action="./ZipcodeAction.me" method="post">
		      <input type="hidden" name="mType" value="<%=mType %>">
			지역명 : <input type="text" name="dong">
			<input type="submit" value="검색"><br>
			동을 입력하세요. (예 : 방배, 원천, 2글자 이상 입력)<br><br><br>
			- 검색 결과 -<br>
			<%
			if (zipcodeList != null && zipcodeList.size() != 0) {
			%>
				<table border="1">
					<tr><th>우편번호</th><th>주소</th></tr>				
					<%
					for (int i = 0; i < zipcodeList.size(); i++) {
						String data = (String)zipcodeList.get(i);
						StringTokenizer st = new StringTokenizer(data, ",");
						zipcode = st.nextToken();
						addr = st.nextToken();
						st = new StringTokenizer(zipcode, "-");
						zip1 = st.nextToken();
						zip2 = st.nextToken();
					%>
						<tr>
							<td><a href="javascript:setZipcode('<%=mType %>','<%=zip1%>','<%=zip2%>','<%=addr%>')"><%=zipcode%></a></td>
							<td><%=addr %></td>
						</tr>
					<%
					}
					%>
				</table>
			<%
			} else {
				%>검색 결과가 없습니다.	<%
			}
			%>
		</form>
	</div>
</body>
</html>