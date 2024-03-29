<%@page import="academy.member.db.MemberBean"%>
<%@page import="academy.employee.db.EmployeeBean"%>
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
<script type="text/javascript">
	function memoOpen(id, ep_memo,date) { // 메모창 열기
		if (ep_memo == "null") ep_memo="";
		window.open("./EmployeeOutgoingMemoAction.em?id=" + id + "&ep_memo=" + ep_memo + "&date=" + date, "memo", "width=350,height=200,scrollbars=no");
	}
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");

	List employeeList = (List) request.getAttribute("employeeList");
	List memberList = (List) request.getAttribute("memberList");
%>
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
<!-- 						<colgroup> -->
<!-- 							<col width="14%"> -->
<!-- 							<col> -->
<!-- 							<col width="14%" span="8"> -->
<!-- 						</colgroup> -->
						<thead>
							<tr>
								<th scope="col" width="150">아이디</th>
								<th scope="col" width="150">이름</th>
								<th scope="col" width="150">담당부서</th>
								<th scope="col" width="150">직급</th>
								<th scope="col" width="150">담당과목</th>
								<th scope="col" width="150">근무시작일</th>
								<th scope="col" width="150">근무종료일</th>
								<th scope="col" width="200">메모</th>
							</tr>
						</thead>
						<tbody>
							<%
								for (int i = 0; i < employeeList.size(); i++) {
									EmployeeBean employee = (EmployeeBean) employeeList.get(i);
									MemberBean member = (MemberBean) memberList.get(i);
							%>
							<tr>
								<td width="150"><a
									href="./EmployeeDetailAction.em?id=<%=employee.getEp_id()%>"><%=employee.getEp_id()%></a></td>
								<td width="150"><%=member.getMm_name()%></td>
								<td width="150"><%=employee.getEp_department()%></td>
								<td width="150"><%=employee.getEp_position()%></td>
								<td width="150"><%=employee.getEp_subject_name()%></td>
								<td width="150"><%=employee.getEp_in_date()%></td>
								<td width="150"><%=employee.getEp_out_date()%></td>
								<td width="200">
									<%
										if (employee.getEp_memo() != null) { // 메모가 기록되어 있을 경우
											if (employee.getEp_memo().length() > 10) { // 메모가 10글자를 넘으면 축약
												%><a href="#" onclick="memoOpen('<%=employee.getEp_id() %>','<%=employee.getEp_memo()%>')"><%=employee.getEp_memo().substring(0,10) + "..."%></a><%
											} else {
												if (employee.getEp_memo().length() == 0) { // 메모 편집 시 글자 다 지우고 완료 시(문자 길이 0일 때) 입력버튼 표시
													%><input type="button" value="입력" onclick="memoOpen('<%=employee.getEp_id() %>','<%=employee.getEp_memo()%>')"><%
												} else {
													%><a href="#" onclick="memoOpen('<%=employee.getEp_id() %>','<%=employee.getEp_memo()%>')"><%=employee.getEp_memo()%></a><%
												}
											} 
										} else { // 메모 없을 경우
											%><input type="button" value="입력" onclick="memoOpen('<%=employee.getEp_id() %>','null')"><%
										}%>
								</td>
							</tr>
							<%
								}
							%>
							<!-- 버튼 -->
<!-- 							<tr align="right"> -->
<!-- 								<td align="center" colspan="8"> -->
<!-- 									<div class="item"> -->
<!-- 										<input type="submit" value="선택 삭제"> -->
<!-- 									</div> -->
<!-- 							</tr> -->
						</tbody>
					</table>
				</form>
				<!-- //UI Object -->

				<!-- //직원 관리 끝 -->

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