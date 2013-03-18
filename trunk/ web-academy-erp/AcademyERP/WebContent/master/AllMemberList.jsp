<%@page import="java.util.Vector"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*session 값 확인 mm_level >= 3*/
	request.setCharacterEncoding("UTF-8");
	List empList = (List) request.getAttribute("empList");
	String findname = "";
	if (request.getParameter("findname") != null) {
		findname = request.getParameter("findname");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>권한 설정</title>
<script>
	
</script>
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

				<!-- 직원 목록 시작 -->

				<!-- UI Object -->

				<table cellspacing="0" border="1" summary="직원리스트"
					class="tbl_type_list">
					<caption>직원 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th id="col">ID</th>
							<th id="col">이름</th>
							<th id="col">직업</th>
							<th id="col">상태</th>
							<th id="col"></th>
							<th id="col"></th>
							<th id="col"></th>

						</tr>
					</thead>
					<tbody>
					</tbody>
				</table>
				<!-- //UI Object -->

				<!-- //전체  관리 끝 -->
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