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
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>권한 설정</title>
<script>
	function changelevel(id, level,findname) {
		var i = confirm("정말 변경 하시겠습니까?");
		if (i) {
			location.href = "./updatelevel.master?id=" + id + "&level=" + level
					+ "&findname=" +findname;
		} else {
			location.href = "./LevelList.master?findname=" + findname;
		}
	}
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
							<th scope="col">ID</th>
							<th scope="col">이름</th>
							<th scope="col">직책</th>
							<th scope="col">부서</th>
							<th scope="col">사수</th>
							<th scope="col">상태</th>
							<th scope="col">현재 권한</th>
							<th></th>
							<th scope="col">권한변경</th>
						</tr>
					</thead>
					<tbody>

						<%
							for (int i = 0; i < empList.size(); i++) {
								List list = (List) empList.get(i);
						%>
						<form name="<%="level" + i%>" action="./updatelevel.master"
							method="post">

							<input type="hidden" name="id" value="<%=list.get(0)%>">
							<tr>
								<td><%=list.get(0)%></td>
								<td><%=list.get(1)%></td>
								<td><%=list.get(2)%></td>
								<td><%=list.get(3)%></td>
								<td><%=list.get(4)%></td>
								<td><%=list.get(6)%></td>
								<td><%=list.get(5)%></td>
								<td>───▷</td>
								<td>
									<%
										if (Integer.parseInt((String) list.get(5)) > 2) {
									%><select name="level"
									onchange="changelevel('<%=list.get(0)%>', value,'<%=findname%>');">
										<option>권한 레벨 선택</option>
										<option value="3" <%if (list.get(5).equals("3")) {%> selected
											<%}%>>3(일반)</option>
										<option value="4" <%if (list.get(5).equals("4")) {%> selected
											<%}%>>4(팀장)</option>
										<option value="5" <%if (list.get(5).equals("5")) {%> selected
											<%}%>>5(최고관리자)</option>
								</select> <%
 	} else {
 %> 변경불가 <%
 	}
 %>
								</td>
								<%
									}
								%>
							</tr>
						</form>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="9">
								<div class="item">
									<input type="button" value="뒤로 가기" onclick="history.back()">
								</div>
						</tr>

						<tr>
							<td align="center" colspan="9">
								<form action="./LevelList.master">
									<div class="item">
										<input type="text" name="findname"><input
											type="submit" value="검색하기">
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