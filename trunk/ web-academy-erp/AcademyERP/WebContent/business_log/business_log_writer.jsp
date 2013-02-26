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

				<!-- 업무일지 글쓰기 시작 -->

				<fieldset>
					<legend>업무일지</legend>

					<form name="business_writer_form">

						<!--상단 왼쪽 위치(로고 또는 문구)-->
						<table border="1" align="center" width="100%">

							<tr height="5px" align="center">
								<td rowspan="2" width="60%"> 이미지 또는 로고 삽입</td>
								<td rowspan="2">결<br>재
								</td>
								<td>담당자</td>
								<td>관리자</td>
								<td>학원장</td>

							</tr>
							<tr height="40px" align="center">
								<td><input type="button" name="check" value="확인"></td>
								<td><input type="button" name="check" value="확인"></td>
								<td><input type="button" name="check" value="확인"></td>


							</tr>

						</table>
						<br>
						<legend> 1.오늘의 주요 업무 사항</legend>
						<br>
						<textarea name="first_content" rows="10" cols="90"></textarea>
						<br> <br>

						<legend> 2.상담 업무</legend>

						<table border="1" align="center" width="100%">
							<tr align="center">
								<td>성 명</td>
								<td>반 명</td>
								<td>상담자</td>
								<td>상담내용</td>
							</tr>

							<tr align="center">
								<!-- 학생명과 반명은 각기 DB에서 출력시킬것 -->
								<td><input type="text" name="student_name" size="5"></td>
								<td><input type="text" name="class_name" size="5"></td>
								<!--담당자 부분은 세션값으로 처리 (강사명) -->
								<td><input type="text" name="employee_name" size="5"></td>
								<td><textarea rows="6" cols="50"></textarea>
							</tr>
						</table>
						<br> <br>
						<legend> 3.기타사항</legend>

						<textarea name="third_content" rows="10" cols="90"></textarea>
				<br><br>
				<div align="center">
				<input type="submit" name="submit" value="등록" >
				<input type="reset" name="reset" value="다시쓰기">
				<input type="button" name="cancle" value="취소" onclick="location.href='#'">
				</div>
				
					</form>

				</fieldset>


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