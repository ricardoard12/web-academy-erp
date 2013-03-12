package academy.faq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log.db.BusinessBean;
import academy.business_log.db.BusinessDAO;
import academy.faq_board.db.Faq_boardDAO;
import academy.faq_board.db.Faq_boardbean;
import academy.lesson_plan.action.Action;
import academy.lesson_plan.action.ActionForward;

public class Faq_BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		Faq_boardbean faq_boardbean = new Faq_boardbean();
		Faq_boardDAO faq_boarddao = new Faq_boardDAO();
		ActionForward forward = new ActionForward();
		
		try{
			faq_boardbean.setFaq_num(Integer.parseInt(request.getParameter("faq_num")));
			faq_boardbean.setFaq_name(request.getParameter("faq_name"));
			faq_boardbean.setFaq_content(request.getParameter("faq_content"));
			faq_boardbean.setFaq_subject(request.getParameter("faq_subject"));
			
			faq_boarddao.faqboardinsert(faq_boardbean);
			forward.setRedirect(true);
			forward.setPath("./Faq_boardList.fb");
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		
		return forward;
	}

	
}
