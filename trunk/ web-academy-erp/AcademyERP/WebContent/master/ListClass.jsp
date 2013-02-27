
<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.Vector"%>
<%@page import="academy.employee.db.EmployeeBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	ListPackage Pack = (ListPackage) request
			.getAttribute("ListPackage");
	List classlist = Pack.getClasslist();
	int listcount = Pack.getListcount();
	int nowpage = Pack.getPage();
	int maxpage = Pack.getMaxpage();
	int startpage = Pack.getStartpage();
	int endpage = Pack.getEndpage();
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>권한 설정</title>
<script>
	function statusChange(id, page, value) {
		//var str="#status"+;
		if (value == "x") {
			location.reload();
		} else {
			location.href = "./ClassStatusUpdate.master?id=" + id + "&page="
					+ page + "&status=" + value;
		}
		return null;
	}
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

				<!-- UI Object -->
				<form action="./DeleteClass.master" method="post">
					<input type="hidden" name="page" value="<%=nowpage%>">
					<table cellspacing="0" border="1" summary="직원리스트"
						class="tbl_type_list">
						<caption>직원 목록</caption>
						<colgroup>
							<col width="8%">
							<col>
							<col width="8%" span="8">
						</colgroup>
						<thead>
							<tr>
								<th scope="col"></th>
								<th scope="col">ID</th>
								<th scope="col">학급 이름</th>
								<th scope="col">강의실</th>
								<th scope="col">담당교사ID</th>
								<th scope="col">학년</th>
								<th scope="col">학기</th>
								<th scope="col">인원</th>
								<th scope="col">상태</th>
								<th scope="col">시작일</th>
								<th scope="col">종강일</th>

							</tr>
						</thead>
						<tbody>
							<%
								if (listcount > 0) {
									for (int i = 0; i < classlist.size(); i++) {
										List list = (List) classlist.get(i);
										String status = "폐강";
							%>
							<tr>
								<td><input type="checkbox" name="classlistchk"
									value="<%=list.get(0)%>"></td>
								<td><%=list.get(0)%></td>
								<td><%=list.get(1)%></td>
								<td>
									<%
										if (list.get(9) == null) {
									%><a href="#">강의실 넣기</a> <%
 	} else {
 %> <%=list.get(9)%> <%
 	}
 %>
								</td>
								<td><%=list.get(2)%></td>
								<td><%=list.get(3)%></td>
								<td><%=list.get(4)%></td>
								<td><%=list.get(5)%></td>
								<td><select id="status"
									onchange="statusChange('<%=list.get(0)%>','<%=nowpage%>',value)">
										<option value="x">현재 상태 선택</option>
										<option value="0" <%if (list.get(6).equals("0")) {%> selected
											<%}%>>개설</option>
										<option value="1" <%if (list.get(6).equals("1")) {%> selected
											<%}%>>개강중</option>
										<option value="2" <%if (list.get(6).equals("2")) {%> selected
											<%}%>>폐강</option>
										<%=status%></select></td>
								<td><%=list.get(7)%></td>
								<td><%=list.get(8)%></td>
							</tr>
							<%
								}
								}
							%>
						</tbody>
					</table>

					<!-- //UI Object -->
					<div align="right">
						<input type="button" name="createClass" value="학급생성"
							onclick="location.href='./CreateClass.master'"><input
							type="submit" value="삭제">
					</div>
				</form>
				<div class="paginate_complex" align="center">
					<%
						if (nowpage <= 1) {
					%>
					<a href="#" class="direction prev"><span></span><span></span>처음</a>
					<%
						} else {
					%>
					<a href="./ClassList.master?page=<%=nowpage - 1%>"
						class="direction prev"><span></span>이전</a>
					<%
						}
					%>
					<%
						for (int a = startpage; a <= endpage; a++) {
							if (a == nowpage) {
					%><strong><%=a%></strong>&nbsp;<%
						} else {
					%><a href="./ClassList.master?page=<%=a%>"><%=a%></a>
					<%
						}
						}
					%>
					<%
						if (nowpage >= maxpage) {
					%><a href="#" class="direction next">끝<span></span><span></span></a>
					<%
						} else {
					%><a href="./ClassList.master?page=<%=nowpage + 1%>"
						class="direction next">다음 </a>
					<%
						}
					%>
				</div>
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