package academy.noticle.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;

public class NoticeModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		NoticeBean notice = null;
		
		NoticeDAO noticedao = new NoticeDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		notice = noticedao.getdetail(num);
		
		request.setAttribute("notice", notice);
		forward.setRedirect(false);
		forward.setPath("./notice/notice_modify.jsp");
		return forward;
	}

}
