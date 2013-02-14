package academy.student.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class StudentOutAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String[] st_status = request.getParameterValues("st_status");
		
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		String check = request.getParameter("check");
		
		studentdao.updatestudentOut(st_status); // DB에서 퇴학 정보를 업데이트 시킨다.
		
		//System.out.println(check);
		
		if(check.equals("1")){
			forward.setRedirect(true);
			forward.setPath("./StudentListAction.st"); //StudentListAction 으로 이동
		}else if(check.equals("2")){
			forward.setRedirect(true);
			forward.setPath("./StudentOffList.st"); //StudentListAction 으로 이동
		}
		return forward;
		
	}

}
