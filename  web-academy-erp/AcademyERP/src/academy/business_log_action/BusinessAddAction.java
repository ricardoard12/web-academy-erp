package academy.business_log_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log_db.BusinessBean;
import academy.business_log_db.BusinessDAO;

public class BusinessAddAction implements Action{

	@Override
	public academy.business_log_action.ActionForward execute(
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		request.setCharacterEncoding("utf-8");
		BusinessBean businessbean = new BusinessBean();
		BusinessDAO businessdao = new BusinessDAO();
		ActionForward forward = new ActionForward();
		
		try {
			
			businessbean.setBusiness_num(Integer.parseInt(request.getParameter("business_num")));
			businessbean.setBusiness_today(request.getParameter("business_today"));
			businessbean.setBusiness_counsel(request.getParameter("business_counsel"));
			businessbean.setBusiness_etc(request.getParameter("business_etc"));
			businessdao.businessinsert(businessbean);
			forward.setRedirect(true);
			forward.setPath("./BusinessNotice.bl");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return forward;
	}

	
}
