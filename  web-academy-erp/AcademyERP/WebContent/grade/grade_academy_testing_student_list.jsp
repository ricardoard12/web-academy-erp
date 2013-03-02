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
		<p>#wrap</p>
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
				    List gradeAcademyTestingStudentList = (List)request.getAttribute("gradeAcademyTestingStudentList");
				%>
				<legend>학원 시험중 학생리스트</legend>
				<form action="./GradeAcademyTesting.gr" name="grAddCheck" method="post" >
				<table cellspacing="0" border="1" summary="학원 시험중 학생리스트" class="tbl_type_list">
					<caption>학원 시험중 학생리스트</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="16%" span="6">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">전체선택<br><input type="checkbox" name="all" onclick="CheckAll()"></th>
							<th scope="col">학생아이디</th>
							<th scope="col">학생성적</th>
						</tr>
					</thead>
					<tbody>

					<%	if(gradeAcademyTestingStudentList == null){ %>
						<tr><td colspan="3"><h1>학생추가하세요</h1></td></tr>	    
					<% }else{ %>
					
					<% for(int i=0; i<gradeAcademyTestingStudentList.size(); i++){
						GradeBean gradebean = (GradeBean)gradeAcademyTestingStudentList.get(i);%>
						<tr>
							<td><input type="checkbox" name="check" value="<%=gradebean.getGr_code() %>"></td>
							<td><%=gradebean.getMm_id() %></td>
							<td><%=gradebean.getGr_score() %></td>
						</tr>
					<% }
					}	%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="6">
								<div class="item">
									<input type="text" name="mm_name">
			 						<input type="button" value="학생추가" onclick="addStduent()">
									<input type="submit" value="시험목록">
								</div>
						</tr>

					</tbody>
				</table>
				</form>
				
				<!-- //UI Object -->
				
<script type="text/javascript">
	function CheckAll() {
		if (document.grAddCheck.all.checked == true) { // 체크가 되었다면
			for ( var x = 0; x < grAddCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
				document.grAddCheck.check[x].checked = true; //for문을 사용하여 모두 체크 시킨다.
			}
		} else {
			for ( var x = 0; x < grAddCheck.check.length; x++) { // 모두 해제 시킨다..
				document.grAddCheck.check[x].checked = false;
			}
		}
	}
	
	function addStduent(num) {

		mm_name = document.grAddCheck.mm_name.value;
		if (mm_name.length == 0) {
			alert("학생 이름를 입력하세요");
			document.grAddCheck.mm_name.focus();
			return false;
		}
		window.open('./GradeSsearch.gr?mm_name=' + mm_name, '_blank', 'height=200, width=400');
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