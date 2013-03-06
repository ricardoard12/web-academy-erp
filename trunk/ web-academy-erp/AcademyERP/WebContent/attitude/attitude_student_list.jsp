<%@page import="academy.attitude.db.AttitudeBean"%>
<%@page import="academy.accounting.db.AccountingBean"%>
<%@page import="academy.student.db.StudentBean"%>
<%@page import="academy.student.db.StudentDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<%
int attitudecount=((Integer)request.getAttribute("attitudecount")).intValue();
int nowpage=((Integer)request.getAttribute("page")).intValue();
int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
int startpage=((Integer)request.getAttribute("startpage")).intValue();
int endpage=((Integer)request.getAttribute("endpage")).intValue();
String memberid = (String)request.getAttribute("memberid");//아뒤갑 넘김
%>
<title>Insert title here</title>
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

	<!-- 학생 목록 시작 -->
				<form name="Student_List">
				<!-- UI Object -->
				<table cellspacing="0" border="1" summary="휴원생 리스트"
					class="tbl_type_list">
					<caption>출석 내역 조회</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">상태</th>
							<th scope="col">출석시간</th>
							<th scope="col">퇴실시간</th>
							<th scope="col">메모</th>
						</tr>
					</thead>
					<tbody>
					<%
						if(request.getAttribute("attitude")!=null){
							List attitude = (List)request.getAttribute("attitude");
							for(int i = 0; i< attitude.size(); i++){
							AttitudeBean attitudebean = (AttitudeBean)attitude.get(i);
							
					%>
						<tr>
							<td><%=attitudebean.getAt_report_state() %></td>
							<td><%=attitudebean.getAt_come_time()%></td>
							<td><%= attitudebean.getAt_leave_time()%></td>
							<td><%= attitudebean.getAt_memo()%></td>
						</tr>
					<%
					}	
					%>

					<%} else {%>
					<tr><td colspan="7">정보 없음</td></tr>
					<%} %>
															<tr>
						<td colspan="7" align="center">
												<%
		if(nowpage<=1){
	%>
	
	<%
	}else{
	%>
		<a href="./AttitudeStudent.at?page=<%=nowpage-1%>&id=<%=memberid%>">[이전]</a>
	<%
}
%>
<%
for(int a=startpage;a<=endpage;a++){
	if(a==nowpage){
		%>[<%=a %>]&nbsp;<%
	}else{
		%><a href="./AttitudeStudent.at?page=<%=a%>&id=<%=memberid%>">[<%=a %>]&nbsp;</a><%
	}
}
%>
<%
if(nowpage>=maxpage){
	%><%
}else{
	%><a href="./AttitudeStudent.at?page=<%=nowpage+1%>&id=<%=memberid%>">[다음]</a><%
}

%>
				</td>
					
					</tr>
					
					
					</tbody>
					
				</table>
				<!-- //UI Object -->
				</form>
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

	<script type="text/javascript">
		jQuery(function() {
			// Help Toggle
			$('.item>.i_help').click(function() {
				$(this).parent('.item').find('.i_dsc').toggleClass('hide');
			});
			// Input Clear
			var i_text = $('.item>.i_label').next('.i_text');
			$('.item>.i_label').css('position', 'absolute');
			i_text.focus(function() {
				$(this).prev('.i_label').css('visibility', 'hidden');
			}).blur(function() {
				if ($(this).val() == '') {
					$(this).prev('.i_label').css('visibility', 'visible');
				} else {
					$(this).prev('.i_label').css('visibility', 'hidden');
				}
			}).change(function() {
				if ($(this).val() == '') {
					$(this).prev('.i_label').css('visibility', 'visible');
				} else {
					$(this).prev('.i_label').css('visibility', 'hidden');
				}
			}).blur();
		});
	</script>
</body>
</html>