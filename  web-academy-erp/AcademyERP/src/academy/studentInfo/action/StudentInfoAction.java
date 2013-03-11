package academy.studentInfo.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;
import academy.studentInfo.db.StudentInfoDAO;

public class StudentInfoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		StudentDAO studentdao = new StudentDAO();
		StudentBean studentbean = null;
		
		String id = request.getParameter("id");
//		String check = request.getParameter("check");
//		if(check == null){
//			check = "1";
//		}
		studentbean = studentdao.getStudentDetail(id);
		List groups = studentdao.getstudentgroups();
		
		request.setAttribute("studentbean", studentbean);
		request.setAttribute("groups", groups);
//		request.setAttribute("check", check);
		
		forward.setRedirect(false);
		forward.setPath("./studentInfo/student_informaion.jsp");
		return null;
	}

}
