package academy.business_log_action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import academy.business_log_db.BusinessDAO;



public class BusinessDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		BusinessDAO businessdao = new BusinessDAO();
		
		ActionForward forward=new ActionForward();
		
		String[] business_check = request.getParameterValues("business_check");
		
		for(int i=0 ; i< business_check.length ; i++){
			System.out.println(business_check[i]);
		}
		
		boolean result=businessdao.busienssDelete(business_check);
		
		if(result==false){
			System.out.println("삭제실패");
		}
		System.out.println("삭제성공");
		
		forward.setRedirect(false);
		forward.setPath("./BusinessNotice.bl");
		return forward;
	}

}
