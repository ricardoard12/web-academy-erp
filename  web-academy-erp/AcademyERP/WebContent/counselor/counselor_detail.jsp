<%@page import="academy.counsel.db.CounselerBean"%>
<%@page import="academy.student.db.StudentBean"%>
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
StudentBean student=(StudentBean) request.getAttribute("student");
CounselerBean counselor = (CounselerBean)request.getAttribute("counselerbean");
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
<table cellspacing="0" border="1" summary="글 내용을 표시" class="tbl_type_notice">
<caption>상담내역</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>




<thead>
<tr>
<th scope="row">제목</th>
<td colspan="11"><%=counselor.getCc_subject() %></td>
</tr>
</thead>
<tbody>
<tr>
<th scope="row">학생</th>
<td><%=student.getMm_id()%></td>
<th scope="row">부모</th>
<td><%=student.getSt_parent_name() %></td>
<th scope="row">학급</th>
<td><%=student.getGp_name() %></td>
<th scope="row">담임</th>
<td><%=student.getMm_manager_id() %></td>
<th scope="row">작성자</th>
<td><%=counselor.getEp_id() %></td>
<th scope="row">상담일자</th>
<td><%=counselor.getCc_date() %></td>
</tr>
<tr>
<td colspan="6" class="cont">
<%=counselor.getCc_content() %>
</td>
</tr>
</tbody>
</table>
<!-- 수정 / 삭제 -->
<br>
<div align="center">
<input type="button" name="board_modify" value="수정" onclick="location.href='CounselorModify.cc?idx=<%=counselor.getIdx()%>&id=<%=counselor.getMm_id()%>'">
<input type="button" name="" value="목록" onclick="location.href='StudentCounsel.st?id=<%=counselor.getMm_id()%>'">
</div>
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