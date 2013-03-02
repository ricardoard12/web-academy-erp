package academy.employee.action;

import java.util.ArrayList;
import java.util.List;

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
		List outgoingList = new ArrayList();
		
		String[] ep_id = (String[])request.getParameterValues("employeeSelect");
		for (int i = 0; i < ep_id.length; i++) {
			System.out.println("EP_ID 넣기. ep_id : " + ep_id[i]);
//			employeeDAO.employeeOutgoing(ep_id[i]);
			outgoingList.add(ep_id[i]);
		}
		
		employeeDAO.employeeOutgoing(outgoingList);
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeOutgoingListAction.em");
		return forward;
	}

}
