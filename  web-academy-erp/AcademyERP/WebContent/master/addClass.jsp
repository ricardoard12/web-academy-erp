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
<link href="./css/join.css" rel="stylesheet" type="text/css">
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
			if (sub == "x") {
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

	<form action="./createClassAction.master" method="post" id="classForm">
		<fieldset>
			<legend>학급 등록/생성</legend>
			<div class="form_table">
				<table border="1" cellspacing="0" summary="회계등록">
					<tbody>

						<!-- 회계 아이디는 일련번호 자동 등록 -->
						<tr>
							<th scope="row">담당 선생 선택</th>
							<td>
								<div class="item">
									<select id="teacher_sel" name="teacher_sel">
										<option value="x">선택하세요.</option>
										<%
											for (int i = 0; i < teacherlist.size(); i++) {
												List tList = (List) teacherlist.get(i);
										%>
										<option value="<%=tList.get(0)%>"><%=tList.get(1)%></option>
										<%
											}
										%>
									</select>
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">레벨 선택</th>
							<td>
								<div class="item">
									<select name="level" id="lel">
										<option value="x">레벨 선택</option>
										<option value="초등">초등</option>
										<option value="중등">중</option>
										<option value="고등">고등</option>
									</select> 학교
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">학년 선택</th>
							<td>
								<div class="item">
									<select name="level2" id="lev">
										<option value="x">학년</option>
										<option value="1">1</option>
										<option value="2">2</option>
										<option value="3">3</option>
										<option value="4" class="hide">4</option>
										<option value="5" class="hide">5</option>
										<option value="6" class="hide">6</option>
									</select>학년
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">학기 선택</th>
							<td>
								<div class="item">
									<select name="half">
										<option value="x">학기</option>
										<option value="1">1</option>
										<option value="2">2</option>
									</select>학기
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">반 이름 선택</th>
							<td>
								<div class="item">
									<select name="sub_name">
										<option>반 선택</option>
										<option value="A">A</option>
										<option value="B">B</option>
										<option value="C">C</option>
										<option value="D">D</option>
									</select>학기
								</div>
							</td>
						</tr>
						<tr>
							<th scope="row">기간 선택</th>
							<td>
								<div class="item">
									시작일<input type="text" name="startdate" readonly="readonly"
										value="" onclick="datePicker(event,'startdate',0)">
									종료일<input type="text" name="enddate" value=""
										readonly="readonly" onclick="datePicker(event,'enddate',0)">
								</div>
							</td>
						</tr>
						<!--  수입/지출 등록 버튼 -->
						<tr align="right">
							<td></td>
							<td align="left">
								<div class="item">
									<input type="submit" value="등록" onclick="return CheckDate()">
									<input type="reset" value="취소">
								</div>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
		</fieldset>
	</form>
	<!-- //content -->

</body>
</html>