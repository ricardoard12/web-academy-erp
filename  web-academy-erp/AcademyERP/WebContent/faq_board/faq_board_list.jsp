<%@page import="academy.faq_board.db.Faq_boardbean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
       
    request.setCharacterEncoding("utf-8");
       
    Faq_boardbean faq_boardbean = (Faq_boardbean) request.getAttribute("faq_boardbean");
   
    List faq_boardList = (List)request.getAttribute("faq_boardList");
    
    int listcount=((Integer)request.getAttribute("listcount")).intValue();
    int nowpage=((Integer)request.getAttribute("page")).intValue();
    int maxpage=((Integer)request.getAttribute("maxpage")).intValue();
    int startpage=((Integer)request.getAttribute("startpage")).intValue();
    int endpage=((Integer)request.getAttribute("endpage")).intValue();
   
    
  	//세션으로 id 값 받음
	String id = (String) session.getAttribute("id");
	//세션으로 name값 받음
	String name = (String) session.getAttribute("name");
	//세션으로 level 값 받음
	String level = (String) session.getAttribute("level");
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
<form method="post" action="Faq_BoardDeleteAction.fb">
<table cellspacing="0" border="1" summary="게시판의 글제목 리스트" class="tbl_type_notice">
<!-- level세션값 전달 -->
<%-- <input type="hidden" name="level" value=<%=session.getAttribute("level")%>> --%>

<caption>FAQ 리스트</caption>

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
<th scope="col"colspan="2">날짜</th>

</tr>
</thead>

<tbody>

<%
    if(listcount>0){
    	for(int i=0;i<faq_boardList.size();i++){
    		faq_boardbean=(Faq_boardbean)faq_boardList.get(i);
    		%>
    		<tr>
    		<td class="frm"><input type="checkbox" name="faq_board_check" id="chk_sel" value="<%=faq_boardbean.getFaq_num()%>"><label for="chk_sel">선택</label></td>
    		<td class="num"><%=faq_boardbean.getFaq_num() %></td>
    		<td class="title"><a href="./Faq_BoardDetailAction.fb?num=<%=faq_boardbean.getFaq_num()%>&name=<%=name%>"><%=faq_boardbean.getFaq_subject() %></a></td>
    		<td><a href="#"><%=faq_boardbean.getFaq_name() %></a></td>
    		<td class="date" colspan="2"><%=faq_boardbean.getFaq_date() %></td>
    		
    		</tr>
    		
    		
    		<%
    	}
    }

    		%>

    	
</tbody>
</table>
<div align="right">

<input type="button" name="faq_boardwrite" value="글쓰기" onclick="location.href='./Faq_boardWrite.fb?level=<%=level%>&id=<%=id%>&name=<%=name%>'">
<input type="submit" name="faq_boarddelete" value="삭제">

</form>
</div>


<!-- Paginate -->


<div class="paginate_complex" align="center">
<%
if(nowpage<=1){
	%>
	<a href="#" class="direction prev"><span></span><span></span>처음</a>
	<%
}else{
	%>
	<a href="./faq_boardList.fb?page=<%=nowpage-1%>" class="direction prev"><span></span>이전</a>
	<%
}
%>
<%
for(int a=startpage;a<=endpage;a++){
	if(a==nowpage){
		%><strong><%=a %></strong>&nbsp;<%
	}else{
		%><a href="./faq_boardList.fb?page=<%=a%>"><%=a %></a><%
	}
}
%>
<%
if(nowpage>=maxpage){
	%><a href="#" class="direction next">끝<span></span><span></span></a><%
}else{
	%><a href="./faq_boardList.fb?page=<%=nowpage+1%>" class="direction next">다음 </a><%
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