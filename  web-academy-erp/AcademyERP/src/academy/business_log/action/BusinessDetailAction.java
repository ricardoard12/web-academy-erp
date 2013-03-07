package academy.business_log.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log.db.BusinessBean;
import academy.business_log.db.BusinessDAO;



public class BusinessDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BusinessBean businessbean = new BusinessBean();
		BusinessDAO businessdao  = new BusinessDAO();
		
		ActionForward forward=new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("현재 num값 : "+num);
		String name = (String) request.getParameter("name");
		System.out.println("현재 name값 : " + name);
		
		
		businessbean = businessdao.getDetail(num);
//		boolean usercheck = businessdao.userchk(num, name);
		
//		if(usercheck == false){
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out = response.getWriter();
//			out.println("<script>");
//			out.println("alert('본인의 게시물이 아닙니다.')");
//			out.println("location.href='./BusinessNotice.bl");
//			out.println("<script>");
//			out.close();
//			return null;
//		}
		
		
		request.setAttribute("businessbean", businessbean);
		
		forward.setRedirect(false);
		forward.setPath("./business_log/business_detail.jsp");
		return forward;
	}

}
