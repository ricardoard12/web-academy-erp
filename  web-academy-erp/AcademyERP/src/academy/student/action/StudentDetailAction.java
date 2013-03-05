package academy.student.action;

import java.util.List;

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
		
		String check=request.getParameter("check"); //수강생(1),휴원생(2),퇴원생(3)에서의 목록인지 판단  
		
		String id  = request.getParameter("id");  // 아이디값 저정
		
		studentbean=  studentdao.getStudentDetail(id); // DB에서 조회
		List  groups = studentdao.getstudentgroups();
		
		request.setAttribute("studentbean", studentbean);
		request.setAttribute("groups", groups);//소속학급 수정시 전체 소속학급 조회
		request.setAttribute("check", check);//수강생(1),휴원생(2),퇴원생(3)에서의 목록인지 판단후 저장된값 전송  
		
		forward.setRedirect(false);
		forward.setPath("./student/student_detail.jsp");
		return forward;
	}

}
