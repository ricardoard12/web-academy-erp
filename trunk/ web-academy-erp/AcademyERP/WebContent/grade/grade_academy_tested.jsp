<%@page import="academy.grade.db.GradeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>
	<!-- UI Object -->
	<div id="wrap">
		<!-- header -->
		<div id="header">
			<jsp:include page="../page/header.jsp"></jsp:include>
		</div>
		<!-- //header -->
		<!-- container -->
		<div id="container">
			<!-- snb -->
			<div class="snb">
				<jsp:include page="../page/snb.jsp"></jsp:include>
			</div>
			<!-- //snb -->
			<!-- content -->
			<div id="content">

				<!-- 학원 시험 목록 시작 -->

				<!-- UI Object -->

				<%
				    List gradeAcademyTested = (List)request.getAttribute("gradeAcademyTested");
				%>
				<legend>학원 시험완료</legend>
				<form name="grCheck" method="post" >
				<table cellspacing="0" border="1" summary="학원 시험 목록" class="tbl_type_list">
					<caption>학원 시험 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="16%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">전체선택<br><input type="checkbox" name="all" onclick="CheckAll()"></th>
							<th scope="col">과목코드</th>
							<th scope="col">학급명</th>
							<th scope="col">과목명</th>
							<th scope="col">과목설명</th>
							<th scope="col">담당강사</th>
							<th scope="col">응시날짜</th>
						</tr>
					</thead>
					<tbody>

					<% for(int i=0; i<gradeAcademyTested.size(); i++){
						GradeBean gradebean = (GradeBean)gradeAcademyTested.get(i);%>
						<tr>
							<td><input type="checkbox" name="check" value="<%=gradebean.getGr_code() %>"></td>
							<td><a href=""><%=gradebean.getGr_code() %></a></td>
							<td><%=gradebean.getGp_name() %></td>
							<td><%=gradebean.getGr_subject() %></td>
							<td><%=gradebean.getGr_memo() %></td>
							<td><%=gradebean.getEp_id() %></td>
							<td><%=gradebean.getGr_exam_date() %></td>
						</tr>
					<% }%>
						<!-- 버튼 -->
						<!-- <tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="button" value="시험삭제">
								</div>
						</tr> -->

					</tbody>
				</table>
				</form>
				
				<!-- //UI Object -->
				
<script type="text/javascript">
	function CheckAll() {
		if (document.grCheck.all.checked == true) { // 체크가 되었다면
			for ( var x = 0; x < grCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
				document.grCheck.check[x].checked = true; //for문을 사용하여 모두 체크 시킨다.
			}
		} else {
			for ( var x = 0; x < grCheck.check.length; x++) { // 모두 해제 시킨다..
				document.grCheck.check[x].checked = false;
			}
		}
	}
	
	function MoveTested() {
		document.grCheck.action = "./GradeMoveTested.gr";
		document.grCheck.submit();
	}
</script>
				<!-- //수강생 관리 끝 -->

			</div>
			<!-- //content -->
		</div>
		<!-- //container -->
		<!-- footer -->
		<div id="footer">
			<jsp:include page="../page/footer.jsp"></jsp:include>
		</div>
		<!-- //footer -->
	</div>
	<!-- //UI Object -->

</body>
</html>