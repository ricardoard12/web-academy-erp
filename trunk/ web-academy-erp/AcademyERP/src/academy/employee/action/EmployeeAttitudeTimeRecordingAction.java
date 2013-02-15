package academy.employee.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;
import academy.employee.db.EmployeeDAO;

public class EmployeeAttitudeTimeRecordingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeTimeRecordingAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		attitudeDAO.employeeAttitudeTimeRecording(id, type);
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeAttitudeListAction.em");
		return forward;
	}

}
