package academy.noticle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeDAO;

public class NoticeDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		NoticeDAO noticedao = new NoticeDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		noticedao.DeleteNotice(num);
		forward.setRedirect(false);
		forward.setPath("./NoticeList.no");
		return forward;
	}

}
