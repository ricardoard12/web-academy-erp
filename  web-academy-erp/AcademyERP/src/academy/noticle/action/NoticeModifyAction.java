package academy.noticle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;

public class NoticeModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		NoticeBean  notice = new NoticeBean();
		
		NoticeDAO noticedao = new NoticeDAO();
		
		notice.setNot_content(request.getParameter("not_content"));
		notice.setNot_num(Integer.parseInt(request.getParameter("num")));
		notice.setNot_title(request.getParameter("not_title"));
		
		noticedao.setNotice(notice);
		request.setAttribute("num",Integer.parseInt(request.getParameter("num")));
		forward.setRedirect(false);
		forward.setPath("./NoticeDetail.no");
		return forward;
	}

}
