
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
	src="./business_log/SE2.2.1.O9186/js/HuskyEZCreator.js" charset="utf-8"></script>
	<title>Insert title here</title>
	
	<script type="text/javascript">
	function checkForm(){
		if(document.business_WriteForm.business_subject.value.length == 0 ){
			alert("제목을 입력하세요.");
			document.business_WriteForm.business_subject.focus();
			return false;
		}
		if(document.business_WriteForm.business_today.value.length == 0 ){
			alert("오늘의 주요 업무 사항을 입력하세요.");
			document.business_WriteForm.business_today.focus();
			return false;
		}
		if(document.business_WriteForm.business_counsel.value.length == 0 ){
			alert(" 상담내역을 입력하세요.");
			document.business_WriteForm.business_counsel.focus();
			return false;
		}
		if(document.business_WriteForm.business_etc.value.length == 0 ){
			alert("기타 및 건의사항을 입력하세요.");
			document.business_WriteForm.business_etc.focus();
			return false;
		}
	}
	</script>
	<%
		//세션으로 id 값 받음
		String id = (String) session.getAttribute("id");
		//세션으로 name값 받음
		String business_name = (String) session.getAttribute("name");
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

				<form action="./BusinessAddAction.bl" method="post" name="business_WriteForm" onsubmit="return checkForm()">
						<br>
					<fieldset>
				
						<legend><b>업무일지 입력 FORM</b></legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
							<input type="hidden" name="business_name" value="<%=business_name%>">
								<tbody>

									<tr>
										<th scope="row">제목</th>
										<td>
											<div class="item">
												<input type="text" name="business_subject" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									
									<tr>
										<th scope="row" colspan="2">1 . 오늘의 주요 업무 사항</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											<div class="item">
<!-- 												<textarea name="business_today" cols="100" rows="10" -->
<!-- 													title="레이블 텍스트" id="contents" class="i_text" -->
<!-- 													style="display: none;"></textarea> -->
													<textarea name="business_today" cols="150" rows="10"></textarea>
											</div>
										</td>
									</tr>
									
								
									
									<tr>
										<th scope="row" colspan="2"> 2 . 상담 내역</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											<div class="item">
<!-- 											<textarea name="business_counsel" id="business_counsel" cols="100" rows="10" -->
<!-- 											class="i_text" style="display: none;" title="레이블 텍스트"></textarea> -->
											<textarea name="business_counsel" id="business_counsel" cols="150" rows="10"></textarea>
											</div>
										</td>
									</tr>
									
									<tr>
										<th scope="row" colspan="2"> 3 . 기타 및 건의사항</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											<div class="item">
<!-- 												<textarea name="business_etc" id="business_etc" cols="100" rows="10" -->
<!-- 												class="i_text" style="display: none;" title="레이블 텍스트"></textarea> -->
													<textarea name="business_etc" id="business_etc" cols="150" rows="10"></textarea>
											</div>
										</td>
									</tr>
									
									

									
									<!--  글쓰기버튼 -->
									<tr align="center">
										<td></td>
										<td align="center">
											<div class="item">
												<input type="submit" value="등록" onclick="submitContents(this)">
													 
													<input type="button" name="" value="취소" onclick="location.href='./BusinessNotice.bo'">
											</div>
										</td>
									</tr>


								</tbody>
							</table>
						</div>
					</fieldset>
				</form>
<!-- 				<script type="text/javascript"> -->
<!-- 					var oEditors = []; -->
<!-- 					nhn.husky.EZCreator -->
<!-- 							.createInIFrame({ -->
<!-- 								oAppRef : oEditors, -->
<!-- // 								elPlaceHolder : "contents", "business_counsel","business_etc", -->
<!-- 								elPlaceHolder : "contents", -->
<!-- 								sSkinURI : "./business_log/SE2.2.1.O9186/SmartEditor2Skin.html", -->
<!-- 								htParams : { -->
<!-- 									bUseToolbar : true, -->
<!-- 									fOnBeforeUnload : function() { -->
<!-- 										//alert("아싸!");	 -->
<!-- 									} -->
<!-- 								}, //boolean -->
<!-- 								fOnAppLoad : function() { -->
<!-- 									//예제 코드 -->
<!-- 									//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]); -->
<!-- 								}, -->
<!-- 								fCreator : "createSEditor2" -->
<!-- 							}); -->

<!-- 					function pasteHTML() { -->
<!-- 						var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>"; -->
<!-- 						oEditors.getById["contents"].exec("PASTE_HTML", -->
<!-- 								[ sHTML ]); -->
<!-- 					} -->

<!-- 					function showHTML() { -->
<!-- 						var sHTML = oEditors.getById["contents"].getIR(); -->
<!-- 						alert(sHTML); -->
<!-- 					} -->

<!-- 					function submitContents(elClickedObj) { -->
<!-- 						oEditors.getById["contents"].exec( -->
<!-- 								"UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다. -->

<!-- 						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다. -->

<!-- 						try { -->
<!-- 							elClickedObj.form.submit(); -->
<!-- 						} catch (e) { -->
<!-- 						} -->
<!-- 					} -->

<!-- 					function setDefaultFont() { -->
<!-- 						var sDefaultFont = '궁서'; -->
<!-- 						var nFontSize = 24; -->
<!-- 						oEditors.getById["contents"].setDefaultFont( -->
<!-- 								sDefaultFont, nFontSize); -->
<!-- 					} -->
<!-- 				</script> -->
<!-- 				금일 업무 부분 스마트 에디터 적용 종료 -->
<!-- 금일 상담내역 부분 스마트에디터 적용 시작 -->

<!-- 	<script type="text/javascript"> -->
<!-- 					var oEditors = []; -->
<!-- 					nhn.husky.EZCreator -->
<!-- 							.createInIFrame({ -->
<!-- 								oAppRef : oEditors, -->
<!--  								elPlaceHolder : "contents", "business_counsel","business_etc", -->
<!-- 								elPlaceHolder : "business_counsel", -->
<!-- 								sSkinURI : "./business_log/SE2.2.1.O9186/SmartEditor2Skin.html", -->
<!-- 								htParams : { -->
<!-- 									bUseToolbar : true, -->
<!-- 									fOnBeforeUnload : function() { -->
<!-- 										//alert("아싸!");	 -->
<!-- 									} -->
<!-- 								}, //boolean -->
<!-- 								fOnAppLoad : function() { -->
<!-- 									//예제 코드 -->
<!-- 									//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]); -->
<!-- 								}, -->
<!-- 								fCreator : "createSEditor2" -->
<!-- 							}); -->

<!-- 					function pasteHTML() { -->
<!-- 						var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>"; -->
<!-- 						oEditors.getById["business_counsel"].exec("PASTE_HTML", -->
<!-- 								[ sHTML ]); -->
<!-- 					} -->

<!-- 					function showHTML() { -->
<!-- 						var sHTML = oEditors.getById["business_counsel"].getIR(); -->
<!-- 						alert(sHTML); -->
<!-- 					} -->

<!-- 					function submitContents(elClickedObj) { -->
<!-- 						oEditors.getById["business_counsel"].exec( -->
<!-- 								"UPDATE_BUSINESS_COUNSEL_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다. -->

<!-- 						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다. -->

<!-- 						try { -->
<!-- 							elClickedObj.form.submit(); -->
<!-- 						} catch (e) { -->
<!-- 						} -->
<!-- 					} -->

<!-- 					function setDefaultFont() { -->
<!-- 						var sDefaultFont = '궁서'; -->
<!-- 						var nFontSize = 24; -->
<!-- 						oEditors.getById["business_counsel"].setDefaultFont( -->
<!-- 								sDefaultFont, nFontSize); -->
<!-- 					} -->
<!-- 				</script> -->
<!-- 				금일 상담부분 스마트 에디터 종료 -->

<!-- 금일 기타 및 건의사항 스마트 에디터 시작-->

<!-- <script type="text/javascript"> -->
<!-- 					var oEditors = []; -->
<!-- 					nhn.husky.EZCreator -->
<!-- 							.createInIFrame({ -->
<!-- 								oAppRef : oEditors, -->
<!--  								elPlaceHolder : "contents", "business_counsel","business_etc", -->
<!-- 								elPlaceHolder : "business_etc", -->
<!-- 								sSkinURI : "./business_log/SE2.2.1.O9186/SmartEditor2Skin.html", -->
<!-- 								htParams : { -->
<!-- 									bUseToolbar : true, -->
<!-- 									fOnBeforeUnload : function() { -->
<!-- 										//alert("아싸!");	 -->
<!-- 									} -->
<!-- 								}, //boolean -->
<!-- 								fOnAppLoad : function() { -->
<!-- 									//예제 코드 -->
<!-- 									//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]); -->
<!-- 								}, -->
<!-- 								fCreator : "createSEditor2" -->
<!-- 							}); -->

<!-- 					function pasteHTML() { -->
<!-- 						var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>"; -->
<!-- 						oEditors.getById["business_etc"].exec("PASTE_HTML", -->
<!-- 								[ sHTML ]); -->
<!-- 					} -->

<!-- 					function showHTML() { -->
<!-- 						var sHTML = oEditors.getById["business_etc"].getIR(); -->
<!-- 						alert(sHTML); -->
<!-- 					} -->

<!-- 					function submitContents(elClickedObj) { -->
<!-- 						oEditors.getById["business_etc"].exec( -->
<!-- 								"UPDATE_BUSINESS_ETC_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다. -->

<!-- 						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다. -->

<!-- 						try { -->
<!-- 							elClickedObj.form.submit(); -->
<!-- 						} catch (e) { -->
<!-- 						} -->
<!-- 					} -->

<!-- 					function setDefaultFont() { -->
<!-- 						var sDefaultFont = '궁서'; -->
<!-- 						var nFontSize = 24; -->
<!-- 						oEditors.getById["business_etc"].setDefaultFont( -->
<!-- 								sDefaultFont, nFontSize); -->
<!-- 					} -->
<!-- 				</script> -->
				
<!-- 				기타 및 건의 사항 스마트 에디터 종료 -->
				
				<!-- 회원가입 끝 -->

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