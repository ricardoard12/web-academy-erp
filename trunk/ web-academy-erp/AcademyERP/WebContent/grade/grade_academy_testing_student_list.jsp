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
					String gp_name = request.getParameter("gp_name");
					String gr_code = request.getParameter("gr_code");
				%>
				<legend>학원 시험중 학생리스트 : 학급명<%=gp_name %></legend>
				<form action="./GradeAcademyTestingStudentAdd.gr" name="grAddCheck" method="post" >
				<input type="hidden" name="gr_code" value="<%=gr_code %>">
				<input type="hidden" name="gp_name" value="<%=gp_name %>">
				<table cellspacing="0" border="1" summary="학원 시험중 학생리스트" class="tbl_type_list">
					<caption>학원 시험중 학생리스트 </caption>
					<colgroup>
						<col width="16%" span="6">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">전체선택<br><input type="checkbox" name="all" onclick="CheckAll()"></th>
							<th scope="col">학생이름</th>
							<th scope="col">학생아이디</th>
							<th scope="col">학교이름</th>
							<th scope="col">시험성적</th>
							<th scope="col">수정</th>
						</tr>
					</thead>
					<tbody>

                    <%	if(gradeAcademyTestingStudentList == null){ %>
						<tr><td colspan="6"><h1>학생추가하세요</h1></td></tr>	    
					<% }else{     
							for(int i=0; i<gradeAcademyTestingStudentList.size(); i++){
							GradeBean gradebean = (GradeBean)gradeAcademyTestingStudentList.get(i);%>
						<tr>
							<td><input type="checkbox" name="check" value="<%=gradebean.getSt_id() %>"></td>
							<td><%=gradebean.getMm_name() %></td>
							<td><%=gradebean.getSt_id() %></td>
							<td><%=gradebean.getSt_school_name() %></td>
							
							<td><%if(gradebean.getGr_score() == null){ %>
							<input type="text" name="gr_score" value="<%=gradebean.getGr_score() %>" size="3"  >
							<%}else{ %>
							<%=gradebean.getGr_score() %>
							 <%} %></td>
							 
							<td><%if(gradebean.getGr_score() != null){%>
							    <input type="button" value="성적수정" onclick="">
							<%}else{%>
							    
								<%}%></td>
						</tr>
					<% }
					}	%>
					
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="6">
								<div class="item">
									<input type="submit" value="성적입력" onclick="return oneMore()">
									<input type="button" value="시험목록" onclick="location.href='./GradeAcademyTesting.gr'">
								</div>
						</tr>

					</tbody>
				</table>
				</form>
				
				<!-- //UI Object -->
				
<script type="text/javascript">


	$(document).ready(function() {
		// #login-box password field
		$('#password').attr('type', 'text');
		$('#password').val('Password');
	});

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

	function oneMore() {
		count = 0;
		for ( var x = 0; x < grAddCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
			if (document.grAddCheck.check[x].checked == true) {
				count++;
			} //for문을 사용하여 모두 체크 시킨다.
		}

		if (count <= 0) {
			alert("한 개이상 선택하세요");
			return false;
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