<%@page import="academy.student.db.StudentBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function allChecked(check) 
	{
	    var chkStudent = document.getElementsByName('chkStudent');
	 
	    if (check) {
	        for (var i=0; i<chkStudent.length; i++) {
	        	chkStudent[i].checked = true;
	        }
	    } else {
	        for (var i=0; i<chkItem.length; i++) {
	        	chkStudent[i].checked = false;
	        }
	    }
	}
	
	function checkForm() {
		var count = 0;
	        var chk = document.getElementsByName("chkStudent");
	        
	        for (var i = 0; i < chk.length; i++) { 
	                if (chk[i].checked == true) {
	                        count++;
	                        chkValue += chk[i].value; // 체크된 모든 아이디 문자열 결합
	                        chkValue += ","; // split 에서 구분자로 사용할 문자 삽입
	                } 
       		}
	        
	        if (count <= 0){
	                alert("선택된 항목이 없습니다.");
	                return false;
        	} else {
          	      return true;
        	}
	}

</script>
</head>
<%
	request.setCharacterEncoding("UTF-8");

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

	String gp_name = (String)request.getAttribute("gp_name");
	List studentList = (List)request.getAttribute("studentList");
%>
<body>
	<form action="./GroupsAddStudentAction.gp" method="post">
		<input type="hidden" name="gp_name"	value="<%=gp_name %>">
		<div align="center">
			<table border="1" style="width: 600px;">
				<tr><th colspan="3"><h2>학급 학생 추가</h2></th></tr>
				<tr><th width="80">선택 <input type=checkbox onclick="allChecked(this.checked)"></th><th width="200">이름(아이디)</th><th width="320">학교명(학년)</th>
				<%if (studentList.size() == 0) {%>
				<tr><td colspan="3" height="50" align="center"><h3>학급에 새로 추가할 학생이 없습니다.</h3></td></tr>
				<%} else {
					for (int i = 0; i < studentList.size(); i++) {
						StudentBean student = (StudentBean)studentList.get(i);
				%>
				<tr>
					<td align="center"><input name="chkStudent" type="checkbox" id="a1"
							class="i_check" value="<%=student.getMm_id()%>"><label for="a1"></label></td>
					<td align="center">	<%=student.getMm_name() %>(<%=student.getMm_id() %>)</td>
					<td align="center"><%=student.getSt_school_name() %>(<%=student.getSt_school_grade() %>)</td>
				</tr>
				<%
					} 
				}
				%>
				<tr>
					<td align="center" colspan="3">
						<input type="submit" value="추가" onclick="return checkForm()">
						<input type="button" value="취소" onclick="window.close()">
					</td>
				</tr>
			</table>
		</div>
	</form>
</body>
</html>