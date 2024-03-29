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
				    List gradeSchoolList = (List)request.getAttribute("gradeSchoolList");
				%>
				<table cellspacing="0" border="1" summary="학교 시험 목록" class="tbl_type_list">
					<caption>학교 시험 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="14%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">학교명</th>
							<th scope="col">과목명</th>
							<th scope="col">과목성적</th>
							<th scope="col">과목설명</th>
							<th scope="col">시험기간</th>
						</tr>
					</thead>
					<tbody>

					<% for(int i=0; i<gradeSchoolList.size(); i++){
						GradeBean gradebean = (GradeBean)gradeSchoolList.get(i);%>
						<tr>
							<td><input name="" type="checkbox" value="" id="a1" class="i_check">
							<label for="a1"></label></td>
							<td><%=gradebean.getSt_school_name() %></td>
							<td><%=gradebean.getGr_subject() %></td>
							<td><%=gradebean.getGr_school() %></td>
							<td><%=gradebean.getGr_memo() %></td>
							<td><%=gradebean.getGr_period() %></td>
						</tr>
					<% }%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="submit" value="선택 삭제">
								</div>
						</tr>


					</tbody>
				</table>
				<!-- //UI Object -->

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