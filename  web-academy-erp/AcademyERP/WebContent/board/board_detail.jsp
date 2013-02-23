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
BoardBean boardbean = (BoardBean) request.getAttribute("boardbean");
//Re_BoardBean re_boardbean = (Re_BoardBean) request.getAttribute("re_boardbean");

//List re_boardList=(List)request.getAttribute("re_boardlist");
// int re_listcount=((Integer)request.getAttribute("relistcount")).intValue();
// int re_nowpage=((Integer)request.getAttribute("page")).intValue();
// int re_maxpage=((Integer)request.getAttribute("maxpage")).intValue();
// int re_startpage=((Integer)request.getAttribute("startpage")).intValue();
// int re_endpage=((Integer)request.getAttribute("endpage")).intValue();

String level =(String) session.getAttribute("level");
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
<th scope="row">첨부파일</th>
<td><%=boardbean.getBoard_file() %></td>
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

<form action="./Re_BoardAddAction.bo" method="post">
<input type="hidden" name="board_num" value="<%=boardbean.getBoard_num()%>">
<!-- <input type="hidden" name="board_re_ref" value="1"> -->
<!-- <input type="hidden" name="board_re_lev" value="0"> -->
<!-- <input type="hidden" name="board_re_seq" value="0"> -->
<!-- 덧글 전달 값 시작 -->
 <input type="hidden" name="re_board_num" value="<%=boardbean.getBoard_num()%>"> 

<-- 덧글 전달 값 종료 -->

	<table cellspacing="0" border="1" summary="글 내용에 대한 덧글 표시" class="tbl_type_reply">
	<caption>글에 대한 코멘트</caption>
	<colgroup>
	<col width="110">
	<col>
	</colgroup>
	<tbody>
	<tr class="input_txt">
	<td><input type="text" title="이름" name="re_board_name" value="" class="name"></td>
	<td><textarea cols="65" rows="5" name="re_board_content" class="comment"></textarea>
		<input type="submit" title="입력" value="입력" class="submit">
	</td>
	</tr>
<%-- 	<% if(re_listcount > 0){ --%>
<!-- // 		for(int i = 0 ; i < re_boardList.size() ; i++){ -->
<!-- // 		re_boardbean=(Re_BoardBean)re_boardList.get(i); -->
<%-- 		%> --%>
		<tr>
<%-- 		<td><%=re_boardbean.getRe_board__name() %></td> --%>
		<td>테스트</td>
		<td>코멘트를 달아봅니다</td>
		</tr>
		<tr>
		<td>테스트</td>
		<td>코멘트를 달아봅니다 2 </td>
		</tr>
<%-- 		<% --%>
<!-- // 		} -->
<!-- // 	} -->
<%-- 		%> --%>
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