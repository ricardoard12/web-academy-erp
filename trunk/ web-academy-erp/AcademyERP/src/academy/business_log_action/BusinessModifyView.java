package academy.business_log_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log_db.BusinessBean;




public class BusinessModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		int num = Integer.parseInt(request.getParameter("business_num"));
		String name  = request.getParameter("name");
		
		BusinessBean businessbean = new BusinessBean();
		request.setAttribute("businessbean", businessbean);
		
		forward.setRedirect(false);
		forward.setPath("./business_log/business_modify.jsp");
		return forward;
	}

}
