<%@page import="academy.attitude.db.AttitudeBean"%>
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
<%
	request.setCharacterEncoding("UTF-8");
	List attitudeList = (List) request.getAttribute("attitudeList");
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

				<!-- 직원 출근 현황 시작 -->

				<!-- UI Object -->
				<table cellspacing="0" border="1" summary="유형별 자산목록리스트"
					class="tbl_type_list">
					<caption>직원 출근 현황</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="6">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">이름(아이디)</th>
							<th scope="col">출결상황</th>
							<th scope="col">출근시간</th>
							<th scope="col">퇴근시간</th>
							<th scope="col">메모</th>
						</tr>
					</thead>
					<tbody>
					<%
					for (int i = 0; i < attitudeList.size(); i++) {
						AttitudeBean attitude = (AttitudeBean)attitudeList.get(i);
					%>
						<tr>
							<td><input name="" type="checkbox" value="" id="a1"
								class="i_check"><label for="a1"></label>
							</td>
							<td><%=attitude.getMm_name() %>(<%=attitude.getAt_member_id() %>)</td>
							<td><%=attitude.getAt_report_state() %></td>
							<td><%=attitude.getAt_open_time() %></td>
							<td><%=attitude.getAt_close_time() %></td>
							<td><%=attitude.getAt_memo() %></td>
						</tr>
					<%
					}
					%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="submit" value="선택 출근"> <input
										type="button" value="선택 결근"> <input type="button"
										value="선택 문자 발송">
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