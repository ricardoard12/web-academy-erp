<%@page import="academy.counsel.db.CounselerBean"%>
<%@page import="academy.student.db.StudentBean"%>
<%@page import="academy.student.db.StudentDAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/join.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript">
</script>
<%

int counselcount=((Integer)request.getAttribute("counselcount")).intValue();
int nowpage=((Integer)request.getAttribute("page")).intValue();
int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
int startpage=((Integer)request.getAttribute("startpage")).intValue();
int endpage=((Integer)request.getAttribute("endpage")).intValue();
StudentBean studentbean= (StudentBean)request.getAttribute("studentbean");



request.getAttribute("studentbean"); // 학생의 기본정보를 저장
%>

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

	<!-- 학생 목록 시작 -->
				<form action="./StudentModifyAction.st" method="post">
					<fieldset>
						<legend>학생 상담내역</legend>
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
												<%=studentbean.getMm_name()%>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">회원아이디</th>
										<td>
											<div class="item">
												<%=studentbean.getMm_id()%>
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
										<th scope="row">전화번호</th>
										<td>
											<div class="item">
													<%=studentbean.getMm_tel().split("-")[0]%>-<%=studentbean.getMm_tel().split("-")[1]%>-<%=studentbean.getMm_tel().split("-")[2] %>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">휴대폰번호</th>
										<td>
											<div class="item">
									<%=studentbean.getMm_phone().split("-")[0]%>-<%=studentbean.getMm_phone().split("-")[1]%>-<%=studentbean.getMm_phone().split("-")[2]%>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">이메일 주소</th>
										<td>
											<div class="item">
													<%=studentbean.getMm_email().split("@")[0]%>@<%=studentbean.getMm_email().split("@")[1]%>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학교명</th>
										<td>
											<div class="item">
												<%=studentbean.getSt_school_name()%>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학년</th>
										<td>
											<div class="item">
												<%=studentbean.getSt_school_grade() %>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학부모 이름</th>
										<td>
											<div class="item">
												<%=studentbean.getSt_parent_name()%>
											</div>
										</td>
									</tr>

									<tr>
										<th scope="row">학부모 연락처</th>
										<td>
											<div class="item">
												<%=studentbean.getSt_parent_mobile().split("-")[0] %>-<%=studentbean.getSt_parent_mobile().split("-")[1]%>-<%=studentbean.getSt_parent_mobile().split("-")[2]%>
											</div>
										</td>
									</tr>

	                               <tr>
										<th scope="row">재학상태</th>
										<td>
											<div class="item">
												<%=studentbean.getSt_status()%>
											</div>
										</td>
									</tr>
									   <tr>
										<th scope="row">소속학급</th>
										<td>
											<div class="item">
												<%=studentbean.getGp_id()%>
											</div>
										</td>
									</tr>
									<tr>
										<th scope="row">담임</th>
										<td>
											<div class="item">
												<%=studentbean.getEp_id()%>
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

				<!-- 회원정보 끝 -->
				
				<!-- 상담리스트  -->
					<form name="Student_List">
				<!-- UI Object -->
				<table cellspacing="0" border="1" summary="원생 리스트"
					class="tbl_type_list">
					<caption>원생 목록</caption>
					<colgroup>
						<col width="12%">
						<col>
						<col width="12%" span="7">
					</colgroup>
					<thead>
						<tr>
							<th scope="col">글번호</th>
							<th scope="col">상담제목</th>
							<th scope="col">소속학급</th>
							<th scope="col">담임이름</th>
							<th scope="col">날짜</th>
						<th scope="col">상담내역</th>
						</tr>
					</thead>
					<tbody>
					<%
							List counselList = (List)request.getAttribute("counselList");
							for(int i = 0; i< counselList.size(); i++){
							CounselerBean counselerbean = (CounselerBean)counselList.get(i);
							
					%>
						<tr>
							<td><%=counselerbean.getIdx() %></td>
							<td><a href="./StudentDetail.st?id=<%=counselerbean.getMm_id()%>&check=1"><%=counselerbean.getCo_subject()%></a></td>
							<td><%=counselerbean.getGp_id()%></td>
							<td><%=counselerbean.getEp_id()%></td>
							<td><%=counselerbean.getCo_date() %></td>
							<td><input type="button" value="상담"  onclick="location.href='./StudentSounsel.st?id=<%=counselerbean.getMm_id()%>'"></td>
						</tr>
						
						
						
					<%
					}
					%>
					
					
					
						<!-- 버튼 -->
										<tr>
				<td colspan="6" align="center">
												<%
		if(nowpage<=1){
	%>
	
	<%
	}else{
	%>
		<a href="./BoardList.bo?page=<%=nowpage-1%>">[이전]</a>
	<%
}
%>
<%
for(int a=startpage;a<=endpage;a++){
	if(a==nowpage){
		%>[<%=a %>]&nbsp;<%
	}else{
		%><a href="./BoardList.bo?page=<%=a%>">[<%=a %>]&nbsp;</a><%
	}
}
%>
<%
if(nowpage>=maxpage){
	%><%
}else{
	%><a href="./BoardList.bo?page=<%=nowpage+1%>">[다음]</a><%
}

%>
				</td>
				
				</tr>
						
					
						
						<tr align="right">
							<td align="center" colspan="7">
								<div class="item">
									<input type="button" value="원생 등록"  onclick="location.href='./StudentJoin.st'">
									<input type="button" value="원생 휴원" onclick="Off()">
									<input type="button" value="원생 퇴출" onclick="Out()">
								</div>
						</tr>

				




					</tbody>
				</table>
				<!-- //UI Object -->
				</form>

				<!-- //수강생 관리 끝 -->
				
				
				<!-- 상담리스트 끝 -->
				<!-- //수강생 관리 끝 -->

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