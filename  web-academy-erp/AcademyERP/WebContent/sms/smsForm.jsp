<%@page import="java.text.SimpleDateFormat"%>
<%@page import="academy.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>SMS Send</title>
<link href="./css/default.css" rel="stylesheet" type="text/css">
<link href="./css/board.css" rel="stylesheet" type="text/css">
<link href="./css/demos.css" rel="stylesheet">
<link href="./css/iPhoneDesign.css" rel="stylesheet">

<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.1/themes/base/jquery-ui.css" />
<script src="./js/jquery-1.9.1.js"></script>
<script src="./js/jquery-ui.custom.js"></script>
<style>
.content_area{
min-height: 380px;
}
</style>
<script src="js/jquery-1.9.1.js"></script>
<script type="text/javascript">
// keydown 키 눌러질 때 발생
// keypress 글자 입력될 때(영어) keyup 키 뗄 때
	$(document).ready(function() {
		// 대상 textarea keyup()
		// 글자수 변수 inputLength this.val().length
		// 남은 글자 변수 remain
		$('textarea').keyup(function() {
			var str = new String($(this).val());
			var tmp = new String;
      			var inputByte = 0;
	                   if(str.length != 0)
	                   {
	                          for (var i=0; i < str.length; i++) 
	                          {
	                                  var str2 = str.charAt(i);
	                                  if (escape(str2).length > 4) {
                                		  inputByte += 2;
	                                	  if (inputByte > 80) {
	                                		 if (event.keyCode != '8') //백스페이스는 지우기작업시 바이트 체크하지 않기 위해서
	                                	        {
								alert("최대 80Byte 까지만 전송 가능합니다.");
// 		                                		tmp = str.substring(0, str.length - 1);
// 		                                		$('#inputMessage').html(tmp);
// 		                                		$('#inputMessage').replaceWith('<textarea rows="5" cols="45" id="inputMessage" placeholder="여기에 메세지를 입력하세요." style="font-size: 1.2em;padding: 5px 20px 5px 20px;">' + tmp + '</textarea>'); 
	                                	        }
	                                	  }
	                                  } else {
                                		  inputByte++;
	                                	  if (inputByte > 80) {
	                                		 if (event.keyCode != '8') //백스페이스는 지우기작업시 바이트 체크하지 않기 위해서
	                                	        {
								alert("최대 80Byte 까지만 전송 가능합니다.");
// 								alert(str.length);
// 		                                		tmp = str.substring(0, str.length - 1);
// 		                                		$('#inputMessage').html(tmp);
// 		                                		$('#inputMessage').replaceWith(tmp); 
// 		                                		$('#inputMessage').replaceWith('<textarea rows="5" cols="45" id="inputMessage" placeholder="여기에 메세지를 입력하세요." style="font-size: 1.2em;padding: 5px 20px 5px 20px;">' + tmp + '</textarea>');
	                                	        }
	                                	  }
	                                  }
	                          }
	                   }
			
			$('#smsByteCount').html(inputByte + " / 80 Byte");
		});
	});
</script>
<%
	/* 권한 확인 */
	String sid = (String)session.getAttribute("id");
	int level = Integer.parseInt((String) session.getAttribute("level"));
	if (sid == null || sid.equals("") || level < 3) {
		%>
		<script>
			alert("권한이 없습니다.");
			history.back();
		</script>
		<%
	}

	request.setCharacterEncoding("UTF-8");
	List receiverList = (List) request.getAttribute("receiverList");
	String receiverID = "";
	String receiverName = "";
	String receiverPhone = "";
	int credits = (Integer) request.getAttribute("credits");
	SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
%>
</head>
<body>
<div class="wrapper">
  <div class="callout">
    <div class="perimeter">
      <div class="power_button"></div>
      <div class="silent"></div>
      <div class="volume_up"></div>	
      <div class="volume_down"></div>	
      <div class="device">
        <div class="highlight">
          <div class="speaker"><span><p>&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;</p><p>&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;</p><p>&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;</p><p>&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;</p><p>&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;&middot;</p></span></div>
           <div class="face">	
<!--             <div class="face_glare"></div> -->
	          <div class="display">
                <div class="top_bar">
                  <div class="battery"><div class="body"><span></span></div><div class="contact"></div></div>
                  <div class="signal"><div class="one"></div><div class="two"></div><div class="three"></div><div class="four"></div><div class="five"></div></div>
                  <p class="carrier">SMS<span>3G</span></p>
                  <p class="time"><%=sdf.format(System.currentTimeMillis()) %></p>
                </div>
                <form action="./SendSmsAction.sms" method="post">
                <div class="application_title">
                  <h5>SMS Send</h5>
                  <a href="#" onclick="return submit()"><span class="button left">전송</span></a>
                  <a href="#" onclick="window.close()"><span class="button right">취소</span></a>
                </div>
                <div class="content_area">
                	<div><p></p></div>
                	<div style="color: #f5f6f7; margin: 0px 20px 0px 20px; border-collapse: collapse;" id="credits" align="center">
                		<table border="0">
                			<tr>
	                			<td width="200"><h3>전송 가능한 메세지 건 수 : </h3></td>
	                			<td width="40" align="right"><h3><%=credits %>건</h3></td>
                			</tr>
                		</table>
			</div>
			<div align="center" id="message" style="color: #f5f6f7">
                		<table border="0"  style="color: Black; margin: 10px 20px 0px 20px;background-color: #f5f6f7">	
                			<tr>
                				<td width="200"><textarea rows="5" cols="45" name="message" id="inputMessage" placeholder="여기에 메세지를 입력하세요." style="font-size: 1.2em;padding: 5px 20px 5px 20px;"></textarea></td>
       					</tr>
                			<tr><td width="200" id="smsByteCount" align="right" style="font-size: 1.2em;">0 / 80 Byte</td></tr>
                		</table>
			</div> 
			<div align="center" id="sendInfo" style="color: #f5f6f7">
				<table border="0"  style="color: Black; margin: 0px 20px 0px 20px;background-color: #f5f6f7;font-size: 1.2em">	
                			<tr>
                				<td width="120" align="center"><h3>발신번호</h3></td>
       					</tr>
                			<tr><td width="120" align="center"><input type="text" name="senderPhone" value="0518000000" size="20" style="font-size: 1.2em"></td></tr>
                		</table>
			</div>
			<div align="left"style="color: #f5f6f7;width: 318;">
				<table border="1"  style="color: Black; margin: 10px 20px 0px 20px;background-color: #f5f6f7;font-size: 1.2em">
                			<tr>
                				<td width="126" align="center"><h3>수신자</h3></td><td width="160" align="center"><h3>수신번호</h3></td>
       					</tr>
       				</table>
       				</div>
       				<div align="center" id="receiverList" style="color: #f5f6f7;overflow:scroll; overflow-x:hidden; width:315; height:140;">
       				<table  border="1"  style="color: Black; margin: 0px 0px 20px 20px;background-color: #f5f6f7;font-size: 1.2em">
       					<%
					int blank = 5 - receiverList.size();
					for (int i = 0; i < receiverList.size(); i++) {
							MemberBean member = (MemberBean)receiverList.get(i);
							String phone = member.getMm_phone().split("-")[0] + member.getMm_phone().split("-")[1] + member.getMm_phone().split("-")[2];
							receiverID += member.getMm_id() + ",";
							receiverName += member.getMm_name() + ",";
							receiverPhone += phone + ",";
					%>
							<tr bgcolor="WHITE">
								<td width="155" height="25" align="center"><h3><%=member.getMm_name() %></h3></td>
								<td width="190" height="25" align="center"><h3><%=member.getMm_phone() %></h3></td>
							</tr>
					<%
					} 
					if (blank > 0) {
						for (int i = 0; i < blank; i++) {
					%>
					
							<tr bgcolor="WHITE">
								<td width="150" height="25" ></td>
								<td width="170" height="25" ></td>
							</tr>
							<%
						}
					}
					%>
				</table>
	        		<input type="hidden" name="receiverID" value="<%=receiverID %>">
			        <input type="hidden" name="receiverName" value="<%=receiverName %>">
			        <input type="hidden" name="receiverPhone" value="<%=receiverPhone %>">
			</div>
                </div>
              <div class="home_button"><div></div></div>
              </form>
            </div>
          </div>
        </div>
      </div>
    </div><!--callout-->
    
  </div>
</body>
</html>