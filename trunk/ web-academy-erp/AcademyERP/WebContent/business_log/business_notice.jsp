<%@page import="academy.business_log.db.BusinessBean"%>

<%@page import="academy.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
       
    request.setCharacterEncoding("utf-8");
       
    
    BusinessBean businessbean = (BusinessBean) request.getAttribute("businessbean");
    List businessList=(List)request.getAttribute("businesslist");
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
<form method="post" action="BusinessDeleteAction.bl">
<table cellspacing="0" border="1" summary="게시판의 글제목 리스트" class="tbl_type_notice">
<!-- level세션값 전달 -->
<%-- <input type="hidden" name="level" value=<%=session.getAttribute("level")%>> --%>

<caption>업무일지 리스트</caption>
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
    	for(int i=0;i<businessList.size();i++){
    		businessbean=(BusinessBean)businessList.get(i);
    		%>
    		<tr>
    		<td class="frm"><input type="checkbox" name="business_check" id="chk_sel" value="<%=businessbean.getBusiness_num()%>"><label for="chk_sel">선택</label></td>
    		<td class="num"><%=businessbean.getBusiness_num() %></td>
    		<td class="title"><a href="./BusinessDetailAction.bl?num=<%=businessbean.getBusiness_num()%>"><%=businessbean.getBusiness_subject() %></a></td>
<%--     		<td class="title"><a href="./BusinessDetailAction.bl?num=<%=businessbean.getBusiness_num()%>&name=<%=name%>"><%=businessbean.getBusiness_subject() %></a></td> --%>
    		<td><a href="#"><%=businessbean.getBusiness_name() %></a></td>
    		<td class="date" colspan="2"><%=businessbean.getBusiness_date() %></td>
    		
    		</tr>
    		
    		
    		<%
    	}
    }

    		%>

    	
</tbody>
</table>
<div align="right">
<%-- <% if(level.equals("5")){ %> --%>
<input type="button" name="business_write" value="글쓰기" onclick="location.href='./BusinessWrite.bl?level=<%=level%>&id=<%=id%>&name=<%=name%>'">
<%if(level.equals("5")){ %>
<input type="submit" name="business_delete" value="삭제">
<%} %>
</form>
</div>
<%-- <%}else{}%> --%>

<!-- Paginate -->


<div class="paginate_complex" align="center">
<%
if(nowpage<=1){
	%>
	<a href="#" class="direction prev"><span></span><span></span>처음</a>
	<%
}else{
	%>
	<a href="./business_notice.bl?page=<%=nowpage-1%>" class="direction prev"><span></span>이전</a>
	<%
}
%>
<%
for(int a=startpage;a<=endpage;a++){
	if(a==nowpage){
		%><strong><%=a %></strong>&nbsp;<%
	}else{
		%><a href="./business_notice.bl?page=<%=a%>"><%=a %></a><%
	}
}
%>
<%
if(nowpage>=maxpage){
	%><a href="#" class="direction next">끝<span></span><span></span></a><%
}else{
	%><a href="./business_notice.bl?page=<%=nowpage+1%>" class="direction next">다음 </a><%
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