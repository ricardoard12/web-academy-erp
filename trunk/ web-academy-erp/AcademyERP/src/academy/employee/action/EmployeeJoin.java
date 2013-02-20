package academy.employee.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.employee.db.EmployeeDAO;

public class EmployeeJoin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeJoin");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List managerList = new ArrayList(); 
		
		String mm_level = request.getParameter("mm_level");
		if (mm_level == null) {
			mm_level = "3";
		}
		
		System.out.println(mm_level);
		
		managerList = employeeDAO.getManagerList(mm_level);
		
		request.setAttribute("managerList", managerList);
		request.setAttribute("level", mm_level);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_join.jsp");
		return forward;
	}

}
