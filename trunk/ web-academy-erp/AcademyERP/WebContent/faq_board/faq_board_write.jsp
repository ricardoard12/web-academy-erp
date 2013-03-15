
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

				<form action="./Faq_BoardAddAction.fb" method="post">
					<fieldset>
						<legend><b>FAQ BOARD FORM</b></legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
							
								<tbody>

									<tr>
										<th scope="row">제목</th>
										<td>
											<div class="item">
												<input type="text" name="faq_subject" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									
									
									<tr>
										<th scope="row">작성자</th>
										<td>
											<div class="item">
							<!--name값의 유무에 따라 글쓰기 게시판의 작성자 부분에 값이 삽입되거나 없음 -->
											<%if(name!=null){ %>
												<input type="text" name="faq_name" id="temp_input"
													class="i_text" style="width: 300px" value="<%=name%>">
													<%}else { %>
													<input type="text" name="faq_name" id="temp_input"
													class="i_text" style="width: 300px">
													<%} %>
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
												<textarea rows="10" cols="150" name="faq_content"></textarea>
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
												<input type="submit" value="등록" onclick="submitContents(this)">
													 
													<input type="button" name="" value="취소" onclick="location.href='./Faq_boardList.fb'">
											</div>
										</td>
									</tr>


								</tbody>
							</table>
						</div>
					</fieldset>
				</form>
				
<!-- 								강의목표 부분 스마트 에디터 적용 시작 -->
<!-- 				<script type="text/javascript"> -->
<!-- 					var oEditors = [];  -->
<!-- 					nhn.husky.EZCreator  -->
<!-- 							.createInIFrame({ -->
<!-- 								oAppRef : oEditors,  -->
<!-- 								elPlaceHolder : "faq_content",  -->
<!--  								sSkinURI : "./faq_board/SE2.2.1.O9186/SmartEditor2Skin.html",  -->
<!-- 								htParams : {  -->
<!--  									bUseToolbar : true,  -->
<!-- 									fOnBeforeUnload : function() {  -->
<!--  										//alert("아싸!");	  -->
<!--  									}  -->
<!--  								}, //boolean  -->
<!--  								fOnAppLoad : function() {  -->
<!--  									//예제 코드 -->
<!-- //  									oEditors.getById["faq_content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]); -->
<!-- 								},  -->
<!--  								fCreator : "createSEditor2"  -->
<!-- 							});  -->

<!-- 					function pasteHTML() {  -->
<!--  						var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";  -->
<!--  						oEditors.getById["faq_content"].exec("PASTE_HTML",  -->
<!-- 								[ sHTML ]); -->
<!--  					}  -->

<!--  					function showHTML() {  -->
<!-- 						var sHTML = oEditors.getById["faq_content"].getIR(); -->
<!--  						alert(sHTML);  -->
<!--  					}  -->

<!-- 					function submitContents(elClickedObj) {  -->
<!-- 						oEditors.getById["faq_content"].exec(  -->
<!--  								"UPDATE_FAQ_CONTENT_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.  -->

<!--  						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.  -->

<!-- 						try {  -->
<!-- 							elClickedObj.form.submit(); -->
<!--  						} catch (e) {  -->
<!--  						}  -->
<!-- 					}  -->

<!--  					function setDefaultFont() {  -->
<!--  						var sDefaultFont = '궁서';  -->
<!--  						var nFontSize = 24;  -->
<!-- 						oEditors.getById["faq_content"].setDefaultFont( -->
<!--  								sDefaultFont, nFontSize);  -->
<!--  					}  -->
<!--  				</script>  -->
<!-- 				강의목표 부분 스마트 에디터 적용 종료 -->

		
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