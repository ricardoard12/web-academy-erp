package academy.faq_board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.faq_board.db.Faq_boardDAO;
import academy.faq_board.db.Faq_boardbean;

public class Faq_UserCheckAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		Faq_boardbean faq_boardbean = new Faq_boardbean();
		Faq_boardDAO faq_boarddao = new Faq_boardDAO();
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("faq_num"));
		//num값 전달하여 받는 값 확인
		System.out.println("Action에서 Faq_num값은 : " + num + "입니다.");
		
		String password = request.getParameter("faq_passwd");
		//password값 전달하여 받는 값 확인
		System.out.println("Action에서 Faq_password값은 : " + password + "입니다.");
		
		String level = request.getParameter("level");
		System.out.println("Action에서 level값은 : " + level + "입니다.");
		
		String level2 = (String) request.getAttribute("level");
		System.out.println("session level val = " + level2);
		
		
		if(!level.equals("5") && !level.equals("4")){
		boolean userchk = faq_boarddao.userchk(num, password);
		
		if(userchk == false){
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('비밀번호가 일치하지 않습니다.')");
		out.println("history.back()");
		out.println("</script>");
		out.close();
		return null;
	}
		}

		request.setAttribute("faq_boardbean", faq_boardbean);
		request.setAttribute("num", num);
		request.setAttribute("password", password);
		forward.setRedirect(false);
		
		
		
		forward.setPath("./Faq_BoardDetailAction.fb");
		return forward;
		
	}

}
