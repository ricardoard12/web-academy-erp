package academy.counsel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.counsel.db.CounselorDAO;
import academy.student.db.StudentBean;

public class CounselAdd implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		String mm_id= request.getParameter("mm_id");
		StudentBean student = new StudentBean(); //학생 기본 정보를 가지고 오기 위해서 선언
		
		CounselorDAO counselordao = new CounselorDAO(); // DB사용을 위해 선언
		
		student = counselordao.getstudentinfor(mm_id);
		
		
		
		return null;
	}

}
