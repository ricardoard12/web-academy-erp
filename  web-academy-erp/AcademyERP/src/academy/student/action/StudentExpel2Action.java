package academy.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class StudentExpel2Action implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String[] st_status = request.getParameterValues("st_status");
		
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		studentdao.updateStatusExpel(st_status); // DB에 퇴출 정보를 업데이트한다.
		
		forward.setRedirect(true);
		forward.setPath("./StudentLeaveofabsenceList.st");//StudentLeaveofabsenceList로 이동
		
		return forward;
	}

}
