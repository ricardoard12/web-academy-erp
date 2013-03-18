<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link href="../css/default.css" rel="stylesheet" type="text/css">
<link href="../css/sitemap.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
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
 
 <!-- 사이트맵 시작 -->
<div class="cate_view_all">
	<h3><a href="#">카테고리 전체보기<span></span></a></h3>
	<div class="cate_list">
		<ul>
		<li>
			<h4><img src="http://static.naver.com/kin/09renewal/h_cate_k_qna.gif" width="48" height="12" alt="지식 Q&amp;A"></h4>
			<ul>
			<li><a href="#">컴퓨터, 통신</a></li> 
			<li><a href="#">게임 </a></li> 
			<li><a href="#">엔터테인먼트, 예술 </a></li> 
			<li><a href="#">비즈니스, 경제 쇼핑 </a></li> 
			<li><a href="#">사회, 문화 </a></li> 
			<li><a href="#">교육, 학문 </a></li> 
			</ul>
		</li>
		<li>
			<h4><img src="http://static.naver.com/kin/09renewal/h_cate_j_qna.gif" width="59" height="12" alt="쥬니버 Q&amp;A"></h4>
			<ul>
			<li><a href="#">국어</a></li> 
			<li><a href="#">수학</a></li> 
			<li><a href="#">영어</a></li> 
			<li><a href="#">과학</a></li> 
			<li><a href="#">사회</a></li> 
			<li><a href="#">역사</a></li> 
			<li><a href="#">예체능</a></li> 
			<li><a href="#">기타</a></li> 
			</ul>
		</li>
		<li>
			<h4><img src="http://static.naver.com/kin/09renewal/h_cate_l_qna.gif" width="48" height="12" alt="지역 Q&amp;A"></h4>
			<ul>	
			<li><a href="#">서울특별시</a></li> 
			<li><a href="#">부산광역시</a></li> 
			<li><a href="#">대구광역시</a></li> 
			<li><a href="#">인천광역시</a></li> 
			<li><a href="#">광주광역시</a></li> 
			<li><a href="#">강원도</a></li> 
			</ul>
		</li>
		<li class="right">
			<h4><img src="http://static.naver.com/kin/09renewal/h_cate_w_qna.gif" width="48" height="12" alt="고민 Q&amp;A"></h4>
			<ul>	
			<li><a href="#">남과 여</a></li> 
			<li><a href="#">부부클리닉</a></li> 
			<li><a href="#">1318</a></li> 
			<li><a href="#">콤플렉스</a></li> 
			<li><a href="#">직딩비애</a></li> 
			<li><a href="#">가족</a></li>  
			</ul>
		</li>
		</ul>	
	</div>
</div>
<!-- //사이트맵 끝 -->
 
 
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