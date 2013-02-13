<%@page import="academy.student.db.StudentBean"%>
<%@page import="academy.student.db.StudentDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
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

				<!-- UI Object -->
				<table cellspacing="0" border="1" summary="원생 리스트"
					class="tbl_type_list">
					<caption>원생 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">선택</th>
							<th scope="col">학생ID</th>
							<th scope="col">이름</th>
							<th scope="col">학교</th>
							<th scope="col">학년</th>
							<th scope="col">소속학급</th>
							<th scope="col">수강료현황</th>
						</tr>
					</thead>
					<tbody>
					<%
							List studentList = (List)request.getAttribute("studentList");
							for(int i = 0; i< studentList.size(); i++){
							StudentBean studentBean = (StudentBean)studentList.get(i);
							
					%>
						<tr>
							<td><input name="" type="checkbox" value="" id="a1"
								class="i_check"><label for="a1"></label></td>

							<td><a href="./StudentDetail.st?id=<%=studentBean.getMm_id()%>"><%=studentBean.getMm_id()%></a></td>
							<td><%=studentBean.getMm_name() %></td>
							<td><%=studentBean.getSt_school_name() %></td>
							<td><%=studentBean.getSt_school_grade()%></td>
							<td><%=studentBean.getGp_id() %></td>
							<td><%=studentBean.getSt_tuition_state() %></td>
						</tr>
					<%
					}
					%>
						<!-- 버튼 -->
						<tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="button" value="원생 등록">
									<input type="button" value="원생 휴원">
									<input type="button" value="원생 퇴출">
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