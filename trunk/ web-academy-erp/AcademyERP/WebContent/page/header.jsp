<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>

</head>
<body>
<!-- Horizonal navigation -->
<div id="menu" class="menu mc_gray">
	<div class="inset">
		<div class="major">
		<!-- class="major + (m1~m12)"-->
			<ul>
			<li class="m1"><a href="#"><span>HOME</span></a>
			<li class="m2"><a href="#"><span>학원소개</span></a>
				<div class="sub">
					<ul>
					<li><a href="#"><span>강사소개</span></a></li>
					<li><a href="#"><span>찾아오시는 길</span></a></li>
					<li><a href="#"><span>학원소개</span></a></li>
					</ul>
				</div>
			</li>
			<li class="m3"><a href="#"><span>커리큘럼</span></a>
				<div class="sub">
					<ul>
					<li><a href="#"><span>과목소개</span></a></li>
					</ul>
				</div>
			</li>
			
			<li class="m4"><a href="#"><span>커뮤니티</span><!-- <span class="i"></span> --></a>
				<div class="sub">
					<ul>
					<li><a href="#"><span>공지사항</span></a></li>
					<li><a href="#"><span>Q&A</span></a></li>
					</ul>
				</div>
			</li>
			
			
			</ul>
		</div>
		<div class="aside">
			<ul>
			<li class="m1"><a href="../sitemap/sitemap.jsp"><span>site map</span><span class="i"></span></a>
			<li class="m2"><a href="#"><span>만세</span><span class="i"></span></a>
				<!-- <div class="sub">
					<ul>
					<li><a href="#"><span>만세</span></a></li>
					</ul>
				</div> -->
			</li>
			</ul>
		</div>
		<span class="gradient"></span>
	</div>
	<span class="shadow"></span>
</div>
<!-- //Horizonal navigation -->

<script type="text/javascript">
jQuery(function($){
	
	// Menu
	var menu = $('div.menu');
	var major = $('div.major');
	var li_list = major.find('>ul>li');
	
	// Selected
	function onselectmenu(){
		var myclass = [];
		
		$(this).parent('li').each(function(){
			myclass.push( $(this).attr('class') );
		});
		
		myclass = myclass.join(' ');
		if (!major.hasClass(myclass)) major.attr('class','major').addClass(myclass);
	}
	
	// Show Menu
	function show_menu(){
		t = $(this);
		li_list.removeClass('active');
		t.parent('li').addClass('active');
		// IE7 or IE7 documentMode bug fix
		if($.browser.msie) {
			var v = document.documentMode || parseInt($.browser.version);
			if (v == 7) {
				var subWidth = t.next('div.sub').eq(-1).width();
				t.next('div.sub').css('width',subWidth);
			}
		}
	}
	li_list.find('>a').click(onselectmenu).mouseover(show_menu).focus(show_menu);
	
	// Hide Menu
	function hide_menu(){
		li_list.removeClass('active');
	}
	menu.mouseleave(hide_menu);
	li_list.find('div.sub>ul').mouseleave(hide_menu);
	
	//icon
	major.find('div.sub').prev('a').find('>span').append('<span class="i"></span>');
	
	// Aside
	var aside_li = $('.menu>.inset>.aside>ul>li');
	var aside_a = $('.menu>.inset>.aside>ul>li>a');

	// Show Aside
	function show_aside(){
		li_list.removeClass('active');
		aside_li.removeClass('active');
		$(this).parent('li').addClass('active');

		// IE7 or IE7 documentMode bug fix
		if($.browser.msie) {
			var v = document.documentMode || parseInt($.browser.version);

			if (v == 7) {
				var sub = $(this).next('div.sub').eq(-1);
				sub.css('width', '').css('width', sub.width()+'px');
			}
		}
	}	
	aside_a.mouseover(show_aside).focus(show_aside);
	
	// Hide Aside
	function hide_aside(){
		aside_li.removeClass('active');
	}	
	menu.mouseleave(hide_aside);
	aside_li.find('div.sub>ul').mouseleave(hide_aside);

	// Hide Menu & Aside
	$('*:not(".menu *")').focus(hide_menu).focus(hide_aside);
	
});
</script>

</body>
</html>