package academy.groups.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;

// 학급 학생 이동
public class GroupsMoveStudentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsMoveStudentAction");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (sid == null || sid.equals("") || level < 3) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		GroupsDAO groupsDAO = new GroupsDAO();
		
		String chkValue = request.getParameter("chkValue"); // 체크박스 값 결합시킨 문자열 가져오기
		String[] studentList = null; // 문자열 자른 후 저장할 배열
		
		if (chkValue != null && !chkValue.equals("")) {
			studentList = chkValue.split(","); // 구분자(,)를 기준으로 문자열 잘라서 배열에 삽입
		}
		
		String gp_name = request.getParameter("gp_name");
		
		boolean result = groupsDAO.groupsMoveStudent(gp_name, studentList);
		if (result == true) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학급 이동이 완료되었습니다.')");
			out.println("opener.location.reload()"); //부모창 새로고침 후 자식창 닫기
			out.println("window.close()");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학급 이동이 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
