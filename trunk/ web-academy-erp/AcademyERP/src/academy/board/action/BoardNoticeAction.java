package academy.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.board.db.BoardDAO;
import academy.board.db.Re_BoardBean;
import academy.board.db.Re_BoardDAO;
import academy.master.db.ListPackage;
import academy.member.db.MemberBean;

public class BoardNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		// Re_BoardDAO re_boarddao = new Re_BoardDAO();
		// Re_BoardBean re_boardbean = new Re_BoardBean();
		List boardlist = new ArrayList();
		// List re_boardlist = new ArrayList();
		ActionForward forward = new ActionForward();
		String gid = request.getParameter("gid");
		int page = 1;
		int limit = 13;

		// int re_page = 1;
		// int re_limit = 13;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		// if(request.getParameter("re_page") != null){
		// page = Integer.parseInt(request.getParameter("re_page"));
		// }
		// 덧글 관련 리스트 출력
		// int re_listcount = re_boarddao.getReListCount();
		// re_boardlist = re_boarddao.getReBoardList(re_page, re_limit);
		// 게시판 목록 리스트 출력
		int listcount = boarddao.getListCount(gid);
		boardlist = boarddao.getBoardList(page, limit,gid);

		int maxpage = listcount / limit + (listcount % limit == 0 ? 0 : 1);
		maxpage = (int) ((double) listcount / limit + 0.95);
		int pageblock = 5;
		int startpage = ((int) (page / pageblock) - (page % pageblock == 0 ? 1
				: 0)) * pageblock + 1;
		startpage = (((int) ((double) page / pageblock + 0.9)) - 1) * pageblock
				+ 1;
		int endpage = startpage + pageblock - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		// int re_maxpage=re_listcount/re_limit+(re_listcount%re_limit==0?0:1);
		// re_maxpage=(int)((double)re_listcount/re_limit+0.95);
		// int re_pageblock=5;
		// int
		// re_startpage=((int)(re_page/re_pageblock)-(re_page%re_pageblock==0?1:0))*re_pageblock+1;
		// re_startpage=(((int)((double)re_page/re_pageblock+0.9))-1)*re_pageblock+1;
		// int re_endpage=re_startpage+re_pageblock-1;
		// if(re_endpage>re_maxpage){
		// re_endpage=re_maxpage;
		// }
		//
//		request.setAttribute("page", page);
//		request.setAttribute("maxpage", maxpage);
//		request.setAttribute("startpage", startpage);
//		request.setAttribute("endpage", endpage);
//		request.setAttribute("listcount", listcount);
//		request.setAttribute("boardlist", boardlist);
		ListPackage Pack=new ListPackage(page, maxpage, startpage, endpage, listcount, boardlist);
		Pack.setGid(gid);
		request.setAttribute("listpack", Pack);
		// request.setAttribute("re_page", re_page);
		// request.setAttribute("re_maxpage", re_maxpage);
		// request.setAttribute("re_startpage", re_startpage);
		// request.setAttribute("re_endpage", re_endpage);
		// request.setAttribute("re_listcount", re_listcount);
		// request.setAttribute("re_boardlist", re_boardlist);
		forward.setRedirect(false);
		forward.setPath("./board/board_notice.jsp");

		return forward;
	}

}
