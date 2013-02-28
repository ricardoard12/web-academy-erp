package academy.groups.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsDAO;

public class GroupsAddStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsAddStudent");
		request.setCharacterEncoding("UTF-8");
		
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
