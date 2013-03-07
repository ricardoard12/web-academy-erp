package academy.business_log.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.business_log.db.BusinessDAO;



public class BusinessNoticeAction implements Action {
	

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BusinessNoticeAction Start");
		BusinessDAO businessdao = new BusinessDAO();
		
		List businesslist = new ArrayList();

		ActionForward forward = new ActionForward();
		String level = request.getParameter("level");
		String id = request.getParameter("id");
		String name = request.getParameter("name");
				
		int page = 1;
		int limit = 13;

		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
	
		int listcount = businessdao.getListCount();
		businesslist=businessdao.getBusinessList(page,limit);
		
		int maxpage=listcount/limit+(listcount%limit==0?0:1);
		maxpage=(int)((double)listcount/limit+0.95);
		int pageblock=5;
		int startpage=((int)(page/pageblock)-(page%pageblock==0?1:0))*pageblock+1;
		startpage=(((int)((double)page/pageblock+0.9))-1)*pageblock+1;
		int endpage=startpage+pageblock-1;
		if(endpage>maxpage){
			endpage=maxpage;
		}
		
		request.setAttribute("level", level);
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("businesslist", businesslist);
		
		forward.setRedirect(false);
		forward.setPath("./business_log/business_notice.jsp");
		return forward;
		
		
	}
	
}
