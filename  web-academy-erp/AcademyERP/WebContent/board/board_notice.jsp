<%@page import="java.util.List"%>
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
BoardBean boardbean =(BoardBean)request.getAttribute("boardbean");
List boardList=(List)request.getAttribute("boardlist");
int listcount=((Integer)request.getAttribute("listcount")).intValue();
int nowpage=((Integer)request.getAttribute("page")).intValue();
int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
int startpage=((Integer)request.getAttribute("startpage")).intValue();
int endpage=((Integer)request.getAttribute("endpage")).intValue();
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
 <%
    if(listcount>0){
    	for(int i=0;i<boardList.size();i++){
    		BoardBean board=(BoardBean)boardList.get(i);
    		%>
    		<tr>

    		<td class="frm"><input type="checkbox" name="" id="chk_sel" value=""><label for="chk_sel">선택</label></td>
    		<td class="num"><%=boardbean.getBoard_num() %></td>
    		<td class="title"><%
     if(board.getBoard_re_lev()>0){//답변글
    	 for(int a=0;a<=boardbean.getBoard_re_lev()*2;a++){
    		 %>
    		 &nbsp;
    		 <%
    	 }
     %>
     	▶
     <%
     }
     %>
    		<a href="./BoardDetailAction.bo?num=<%=boardbean.getBoard_num()%>"><%=boardbean.getBoard_subject() %></a></td>
    		<td><a href="#"><%=boardbean.getBoard_name() %></a></td>
    		<td class="date"><%=boardbean.getBoard_date() %></td>
    		<td class="hit"><%=boardbean.getBoard_readcount() %></td>
    		</tr>
    		<%}
    }
    		%>
<tbody>

<!-- <tr class="reply"> -->
<!-- <td class="frm"><input type="checkbox" name="" id="chk_sel2" value=""><label for="chk_sel2">선택</label></td> -->
<!-- <td class="num">&nbsp;</td> -->
<!-- <td class="title" style="padding-left:10px;"><a href="#">블로그 개편 답글</a></td> -->
<!-- <td><a href="#">네이버맨</a></td> -->
<!-- <td class="date">2008/02/14</td> -->
<!-- <td class="hit">1234</td> -->
<!-- </tr> -->
<!-- <tr class="reply"> -->
<!-- <td class="frm"><input type="checkbox" name="" id="chk_sel3" value=""><label for="chk_sel3">선택</label></td> -->
<!-- <td class="num">&nbsp;</td> -->
<!-- <td class="title" style="padding-left:20px;"><a href="#">블로그 개편 답글</a></td> -->
<!-- <td><a href="#">네이버맨</a></td> -->
<!-- <td class="date">2008/02/14</td> -->
<!-- <td class="hit">1234</td> -->
<!-- </tr> -->
<!-- <tr class="reply"> -->
<!-- <td class="frm"><input type="checkbox" name="" id="chk_sel4" value=""><label for="chk_sel4">선택</label></td> -->
<!-- <td class="num">&nbsp;</td> -->
<!-- <td class="title" style="padding-left:30px;"><a href="#">블로그 개편 답글</a> <img src="img/ic_pic.gif" width="13" height="12" alt="첨부이미지" class="pic"> <a href="#" class="comment">[5]</a> <img src="img/ic_new.gif" width="10" height="9" alt="새글" class="new"></td> -->
<!-- <td><a href="#">네이버맨</a></td> -->
<!-- <td class="date">2008/02/14</td> -->
<!-- <td class="hit">1234</td> -->
<!-- </tr> -->
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
<div class="paginate">
	<a href="#" class="pre">이전</a>
	<a href="#">1</a>
	<strong>2</strong>
	<a href="#">3</a>
	<a href="#">4</a>
	<a href="#">5</a>
	<a href="#">6</a>
	<a href="#">7</a>
	<a href="#">8</a>
	<a href="#">9</a>
	<a href="#" class="next">다음</a>
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