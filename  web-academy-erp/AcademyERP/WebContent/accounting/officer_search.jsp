<%@page import="academy.accounting.db.AccountingBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List officerlist = (List)request.getAttribute("officerlist");
%>
<body>
	<form action="" name="sSearch" method="post">
	
	<table cellspacing="0" border="1" summary="목록" class="tbl_type_list">
					<caption>목록</caption>
					<colgroup>
						<col width="33%" span="3">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">이름</th>
							<th scope="col">ID</th>
							<th scope="col">주민등록번호</th>
						</tr>
					</thead>
					
					<tbody>
					
					<% if(officerlist == null){%>
						<tr><td colspan="3"><h1>학생 입력해주세요</h1></td></tr>
					<%}else{			

						for(int i=0; i<officerlist.size(); i++){
						    AccountingBean acbean = (AccountingBean)officerlist.get(i);%>
						<tr>
							<td><%=acbean.getMm_name() %></td>
							<td onclick="windowclose('<%=acbean.getMm_id() %>')">
							<a href=""><%=acbean.getMm_id() %></a></td>
							<td><%=acbean.getMm_jumin1() %> - <%=acbean.getMm_jumin1() %></td>
						</tr>
					<% 		}
						}		%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="3">
								<div class="item">
									<input type="button" value="취소" onclick="window.close()">
								</div>
						</tr>
					</tbody>
				</table>
	</form>
	
	<script type="text/javascript">
	function windowclose(mm_id){
		opener.document.acCheck.ac_manager_name.value = mm_id;
		window.close();
	}
	</script>
	
</body>
</html>