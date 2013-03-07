package academy.business_log.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log.db.BusinessBean;
import academy.business_log.db.BusinessDAO;


public class BusinessModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		BusinessBean businessbean = new BusinessBean();
		businessbean.setBusiness_num(Integer.parseInt(request.getParameter("business_num")));
		businessbean.setBusiness_name(request.getParameter("business_name"));
		businessbean.setBusiness_subject(request.getParameter("business_subject"));
		businessbean.setBusiness_today(request.getParameter("business_today"));
		businessbean.setBusiness_counsel(request.getParameter("business_counsel"));
		businessbean.setBusiness_etc(request.getParameter("business_etc"));
		
		BusinessDAO businessdao = new BusinessDAO();
		businessdao.businessModify(businessbean);
		
		forward.setRedirect(true);
		forward.setPath("./BusinessNotice.bl");
		return forward;
				
	}

}
