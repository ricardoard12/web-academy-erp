<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script src="./js/jquery-1.9.1.js"></script> 
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

<form action="./GradeJoinAction.gr" name="grade" method="post">
	<fieldset>
		<legend>학원/학교 시험등록</legend>
		<div class="form_table">
		<table border="1" cellspacing="0" summary="회원가입">
		<tbody>
		
		<tr>
		<th scope="row">시험장소</th>
		<td>
			<div class="item">
				<input name="gr_place" type="radio" value="학원" id="academy" class="i_radio" checked><label>학원</label>
				<input name="gr_place" type="radio" value="학교" id="school" class="i_radio" ><label>학교</label>
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">학생ID</th>
		<td>
			<div class="item">
				<input type="text" name="mm_id" title="학생ID" class="i_text">
				<input type="button" value="아이디찾기" onclick="searchopen('s')">
			</div>
		</td>
		</tr>
		<!-- tr태그의 school 클래스 academy클래스 폼 구별 -->
		<tr class="school"> 
		<th scope="row">학교명</th>
		<td>
			<div class="item">
				아이디에서 학교명만 가져와서 보여주기 입력은 안해도 되
			</div>
		</td>
		</tr>
		
		<tr class="academy"> 
		<th scope="row">과목코드</th>
		<td>
			<div class="item">
				<input type="text" name="gr_code" title="" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목명</th>
		<td>
			<div class="item">
				<input type="text" name="gr_subject" title="" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목점수</th>
		<td>
			<div class="item">
				<input type="text" name="gr_score" title="" class="i_text">
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목 설명</th>
		<td>
			<div class="item">
				<textarea rows="6" cols="42" name="gr_memo"></textarea>
			</div>
		</td>
		</tr>
		
		
		<tr class="academy"> 
		<th scope="row">담당강사</th>
		<td>
			<div class="item">
				<input type="text" name="ep_id" title="" class="i_text">
				<input type="button" value="강사찾기" onclick="searchopen('t')">
			</div>
		</td>
		</tr>
		
		
		
		<tr class="academy"> 
		<th scope="row">응시일자</th>
		<td colspan="3">
			<div class="item">
				<input type="text" name="gr_exam_date" title="" class="i_text">
				<input type="button" value="달력보기" onClick="datePicker(event,'gr_exam_date',0)">
				<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->
			</div>
		</td>
		</tr>
		
		
		<!-- width 값 강제  -->
		<tr class="school">
		<th scope="row" width="170">학교시험종류</th>
		<td>
			<div class="item">
				<input name="gr_period" type="radio" value="1-1-중간" id="d1" class="i_radio"><label for="d1">1학년-1학기-중간고사</label>
				<input name="gr_period" type="radio" value="1-1-기말" id="d2" class="i_radio"><label for="d2">1학년-1학기-기말고사</label>
				<input name="gr_period" type="radio" value="1-2-중간" id="d1" class="i_radio"><label for="d1">1학년-2학기-중간고사</label>
				<input name="gr_period" type="radio" value="1-2-기말" id="d2" class="i_radio"><label for="d2">1학년-2학기-기말고사</label>
				
				<br>
				
				<input name="gr_period" type="radio" value="2-1-중간" id="d1" class="i_radio"><label for="d1">2학년-1학기-중간고사</label>
				<input name="gr_period" type="radio" value="2-1-기말" id="d2" class="i_radio"><label for="d2">2학년-1학기-기말고사</label>
				<input name="gr_period" type="radio" value="2-2-중간" id="d1" class="i_radio"><label for="d1">2학년-2학기-중간고사</label>
				<input name="gr_period" type="radio" value="2-2-기말" id="d2" class="i_radio"><label for="d2">2학년-2학기-기말고사</label>
				
				<br>
				
				<input name="gr_period" type="radio" value="3-1-중간" id="d1" class="i_radio"><label for="d1">3학년-1학기-중간고사</label>
				<input name="gr_period" type="radio" value="3-1-기말" id="d2" class="i_radio"><label for="d2">3학년-1학기-기말고사</label>
				<input name="gr_period" type="radio" value="3-2-중간" id="d1" class="i_radio"><label for="d1">3학년-2학기-중간고사</label>
				<input name="gr_period" type="radio" value="3-2-기말" id="d2" class="i_radio"><label for="d2">3학년-2학기-기말고사</label>
			</div>
		</td>
		</tr>
		
		
		
		<!--  가입버튼 -->
		<tr align="right" >
		<td>
		</td>
		<td align="left">
			<div class="item">
				<input type="submit" value="등록">
				<input type="button" value="취소">
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

<script src="./js/calendar.js"></script>

<script type="text/javascript">

function searchopen(name){
	
	if (name == "s") {
		id = document.grade.mm_id.value;
		if (id.length == 0) {
			alert("학생 이름를 입력하세요");
			document.grade.mm_id.focus();
			return false;
		}
	}else if(name == "t"){
		id = document.grade.ep_id.value;
		if (id.length == 0) {
			alert("강사 이름를 입력하세요");
			document.grade.ep_id.focus();
			return false;
		}
	}
	
	window.open('GradeIDSearch.gr?id=' + id, '_blank', 'height=200, width=400');
		
}
	
	

	jQuery(document).ready(function() {

		/* 클래스 academy, school 폼 변환*/
		if (jQuery("#academy").is(":checked")) {
			jQuery(".school").hide();
			jQuery(".academy").show();
		}

		jQuery("#academy").click(function() {
			if (jQuery(this).is(":checked")) {
				jQuery(".school").hide();
			}
			jQuery(".academy").show();
		});

		jQuery("#school").click(function() {
			if (jQuery(this).is(":checked")) {
				jQuery(".academy").hide();
			}
			jQuery(".school").show();
		});

	});

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