<%@page import="java.text.SimpleDateFormat"%>
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
<script type="text/javascript">
	function memoOpen(id, at_memo) {
		if (at_memo == "null") at_memo="";
		window.open("./EmployeeAttitudeMemoAction.em?id=" + id + "&at_memo=" + at_memo, "memo", "width=350,height=200,scrollbars=no");
	}
	function timeEditOpen(id, time, type) {
		window.open("./EmployeeAttitudeEditTime.em?id=" + id + "&time=" + time + "&type=" + type, "timeEdit", "width=350,height=200,scrollbars=no");
	}
	function confirmCancel(id, type) {
		if (confirm("결근 처리 하시겠습니까?") == true) {
			location.href="./EmployeeAttitudeCancelAction.em?id=" + id + "&type=" + type;
			return null;
		}
	}
	function confirmCancel2(id, type) {
		if (confirm("취소 처리 하시겠습니까?") == true) {
			location.href="./EmployeeAttitudeCancelAction.em?id=" + id + "&type=" + type;
			return null;
		}
	}	
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List attitudeList = (List) request.getAttribute("attitudeList");
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
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
								<th scope="col">이름(아이디)</th>
								<th scope="col">출결상황</th>
								<th scope="col">출근시간</th>
								<th scope="col">퇴근시간</th>
								<th scope="col">메모</th>
								<th scope="col">취소</th>
							</tr>
						</thead>
						<tbody>
						<%
						for (int i = 0; i < attitudeList.size(); i++) {
							AttitudeBean attitude = (AttitudeBean)attitudeList.get(i);
						%>
							<tr>
								<td><%=attitude.getMm_name() %>(<%=attitude.getAt_member_id() %>)</td>
								<td>
									<%
										// at_report_state Y : 출근, N : 미출근
										if (attitude.getAt_report_state().equals("Y")) {%>출근<%	} 
										else {%>미출근<%} 
									%>
								</td>
								<td>
									<%
										if (attitude.getAt_come_time() != null) { // 출근 시간이 기록되어 있을 경우
											%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdf.format(attitude.getAt_come_time()) %>','come')"><%=sdf.format(attitude.getAt_come_time()) %></a>&nbsp;
											<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','come')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
										} else { %><input type="button" value="출근" onclick="location.href='./EmployeeAttitudeTimeRecordingAction.em?type=come&id=<%=attitude.getAt_member_id()%>'">	<%} 
									%>	
								</td>
								<td>
									<%
										if (attitude.getAt_leave_time() != null) { // 퇴근 시간이 기록되어 있을 경우
											%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdf.format(attitude.getAt_leave_time()) %>','leave')"><%=sdf.format(attitude.getAt_leave_time()) %></a>&nbsp;
											<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','leave')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
										} else { %><input type="button" value="퇴근" onclick="location.href='./EmployeeAttitudeTimeRecordingAction.em?type=leave&id=<%=attitude.getAt_member_id()%>'">	<%} 
									%>	
								</td>
								<td>
									<%
										if (attitude.getAt_memo() != null) { // 메모가 기록되어 있을 경우
											if (attitude.getAt_memo().length() > 10) { // 메모가 10글자를 넘으면 축약
												%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>')"><%=attitude.getAt_memo().substring(0,10) + "..."%></a><%
											} else {
												if (attitude.getAt_memo().length() == 0) { // 메모 편집 시 글자 다 지우고 완료 시(문자 길이 0일 때) 입력버튼 표시
													%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>')"><%
												} else {
													%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>')"><%=attitude.getAt_memo()%></a><%
												}
											} 
										} else { // 메모 없을 경우
											%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','null')"><%
										}%>
								</td>
								<td>
									<input type="button" value="결근처리" onclick="confirmCancel('<%=attitude.getAt_member_id() %>','all')">
								</td>
							</tr>
						<%
						} // for문 종료
						%>
							<!-- 버튼 -->
<!-- 							<tr align="right"> -->
<!-- 								<td align="center" colspan="7"> -->
<!-- 									<div class="item"> -->
<!-- 										<input type="button" value="선택 출근" onclick="checkForm(btnCome)">  -->
<!-- 										<input type="button" value="선택 퇴근" onclick="checkForm(btnLeave)">  -->
<!-- 										<input type="button" value="선택 결근" onclick="checkForm(btnAbsence)"> -->
<!-- 										<input type="button" value="선택 문자 발송"> -->
<!-- 									</div> -->
<!-- 							</tr> -->
						</tbody>
					</table>
				</form>
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