package academy.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.board.db.BoardDAO;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		/*
		 * MemberBean member=(MemberBean)session.getAttribute("세션에 저장된 멤버 객체");
		 * if(member==null){
		 *  세션 값 없음.
		 *  메인 페이지로 돌리기.
		 * }
		 * String level=member.getMm_level();
		 * 
		 * 
		 * */
		/*재우 소스*/
		/*String id = (String) session.getAttribute("board_name");

		if (id == null) {
			forward.setRedirect(true);
			forward.setPath("./BoardNotice.bo ");
			return forward;
		}*/
		
		String gid = "";//게시판을 구분 변수
		BoardDAO boarddao = new BoardDAO();

		List boardlist = new ArrayList();
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = boarddao.getListCount();

		boardlist = boarddao.getBoardList(gid, page, limit);

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
		request.setAttribute("boardlist", boardlist);

		forward.setRedirect(false);
		forward.setPath("./board/board_notice.jsp");
		return forward;
	}

}
