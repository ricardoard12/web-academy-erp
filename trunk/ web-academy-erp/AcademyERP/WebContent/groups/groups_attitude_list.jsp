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
<script src="./js/calendar.js"></script>
<script type="text/javascript">
	function memoOpen(id, at_memo,date) { // 메모창 열기
		if (at_memo == "null") at_memo="";
		window.open("./GroupsAttitudeMemoAction.gp?id=" + id + "&at_memo=" + at_memo + "&date=" + date, "memo", "width=350,height=200,scrollbars=no");
	}
	
	function timeEditOpen(id, time, type, date) { // 시간 수정창 열기
// 		var date = document.emAttitudeForm.date.value;
		window.open("./GroupsAttitudeEditTime.gp?id=" + id + "&time=" + time + "&type=" + type + "&date=" + date, "timeEdit", "width=350,height=200,scrollbars=no");
	}
	
	function confirmCancel(id, type, date) { // 버튼 클릭 확인
		if (confirm("결석 처리 하시겠습니까?") == true) {
// 			var date = document.emAttitudeForm.date.value;
			location.href="./GroupsAttitudeCancelAction.gp?id=" + id + "&type=" + type + "&date=" + date;
			return null;
		}
	}
	
	function confirmCancel2(id, type, date) { // 버튼 클릭 확인
		if (confirm("취소 처리 하시겠습니까?") == true) {
// 			var date = document.emAttitudeForm.date.value;
			location.href="./GroupsAttitudeCancelAction.gp?id=" + id + "&type=" + type + "&date=" + date;
			return null;
		}
	}	
	
	function CheckDate(gp_name) { // 달력 체크
		var date = document.stAttitudeForm.date.value;
		if (date == "") {
			alert('날짜 입력하세요');
		} else {
			document.stAttitudeForm.action = "./GroupsAttitudeListAction.gp?date=" + date + "&gp_name=" + gp_name;
			document.stAttitudeForm.submit();
		}
	}

	function timeRecord(type, id, date) { 
// 		var date = document.emAttitudeForm.date.value;
		location.href="./GroupsAttitudeTimeRecordingAction.gp?type=" + type + "&id=" + id + "&date=" + date;
	}
	
	function selGroups(gp_name, date) { // 학급 선택
		location.href="./GroupsAttitudeListAction.gp?date=" + date + "&gp_name=" + gp_name;
	}

	function addStudent(gp_name) { // 학급 학생 추가
		if (gp_name == "") {
			alert('학급을 선택하세요.');
		} else {
			window.open("./GroupsAddStudent.gp?gp_name=" + gp_name, "addStudent", "width=400,height=500,scrollbars=yes");
		}
	}
	
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	List attitudeList = (List) request.getAttribute("attitudeList");
	String date = (String) request.getAttribute("date");
	String gp_name = "";
	if (request.getAttribute("gp_name") != null) {
		gp_name = (String)request.getAttribute("gp_name");
	}
	
	SimpleDateFormat sdfTime = new SimpleDateFormat("HH:mm");
	
	int nowPage = ((Integer)request.getAttribute("page")).intValue();
	int maxPage = ((Integer)request.getAttribute("maxPage")).intValue();
	int startPage = ((Integer)request.getAttribute("startPage")).intValue();
	int endPage = ((Integer)request.getAttribute("endPage")).intValue();
	int listCount = ((Integer)request.getAttribute("listCount")).intValue();
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
			<form action="" name="stAttitudeForm" method="post">
			<input type="hidden" name="page" value="<%=nowPage%>">
				<!-- 직원 출근 현황 시작 -->

				<!-- UI Object -->
					<table cellspacing="0" border="1" summary="유형별 자산목록리스트"
						class="tbl_type_list">
						<caption>학급 출석 현황</caption>
						<colgroup>
							<col width="12%">
							<col>
							<col width="12%" span="7">
						</colgroup>
						
						<thead>
							<tr>
								<th scope="row">검색날짜선택</th>
								<td colspan="7" align="left">
									<div class="item">
										<input type="text" name="date"> 
										<input type="button" value="검색" onclick="CheckDate('<%=gp_name%>')">
										<input type="button" value="달력보기" onClick="datePicker(event,'date',0)">
										<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<%=date.split("-")[0] %>년 <%=date.split("-")[1] %>월 <%=date.split("-")[2] %>일
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										학급 : 
										<select name="gp_name" onchange="selGroups(value, '<%=date%>')">
											<option value="">학급 선택</option>
											<%if (gp_name.equals("1A")) { %> <option value="1A" selected>1A</option> <%} else {%><option value="1A">1A</option><%} %>
											<%if (gp_name.equals("1B")) { %> <option value="1B" selected>1B</option> <%} else {%><option value="1B">1B</option><%} %>
											<%if (gp_name.equals("2A")) { %> <option value="2A" selected>2A</option> <%} else {%><option value="2A">2A</option><%} %>
											<%if (gp_name.equals("2B")) { %> <option value="2B" selected>2B</option> <%} else {%><option value="2B">2B</option><%} %>
											<%if (gp_name.equals("3A")) { %> <option value="3A" selected>3A</option> <%} else {%><option value="3A">3A</option><%} %>
											<%if (gp_name.equals("3B")) { %> <option value="3B" selected>3B</option> <%} else {%><option value="3B">3B</option><%} %>
										</select> 
									</div>
								</td>
							</tr>
						</thead>
						
						<thead>
							<tr>
								<th scope="col">선택</th>
								<th scope="col">이름(아이디)</th>
								<th scope="col">출결상황</th>
								<th scope="col">입실시간</th>
								<th scope="col">퇴실시간</th>
								<th scope="col">메모</th>
								<th scope="col">취소</th>
							</tr>
						</thead>
						<tbody>
						<%
						if (attitudeList.size() == 0) {
						%><tr><td colspan="7">선택하신 학급 학생이 없습니다. </td></tr><%	
						} else {
							for (int i = 0; i < attitudeList.size(); i++) {
								AttitudeBean attitude = (AttitudeBean)attitudeList.get(i);
							%>
								<tr>
									<td><input name="studentSelect" type="checkbox" id="a1"
										class="i_check" value="<%=attitude.getAt_member_id()%>"><label for="a1"></label></td>
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
												%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdfTime.format(attitude.getAt_come_time()) %>','come','<%=date%>')"><%=sdfTime.format(attitude.getAt_come_time()) %></a>&nbsp;
												<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','come','<%=date%>')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
											} else { %><input type="button" value="출석" onclick="timeRecord('come', '<%=attitude.getAt_member_id() %>','<%=date%>')">	<%} 
										%>	
									</td>
									<td>
										<%
											if (attitude.getAt_leave_time() != null) { // 퇴근 시간이 기록되어 있을 경우
												%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdfTime.format(attitude.getAt_leave_time()) %>','leave','<%=date%>')"><%=sdfTime.format(attitude.getAt_leave_time()) %></a>&nbsp;
												<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','leave','<%=date%>')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
											} else { 
												%><input type="button" value="퇴실" onclick="timeRecord('leave', '<%=attitude.getAt_member_id() %>','<%=date%>')"><%
											} 
										%>	
									</td>
									<td>
										<%
											if (attitude.getAt_memo() != null) { // 메모가 기록되어 있을 경우
												if (attitude.getAt_memo().length() > 10) { // 메모가 10글자를 넘으면 축약
													%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>')"><%=attitude.getAt_memo().substring(0,10) + "..."%></a><%
												} else {
													if (attitude.getAt_memo().length() == 0) { // 메모 편집 시 글자 다 지우고 완료 시(문자 길이 0일 때) 입력버튼 표시
														%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>')"><%
													} else {
														%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>')"><%=attitude.getAt_memo()%></a><%
													}
												} 
											} else { // 메모 없을 경우
												%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','null','<%=date%>')"><%
											}%>
									</td>
									<td>
										<input type="button" value="결석처리" onclick="confirmCancel('<%=attitude.getAt_member_id() %>','all','<%=date%>')">
									</td>
								</tr>
							<%
							} // for문 종료
						}
						%>
						
							<!-- 버튼 -->
							<tr align="right">
								<td align="center" colspan="7">
									<div class="item">
										<input type="button" value="학생 추가" onclick="addStudent('<%=gp_name%>')"> 
										<input type="button" value="학급 이동" onclick="moveStudent()"> 
										<input type="button" value="학생 제외" onclick="checkForm('<%=gp_name%>')">
										<input type="button" value="선택 문자 발송" onclick="">
									</div>
							</tr>
						</tbody>
					</table>
				</form>
				<div class="paginate_complex" align="center">
					<%
						if (nowPage <= 1) {
					%>
					<a href="#" class="direction prev"><span></span><span></span>처음</a>
					<%
						} else {
					%>
					<a href="./GroupsAttitudeListAction.gp?page=<%=nowPage - 1%>"
						class="direction prev"><span></span>이전</a>
					<%
						}
					%>
					<%
						for (int a = startPage; a <= endPage; a++) {
							if (a == nowPage) {
					%><strong><%=a%></strong>&nbsp;<%
						} else {
					%><a href="./GroupsAttitudeListAction.gp?page=<%=a%>"><%=a%></a>
					<%
						}
						}
					%>
					<%
						if (nowPage >= maxPage) {
					%><a href="#" class="direction next">끝<span></span><span></span></a>
					<%
						} else {
					%><a href="./GroupsAttitudeListAction.gp?page=<%=nowPage + 1%>"
						class="direction next">다음 </a>
					<%
						}
					%>
				</div>
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