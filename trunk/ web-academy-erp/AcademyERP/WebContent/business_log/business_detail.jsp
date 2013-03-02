<%@page import="academy.business_log_db.BusinessBean"%>
<%@page import="java.util.List"%>
<%@page import="academy.board.db.Re_BoardBean"%>
<%@page import="academy.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<%
BusinessBean businessbean = (BusinessBean) request.getAttribute("businessbean");
List businessList=(List)request.getAttribute("businesslist");
String level =(String) session.getAttribute("level");
String name= (String)session.getAttribute("name");
String id = (String)session.getAttribute("id");

%>
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
        
<!-- detail 게시판 시작 -->
<form action="./BusinessModifyAction.bl" method="post">
<table cellspacing="0" border="1" summary="글 내용을 표시" class="tbl_type_notice">
<input type="hidden" name="business_num" value="<%=request.getParameter("num")%>">
<caption>글 읽기</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>
<thead>
<tr>
<th scope="row">제목</th>
<td colspan="5" align="left"><%=businessbean.getBusiness_subject() %></td>
</tr>
</thead>
<tbody>
<tr>
<th scope="row">작성자</th>
<td colspan="5"><%=businessbean.getBusiness_name()%></td>
</tr>
<tr>
<th scope="row">작성일</th>
<td colspan="5"><%=businessbean.getBusiness_date() %></td>
</tr>
<!-- 금일업무 데이터 출력 -->
<tr>
<th scope="row" colspan="6">1. 금일 업무 사항</th></tr><tr>
<td colspan="6" class="cont">
<%=businessbean.getBusiness_today() %>
</td>
</tr>
<!-- 상담내역 출력 -->
<tr>
<th scope="row" colspan="6">2. 금일 상담 내역</th></tr><tr>
<td colspan="6" class="cont">
<%=businessbean.getBusiness_counsel() %>
</tr>
<!-- 기타사항 출력 -->
<tr>
<th scope="row" colspan="6">2. 금일 기타사항</th></tr><tr>
<td colspan="6" class="cont">
<%=businessbean.getBusiness_etc() %>
</tr>
</tbody>
</table>

<!-- 수정 / 삭제 -->
<br>
<div align="center">
<input type="submit" name="business_modify" value="수정">
<input type="button" name="" value="목록" onclick="location.href='BusinessNotice.bl'">
</div>
</form>
<br>
            
<!-- //dtail 게시판 끝 -->
            
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