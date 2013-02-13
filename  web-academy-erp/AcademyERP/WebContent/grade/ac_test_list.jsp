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
        
            <!-- 학원 시험 목록 시작 -->
            
            <!-- UI Object -->
<table cellspacing="0" border="1" summary="유형별 자산목록리스트" class="tbl_type_list">
<caption>학원 시험 목록</caption>
<colgroup>
<col width="12%"><col><col width="12%" span="7">
</colgroup>
<thead>
<tr>

<th scope="col">선택</th>
<th scope="col">과목ID</th>
<th scope="col">과목명</th>
<th scope="col">시험내용</th>
<th scope="col">담당강사</th>
<th scope="col">응시일자</th>
<th scope="col">응시인원</th>
</tr>
</thead>
<tbody>


<tr>
		<td>
				<input name="" type="checkbox" value="" id="a1" class="i_check"><label for="a1"></label> 
		</td>
		
		<td>E001</td>
<td>영어I</td>
<td>2월 영어 문법 테스트</td>
<td>영어강사I</td>
<td>2013-02-05</td>
<td>50명</td>		
</tr>
		
		<!-- 버튼 -->
		<tr align="right" >
		<td align="center" colspan="7">
			<div class="item">
				<input type="submit" value="선택 삭제">
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