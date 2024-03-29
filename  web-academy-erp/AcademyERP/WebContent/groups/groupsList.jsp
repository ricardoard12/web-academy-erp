<%@page import="academy.master.db.ListPackage"%>
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
<title><%=session.getAttribute("name")%>님의 담당 학급</title>
<script>
	function goAttitue(value){
		location.href="./GroupsAttitudeListAction.gp?gp_name="+value;
	}
</script>
<style>
tbody tr:hover {
	background: #FAECC5;
	cursor: pointer;
}
</style>
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
				<form action="./DeleteClass.master" method="post">
					<input type="hidden" name="page" value="<%=nowpage%>">
					<table cellspacing="0" border="1" summary="학급 목록"
						class="tbl_type_list">
						<caption>담당 학급 목록</caption>
						<colgroup>
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
							<col width="8%">
						</colgroup>
						<thead>
							<tr>
								<th scope="col">ID</th>
								<th scope="col">학급 이름</th>
								<th scope="col">강의실</th>
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
							<tr onclick="goAttitue('<%=list.get(1)%>')">
								<td><%=list.get(0)%></td>
								<td><%=list.get(1)%></td>
								<td>
									<%
										if (list.get(9) == null) {
									%> 미정 <%
										} else {
									%><%=list.get(9)%> <%
 	}
 %>
								</td>
								<td><%=list.get(3)%></td>
								<td><%=list.get(4)%></td>
								<td><%=list.get(5)%></td>
								<td>
									<%
										if (list.get(6).equals("0")) {
									%> 개설 <%
										} else if (list.get(6).equals("1")) {
									%> 개설중 <%
										} else {
									%> 폐강 <%
										}
									%>
								</td>
								<td><%=list.get(7)%></td>
								<td><%=list.get(8)%></td>
							</tr>
							<%
								}
								} else {
							%>
							<tr>
								<td colspan="10">
									<div align="center">
										<h1>현재 맡은 학급이 없습니다.</h1>
									</div> <%
 	}
 %>
								</td>
							</tr>
						</tbody>
					</table>

					<!-- //UI Object -->
					<div align="right">
						<!-- 여기에 뭘 하긴 해야하는데.... -->
						<input type="button" value="뭘"><input type="button"
							value="넣지...">
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