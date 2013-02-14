<%@page import="java.util.Vector"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	/*session 값 확인 mm_level >= 3*/
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>권한 설정</title>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List empList = (List) request.getAttribute("empList");
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
							<th scope="col">ID</th>
							<th scope="col">이름</th>
							<th scope="col">사수</th>
							<th scope="col">부서</th>
							<th scope="col">직책</th>
							<th scope="col">권한변경</th>
						</tr>
					</thead>
					<tbody>
						<%
							for (int i = 0; i < empList.size(); i++) {
								List list = (List) empList.get(i);
						%>
						<tr>
							<td><input name="masterchklist" type="checkbox" value=""
								id="a1" class="i_check"><label for="a1"></label></td>
							<%
								int j = 1;
							%>
							<td><%=list.get(j++)%></td>
							<td><%=list.get(j++)%></td>
							<td><%=list.get(j++)%></td>
							<td><%=list.get(j++)%></td>
							<td><%=list.get(j++)%></td>
							<td><select>
									<option></option>
									<option <%if (list.get(j).equals("3")) {%> selected <%}%>>3</option>
									<option <%if (list.get(j).equals("4")) {%> selected <%}%>>4</option>
									<option <%if (list.get(j).equals("5")) {%> selected <%}%>>5</option>
							</select></td>
						</tr>
						<%
							}
						%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="submit" value="저장하기"><input type="button"
										value="뒤로 가기" onclick="history.back()">
								</div>
						</tr>
						<tr>
							<td align="center" colspan="7">
								<form action="./EmpListAction.em">
									<div class="item">
										<input type="text"><input type="submit">
									</div>
								</form>
							</td>
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