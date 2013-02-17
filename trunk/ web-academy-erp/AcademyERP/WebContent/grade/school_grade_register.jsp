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
        
<!-- 학교시험등록시작 -->

<form action="" name="Grade" method="post">
	<fieldset>
		<legend>학교시험 성적등록</legend>
		<div class="form_table">
		<table border="1" cellspacing="0" summary="학교성적">
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
		<th scope="row">학교명</th>
		<td>
			<div class="item">
				<input type="text" name="" title="학교" class="i_text">
			</div>
		</td>
		</tr>
		
		<!-- <tr> 
		<th scope="row">학년</th>
		<td>
			<div class="item">
				
				<select name="">
					<option value="1-1-중간">1학년-1학기-중간고사</option>
					<option value="1-1-기말">1학년-1학기-기말고사</option>
					
					<option value="1-2-중간">1학년-2학기-중간고사</option>
					<option value="1-2-기말">1학년-2학기-기말고사</option>
					
					<option value="2-1-중간">2학년-1학기-중간고사</option>
					<option value="2-1-기말">2학년-1학기-기말고사</option>
					
					<option value="2-2-중간">2학년-2학기-중간고사</option>
					<option value="2-2-기말">2학년-2학기-기말고사</option>
					
					<option value="3-1-중간">3학년-1학기-중간고사</option>
					<option value="3-1-기말">3학년-1학기-기말고사</option>
					
					<option value="3-2-중간">3학년-2학기-중간고사</option>
					<option value="3-2-기말">3학년-2학기-기말고사</option>
				</select>
			</div>
		</td>
		</tr> -->
		
		
		<tr>
		<th scope="row">시험</th>
		<td>
			<div class="item">
				<input name="" type="radio" value="1-1-중간" id="d1" class="i_radio"><label for="d1">1학년-1학기-중간고사</label>
				<input name="" type="radio" value="1-1-기말" id="d2" class="i_radio"><label for="d2">1학년-1학기-기말고사</label>
				<input name="" type="radio" value="1-2-중간" id="d1" class="i_radio"><label for="d1">1학년-2학기-중간고사</label>
				<input name="" type="radio" value="1-2-기말" id="d2" class="i_radio"><label for="d2">1학년-2학기-기말고사</label>
				
				<br>
				
				<input name="" type="radio" value="2-1-중간" id="d1" class="i_radio"><label for="d1">2학년-1학기-중간고사</label>
				<input name="" type="radio" value="2-1-기말" id="d2" class="i_radio"><label for="d2">2학년-1학기-기말고사</label>
				<input name="" type="radio" value="2-2-중간" id="d1" class="i_radio"><label for="d1">2학년-2학기-중간고사</label>
				<input name="" type="radio" value="2-2-기말" id="d2" class="i_radio"><label for="d2">2학년-2학기-기말고사</label>
				
				<br>
				
				<input name="" type="radio" value="3-1-중간" id="d1" class="i_radio"><label for="d1">3학년-1학기-중간고사</label>
				<input name="" type="radio" value="3-1-기말" id="d2" class="i_radio"><label for="d2">3학년-1학기-기말고사</label>
				<input name="" type="radio" value="3-2-중간" id="d1" class="i_radio"><label for="d1">3학년-2학기-중간고사</label>
				<input name="" type="radio" value="3-2-기말" id="d2" class="i_radio"><label for="d2">3학년-2학기-기말고사</label>
			</div>
		</td>
		</tr>
		
		<tr> 
		<th scope="row">과목</th>
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
		
		
		
		<!--  성적등록버튼 -->
		<tr align="right" >
		<td>
		</td>
		<td align="left">
			<div class="item">
				<input type="submit"" value="등록">
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

<script type="text/javascript">


	function windowopen() {
		var id = document.Grade.mm_id.value;
		window.open("./GradeIdSearch.gr?id=" + id , "width=350,height=200,scrollbars=no");
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