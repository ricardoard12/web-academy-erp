<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/board.css" rel="stylesheet" type="text/css">
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
        
            <!-- 직원 출근 현황 시작 -->
            
            <!-- UI Object -->
<table cellspacing="0" border="1" summary="유형별 자산목록리스트" class="tbl_type_list">
<caption>직원 출근 현황</caption>
<colgroup>
<col width="12%"><col><col width="12%" span="6">
</colgroup>
<thead>
<tr>

<th scope="col">선택</th>
<th scope="col">이름</th>
<th scope="col">출근시간</th>
<th scope="col">퇴근시간</th>
<th scope="col">출결여부</th>
<th scope="col">메모</th>
</tr>
</thead>
<tbody>


<tr>
		<td>
				<input name="" type="checkbox" value="" id="a1" class="i_check"><label for="a1"></label> 
		</td>
		
		<td>PC</td>
<td>A12334654</td>
<td>삼성전자</td>
<td>3QZG615</td>
<td>GX620</td>
		
</tr>
		
		<!-- 버튼 -->
		<tr align="right" >
		<td align="center" colspan="7">
			<div class="item">
				<input type="submit" value="선택 출근">
				<input type="button"  value="선택 결근">
				<input type="button"  value="선택 문자 발송">
			</div>
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