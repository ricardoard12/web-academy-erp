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
	function memoOpen(id, at_memo, date, gp_name) { // 메모창 열기
		if (at_memo == "null") at_memo="";
		window.open("./GroupsAttitudeMemoAction.gp?id=" + id + "&at_memo=" + at_memo + "&date=" + date + "&gp_name=" + gp_name, "memo", "width=350,height=200,scrollbars=no");
	}
	
	function timeEditOpen(id, time, type, date, gp_name) { // 시간 수정창 열기
// 		var date = document.emAttitudeForm.date.value;
		window.open("./GroupsAttitudeEditTime.gp?id=" + id + "&time=" + time + "&type=" + type + "&date=" + date + "&gp_name=" + gp_name, "timeEdit", "width=350,height=200,scrollbars=no");
	}
	
	function confirmCancel(id, type, date, gp_name) { // 버튼 클릭 확인
		if (confirm("결석 처리 하시겠습니까?") == true) {
// 			var date = document.emAttitudeForm.date.value;
			location.href="./GroupsAttitudeCancelAction.gp?id=" + id + "&type=" + type + "&date=" + date + "&gp_name=" + gp_name;
			return null;
		}
	}
	
	function confirmCancel2(id, type, date, gp_name) { // 버튼 클릭 확인
		if (confirm("취소 처리 하시겠습니까?") == true) {
// 			var date = document.emAttitudeForm.date.value;
			location.href="./GroupsAttitudeCancelAction.gp?id=" + id + "&type=" + type + "&date=" + date + "&gp_name=" + gp_name;
			return null;
		}
	}	
	
	function CheckDate(gp_name) { // 달력 체크
		var date = document.stAttitudeForm.date.value;
		if (date == "") {
			alert('날짜 입력하세요');
		} else {
			document.stAttitudeForm.action = "./GroupsAttitudeListAction.gp?date=" + date;
			document.stAttitudeForm.submit();
		}
	}

	function timeRecord(type, id, date, gp_name) { 
// 		var date = document.emAttitudeForm.date.value;
		location.href="./GroupsAttitudeTimeRecordingAction.gp?type=" + type + "&id=" + id + "&date=" + date + "&gp_name=" + gp_name;
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
	
	function allChecked() { // 전체 선택
		var chk = document.getElementsByName("chkStudent");
		
		if (document.stAttitudeForm.allCheck.checked == true) {
			for (var i = 0; i < chk.length; i++) {
				chk[i].checked = true;
			}
		} else {
			for (var i = 0; i < chk.length; i++) {
				chk[i].checked = false;
			}
		}
	}
	
	function delStudent() { // 학급 학생 제외
		var count = 0;
		var chk = document.getElementsByName("chkStudent");
		
		for (var i = 0; i < chk.length; i++) {
			if (chk[i].checked == true) {
				count++;
			} 
		}
		
		if (count <= 0){
			alert("제외할 학생을 선택하세요");
			return false;
		} else if(confirm("선택 학생을 학급에서 제외하시겠습니까?") == true) {
			document.stAttitudeForm.action = "./GroupsDelStudentAction.gp";
			document.stAttitudeForm.submit();
		}
	}
	
	function moveStudent(gp_name) { // 학급 학생 제외
		var count = 0;
		var chk = document.getElementsByName("chkStudent");
		var chkValue = "";
		
		for (var i = 0; i < chk.length; i++) { // 체크박스 값 받아서 문자열 결합
			if (chk[i].checked == true) {
				count++;
				chkValue += chk[i].value;
				chkValue += ","; // split 에서 구분자로 사용할 문자 삽입
			} 
		}
		
		if (count <= 0){
			alert("이동시킬 학생을 선택하세요");
			return false;
		} else {
			window.open("./GroupsMoveStudent.gp?gp_name=" + gp_name + "&chkValue=" + chkValue, "moveStudent", "width=400,height=500,scrollbars=yes");
		}
	}
	
	function sendSMS() { // 문자 발송
	        var count = 0;
	        var chk = document.getElementsByName("chkStudent");
	        var chkValue = "";
	        
	        for (var i = 0; i < chk.length; i++) { 
	                if (chk[i].checked == true) {
	                        count++;
	                        chkValue += chk[i].value; // 체크된 모든 아이디 문자열 결합
	                        chkValue += ","; // split 에서 구분자로 사용할 문자 삽입
	                } 
	        }
	        
	        if (count <= 0){
	                alert("메세지를 전송할 대상을 선택하세요");
	                return false;
	        } else {
	                window.open("./SendSms.sms?&chkValue=" + chkValue, "sendSms", "width=400,height=600,scrollbars=no");
	        }
	}
	
</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");
	
	List attitudeList = (List) request.getAttribute("attitudeList");
	
	List gpList = null;
	if (request.getAttribute("gpList") != null) {
		gpList = (List) request.getAttribute("gpList");
	}
	
	String gp_name = "";
	if (request.getAttribute("gp_name") != null) {
		gp_name = (String)request.getAttribute("gp_name");
	}
	
	String date = (String) request.getAttribute("date");
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
			<input type="hidden" name="gp_name" value="<%=gp_name%>">
			<input type="hidden" name="date" value="<%=date%>">
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
											<%
											if (gpList != null) {
												for (int i = 0; i < gpList.size(); i++) {
													String gpNameList = (String)gpList.get(i);
													if (gp_name.equals(gpNameList)) { 
														%><option value="<%=gpNameList%>" selected><%=gpNameList%></option><%
													} else {
														%><option value="<%=gpNameList%>"><%=gpNameList%></option><%
													}
												}
											} else {
												%><option value="">담당 학급 없음</option><%
											}%>
										</select> 
									</div>
								</td>
							</tr>
						</thead>
						
						<thead>
							<tr>
								<th scope="col">선택 <input type=checkbox name="allCheck" onclick="allChecked()"></th>
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
									<td><input name="chkStudent" type="checkbox" id="a1"
										class="i_check" value="<%=attitude.getAt_member_id()%>"><label for="a1"></label></td>
									<td><a href="./StudentDetail.st?id=<%=attitude.getAt_member_id()%>"><%=attitude.getMm_name() %>(<%=attitude.getAt_member_id() %>)</a></td>
									<td>
										<%
											// at_report_state Y : 출석, N : 결석
											if (attitude.getAt_report_state().equals("Y")) {%>출석<%	} 
											else {%>결석<%} 
										%>
									</td>
									<td>
										<%
											if (attitude.getAt_come_time() != null) { // 입실 시간이 기록되어 있을 경우
												%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdfTime.format(attitude.getAt_come_time()) %>','come','<%=date%>','<%=gp_name%>')"><%=sdfTime.format(attitude.getAt_come_time()) %></a>&nbsp;
												<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','come','<%=date%>','<%=gp_name%>')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
											} else { %><input type="button" value="출석" onclick="timeRecord('come', '<%=attitude.getAt_member_id() %>','<%=date%>','<%=gp_name%>')">	<%} 
										%>	
									</td>
									<td>
										<%
											if (attitude.getAt_leave_time() != null) { // 퇴실 시간이 기록되어 있을 경우
												%><a href="#" onclick="timeEditOpen('<%=attitude.getAt_member_id() %>','<%=sdfTime.format(attitude.getAt_leave_time()) %>','leave','<%=date%>','<%=gp_name%>')"><%=sdfTime.format(attitude.getAt_leave_time()) %></a>&nbsp;
												<a href="#" onclick="confirmCancel2('<%=attitude.getAt_member_id() %>','leave','<%=date%>','<%=gp_name%>')"><img src="./img/icon_cancel.gif" width="10" height="10"></a><%
											} else { 
												%><input type="button" value="퇴실" onclick="timeRecord('leave', '<%=attitude.getAt_member_id() %>','<%=date%>','<%=gp_name%>')"><%
											} 
										%>	
									</td>
									<td>
										<%
											if (attitude.getAt_memo() != null) { // 메모가 기록되어 있을 경우
												if (attitude.getAt_memo().length() > 10) { // 메모가 10글자를 넘으면 축약
													%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>','<%=gp_name%>')"><%=attitude.getAt_memo().substring(0,10) + "..."%></a><%
												} else {
													if (attitude.getAt_memo().length() == 0) { // 메모 편집 시 글자 다 지우고 완료 시(문자 길이 0일 때) 입력버튼 표시
														%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>','<%=gp_name%>')"><%
													} else {
														%><a href="#" onclick="memoOpen('<%=attitude.getAt_member_id() %>','<%=attitude.getAt_memo()%>','<%=date%>','<%=gp_name%>')"><%=attitude.getAt_memo()%></a><%
													}
												} 
											} else { // 메모 없을 경우
												%><input type="button" value="입력" onclick="memoOpen('<%=attitude.getAt_member_id() %>','null','<%=date%>','<%=gp_name%>')"><%
											}%>
									</td>
									<td>
										<input type="button" value="결석처리" onclick="confirmCancel('<%=attitude.getAt_member_id() %>','all','<%=date%>','<%=gp_name%>')">
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
										<input type="button" value="학급 이동" onclick="moveStudent('<%=gp_name%>')"> 
										<input type="button" value="학생 제외" onclick="delStudent()">
										<input type="button" value="선택 문자 발송" onclick="sendSMS()">
									</div>
								</td>
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