package academy.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.employee.db.EmployeeDAO;

public class EmployeeOutgoingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeDeleteAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		EmployeeDAO employeeDAO = new EmployeeDAO();

		String[] ep_id = (String[])request.getParameterValues("employeeSelect");
		for (int i = 0; i < ep_id.length; i++) {
			employeeDAO.employeeOutgoing(ep_id[i]);
		}
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeListAction.em");
		return forward;
	}

}
