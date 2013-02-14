package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;

public class BoardModifyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao=new BoardDAO();
		BoardBean boardbean=new BoardBean();
		ActionForward forward=new ActionForward();
		int num=Integer.parseInt(request.getParameter("num"));
		
		boardbean=boarddao.getDetail(num);
		if(boardbean==null){
			System.out.println("게시판수정내용가져오기실패");
			return null;
		}
		
		request.setAttribute("boardbean", boardbean);
		forward.setRedirect(false);
		forward.setPath("./board/board_modify.jsp");
		return forward;
	}

}
