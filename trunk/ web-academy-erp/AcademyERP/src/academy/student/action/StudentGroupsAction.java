package academy.student.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class StudentGroupsAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		List  groups = new ArrayList();
		
		StudentDAO studentDAO = new StudentDAO();
		
		groups = studentDAO.getstudentgroups();
		
		request.setAttribute("groups", groups);
		forward.setRedirect(false);
		forward.setPath("./StudentJoin.st");
		return forward;
	}

}
