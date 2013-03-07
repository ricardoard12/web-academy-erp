package academy.counsel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.counsel.db.CounselerBean;
import academy.counsel.db.CounselorDAO;
import academy.student.db.StudentBean;

public class CounselorModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		StudentBean student = new StudentBean();
		
		CounselorDAO counselordao = new CounselorDAO();
		
		CounselerBean counseler = new CounselerBean();
		
		int idx = Integer.parseInt(request.getParameter("idx"));
		String id =request.getParameter("id");
		
		student = counselordao.getstudentinfor(id);
		
		counseler = counselordao.getcounseler(idx,id);
		
		
		request.setAttribute("student", student);
		request.setAttribute("counselerbean", counseler);
		
		forward.setRedirect(false);
		forward.setPath("./counselor/counselor_modify.jsp");
		return forward;
	}

}
