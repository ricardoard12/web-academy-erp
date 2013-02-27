package academy.business_log_action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.business_log_db.BusinessDAO;

public class BusinessListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("BusinessListAction Start");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String level = request.getParameter("level");
		System.out.println(level);
		HttpSession session = request.getSession();
			
		BusinessDAO businessdao = new BusinessDAO();

		List businesslist = new ArrayList();
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = businessdao.getListCount();


		businesslist = businessdao.getBusinessList(page, limit);

		int maxpage = (int) ((double) listcount / limit + 0.95);

		int pageBlock = 10;

		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)
				* pageBlock + 1;

		int endpage = startpage + pageBlock - 1;
		if (endpage > maxpage)
			endpage = maxpage;

		// 값저장
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
