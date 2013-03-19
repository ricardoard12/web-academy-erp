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
<script src="./js/calendar.js"></script>
<title>Insert title here</title>

</head>
<%
	String kind = request.getParameter("kind");
	//날짜 검색시 구별 하는 방법
	if(kind.equals("")){
	    kind = (String)request.getAttribute("kind");
	}
	
	List aclist = (List)request.getAttribute("aclist");
    
%>
<body>
	<!-- UI Object -->
	<div id="wrap">
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

					<form name="acCheck" method="post" >
					<input type="hidden" name=kind value="<%=kind %>">
						<!-- 회계 목록 시작 -->

							<!-- UI Object -->
							<table cellspacing="0" border="1" summary="유형별 자산목록리스트" class="tbl_type_list">
								<caption>회계 목록</caption>
								<colgroup>
									<col width="12%">
									<col>
									<col width="12%" span="9">
								</colgroup>
								
						<thead>
							<tr>
								<th scope="row">검색날짜선택</th>
								<td colspan="8" align="left">
									<div class="item">
										<input type="text" name="date"> 
										<input type="button" value="검색" onclick="CheckDate()">
										<input type="button" value="달력보기" onClick="datePicker(event,'date',0)">
										<!-- 동일한 날짜입력 의 경우 세번째 1일 타켓 구분 입력 안하면 기본 0값 -->
									</div>
								</td>
							</tr>
						</thead>

								<thead>
									<tr>
										<th scope="col">전체선택<br><input type="checkbox" name="all" onclick="CheckAll()"></th>
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
						    for (int i = 0; i < aclist.size(); i++) {
						        AccountingBean acBean = (AccountingBean) aclist.get(i);
						%>
									<tr>		<!-- 삭제 체크값 넘기기 -->
										<td><input type="checkbox" name="check" value="<%=acBean.getAc_id()%>"></td>
										<td><%=acBean.getAc_id()%></td>
										<td><%=acBean.getMm_id()%></td>
										<td><%=acBean.getMm_name()%></td>
										<td><%=acBean.getAc_io_type()%></td>
										<td><%=acBean.getAc_price()%>원</td>
										<td><%=acBean.getAc_cc_type()%></td>
										<td><%=acBean.getAc_manager_name()%></td>
										<td><%=acBean.getAc_memo()%></td>
									</tr>
									<%
						    }
						%>
									<!-- 버튼 -->
									<tr align="right">
										<td align="center" colspan="9">
											<div class="item">
												<input type="button" value="선택삭제" onclick="Del()">
											</div>
										</td>
									</tr>
								</tbody>
							</table>
							<!-- //UI Object -->
							</form>
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
<!--  체크박스 확인 -->
<script language="javascript">

	function CheckAll() {
		if (document.acCheck.all.checked == true) { // 체크가 되었다면
			for ( var x = 0; x < acCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
				document.acCheck.check[x].checked = true; //for문을 사용하여 모두 체크 시킨다.
			}
		} else {
			for ( var x = 0; x < acCheck.check.length; x++) { // 모두 해제 시킨다..
				document.acCheck.check[x].checked = false;
			}
		}
	}

	function Del() {
		count = 0;
		for ( var x = 0; x < acCheck.check.length; x++) { // int가 아닌 var를 사용한다.. 
			if(document.acCheck.check[x].checked == true){
				count++;
			} //for문을 사용하여 모두 체크 시킨다.
		}
		
		if(count<=0){
			alert("한 개이상 선택하세요");
			return false;
		}else if(confirm("삭제 하시겠습니까?") == true){
			document.acCheck.action = "./AccountingDelete.ac";
			document.acCheck.submit();
		}
	}
	
	function CheckDate() {
		var date = document.acCheck.date.value;
		if(date == ''){
			alert('날짜 입력하세요');
		}else{
			document.acCheck.action = "./AccountingList.ac?date="+date;
			document.acCheck.submit();
		}
	}
</script>
</body>

</html>