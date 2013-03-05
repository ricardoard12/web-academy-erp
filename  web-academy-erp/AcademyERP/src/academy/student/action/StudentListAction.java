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
		
		studentList = studentdao.studentList(); //재학중인 학생을 조회한다.
		
		request.setAttribute("studentList", studentList); // 조회한 학생을 저장한다.
		
		if(studentList!=null){
			System.out.println("1");
		}
		forward.setRedirect(false);
		forward.setPath("./student/student_list.jsp"); //student_list 폼으로 이동
		
		return forward;
	}

}
