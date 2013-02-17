<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script> 
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
        
<!-- 회원가입시작 -->

<form action="" method="post">
	<fieldset>
		<legend>학원 시험등록</legend>
		<div class="form_table">
		<table border="1" cellspacing="0" summary="회원가입">
		<tbody>
		
		
		<tr> 
		<th scope="row">학생ID</th>
		<td>
			<div class="item">
				<input type="text" name="mm_id" title="학생ID" class="i_text"><input type="button" value="아이디찾기" onclick="windowopen()">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목코드</th>
		<td>
			<div class="item">
				<input type="text" name="" title="휴대폰" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목명</th>
		<td>
			<div class="item">
				<input type="text" name="" title="휴대폰" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">점수</th>
		<td>
			<div class="item">
				<input type="text" name="" title="휴대폰" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">시험 내용</th>
		<td>
			<div class="item">
				<input type="text" name="" title="휴대폰" class="i_text">
			</div>
		</td>
		</tr>
		
		
		<tr> 
		<th scope="row">담당강사</th>
		<td>
			<div class="item">
				<input type="text" name="" title="휴대폰" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr>
		<th scope="row">응시학급</th>
		<td>
			<div class="item">
				<select name="" multiple="multiple">
					<option>옵션 하나</option>
					<option>옵션 둘</option>
					<option>옵션 셋</option>
					<option>옵션 셋</option>
					<option>옵션 셋</option>
					<option>옵션 셋</option>
					<option>옵션 셋</option>
					<option>옵션 셋</option>
					
				</select>
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">응시일자</th>
		<td>
			<div class="item">
				<input type="text" name="date" title="휴대폰" class="i_text">
				<input type="button" value="검색" onclick="CheckDate()">
				<input type="button" value="달력보기" onClick="datePicker(event,'date',0)">
				<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->
			</div>
		</td>
		</tr>
		
		
		
		
		<!--  가입버튼 -->
		<tr align="right" >
		<td>
		</td>
		<td align="left">
			<div class="item">
				<input type="submit"" value="가입">
				<input type="button" name="" value="취소">
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

<script src="../js/calendar.js"></script>
<script type="text/javascript">

function CheckDate() {
	var date = document.acCheck.date.value;
	if(date == ''){
		alert('날짜 입력하세요');
	}else{
		document.acCheck.action = "./AccountingList.ac?date="+date;
		document.acCheck.submit();
	}
}

jQuery(function(){
	// Help Toggle
	$('.item>.i_help').click(function(){
		$(this).parent('.item').find('.i_dsc').toggleClass('hide');
	});
	// Input Clear
	var i_text = $('.item>.i_label').next('.i_text');
	$('.item>.i_label').css('position','absolute');
	i_text
		.focus(function(){
			$(this).prev('.i_label').css('visibility','hidden');
		})
		.blur(function(){
			if($(this).val() == ''){
				$(this).prev('.i_label').css('visibility','visible');
			} else {
				$(this).prev('.i_label').css('visibility','hidden');
			}
		})
		.change(function(){
			if($(this).val() == ''){
				$(this).prev('.i_label').css('visibility','visible');
			} else {
				$(this).prev('.i_label').css('visibility','hidden');
			}
		})
		.blur();
});
</script>
</body>
</html>