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

				<form action="AccountingInsert.ac" method="post">
					<fieldset>
						<legend>수입/지출 등록</legend>
						<div class="form_table">
							<table border="1" cellspacing="0" summary="회원가입">
								<tbody>
									<!-- <tr>
		<th scope="row">회원 이름</th>
		<td>
			<div class="item">
				<input type="text" name="" title="이름입력하세요" class="i_text">
				<button type="button" class="i_help"><span>?</span></button>
				<span class="i_dsc hide">숨겨둔 설명 입니다. 줄 바꿈 없습니다.</span>
			</div>
		</td>
		</tr> 
		<tr>
		<th scope="row">인풋:설명 숨기고 줄 바꿈</th>
		<td>
			<div class="item">
				<input type="text" name="" title="레이블 텍스트" class="i_text">
				<button type="button" class="i_help"><span>?</span></button>
				<p class="i_dsc hide">숨겨둔 설명 입니다. 줄 바꿈 했습니다.</p>
			</div>
		</td>
		</tr> -->
									<!-- <tr>
		<th scope="row">회원 이름</th>
		<td>
			<div class="item">
				<input type="text" name="" title="이름 입력하세요" class="i_text"> 숨겨두지 않은 설명 입니다. 줄 바꿈 없습니다.
			</div>
		</td>
		</tr>-->


									<!-- 회계 아이디는 일련번호 자동 등록 -->
				<tr>
					<th scope="row">항목 유형</th>
					<td>
						<div class="item">
							<input name="" type="radio" value="" id="c1" class="i_radio"><label
								for="c1">수강료</label> <input name="" type="radio" value=""
								id="c1" class="i_radio"><label for="c1">수입</label>
							<input name="" type="radio" value="" id="c1" class="i_radio"><label
								for="c1">지출</label>
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">회원 아이디</th>
					<td>
						<div class="item">
							<label for="temp_input" class="i_label"
								style="position: absolute; visibility: visible;">아이디
								입력</label> <input type="text" name="" id="temp_input"
								class="i_text" style="width: 300px">
						</div>
					</td>
				</tr>

				<!-- <tr>
<th scope="row">인풋:설명 보이고 줄 바꿈</th>
<td>
	<div class="item">
		<input type="text" name="" title="레이블 텍스트" class="i_text">
		<p class="i_dsc">숨겨두지 않은 설명 입니다. 줄 바꿈 했습니다.</p>
	</div>
</td>
</tr>-->

				<tr>
					<th scope="row">금액</th>
					<td>
						<div class="item">
							<input type="text" name="" title="학교명" class="i_text">
							원
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">결제 유형</th>
					<td>
						<div class="item">
							<input name="" type="radio" value="" id="c1" class="i_radio"><label
								for="c1">현금</label> <input name="" type="radio" value=""
								id="c2" class="i_radio"><label for="c2">카드</label>
						</div>
					</td>
				</tr>


				<tr>
					<th scope="row">결제일</th>
					<td>
						<div class="item">
<input type="text" name="target_date">
<input type="button" value="달력보기" onClick="datePicker(event,'target_date',0)">
<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->						
										</div>
					</td>
				</tr>

				<tr>
					<th scope="row">담당자 이름</th>
					<td>
						<div class="item">
							<label for="temp_input" class="i_label"
								style="position: absolute; visibility: visible;">이름
								입력</label> <input type="text" name="" id="temp_input"
								class="i_text" style="width: 300px">
						</div>
					</td>
				</tr>

				<tr>
					<th scope="row">메모</th>
					<td>
						<div class="item">
							<textarea name="" cols="50" rows="5" title="레이블 텍스트"
								class="i_text"></textarea>
						</div>
					</td>
				</tr>





				<!--  가입버튼 -->
				<tr align="right">
					<td></td>
					<td align="left">
						<div class="item">
							<input type="submit" " value="등록"> <input
								type="reset" name="" value="취소">
						</div>
					</td>
				</tr>



									<!-- <tr>
		<th scope="row">멀티라인 인풋</th>
		<td>
			<div class="item">
				<textarea name="" cols="50" rows="5" title="레이블 텍스트" class="i_text"></textarea>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">멀티라인 인풋:레이블 클리어</th>
		<td>
			<div class="item">
				<label for="temp_textarea" class="i_label" style="position:absolute; visibility:visible;">레이블 텍스트를 멀티라인 인풋과 오버레이</label>
				<textarea name="" cols="50" rows="5" id="temp_textarea" class="i_text"></textarea>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">셀렉트:단일 선택</th>
		<td>
			<div class="item">
				<select name="">
					<option>옵션 하나</option>
					<option>옵션 둘</option>
					<option>옵션 셋</option>
				</select>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">셀렉트:복수 선택</th>
		<td>
			<div class="item">
				<select name="" multiple="multiple">
					<option>옵션 하나</option>
					<option>옵션 둘</option>
					<option>옵션 셋</option>
				</select>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">체크박스 인풋:줄 바꿈 없는</th>
		<td>
			<div class="item">
				<input name="" type="checkbox" value="" id="a1" class="i_check"><label for="a1">레이블</label> <input name="" type="checkbox" value="" id="a2" class="i_check"><label for="a2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">체크박스 인풋:줄 바꿈 있는</th>
		<td>
			<div class="item">
				<input name="" type="checkbox" value="" id="b1" class="i_check"><label for="b1">레이블</label><br>
				<input name="" type="checkbox" value="" id="b2" class="i_check"><label for="b2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">라디오 인풋:줄 바꿈 없는</th>
		<td>
			<div class="item">
				<input name="" type="radio" value="" id="c1" class="i_radio"><label for="c1">레이블</label> <input name="" type="radio" value="" id="c2" class="i_radio"><label for="c2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">라디오 인풋:줄 바꿈 있는</th>
		<td>
			<div class="item">
				<input name="" type="radio" value="" id="d1" class="i_radio"><label for="d1">레이블</label><br>
				<input name="" type="radio" value="" id="d2" class="i_radio"><label for="d2">레이블</label>
			</div>
		</td>
		</tr>
		<tr>
		<th scope="row">파일</th>
		<td>
			<div class="item">
				<input name="" type="file" title="레이블 텍스트">
			</div>
		</td>
		</tr> -->
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