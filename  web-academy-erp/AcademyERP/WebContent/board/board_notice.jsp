<%@page import="java.util.List"%>
<%@page import="academy.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    BoardBean boardbean = (BoardBean) request.getAttribute("boardbean");
    List boardList=(List)request.getAttribute("boardlist");
    int listcount=((Integer)request.getAttribute("listcount")).intValue();
    int nowpage=((Integer)request.getAttribute("page")).intValue();
    int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
    int startpage=((Integer)request.getAttribute("startpage")).intValue();
    int endpage=((Integer)request.getAttribute("endpage")).intValue();
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
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
        
            <!-- 게시판 시작 -->
            <!-- UI Object -->
<table cellspacing="0" border="1" summary="게시판의 글제목 리스트" class="tbl_type_notice">
<caption>게시판 리스트</caption>
<colgroup>
<col width="30"><col width="80"><col>
<col width="115"><col width="85"><col width="60">
</colgroup>
<thead>
<tr>
<th scope="col">&nbsp;</th>
<th scope="col">No</th>
<th scope="col">제목</th>
<th scope="col">글쓴이</th>
<th scope="col">날짜</th>
<th scope="col">조회수</th>
</tr>
</thead>

<tbody>

<%
    if(listcount>0){
    	for(int i=0;i<boardList.size();i++){
    		boardbean=(BoardBean)boardList.get(i);
    		%>
    		<tr>
    		<td class="frm"><input type="checkbox" name="" id="chk_sel" value=""><label for="chk_sel">선택</label></td>
    		<td class="num"><%=boardbean.getBoard_num() %></td>
    		<td class="title"><a href="./BoardDetailAction.bo?num=<%=boardbean.getBoard_num()%>"><%=boardbean.getBoard_subject() %></a></td>
    		<td><a href="#"><%=boardbean.getBoard_name() %></a></td>
    		<td class="date"><%=boardbean.getBoard_date() %></td>
    		<td class="hit"><%=boardbean.getBoard_readcount() %></td>
    		</tr>
    		
    		
    		<%
    	}
    }
    		%>
    		<%
     if(boardbean.getBoard_re_lev()>0){//답변글
    	 for(int a=0;a<=boardbean.getBoard_re_lev()*2;a++){
    		 %>
    		<tr class="reply">
			<td class="frm"><input type="checkbox" name="" id="chk_sel3" value=""><label for="chk_sel3">선택</label></td>
			<td class="num">&nbsp;</td>
			<td class="title" style="padding-left:20px;"><a href="#"><%=boardbean.getBoard_subject() %></a></td>
			<td><a href="#"><%=boardbean.getBoard_name() %></a></td>
			<td class="date"><%=boardbean.getBoard_date() %></td>
			<td class="hit"><%=boardbean.getBoard_readcount() %></td>
			</tr>
    		 
<%
    	 }
     }
     %>


</tbody>
</table>


<!-- Paginate -->
<!-- <div class="paginate">
	<a href="#" class="pre_end">맨앞</a>
	<a href="#" class="pre">이전</a>
	<a href="#">11</a>
	<strong>12</strong>
	<a href="#">13</a>
	<a href="#">14</a>
	<a href="#">15</a>
	<a href="#">16</a>
	<a href="#">17</a>
	<a href="#">18</a>
	<a href="#">19</a>
	<a href="#">20</a>
	<a href="#" class="next">다음</a>
	<a href="#" class="next_end">맨뒤</a>
</div> -->

<div align="right"><input type="button" name="board_write" value="글쓰기" onclick="location.href='./BoardWrite.bo'"></div>
<div align="center">
<%
if(nowpage<=1){
	%>
	[이전]
	<%
}else{
	%>
	<a href="./BoardNotice.bo?page=<%=nowpage-1%>">[이전]</a>
	<%
}
%>
<%
for(int a=startpage;a<=endpage;a++){
	if(a==nowpage){
		%>[<%=a %>]&nbsp;<%
	}else{
		%><a href="./BoardNotice.bo?page=<%=a%>">[<%=a %>]&nbsp;</a><%
	}
}
%>
<%
if(nowpage>=maxpage){
	%>[다음]<%
}else{
	%><a href="./BoardNotice.bo?page=<%=nowpage+1%>">[다음]</a><%
}

%>

</div>

<!-- //Paginate -->
            
<!-- //게시판 끝 -->
            
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