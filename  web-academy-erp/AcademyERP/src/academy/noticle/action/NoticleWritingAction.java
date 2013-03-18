package academy.noticle.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;


public class NoticleWritingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		NoticeDAO  noticedao = new NoticeDAO();
		
		NoticeBean noticebean = new NoticeBean();
		
		noticebean.setNot_title(request.getParameter("not_title"));
		noticebean.setNot_content(request.getParameter("not_content"));
		
		boolean check = noticedao.insertNotice(noticebean);
		
		if(check==false){
		System.out.println("성공");	
		}
		forward.setRedirect(true);
		forward.setPath("./NoticeList.no");
		
		return forward;
	}

}
