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
<script type="text/javascript">
	function selectDomain() { // 도메인 선택
		document.joinEmployeeForm.mm_email2.value = document.joinEmployeeForm.select_domain.value;
	}
</script>
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

				<!-- 직원 회원가입시작 -->

				<form action="./EmployeeAddAction.em" method="post" name="joinEmployeeForm">
					<fieldset>
						<legend>직원 회원가입</legend>
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

									<tr>
										<th scope="row">회원이름</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">이름
													입력</label> <input type="text" name="mm_name" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">회원아이디</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">아이디
													입력</label> <input type="text" name="mm_id" id="temp_input"
													class="i_text" style="width: 300px">
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">비밀번호</th>
										<td>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력</label> <input type="password" name="mm_passwd" id="temp_input"
													class="i_text" style="width: 300px">
											</div> <br>
											<div class="item">
												<label for="temp_input" class="i_label"
													style="position: absolute; visibility: visible;">비밀번호
													입력 확인</label> <input type="password" name="mm_passwd2" id="temp_input"
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
										<th scope="row">주민등록번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_jumin1" title="주민등록번호 입력"
													class="i_text"> - <input type="password"
													name="mm_jumin2" title="레이블 텍스트" class="i_text">
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">전화번호</th>
										<td>
											<div class="item">
												<select name="mm_telDDD">
													<option value="02">02</option>
													<option value="051">051</option>
													<option value="055">055</option>
												</select> <input type="text" name="mm_tel" title="전화번호"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">휴대폰번호</th>
										<td>
											<div class="item">
												<select name="mm_phoneDDD">
													<option>010</option>
													<option>011</option>
													<option>017</option>
												</select> <input type="text" name="mm_phone" title="휴대폰"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">우편번호</th>
										<td>
											<div class="item">
												<input type="text" name="mm_zipcode1" title="우편번호1" class="i_text">
												- <input type="text" name="mm_zipcode2" title="우편번호2" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr1" title="주소"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상세주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_addr2" title="상세주소"
													class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">이메일 주소</th>
										<td>
											<div class="item">
												<input type="text" name="mm_email1" title="이메일" class="i_text">@
												<input type="text" name="mm_email2" title="도메인" class="i_text" >
												<select name="select_domain" onchange="selectDomain()">
													<option value="">직접 입력</option>
													<option value="nate.com">nate.com</option>
													<option value="naver.com">naver.com</option>
													<option value="gmail.com">gmail.com</option>
													<option value="hanmail.net">hanmail.net</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">회원 등급</th>
										<td>
											<div class="item">
												<select name="mm_level">
													<option value="3">3(강사)</option>
													<option value="4">4(관리자)</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">상위 관리자 ID</th>
										<td>
											<div class="item">
												<input type="text" name="mm_manager_id" title="상위 관리자 ID" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당부서</th>
										<td>
											<div class="item">
												<input type="text" name="ep_department" title="담당부서" class="i_text">
											</div>
										</td>
									</tr>


									<tr>
										<th scope="row">직급</th>
										<td>
											<div class="item">
												<input type="text" name="ep_position" title="직급" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당과목</th>
										<td>
											<div class="item">
												<select name="ep_subject_name">
													<option value="국어">국어</option>
													<option value="영어">영어</option>
													<option value="수학">수학</option>
													<option value="사회">사회</option>
													<option value="과학">과학</option>
												</select>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">담당학급</th>
										<td>
											<div class="item">
												<input type="text" name="ep_group_id" title="담당학급" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">연봉</th>
										<td>
											<div class="item">
												<input type="text" name="ep_salary" title="연봉" class="i_text">원
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">은행명</th>
										<td>
											<div class="item">
												<input type="text" name="ep_bank_name" title="은행명" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">계좌번호</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_num" title="계좌번호" class="i_text">
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">예금주</th>
										<td>
											<div class="item">
												<input type="text" name="ep_account_name" title="예금주" class="i_text">
											</div>
										</td>
									</tr>






									<!--  가입버튼 -->
									<tr align="right">
										<td></td>
										<td align="left">
											<div class="item">
												<input type="submit" value="가입"> <input
													type="button" name="" value="취소">
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