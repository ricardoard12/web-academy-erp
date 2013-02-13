package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;

public class BoardDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		BoardBean boardbean = new BoardBean();
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward=new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		boarddao.setReadCountUpdate(num);
		boardbean = boarddao.getDetail(num);
		request.setAttribute("boardbean", boardbean);
		forward.setRedirect(false);
		forward.setPath("./board/board_notice.jsp");
		return forward;
	}

}
