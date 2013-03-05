package academy.counsel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.counsel.db.CounselerBean;
import academy.counsel.db.CounselorDAO;
import academy.student.db.StudentBean;

public class CounselDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		CounselorDAO counselordao = new CounselorDAO();
		
		CounselerBean counselerbean = new CounselerBean();
		StudentBean student = new StudentBean();

		int idx = Integer.parseInt(request.getParameter("idx"));
		String id =request.getParameter("id");
		
		student = counselordao.getstudentinfor(id);

		counselerbean=counselordao.getcounseler(idx,id);
		
		request.setAttribute("student", student);
		request.setAttribute("counselerbean", counselerbean);
		
		forward.setRedirect(false);
		forward.setPath("./counselor/counselor_detail.jsp");
		return forward;
	}

}
