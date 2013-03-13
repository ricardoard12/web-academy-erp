
package academy.faq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.faq_board.db.Faq_boardDAO;

public class Faq_BoardDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Faq_boardDAO faq_boarddao = new Faq_boardDAO();
		ActionForward forward=new ActionForward();
		
		String id = request.getParameter("id");
		
		String[] faq_board_check = request.getParameterValues("faq_board_check");
		
		for(int i=0 ; i< faq_board_check.length ; i++){
			System.out.println(faq_board_check[i]);
		}
		
		boolean result=faq_boarddao.faq_boardDelete(faq_board_check);
		
		if(result==false){
			System.out.println("삭제실패");
		}
		System.out.println("삭제성공");
		
		forward.setRedirect(false);
		forward.setPath("./Faq_boardList.fb");
		return forward;
	}

}
