package academy.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		StudentBean studentbean = null;
		
		
		String id  = request.getParameter("id");  // 아이디값 저정
		
		studentbean=  studentdao.getStudentDetail(id); // DB에서 조회
		
		request.setAttribute("studentbean", studentbean);
		
		forward.setRedirect(false);
		forward.setPath("./student/student_detail.jsp");
		return forward;
	}

}
