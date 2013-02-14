package academy.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class StudentOffAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		String[] st_status = request.getParameterValues("st_status");
		
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		studentdao.updateStudentOff(st_status); // 휴학생으로 업데이트 시킨다.
		
	
		forward.setRedirect(true);
		forward.setPath("./StudentListAction.st"); //StudentListAction으로 이동
		
		return forward;
		
	}

}
