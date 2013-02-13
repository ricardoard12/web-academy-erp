package academy.employee.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.employee.db.EmployeeDAO;

public class EmployeeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeListAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Vector vector = new Vector();
		List employeeList = new ArrayList();
		List memberList = new ArrayList();
		
		vector = employeeDAO.getEmployeeList();
		employeeList = (List)vector.get(0);
		memberList = (List)vector.get(1);
		
		request.setAttribute("employeeList", employeeList);
		request.setAttribute("memberList", memberList);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_list.jsp");
		return forward;
	}

}
