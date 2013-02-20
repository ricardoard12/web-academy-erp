package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;



public class BoardReplyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		ActionForward forward=new ActionForward();
		BoardDAO boarddao=new BoardDAO();
		BoardBean boardbean=new BoardBean();
		// 폼 => 자바빈 저장
		boardbean.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		boardbean.setBoard_re_ref(Integer.parseInt(request.getParameter("board_re_ref")));
		boardbean.setBoard_re_lev(Integer.parseInt(request.getParameter("board_re_lev")));
		boardbean.setBoard_re_seq(Integer.parseInt(request.getParameter("board_re_seq")));
		boardbean.setBoard_name(request.getParameter("board_name"));
		boardbean.setBoard_pass(request.getParameter("board_pass"));
		boardbean.setBoard_memo(request.getParameter("board_memo"));
	
		int result=0;
		result=boarddao.boardReply(boardbean);
		if(result==0){
			System.out.println("답장실패");
			return null;
		}
		System.out.println("답장성공");
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+result);
		return forward;
	}

}
