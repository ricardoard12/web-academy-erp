package academy.faq_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.faq_board.db.Faq_boardDAO;
import academy.faq_board.db.Faq_boardbean;

public class Faq_BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		Faq_boardbean faq_boardbean = new Faq_boardbean();
		Faq_boardDAO faq_boarddao = new Faq_boardDAO();
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward=new ActionForward();

		int num = (int) request.getAttribute("num");
		System.out.println("Action num값 : "+num);
				

		String password = (String) request.getAttribute("password");
		System.out.println("Action password값 : " + password);
				
		faq_boardbean = faq_boarddao.getDetail(num);
		
		request.setAttribute("num", num);
		request.setAttribute("password", password);
		request.setAttribute("faq_boardbean", faq_boardbean);
		forward.setRedirect(false);
		forward.setPath("./faq_board/faq_board_detail.jsp");
		return forward;
	}

}
