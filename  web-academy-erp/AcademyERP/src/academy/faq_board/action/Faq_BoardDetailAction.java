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
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("Action num값 : "+num);
		HttpSession session = request.getSession();
//		String name = (String) request.getParameter("name");
		String name = (String) session.getAttribute("name");
		System.out.println("Action name값 : " + name);
		
		faq_boardbean = faq_boarddao.getDetail(num);
		boolean usercheck = faq_boarddao.userchk(num, name);
		
		
		
//		if(usercheck == false){
//			response.setContentType("text/html;charset=UTF-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('본인의 게시물이 아닙니다.')");
//			out.println("history.back()");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
				
		request.setAttribute("faq_boardbean", faq_boardbean);
		forward.setRedirect(false);
		forward.setPath("./faq_board/faq_board_detail.jsp");
		return forward;
	}

}
