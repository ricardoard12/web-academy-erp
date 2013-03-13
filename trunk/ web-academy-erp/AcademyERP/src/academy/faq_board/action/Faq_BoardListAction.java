package academy.faq_board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.faq_board.db.Faq_boardDAO;

 public class Faq_BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Faq_boardListAction Start");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String level = request.getParameter("level");
		System.out.println(level);

		HttpSession session = request.getSession();
		Faq_boardDAO faq_boarddao = new Faq_boardDAO();
		List faq_boardList = new ArrayList();

		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = faq_boarddao.getListCount();


		faq_boardList = faq_boarddao.getFaq_boardList(page, limit);

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
		request.setAttribute("faq_boardList", faq_boardList);

		System.out.println("Faq_boardListAction End");
		forward.setRedirect(false);
		forward.setPath("./faq_board/faq_board_list.jsp");
		return forward;
	}

}
