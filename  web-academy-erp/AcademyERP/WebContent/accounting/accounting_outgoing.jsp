<%@page import="academy.accounting.db.AccountingBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<%
List acoutgoingList = (List)request.getAttribute("acoutgoingList");
%>
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
        
            <!-- 회계 목록 시작 -->
	      
            <!-- UI Object -->
<table cellspacing="0" border="1" summary="유형별 자산목록리스트" class="tbl_type_list">
<caption>회계 목록</caption>
<colgroup>
<col width="12%"><col><col width="12%" span="9">
</colgroup>
<thead>
<tr>
		<td colspan="9">
			<div class="item">
				<select name="">
				<%
				for (int i = 1; i <= 12; i++) {
				%>
					<option><%=i %>년</option>
				<%
				}
				%>
				</select>
			
				<input type="button" value="월별">
			</div>
		</td>
		</tr> 
</thead>

<thead>
<tr>
<th scope="col">선택</th>
<th scope="col">번호</th>
<th scope="col">회원ID</th>
<th scope="col">이름</th>
<th scope="col">항목 유형</th>
<th scope="col">금액</th>
<th scope="col">결제 유형</th>
<th scope="col">담당자</th>
<th scope="col">메모</th>
</tr>
</thead>
<tbody>

<% 
for(int i=0;i<acoutgoingList.size();i++){
	AccountingBean acBean = (AccountingBean)acoutgoingList.get(i);%>
<tr>
	<td>
		<input type="checkbox" name="ac_id" value="<%=acBean.getAc_id()%>">
	</td>
<td><%=acBean.getAc_id() %></td>
<td><%=acBean.getMm_id() %></td>
<td>직원,학생에서 가져와야됨</td>
<td><%=acBean.getAc_io_type() %></td>
<td><%=acBean.getAc_price() %>원</td>
<td><%=acBean.getAc_cc_type() %></td>
<td><%=acBean.getAc_manager_name() %></td>
<td><%=acBean.getAc_memo() %></td>
</tr>
<%} %>
		<!-- 버튼 -->
		<tr align="right" >
		<td align="center" colspan="9">
			<div class="item">
				<input type="submit" value="선택 삭제">
			</div>
		</td>
		</tr>
</tbody>
</table>
<!-- //UI Object -->

            <!-- //수강생 관리 끝 -->
            
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