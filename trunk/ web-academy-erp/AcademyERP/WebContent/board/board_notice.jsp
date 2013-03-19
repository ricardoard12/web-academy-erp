<%@page import="academy.master.db.ListPackage"%>
<%@page import="academy.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@page import="academy.board.db.BoardBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	ListPackage pack = (ListPackage) request.getAttribute("listpack");
	List boardList = pack.getClasslist();
	int listcount = pack.getListcount();
	int nowpage = pack.getPage();
	int maxpage = pack.getMaxpage();
	int startpage = pack.getStartpage();
	int endpage = pack.getEndpage();
	String gid = pack.getGid();
	List noticeList = (List) request.getAttribute("noticelist");
	String level = (String) session.getAttribute("level");
	int lev = 0;
	if (level != null) {
		lev = Integer.parseInt(level);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<title>상세정보</title>
</head>
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

				<!-- 게시판 시작 -->
				<!-- UI Object -->
				<form method="post" action="./BoardDeleteAction.bo">
					<input type="hidden" name="gid" value="<%=gid%>">
					<table cellspacing="0" border="1" summary="게시판의 글제목 리스트"
						class="tbl_type_notice">
						<!-- level세션값 전달 -->
						<input type="hidden" name="level"
							value="<%=session.getAttribute("level")%>">

						<caption>게시판 리스트</caption>
						<colgroup>
							<%
								if (lev >= 4) {
							%>
							<col width="30">
							<%
								}
							%>
							<col width="80">
							<col>
							<col width="115">
							<col width="85">
							<col width="60">
						</colgroup>
						<thead>
							<tr>
								<%
									if (lev >= 4) {
								%>
								<th scope="col">&nbsp;</th>
								<%} %>
								<th scope="col">No</th>
								<th scope="col">제목</th>
								<th scope="col">글쓴이</th>
								<th scope="col">날짜</th>
								<th scope="col">조회수</th>
							</tr>
						</thead>

						<tbody>
							<%
								if (noticeList.size() > 0) {
									for (int i = 0; i < noticeList.size(); i++) {
										BoardBean boardbean = (BoardBean) noticeList.get(i);
							%>
							<!-- notice sector -->
							<tr style="background: #F6F6F6">
								<%if(lev>=4){ %>
								<td class="frm"><input type="checkbox" name="board_check"
									id="chk_sel" value="<%=boardbean.getBoard_num()%>"><label
									for="chk_sel">선택</label></td>
								<%} %>
								<td class="num"><%=boardbean.getBoard_num()%></td>
								<td class="title"><img src="./img/notice_icon.gif"
									width="15" height="15" /> <a
									href="./BoardDetailAction.bo?num=<%=boardbean.getBoard_num()%>&?gid=<%=gid%>'"><%=boardbean.getBoard_subject()%></a></td>
								<td><a href="#"><%=boardbean.getBoard_name()%></a></td>
								<td class="date"><%=boardbean.getBoard_date()%></td>
								<td class="hit"><%=boardbean.getBoard_readcount()%></td>
							</tr>
							<!-- notice sector -->

							<%
								}
								}

								if (listcount > 0) {
									for (int i = 0; i < boardList.size(); i++) {
										BoardBean boardbean = (BoardBean) boardList.get(i);
							%>
							<tr>
								<%
									if (lev >= 4) {
								%>
								<td class="frm"><input type="checkbox" name="board_check"
									id="chk_sel" value="<%=boardbean.getBoard_num()%>"><label
									for="chk_sel">선택</label></td>
								<%
									}
								%>
								<td class="num"><%=boardbean.getBoard_num()%></td>
								<td class="title"><a
									href="./BoardDetailAction.bo?num=<%=boardbean.getBoard_num()%>&?gid=<%=gid%>'"><%=boardbean.getBoard_subject()%></a></td>
								<td><a href="#"><%=boardbean.getBoard_name()%></a></td>
								<td class="date"><%=boardbean.getBoard_date()%></td>
								<td class="hit"><%=boardbean.getBoard_readcount()%></td>
							</tr>


							<%
								}
								}
							%>



							<!-- <tr class="reply"> -->
							<!-- <td class="frm"><input type="checkbox" name="" id="chk_sel2" value=""><label for="chk_sel2">선택</label></td> -->
							<!-- <td class="num">&nbsp;</td> -->
							<!-- <td class="title" style="padding-left:10px;"><a href="#">블로그 개편 답글</a></td> -->
							<!-- <td><a href="#">네이버맨</a></td> -->
							<!-- <td class="date">2008/02/14</td> -->
							<!-- <td class="hit">1234</td> -->
							<!-- </tr> -->

						</tbody>
					</table>
					<!-- level에 따라보이게하기 -->
					<!-- 공지게시판이므로 level 5만 적용 -->
					<!-- 로그인시 적용되나, 비로그인시 적용이 불가 시작점부터 접근금지 -->
					<div align="right">
						<%-- <% if(level.equals("5")){ %> --%>
						<input type="button" name="board_write" value="글쓰기"
							onclick="location.href='./BoardWrite.bo?gid=<%=gid%>'"> <input
							type="submit" name="board_delete" value="삭제">
				</form>
			</div>
			<%-- <%}else{}%> --%>

			<!-- Paginate -->


			<div class="paginate_complex" align="center">
				<%
					if (nowpage <= 1) {
				%>
				<a href="#" class="direction prev"><span></span><span></span>처음</a>
				<%
					} else {
				%>
				<a href="./BoardNotice.bo?page=<%=nowpage - 1%>&?gid=<%=gid%>'"
					class="direction prev"><span></span>이전</a>
				<%
					}
				%>
				<%
					for (int a = startpage; a <= endpage; a++) {
						if (a == nowpage) {
				%><strong><%=a%></strong>&nbsp;<%
					} else {
				%><a href="./BoardNotice.bo?page=<%=a%>&?gid=<%=gid%>'"><%=a%></a>
				<%
					}
					}
				%>
				<%
					if (nowpage >= maxpage) {
				%><a href="#" class="direction next">끝<span></span><span></span></a>
				<%
					} else {
				%><a href="./BoardNotice.bo?page=<%=nowpage + 1%>&?gid=<%=gid%>'"
					class="direction next">다음 </a>
				<%
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