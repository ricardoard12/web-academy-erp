package academy.groups.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;

// 학급 학생 제거
public class GroupsDelStudentAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsDelStudentAction");
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
		
		ActionForward forward = new ActionForward();
		GroupsDAO groupsDAO = new GroupsDAO();
		List studentList = new ArrayList();
		
		String gp_name = request.getParameter("gp_name");
		String[] chkStudent = (String[])request.getParameterValues("chkStudent");
		for (int i = 0; i < chkStudent.length; i++) { // 체크박스 값 받아와서 리스트에 저장
			studentList.add(chkStudent[i]);
		}
		
		boolean result = groupsDAO.groupsDelStudent(studentList);
		if (result ==  false) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('학급 학생 제외 실패.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./GroupsAttitudeListAction.gp?gp_name=" + gp_name);
		return forward;
	}

}
