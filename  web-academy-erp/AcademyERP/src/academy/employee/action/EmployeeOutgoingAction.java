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
			outgoingList.add(ep_id[i]); // 체크 박스 값 받아와서 리스트에 저장
		}
		
		employeeDAO.employeeOutgoing(outgoingList);
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeOutgoingListAction.em");
		return forward;
	}

}
