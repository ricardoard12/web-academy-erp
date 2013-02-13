package academy.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		StudentBean studentbean = new StudentBean();
		
		StudentDAO studentdao = new StudentDAO();
		
		List studentList = null;
		
		studentList = studentdao.studentList();
		
		request.setAttribute("studentList", studentList);
		
		forward.setRedirect(false);
		forward.setPath("./student/student_list.jsp");
		
		return forward;
	}

}
