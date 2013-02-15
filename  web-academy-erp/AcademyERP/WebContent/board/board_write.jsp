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
<script type="text/javascript" src="./board/SE2.2.1.O9186/js/HuskyEZCreator.js" charset="utf-8"></script>
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

				<form action="./BoardAddAction.bo" method="post" enctype="multipart/form-data">
					<fieldset>
						<legend>게시판 글쓰기</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
								<tbody>
	

		<tr>
			<th scope="row">아이디</th>
			<td>
				<div class="item">
					<input type="text" name="board_name" title="학교명" class="i_text">
				</div>
			</td>
		</tr>
		
		<tr>
			<th scope="row">제목</th>
			<td>
				<div class="item">
					<input type="text" name="board_subject" id="temp_input"class="i_text" style="width: 300px">
		</div>
						</td>
					</tr>
		
			

				<tr>
				<th scope="row">파일</th>
				<td>
					<div class="item">
						<input name="board_file" type="file" title="레이블 텍스트">
					</div>
				</td>
				</tr>

	<tr>
			<th scope="row">내용</th>
			<td>
				<div class="item">
					<textarea name="board_content" cols="100" rows="10" title="레이블 텍스트"
						id="board_content" class="i_text" style="display: none;"></textarea>
				</div>
			</td>
		</tr>




		<tr>
			<th scope="row">비밀번호</th>
			<td>
				<div class="item">
					<label for="temp_input" class="i_label"
						style="position: absolute; visibility: visible;">비밀번호
						입력</label> <input type="password" name="board_pass" id="temp_input"
						class="i_text" style="width: 300px">
				</div>
			</td>
		</tr>

	<!--  글쓰기버튼 -->
		<tr align="right">
			<td></td>
			<td align="left">
				<div class="item">
					<input type="submit"  value="글쓰기"> 
					<input type="button" name="" value="취소" onclick="location.href='./BoardNotice.bo'">
				</div>
			</td>
		</tr>


								</tbody>
							</table>
						</div>
					</fieldset>
				</form>

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
	
	<script type="text/javascript">
			var oEditors = [];
			nhn.husky.EZCreator.createInIFrame({
				oAppRef : oEditors,
				elPlaceHolder : "board_content",
				sSkinURI : "./board/SE2.2.1.O9186/SmartEditor2Skin.html",
				htParams : {
					bUseToolbar : true,
					fOnBeforeUnload : function() {
						//alert("아싸!");	
					}
				}, //boolean
				fOnAppLoad : function() {
					//예제 코드
					//oEditors.getById["board_content"].exec("PASTE_HTML", ["로딩이 완료된 후에 본문에 삽입되는 text입니다."]);
				},
				fCreator : "createSEditor2"
			});

			function pasteHTML() {
				var sHTML = "<span style='color:#FF0000;'>이미지도 같은 방식으로 삽입합니다.<\/span>";
				oEditors.getById["board_content"].exec("PASTE_HTML", [ sHTML ]);
			}

			function showHTML() {
				var sHTML = oEditors.getById["board_content"].getIR();
				alert(sHTML);
			}

			function submitboard_content(elClickedObj) {
				oEditors.getById["board_content"].exec("UPDATE_board_content_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

				// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.

				try {
					elClickedObj.form.submit();
				} catch (e) {
				}
			}

			function setDefaultFont() {
				var sDefaultFont = '궁서';
				var nFontSize = 24;
				oEditors.getById["board_content"].setDefaultFont(sDefaultFont,
						nFontSize);
			}
		</script>
		
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