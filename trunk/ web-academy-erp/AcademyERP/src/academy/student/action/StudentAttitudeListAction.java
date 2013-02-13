package academy.student.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class StudentAttitudeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		StudentDAO studentdao = new StudentDAO();
		
		List StudentAttitudeList = null;
		StudentAttitudeList = studentdao.getStudentAttitudeList();
		
		request.setAttribute("attitudeList", StudentAttitudeList);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_attitude_list.jsp");
		return forward;
	}

}
