package academy.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class StudentAttitudeMemoAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("EmployeeAttitudeMemoAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String at_memo = request.getParameter("at_memo");
		
		request.setAttribute("id", id);
		request.setAttribute("at_memo", at_memo);
		
		forward.setRedirect(false);
		forward.setPath("./student/student_memo.jsp");
		return forward;
	}

}
