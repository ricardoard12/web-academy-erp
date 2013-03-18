package academy.noticle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;

public class NoticeDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num=0;
		ActionForward forward = new ActionForward();
		NoticeBean notice = null;
		NoticeDAO noticedao = new NoticeDAO();
		
		if( request.getParameter("num")!= null){
			num = Integer.parseInt(request.getParameter("num"));
		}else{
			num=(int)request.getAttribute("num");
		}
		
		noticedao.setcount(num);
		
		notice=noticedao.getdetail(num);
		
		request.setAttribute("notice", notice);
		forward.setRedirect(false);
		forward.setPath("./notice/notice_detail.jsp");
		return forward;
	}

}
