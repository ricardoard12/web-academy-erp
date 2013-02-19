package academy.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class EmployeeAttitudeMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeMemoAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String at_memo = request.getParameter("at_memo");
		String date = request.getParameter("date");
		
		request.setAttribute("id", id);
		request.setAttribute("at_memo", at_memo);
		request.setAttribute("date", date);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_memo.jsp");
		return forward;
	}

}
