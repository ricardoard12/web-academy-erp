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
        
<!-- detail 게시판 시작 -->
<table cellspacing="0" border="1" summary="글 내용을 표시" class="tbl_type">
<caption>글 읽기</caption>
<colgroup>
<col width="80"><col><col width="80">
<col width="150"><col width="80"><col width="150">
</colgroup>
<thead>
<tr>
<th scope="row">제목</th>
<td colspan="5">제발 울프팀 업데이트 끝내주세요!!</td>
</tr>
</thead>
<tbody>
<tr>
<th scope="row">작성자</th>
<td>노찬현</td>
<th scope="row">작성일</th>
<td>2008.2.26</td>
<th scope="row">조회</th>
<td>19</td>
</tr>
<tr>
<td colspan="6" class="cont">
	안녕하세요 Ready to Combat 울프팀 입니다.<br>
	금일 오전 7시 부터 진행된 정기점검이 완료되어 현재 정상적으로 서버가 오픈되었습니다. <br>
	고객님들께서 게임에 접속이 가능하신 상태이니 게임에 접속을 해보시기 바랍니다. <br>
	<br>
	<strong>[업데이트 내용]</strong><br>
	1.선물함 추가 및 선물시스템 업데이트 <br>
	2.EX아이템(한코인아이템) 업데이트 <br>
	3.신규 무기 칼(근접무기) 업데이트 <br>
	&nbsp;* EX진지점령/울프진지점령 모드에서 인간형으로 플레이시 3번키로 선택가능<br>
	4.신규 총기 P-226(보조무기) 업데이트 <br>
	6.프라이드 마크 130개 업데이트<br>
	<br>
	<span style="color:#FF6600;">감사합니다.</span>
</td>
</tr>
</tbody>
</table>

<form name="" action="method">
<fieldset>
<legend>코멘트 영역</legend>
	<table cellspacing="0" border="1" summary="글 내용에 대한 덧글 표시" class="tbl_type2">
	<caption>글에 대한 코멘트</caption>
	<colgroup>
	<col width="110">
	<col>
	</colgroup>
	<tbody>
	<tr class="input_txt">
	<td><input type="text" title="이름" name="" value="" class="name"></td>
	<td><textarea cols="65" rows="5" name="" class="comment"></textarea>
		<input type="submit" title="입력" value="입력" class="submit">
	</td>
	</tr>
	<tr>
	<td>테스트</td>
	<td>코멘트를 달아봅니다</td>
	</tr>
	<tr>
	<td>테스트</td>
	<td>코멘트를 달아봅니다 2 </td>
	</tr>
	</tbody>
	</table>
</fieldset>
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