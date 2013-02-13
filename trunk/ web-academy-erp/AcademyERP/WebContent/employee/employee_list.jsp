<%@page import="academy.member.db.MemberBean"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	List employeeList = (List) request.getAttribute("employeeList");
	List memberList = (List) request.getAttribute("memberList");
%>
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
							<th scope="col">선택</th>
							<th scope="col">직원ID</th>
							<th scope="col">이름</th>
							<th scope="col">담당부서</th>
							<th scope="col">직급</th>
							<th scope="col">담당과목</th>
							<th scope="col">담당학급</th>
						</tr>
					</thead>
					<tbody>
					<%
					for (int i = 0; i < employeeList.size(); i++) {
						EmployeeBean employee = (EmployeeBean)employeeList.get(i);
						MemberBean member = (MemberBean)memberList.get(i);
					%>
						<tr>
							<td><input name="" type="checkbox" value="" id="a1"
								class="i_check"><label for="a1"></label></td>

							<td><a href="./EmployeeDetail.em?id=<%=employee.getEp_id()%>"><%=employee.getEp_id()%></a></td>
							<td><%=member.getMm_name() %></td>
							<td><%=employee.getEp_department() %></td>
							<td><%=employee.getEp_position() %></td>
							<td><%=employee.getEp_subject_name() %></td>
							<td><%=employee.getEp_group_id() %></td>
						</tr>
					<%
					}
					%>
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