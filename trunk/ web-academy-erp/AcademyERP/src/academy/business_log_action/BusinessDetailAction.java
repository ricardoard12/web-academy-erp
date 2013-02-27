package academy.business_log_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log_db.BusinessBean;
import academy.business_log_db.BusinessDAO;


public class BusinessDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BusinessBean businessbean = new BusinessBean();
		BusinessDAO businessdao  = new BusinessDAO();
		
		ActionForward forward=new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("diiiiid"+num);
		
		businessbean = businessdao.getDetail(num);
		request.setAttribute("businessbean", businessbean);
		
		forward.setRedirect(false);
		forward.setPath("./business_log/business_detail.jsp");
		return forward;
	}

}
