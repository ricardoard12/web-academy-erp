<%@page import="academy.master.db.ListPackage"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	List teacherlist = (List) request.getAttribute("tList");
	if (teacherlist == null) {
		response.sendRedirect("./index.jsp");
	}
	String subject = "x";
	if ((String) request.getAttribute("sub") != null) {
		subject = (String) request.getAttribute("sub");
	}
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<script src="./js/calendar.js"></script>
<script src="./js/jquery-1.9.1.js"></script>
<title>반 생성</title>
<script>
	$(function() {
		$("#lel").change(function() {
			if ($(this).val() != "e") {
				$("#lev .hide").attr({
					hidden : true
				});
				$("#lev:first").attr("selected", "selected");
				$("#lev").focus();
				//$("#lev").attr("value":"x");
			} else {
				$("#lev .hide").removeAttr("hidden");
				$("#lev:first").attr("selected", "selected");
				$("#lev").focus();
				//$("#lev").attr("value":"x");
			}
		});
		$("#classForm").bind("submit", function() {
			var sub = $("#sub").val();
			var teacher = $("#teacher_sel").val();
			var lel = $("#lel").val();
			var lev = $("#lev").val();
			var half = $("#half").val();
			var start = $("startdate").val();
			var end = $("end").val();
			if (sub =="x") {
				alert("반을 선택하세요.");				
				return false;
			}
			if (teacher == "x") {
				alert("담당 선생을 선택하세요.");
				return false;
			}
			if (lel == "x") {
				alert("해당 학교등급을 선택하세요.");
				return false;
			}
			if (lev == "x") {
				alert("학년을 선택하세요.");
				return false;
			}
			if (half == "x") {
				alert("학기를 선택하세요.");
				return false;
			}
			if (start == "") {
				alert("시작일을 선택하세요.");
				return false;
			}
			if (end == "") {
				alert("종료일을 선택하세요.");
				return false;
			}
			if (start.split("-")[0] >= end.split("-")[0]) {//년 계산
				if (start.split("-")[1] >= +end.split("-")[1]) {//달 계산
					if (start.split("-")[2] >= end.split("-")[2]) {//일 계산
						alert("종료일이 시작일보다 앞서 거나 수업 기간이 너무 짧습니다. 있습니다.");
						return false;					
					}
				}
			}
		});

	});
</script>
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
				<form action="./createClassAction.master" id="classForm"
					method="post">
					<table>
						<tr>
							<td>반 :<select name="sub_name" id="sub">
									<option value="x">반을선택하세요</option>
									<option value="A">A</option>
									<option value="B">B</option>
									<option value="C">C</option>
							</select></td>
							<td>담당 선생 선택 : <select id="teacher_sel" name="teacher_sel">
									<option value="x">선택하세요.</option>
									<%
										for (int i = 0; i < teacherlist.size(); i++) {
											List tList = (List) teacherlist.get(i);
									%>
									<option value="<%=tList.get(0)%>"><%=tList.get(1)%></option>
									<%
										}
									%>
							</select></td>
						</tr>
						<tr>
							<td>등급선택:<select name="level" id="lel">
									<option value="x">레벨 선택</option>
									<option value="e">초등</option>
									<option value="m">중</option>
									<option value="h">고등</option>
							</select>학교
							</td>
							<td><select name="level2" id="lev">
									<option value="x">학년</option>
									<option value="1">1</option>
									<option value="2">2</option>
									<option value="3">3</option>
									<option value="4" class="hide">4</option>
									<option value="5" class="hide">5</option>
									<option value="6" class="hide">6</option>
							</select>학년<select name="half">
									<option value="x">학기</option>
									<option value="1">1</option>
									<option value="2">2</option>
							</select>학기</td>

						</tr>
						<tr>
							<td>시작일<input type="text" name="startdate"
								readonly="readonly" value="" onclick="datePicker(event,'startdate',0)">
							</td>
							<td>종료일<input type="text" name="enddate" value="" readonly="readonly"
								onclick="datePicker(event,'enddate',0)"> <!--  문자열 잘라서 날짜 비 -->
							</td>
						</tr>
						<tr>
							<td><input type="submit"><input type="reset"
								value="다시작성"><input type="button" value="목록가기"></td>
						</tr>
					</table>
				</form>
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
	<!-- //UI Object -->
</body>
</html>