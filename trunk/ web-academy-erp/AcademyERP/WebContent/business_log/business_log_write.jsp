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

				<body>
					<table width="50%" border="1" align="center">
						<tr align="center">
							<td rowspan="2" width="60%">학원 로고 및 이미지</td>
							<td rowspan="2">결재</td>
							<td>담당자</td>
							<td>관리자</td>
							<td>학원장</td>
						</tr>
						<tr align="center">
							<td><input type="button" name="btn1" value="확인"></td>
							<td><input type="button" name="btn2" value="확인"></td>
							<td><input type="button" name="btn3" value="확인"></td>
						</tr>
						<tr>
							<td colspan="5"><br>1. 오늘의 주요 업무 사항</td>
						</tr>
						<tr>
							<td colspan="5"><textarea cols="100" rows="10"></textarea></td>
						</tr>
						<tr>
							<td colspan="5">2. 상담 내역</td>
						</tr>
						<tr>
							<td colspan="5"><textarea name="textarea" cols="100"
									rows="10"></textarea></td>
						</tr>
						<tr>
							<td colspan="5">3. 기타 및 건의사항</td>
						</tr>
						<tr>
							<td colspan="5"><textarea name="textarea2" cols="100"
									rows="10"></textarea></td>
						</tr>
						<tr>
							<td colspan="5" align="center"><input type="submit"
								value="등록">&nbsp; <input type="reset" value="다시작성">&nbsp;
								<input type="button" value="취소">&nbsp;</td>
						</tr>
					</table>
			</div>
		</div>
	</div>

</body>


<script type="text/javascript">
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
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
		oEditors.getById["contents"].exec("PASTE_HTML", [ sHTML ]);
	}

	function showHTML() {
		var sHTML = oEditors.getById["contents"].getIR();
		alert(sHTML);
	}

	function submitContents(elClickedObj) {
		oEditors.getById["contents"].exec("UPDATE_CONTENTS_FIELD", []); // 에디터의 내용이 textarea에 적용됩니다.

		// 에디터의 내용에 대한 값 검증은 이곳에서 document.getElementById("content").value를 이용해서 처리하면 됩니다.

		try {
			elClickedObj.form.submit();
		} catch (e) {
		}
	}

	function setDefaultFont() {
		var sDefaultFont = '궁서';
		var nFontSize = 24;
		oEditors.getById["contents"].setDefaultFont(sDefaultFont, nFontSize);
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