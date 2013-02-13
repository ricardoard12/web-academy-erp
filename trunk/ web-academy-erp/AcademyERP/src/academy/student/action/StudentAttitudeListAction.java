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
		StudentAttitudeList = studentdao.getStudentAttitudeList();  //DB에서 출석 관련정보를 가져온다.
		
		request.setAttribute("StudentAttitudeList", StudentAttitudeList); // 가지고온 StudentAttitudeList 정보를 넘긴다.
		
		forward.setRedirect(false);
		forward.setPath("./student/student_attitude_list.jsp");// student_attitude_list 폼으로 이동
		return forward;
	}

}
