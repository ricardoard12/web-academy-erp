package academy.business_log.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.business_log.db.BusinessBean;
import academy.business_log.db.BusinessDAO;



public class BusinessDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BusinessBean businessbean = new BusinessBean();
		BusinessDAO businessdao  = new BusinessDAO();
		request.setCharacterEncoding("utf-8");
		
		ActionForward forward=new ActionForward();

		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("Action num값 : "+num);
		HttpSession session = request.getSession();

		String name = (String) session.getAttribute("name");
		System.out.println("Action name값 : " + name);
		
		//level별 열람 권한을 위해 level값 받음 
		//일정 level값을 가지는 아이디는 usercheck없이 세부 열람
		String level = request.getParameter("level");
		System.out.println("Level값 : " + level);
		
				
		businessbean = businessdao.getDetail(num);
		
//		if(!level.equals("5") || !level.equals("4")){
		
			boolean usercheck = businessdao.userchk(num, name);
				
			if(usercheck == false){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('본인의 게시물이 아닙니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
				return null;
			}
//		}
				
		request.setAttribute("businessbean", businessbean);
		forward.setRedirect(false);
		forward.setPath("./business_log/business_detail.jsp");
		return forward;
	}

}
