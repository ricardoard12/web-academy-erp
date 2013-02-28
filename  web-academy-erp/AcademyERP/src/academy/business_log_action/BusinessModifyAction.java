package academy.business_log_action;

import java.io.PrintWriter;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log_db.BusinessBean;
import academy.business_log_db.BusinessDAO;




public class BusinessModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BusinessBean businessbean = new BusinessBean();
		BusinessDAO businessdao = new BusinessDAO();
		
		ActionForward forward=new ActionForward();
				
		int num = Integer.parseInt(request.getParameter("business_num"));
		String name = request.getParameter("business_name");
		
		boolean userCheck = businessdao.isBoardWriter(num,name);
		System.out.println(userCheck);
		
		if(userCheck==false){//비밀번호틀림
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('비밀번호가 틀립니다.');");
			out.println("location.href='./BusinessNotice.bl';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		businessbean.setBusiness_num(Integer.parseInt(request.getParameter("business_num")));
		businessbean.setBusiness_name(request.getParameter("business_name"));
		businessbean.setBusiness_subject(request.getParameter("business_subject"));
		businessbean.setBusiness_today(request.getParameter("business_today"));
		businessbean.setBusiness_counsel(request.getParameter("business_counsel"));
		businessbean.setBusiness_etc(request.getParameter("business_etc"));
		
		businessdao.businessModify(businessbean);
		
		forward.setRedirect(true);
		forward.setPath("./BusinessModify.bl?num="+businessbean.getBusiness_num());
		return forward;
	}

}
