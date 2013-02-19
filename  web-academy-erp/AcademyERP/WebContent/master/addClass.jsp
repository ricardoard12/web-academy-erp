<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List teacherlist=(List)request.getAttribute("tList");
	if(teacherlist==null){
		response.sendRedirect("./index.jsp");
	}
%>
<!DOCTYPE>
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

				<!-- 직원 목록 시작 -->

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