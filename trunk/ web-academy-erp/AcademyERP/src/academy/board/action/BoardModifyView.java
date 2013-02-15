package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;

public class BoardModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDAO boarddao = new BoardDAO();
		BoardBean boardbean = boarddao.getDetail(num);
		
		request.setAttribute("boardbean", boardbean);
		
		forward.setRedirect(false);
		forward.setPath("./board/board_modify.jsp");
		return forward;
	}

}
