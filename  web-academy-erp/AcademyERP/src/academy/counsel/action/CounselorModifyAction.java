package academy.counsel.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.counsel.db.CounselerBean;
import academy.counsel.db.CounselorDAO;

public class CounselorModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		ActionForward  forward = new ActionForward();
		
		CounselerBean counselor=  new CounselerBean();
		
		CounselorDAO counselordao = new CounselorDAO();
		
		String mm_id = request.getParameter("mm_id");
		
		System.out.println(mm_id);
		
		// 상담내역 넘기기
		counselor.setCc_content(request.getParameter("cc_content"));
		counselor.setCc_subject(request.getParameter("cc_subject"));
		counselor.setIdx(Integer.parseInt(request.getParameter("idx")));
		
		
		System.out.println("글번호"+request.getParameter("idx"));
		
		counselor.setMm_id(mm_id);
		counselor.setEp_id(request.getParameter("ep_id"));
		
		//DB에 집에 넣기
		counselordao.setcounselorupdate(counselor);
		
		request.setAttribute("id",mm_id );
		forward.setRedirect(false);
		forward.setPath("./StudentCounsel.st");
		return forward;
	}

}
