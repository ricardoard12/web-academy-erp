package academy.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardDAO;

public class BoardNoticeAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();
		ActionForward forward = new ActionForward();
		
		int page = 1;
		int limit = 3;
		
		if(request.getParameter("page") != null){
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = boarddao.getListCount();
		boardlist=boarddao.getBoardList(page,limit);
		int maxpage=listcount/limit+(listcount%limit==0?0:1);
		maxpage=(int)((double)listcount/limit+0.95);
		int pageblock=3;
		int startpage=((int)(page/pageblock)-(page%pageblock==0?1:0))*pageblock+1;
		startpage=(((int)((double)page/pageblock+0.9))-1)*pageblock+1;
		int endpage=startpage+pageblock-1;
		if(endpage>maxpage){
			endpage=maxpage;
		}
		
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("boardlist", boardlist);
		forward.setRedirect(false);
		forward.setPath("./board/board_notice.jsp");
		
		return forward;
	}

}
