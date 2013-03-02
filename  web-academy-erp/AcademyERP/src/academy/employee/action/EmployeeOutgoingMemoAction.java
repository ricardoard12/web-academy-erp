package academy.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeOutgoingMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeOutgoingMemoAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		String id = request.getParameter("id");
		String ep_memo = request.getParameter("ep_memo");
		
		request.setAttribute("id", id);
		request.setAttribute("ep_memo", ep_memo);
		
		System.out.println("Memo Action memo : " + ep_memo);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_outgoing_memo.jsp");
		return forward;
	}

}
