<%@page import="academy.qna_board.db.QnaBean"%>
<%@page import="academy.noticle.db.NoticeBean"%>
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
QnaBean qnabean = (QnaBean)request.getAttribute("qna");
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
<caption>공지사항</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>
<thead>
<tr>
<th scope="row">제목</th>
<td colspan="5"><%=qnabean.getQna_title() %></td>
</tr>
</thead>
<tbody>
<tr>

<th scope="row">작성자</th>
<td><%=qnabean.getQna_subject()%></td>
<th scope="row">작성일</th>
<td><%=qnabean.getQna_data() %></td>
<th scope="row">조회</th>
<td><%=qnabean.getQna_recont() %></td>
</tr>
<tr>
<td colspan="6" class="cont">
<%=qnabean.getQna_content() %>
</td>
</tr>
</tbody>
</table>
<!-- 수정 / 삭제 -->
<br>
<div align="center">
<input type="button" name="notice_modify" value="수정" onclick="location.href='./QnaModify.qa?num=<%=qnabean.getQna_num()%>'">
<% if(session.getAttribute("qnaid")!=null){  // 세션을 이용해서 글생성후 새션과 DB에 저장된 작성자가 같으면 삭제 버튼이 나타난다.
	String qnaid = (String)session.getAttribute("qnaid");
	if(qnaid.equals(qnabean.getQna_subject())){
	%>	
	<input type="button"  value="삭제" onclick="location.href='./QnaDelete.qa?num=<%=qnabean.getQna_num()%>'"> 
	<%
	}
}
%>

<input type="button" name="" value="목록" onclick="location.href='./QnaList.qa'">
<%
if(session.getAttribute("level")!=null){
	String level=(String)session.getAttribute("level");
	if(level.equals("4") ||level.equals("5") ){%>
	<input type="button" name="" value="답글" onclick="location.href='./SeqWriting.sq?qna_num=<%=qnabean.getQna_num()%>'">
	<% }}%>
	

</div>

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