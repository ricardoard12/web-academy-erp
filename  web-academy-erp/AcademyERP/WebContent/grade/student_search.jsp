<%@page import="academy.grade.db.GradeBean"%>
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
	List gradeSsearch = (List)request.getAttribute("gradeSsearch");
%>
<body>
	<form action="" name="sSearch" method="post">
	
	<table cellspacing="0" border="1" summary="목록" class="tbl_type_list">
					<caption>목록</caption>
					<colgroup>
						<col width="25%" span="4">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">이름</th>
							<th scope="col">ID</th>
							<th scope="col">학교명</th>
							<th scope="col">주민등록번호</th>
						</tr>
					</thead>
					
					<tbody>
					
					<% if(gradeSsearch == null){%>
						<tr><td colspan="4"><h1>학생 입력해주세요</h1></td></tr>
					<%}else{			

						for(int i=0; i<gradeSsearch.size(); i++){
					    	GradeBean gradebean = (GradeBean)gradeSsearch.get(i);%>
						<tr>
							<td><%=gradebean.getMm_name() %></td>
							<td onclick="windowclose('<%=gradebean.getSt_id() %>', '<%=gradebean.getSt_school_name() %>')">
							<a href=""><%=gradebean.getSt_id() %></a></td>
							<td><%=gradebean.getSt_school_name() %></td>
							<td><%=gradebean.getMm_jumin1() %> - <%=gradebean.getMm_jumin1() %></td>
						</tr>
					<% 		}
						}		%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="4">
								<div class="item">
									<input type="button" value="취소" onclick="window.close()">
								</div>
						</tr>
					</tbody>
				</table>
	</form>
	
	<script type="text/javascript">
	function windowclose(st_id, st_school_name){
		opener.document.grade.st_id.value = st_id;
		opener.document.grade.st_school_name.value = st_school_name;
		window.close();
	}
	</script>
	
</body>
</html>