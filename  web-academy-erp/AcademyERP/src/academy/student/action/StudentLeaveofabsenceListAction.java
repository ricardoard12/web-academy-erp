package academy.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentLeaveofabsenceListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 휴학중인 학생 조회
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		List<StudentBean> LeaveofabsenceList  = null;
		LeaveofabsenceList =  studentdao.getLeaveofabsenceList();// 휴학생 조회해서 lsit 넣기
		
		request.setAttribute("LeaveofabsenceList", LeaveofabsenceList); // LeaveofabsenceList정보를 저장한다.
		forward.setRedirect(false);
		forward.setPath("./student/student_leaveofabsencelist.jsp");//student_leaveofabsencelist폼으로 이동
		
		return forward;
	}

}
