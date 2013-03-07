<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	String day = request.getParameter("day");
	String lesson = request.getParameter("lesson");
	List sublist = (List) request.getAttribute("sub_list");
	List ep_list = (List) request.getAttribute("ep_list");
	String sub_name = (String) request.getAttribute("sub_name");
	String gp_idx = request.getParameter("gp_idx");
%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>과목 삽입</title>
<script>
	function subChange(value) {	
			sub_name = value.split(",")[1];
			location.href = "./InsertSubject.time?day=" +
<%=day%>
	+ "&lesson="
					+
<%=lesson%>
	+ "&sub_name=" + sub_name + "&gp_idx="
					+
<%=gp_idx%>
	;
		}
	
	function check() {
		ep_id = document.sub_form.ep_id.value;
		if (ep_id == "") {
			alert();
			return false;
		} else {
			document.sub_form.action = "./InsertTimeTable.time";
			//opener.location.reload();
			//window.close();
			return true;
		}
	}
</script>
</head>
<body>
	<form action="" method="post" name="sub_form" onsubmit="return check()">
		<input type="hidden" name="day" value="<%=day%>"><input
			type="hidden" name="lesson" value="<%=lesson%>"><input
			type="hidden" name="gp_idx" value="<%=gp_idx%>"> <input
			type="hidden" name="sub_name" value="<%=sub_name%>">
		<table>
			<tr>
				<td><select onchange="subChange(value)"><option>원하는
							과목을 선택하세요.</option>
						<%
							for (int i = 0; i < sublist.size(); i++) {
								List subject = (List) sublist.get(i);
						%>
						<option value="<%=subject.get(0)%>,<%=subject.get(1)%>"
							<%if (sub_name.equals(subject.get(1).toString())) {%> selected
							<%}%>><%=subject.get(1)%>:code(<%=subject.get(2)%>)
						</option>
						<%
							}
						%>
				</select></td>
				<td><select name="ep_id">
						<option>담당 선생을 선택하세요.</option>
						<%
							for (int j = 0; j < ep_list.size(); j++) {
								List teacher = (List) ep_list.get(j);
						%>
						<option value="<%=teacher.get(0)%>"><%=teacher.get(2)%>:<%=teacher.get(1)%></option>
						<%
							}
						%>
				</select></td>
				<td><input type="submit" value="넣기~"><input
					type="button" value="창닫기" onclick="window.close()"></td>

			</tr>
		</table>
	</form>
</body>
</html>