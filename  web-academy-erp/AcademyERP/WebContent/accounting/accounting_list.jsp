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
			
				<select name="">
				<%
				for (int i = 1; i <= 12; i++) {
				%>
					<option><%=i %>월</option>
				<%
				}
				%>
				</select>
				
				<select name="">
				<%
				for (int i = 1; i <= 31; i++) {
				%>
					<option><%=i %>일</option>
				<%
				}
				%>
				</select>
				
				<input type="button" value="년도별">
				<input type="button" value="월별">
				<input type="button" value="일별">
			</div>
		</td>
		</tr> 
</thead>
<thead>
<tr>
<th scope="col">선택</th>
<th scope="col">번호</th>
<th scope="col">항목 유형</th>
<th scope="col">회원ID</th>
<th scope="col">이름</th>
<th scope="col">금액</th>
<th scope="col">결제 유형</th>
<th scope="col">담당자</th>
<th scope="col">메모</th>
</tr>
</thead>
<tbody>


<tr>
	<td>
		<input name="" type="checkbox" value="" id="" class=""></label> 
	</td>
<td>1</td>
<td>수강료</td>
<td>A12334654</td>
<td>김철수</td>
<td>1000000원</td>
<td>카드</td>
<td>관리자</td>
<td>메모</td>
		
</tr>

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