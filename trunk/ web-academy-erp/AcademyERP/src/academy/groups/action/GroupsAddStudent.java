package academy.groups.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;

// 학급 학생 추가 폼 로드
public class GroupsAddStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsAddStudent");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = (Integer) session.getAttribute("level");
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
		
		studentList = groupsDAO.getAddStudentList();
		
		request.setAttribute("gp_name", gp_name);
		request.setAttribute("studentList", studentList);
		
		forward.setRedirect(false);
		forward.setPath("./groups/groups_add_student_list.jsp");
		return forward;
	}

}
