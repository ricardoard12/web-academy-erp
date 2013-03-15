
<%@page import="academy.faq_board.db.Faq_boardbean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="./lesson/SE2.2.1.O9186/js/HuskyEZCreator.js" charset="utf-8"></script>
	<title>Insert title here</title>
	<%
// 		int num = Integer.parseInt(request.getParameter("num"));
		int num = (Integer)request.getAttribute("num");
		String password = (String) request.getAttribute("password");
		
		Faq_boardbean faq_boardbean = (Faq_boardbean) request.getAttribute("faq_boardbean");
		//세션으로 id 값 받음
		String id = (String) session.getAttribute("id");
		//세션으로 name값 받음
		String name = (String) session.getAttribute("name");
		//세션으로 level 값 받음
		String level = (String) session.getAttribute("level");
	%>
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

				<!-- 게시판 글쓰기 시작 -->

				<form action="./Faq_BoardModifyAction.fb" method="post">
					<fieldset>
						<legend><b>FAQ BOARD Detail FORM</b></legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
							<input type="hidden" name="faq_num" value="<%=num%>">
							
								<tbody>

									<tr>
										<th scope="row">제목</th>
										<td>
											<div class="item">
												<input type="text" name="faq_subject" id="temp_input"
													class="i_text" style="width: 300px" value="<%=faq_boardbean.getFaq_subject()%>">
											</div>
										</td>
									</tr>
									
									
									<tr>
										<th scope="row">작성자</th>
										<td>
											<div class="item">
												<input type="text" name="faq_name" id="temp_input"
													class="i_text" style="width: 300px" value="<%=name%>">
										</td>
									</tr>
						
									<tr>
										<th scope="row">내용</th>
										<td>
<!-- 											<div class="item"> -->
<!-- 												<textarea name="lesson_content" cols="100" rows="10" -->
<!-- 													title="레이블 텍스트" id="lesson_content" class="i_text" -->
<!-- 													style="display: none;"></textarea> -->
<!-- 											</div> -->
												<textarea rows="10" cols="150" name="faq_content"><%=faq_boardbean.getFaq_content() %></textarea>
										</td>
									</tr>
									
									<tr>
										<th scope="row">패스워드</th>
										<td><input type="password" name="faq_passwd"></td>
									</tr>
								
									<!--  글쓰기버튼 -->
									<tr align="center">
									<td></td>
										<td align="center">
											<div class="item">
												<input type="submit" value="수정" onclick="submitContents(this)">
													 
													<input type="button" name="" value="취소" onclick="location.href='./Faq_boardList.fb'">
											</div>
										</td>
									</tr>


								</tbody>
							</table>
						</div>
					</fieldset>
				</form>
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


</body>
</html>