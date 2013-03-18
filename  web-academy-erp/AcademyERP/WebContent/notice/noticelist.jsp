<%@page import="academy.noticle.db.NoticeBean"%>
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

int noticecount=((Integer)request.getAttribute("noticecount")).intValue();
int nowpage=((Integer)request.getAttribute("page")).intValue();
int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
int startpage=((Integer)request.getAttribute("startpage")).intValue();
int endpage=((Integer)request.getAttribute("endpage")).intValue();

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
				<table cellspacing="0" border="1" summary="원생 리스트"
					class="tbl_type_list">
					<caption>공지사항</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="7">
					</colgroup>
					
					<tbody>
	
<%
						List noticeList = (List)request.getAttribute("noticeList");

%>
						<thead>
						<tr><th class="item">번호</th><th class="item">제목</th><th class="item">작성자</th><th class="item">날짜</th><th class="item">조회수</th></tr>
						</tbody>
						<%
							if(noticeList!=null){
							for(int i =0; i<noticeList.size(); i++){
								NoticeBean noticebean = (NoticeBean)noticeList.get(i);	
								%>
								<tr><td><%=noticebean.getNot_num() %></td><td><a href="./NoticeDetail.no?num=<%=noticebean.getNot_num()%>"><%=noticebean.getNot_title() %></a></td><td><%=noticebean.getNot_subject() %></td><td><%= noticebean.getNot_data() %></td><td><%= noticebean.getNot_recont()%></td></tr>
								<%
								}
							%>
									<tr>
							<td colspan="8" align="center">
					<%
					if(nowpage<=1){
						%>
						
						<%
						}else{
						%>
							<a href="./NoticeList.no?page=<%=nowpage-1%>">[이전]</a>
						<%
					}
					%>
					<%
					for(int a=startpage;a<=endpage;a++){
						if(a==nowpage){
							%>[<%=a %>]&nbsp;<%
						}else{
							%><a href="./NoticeList.no?page=<%=a%>">[<%=a %>]&nbsp;</a><%
						}
					}
					%>
					<%
					if(nowpage>=maxpage){
						%><%
					}else{
						%><a href="./NoticeList.no?page=<%=nowpage+1%>">[다음]</a><%
					}

					%>
					
					</td>
					</tr>
							
							<%
							}
							
							else
							{
									%>
									<tr><td colspan="5">내용이 없습니다.</td></tr>
									<%
								}
%>	
					</table>
					<%
					if(session.getAttribute("level")!=null){
					String level=(String)session.getAttribute("level");
					if(level.equals("4") ||level.equals("5") ){%>
					<div align="right">
					<input type="button" value="글쓰기" onclick="location.href='./NoticleWriting.no'">
					</div>
					<%
																} 
															}%>

				
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