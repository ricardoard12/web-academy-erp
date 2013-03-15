<%@page import="academy.member.db.MemberBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	                                	  if (inputByte <= 78) {
	                                		  inputByte += 2;
	                                		  tmp = $(this).val();
	                                	  } else {
	                                		  alert("80Byte를 초과하셨습니다.");
	                                		  $('#inputMessage').html(tmp);
	                                	  }
	                                  } else {
	                                	  if (inputByte <= 79) {
	                                		  inputByte++;
	                                		  tmp = $(this).val();
	                                	  } else {
	                                		  alert("80Byte를 초과하셨습니다.");
	                                		  $('#inputMessage').html(tmp);
	                                	  }
	                                	  
	                                  }
	                          }
	                   }
			
			$('#smsByteCount').html(inputByte + " / 80 Byte");
		});
	});
</script>
<title>Insert title here</title>
<%
	request.setCharacterEncoding("UTF-8");
	List receiverList = (List) request.getAttribute("receiverList");
	String receiverID = "";
	String receiverName = "";
	String receiverPhone = "";
	int credits = (Integer) request.getAttribute("credits");
%>
</head>
<body bgcolor="#FFFFFF" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<!-- ImageReady Slices (Untitled-4) -->
	<table id="Table_01" width="400" height="601" border="0"
		cellpadding="0" cellspacing="0">
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_01.gif"
				width="400" height="75" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_02.gif"
				width="400" height="5" alt="" border="0"></td>
		</tr>
		<tr>
			<td><img src="./sms/images/smsForm_03.gif" width="56"
				height="25" alt="" border="0"></td>
			<td colspan="6"><img src="./sms/images/smsForm_04.gif"
				width="194" height="25" alt="" border="0"></td>
			<td colspan="3" width="25"><%=credits %>건</td>
			<td><img src="./sms/images/smsForm_06.gif" width="14"
				height="25" alt="" border="0"></td>
			<td><img src="./sms/images/smsForm_07.gif" width="56"
				height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_08.gif"
				width="400" height="5" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_09.gif"
				width="400" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_10.gif"
				width="96" height="134" alt="" border="0"></td>
			<td colspan="7">
				<table id="Table_01" width="208" height="134" border="0"
					cellpadding="0" cellspacing="0">
					<tr>
						<td width="208" height="100" align="center" id="inputMessage">
						<textarea	cols="26" rows="6" name="message"></textarea></td>
					</tr>
					<tr>
						<td width="208" height="34" align="right" id="smsByteCount">0 / 80 Byte</td>
					</tr>

				</table>
			</td>
			<td colspan="3"><img src="./sms/images/smsForm_12.gif"
				width="96" height="134" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_13.gif"
				width="400" height="11" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_14.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="7"><img src="./sms/images/smsForm_15.gif"
				width="208" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_16.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="3"><img src="./sms/images/smsForm_17.gif"
				width="132" height="25" alt="" border="0"></td>
			<td colspan="5">0518000000</td>
			<td><img src="./sms/images/smsForm_19.gif" width="36"
				height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_20.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_21.gif"
				width="400" height="15" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_22.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="2"><img src="./sms/images/smsForm_23.gif"
				width="79" height="25" alt="" border="0"></td>
			<td colspan="5"><img src="./sms/images/smsForm_24.gif"
				width="129" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_25.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_26.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="7" rowspan="5">
			<div id="receiverList" style="overflow: auto;  width:208; height:125">
				<table id="Table_01" width="208" height="125" border="0"
					cellpadding="0" cellspacing="0">
					<%
					int blank = 5 - receiverList.size();
					for (int i = 0; i < receiverList.size(); i++) {
							MemberBean member = (MemberBean)receiverList.get(i);
							String phone = member.getMm_phone().split("-")[0] + member.getMm_phone().split("-")[1] + member.getMm_phone().split("-")[2];
							receiverID += member.getMm_id() + ",";
							receiverName += member.getMm_name() + ",";
							receiverPhone += phone + ",";
							%>
							<tr>
								<td width="80" height="25" align="center"><%=member.getMm_name() %></td>
								<td width="128" height="25" align="center"><%=member.getMm_phone() %></td>
							</tr>
					<%
					} 
					if (blank > 0) {
						for (int i = 0; i < blank; i++) {
					%>
					
							<tr>
								<td width="80" height="25" ></td>
								<td width="128" height="25" ></td>
							</tr>
							<%
						}
					}
					%>
<!-- 					<tr> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 					</tr> -->
<!-- 					<tr> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 						<td>&nbsp;</td> -->
<!-- 					</tr> -->
				</table>
				</div>
			</td>
			<td colspan="3"><img src="./sms/images/smsForm_28.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_29.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_30.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_31.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_32.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_33.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_34.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="2"><img src="./sms/images/smsForm_35.gif"
				width="96" height="25" alt="" border="0"></td>
			<td colspan="3"><img src="./sms/images/smsForm_36.gif"
				width="96" height="25" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_37.gif"
				width="400" height="30" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_38.gif"
				width="400" height="9" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="3"><img src="./sms/images/smsForm_39.gif"
				width="132" height="32" alt="" border="0"></td>
			<td colspan="2"><a href="#" onclick="return submit()"><img src="./sms/images/smsForm_40.gif"
				width="68" height="32" alt="" border="0"></a></td>
			<td><img src="./sms/images/smsForm_41.gif" width="2" height="32"
				alt="" border="0"></td>
			<td colspan="2"><a href="#" onclick="window.close()"><img src="./sms/images/smsForm_42.gif"
				width="66" height="32" alt="" border="0"></a></td>
			<td colspan="4"><img src="./sms/images/smsForm_43.gif"
				width="132" height="32" alt="" border="0"></td>
		</tr>
		<tr>
			<td colspan="12"><img src="./sms/images/smsForm_44.gif"
				width="400" height="34" alt="" border="0"></td>
		</tr>
		<tr>
			<td><img src="./sms/images/spacer.gif" width="56" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="40" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="36" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="43" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="25" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="2" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="48" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="18" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="36" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="26" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="14" height="1"
				alt="" border="0"></td>
			<td><img src="./sms/images/spacer.gif" width="56" height="1"
				alt="" border="0"></td>
		</tr>
	</table>
	<!-- End ImageReady Slices -->
</body>
</html>