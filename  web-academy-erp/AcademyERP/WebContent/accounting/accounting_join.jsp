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
<script src="./js/calendar.js"></script>
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

				<!-- 회계 등록 시작 -->

				<form action="./AccountingJoinAction.ac" method="post" name="acCheck">
					<fieldset>
						<legend>수입/지출 등록</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회계등록">
								<tbody>

									<!-- 회계 아이디는 일련번호 자동 등록 -->
				<tr>
					<th scope="row">항목 유형</th>
					<td>
						<div class="item">
						<!-- ac_ie_type 수강료 수입 지출 -->
							<input name="ac_io_type" type="radio" value="수강료" id="c1" class="i_radio">
							<label for="c1">수강료</label> 
							<input name="ac_io_type" type="radio" value="수입" id="c1" class="i_radio">
							<label for="c1">수입</label>
							<input name="ac_io_type" type="radio" value="지출" id="c1" class="i_radio">
							<label for="c1">지출</label>
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">회원 아이디</th>
					<td>
						<div class="item">
							<input type="text" name="mm_id" id="temp_input" class="i_text" size="10">
							<input type="button" value="아이디 찾기" onclick="searchIDopen()">
						</div>
					</td>
				</tr>

			<tr>
					<th scope="row">금액</th>
					<td>
						<div class="item">
							<input type="text" name="ac_price" title="학교명" class="i_text">
							원
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">결제 유형</th>
					<td>
						<div class="item">
							<input name="ac_cc_type" type="radio" value="현금" id="c1" class="i_radio">
							<label for="c1">현금</label> 
							<input name="ac_cc_type" type="radio" value="카드" id="c2" class="i_radio">
							<label for="c2">카드</label>
						</div>
					</td>
				</tr>


				<tr>
					<th scope="row">결제일</th>
					<td>
						<div class="item">
							<input type="text" name="ac_date" >
							<input type="button" value="달력보기" onClick="datePicker(event,'ac_date',0)">
							<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->						
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">담당자 이름</th>
					<td>
						<div class="item">
						<input type="text" name="ac_manager_name" id="temp_input"	
						class="i_text" size="10" > <input type="button" value="담당자" onclick="searchOfficerOpen()">
						</div>
					</td>
				</tr>
				

				<tr>
					<th scope="row">메모</th>
					<td>
						<div class="item">
							<textarea name="ac_memo" cols="50" rows="5" title="레이블 텍스트"
								class="i_text"></textarea>
						</div>
					</td>
				</tr>


				<!--  수입/지출 등록 버튼 -->
				<tr align="right">
					<td></td>
					<td align="left">
						<div class="item">
							<input type="submit"  value="등록" onclick="return CheckDate()"> 
							<input type="reset" value="취소">
						</div>
					</td>
				</tr>
				<!--  수입/지출 등록 버튼 끝 -->


								</tbody>
							</table>
						</div>
					</fieldset>
				</form>

				<!-- 회계 등록 폼 끝 -->

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
	
	function searchIDopen(){
		window.open('./AccountingIDSearch.ac', '_blank', 'height=200, width=400');
	}
	
	function searchOfficerOpen(){
		window.open('./AccountingOfficerSearch.ac', '_blank', 'height=200, width=400');
	}
	
	function CheckDate() {
			var date = document.acCheck.ac_date.value;
			if(date == ''){
				alert('날짜 입력하세요');
				return false;
			}
		}
		
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