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
				    List gradeAcademyTesting = (List)request.getAttribute("gradeAcademyTesting");
				%>
				<legend>학원 시험중</legend>
				<form name="grCheck" method="post" >
				<table cellspacing="0" border="1" summary="학원 시험 목록" class="tbl_type_list">
					<caption>학원 시험 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="16%" span="6">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">전체선택<br><input type="checkbox" name="all" onclick="CheckAll()"></th>
							<th scope="col">과목ID</th>
							<th scope="col">과목명</th>
							<th scope="col">과목설명</th>
							<th scope="col">담당강사</th>
							<th scope="col">응시날짜</th>
						</tr>
					</thead>
					<tbody>

					<% for(int i=0; i<gradeAcademyTesting.size(); i++){
						GradeBean gradebean = (GradeBean)gradeAcademyTesting.get(i);%>
						<tr>
							<td><input type="checkbox" name="check" value="<%=gradebean.getGr_code() %>"></td>
							<td><a href=""><%=gradebean.getGr_code() %></a></td>
							<td><%=gradebean.getGr_subject() %></td>
							<td><%=gradebean.getGr_memo() %></td>
							<td><%=gradebean.getEp_id() %></td>
							<td><%=gradebean.getGr_exam_date() %></td>
						</tr>
					<% }%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="6">
								<div class="item">
									<input type="button" value="시험완료" onclick="MoveTested()">
								</div>
						</tr>

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
	
	function confirmCancel(id, type, date) { // 버튼 클릭 확인
		if (confirm("결근 처리 하시겠습니까?") == true) {
// 			var date = document.emAttitudeForm.date.value;
			location.href="./EmployeeAttitudeCancelAction.em?id=" + id + "&type=" + type + "&date=" + date;
			return null;
		}
	}
	
	function MoveTested() {
		count = 0;
		for ( var x = 0; x < grCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
			if(document.grCheck.check[x].checked == true){
				count++;
			} //for문을 사용하여 모두 체크 시킨다.
		}
		
		if(count<=0){
			alert("한 개이상 선택하세요");
			return false;
		}else if(confirm("시험 완료 하시겠습니까?") == true){
			document.grCheck.action = "./GradeMoveTested.gr";
			document.grCheck.submit();
		}
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