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
	src="./board/SE2.2.1.O9186/js/HuskyEZCreator.js" charset="utf-8"></script>
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

				<!-- 게시판 글쓰기 시작 -->

				<form action="./BusinessAddAction.bl" method="get">
					<fieldset>
						<legend>업무일지 입력 FORM</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
								<tbody>


									<tr>
									<th align="right" colspan="2" >|		담당자		|		관리자		|		학원장		|	</th>
									
									
									
									</tr>
									
									<tr>
									<th colspan="2">|	<input type="button" value="결재">|	<input type="button" value="결재">|	<input type="button" value="결재">|</th>
									
												
									
									</tr>

									<tr>
										<th scope="row" colspan="2">1. 오늘의 주요 업무 사항</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											<div class="item">
												<textarea name="business_today" cols="100" rows="10"
													title="레이블 텍스트" id="contents" class="i_text"
													style="display: none;"></textarea>
											</div>
										</td>
									</tr>
									
								
									
									<tr>
										<th scope="row" colspan="2">2. 상담 내역</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											<textarea name="business_counsel" cols="100" rows="10"
													title="레이블 텍스트"></textarea>
										</td>
									</tr>
									
									<tr>
										<th scope="row" colspan="2">3. 기타 및 건의사항</th>
										
									</tr>
									<tr>
										<th scope="row">내용</th>
										<td>
											
												<textarea name="business_etc" cols="100" rows="10"
													title="레이블 텍스트"></textarea>
											
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
				<script type="text/javascript">
					var oEditors = [];
					nhn.husky.EZCreator
							.createInIFrame({
								oAppRef : oEditors,
								elPlaceHolder : "contents",
								sSkinURI : "./board/SE2.2.1.O9186/SmartEditor2Skin.html",
								htParams : {
									bUseToolbar : true,
									fOnBeforeUnload : function() {
										//alert("아싸!");	
									}
								}, //boolean
								fOnAppLoad : function() {
									//예제 코드
									//oEditors.getById["contents"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
								},
								fCreator : "createSEditor2"
							});

					function pasteHTML() {
						var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
						oEditors.getById["contents"].exec("PASTE_HTML",
								[ sHTML ]);
					}

					function showHTML() {
						var sHTML = oEditors.getById["contents"].getIR();
						alert(sHTML);
					}

					function submitContents(elClickedObj) {
						oEditors.getById["contents"].exec(
								"UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

						// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.

						try {
							elClickedObj.form.submit();
						} catch (e) {
						}
					}

					function setDefaultFont() {
						var sDefaultFont = '궁서';
						var nFontSize = 24;
						oEditors.getById["contents"].setDefaultFont(
								sDefaultFont, nFontSize);
					}
				</script>
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