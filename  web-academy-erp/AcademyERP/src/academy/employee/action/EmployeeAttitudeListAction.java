package academy.employee.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;
import academy.employee.db.EmployeeDAO;

public class EmployeeAttitudeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 직원 출결 현황
		System.out.println("EmployeeAttitudeListAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeBean attitude = new AttitudeBean();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		List attitudeList = new ArrayList();
		attitudeList = attitudeDAO.getEmployeeAttitudeList();
		
		request.setAttribute("attitudeList", attitudeList);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_attitude_list.jsp");
		return forward;
	}
	
}
