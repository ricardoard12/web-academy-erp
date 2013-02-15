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
BoardBean boardbean = (BoardBean) request.getAttribute("boardbean");
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
<caption>글 읽기</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>
<thead>
<tr>
<th scope="row">제목</th>
<td colspan="5"><%=boardbean.getBoard_subject() %></td>
</tr>
</thead>
<tbody>
<tr>
<th scope="row">작성자</th>
<td><%=boardbean.getBoard_name() %></td>
<th scope="row">작성일</th>
<td><%=boardbean.getBoard_date() %></td>
<th scope="row">조회</th>
<td><%=boardbean.getBoard_readcount() %></td>
</tr>
<tr>
<td colspan="6" class="cont">
<%=boardbean.getBoard_content() %>
</td>
</tr>
</tbody>
</table>
<!-- 수정 / 삭제 -->
<br>
<div align="center">
<input type="button" name="board_modify" value="수정" onclick="location.href='BoardModify.bo?num=<%=boardbean.getBoard_num()%>'">
<input type="button" name="" value="목록" onclick="location.href='BoardNotice.bo'">
</div>
<br>

<form action="./BoardReplyAction.bo" method="post">
	<table cellspacing="0" border="1" summary="글 내용에 대한 덧글 표시" class="tbl_type_reply">
	<caption>글에 대한 코멘트</caption>
	<colgroup>
	<col width="110">
	<col>
	</colgroup>
	<tbody>
	<tr class="input_txt">
	<td><input type="text" title="이름" name="board_name" value="" class="name"></td>
	<td><textarea cols="65" rows="5" name="board_content" class="comment"></textarea>
		<input type="submit" title="입력" value="입력" class="submit">
	</td>
	</tr>
	<tr>
	<td>테스트</td>
	<td>코멘트를 달아봅니다</td>
	</tr>
	<tr>
	<td>테스트</td>
	<td>코멘트를 달아봅니다 2 </td>
	</tr>
	</tbody>
	</table>
	</form>
            
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