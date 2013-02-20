package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;



public class BoardReplyView implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		//forward
		ActionForward forward=new ActionForward();
		//boarddao
		BoardDAO boarddao=new BoardDAO();
		//boardata
		BoardBean boardbean=new BoardBean();
		//num가져오기
		int num=Integer.parseInt(request.getParameter("num"));
		// boardbean=  .getDetail(num)
		boardbean=boarddao.getDetail(num);
		// boardbean==null 답장 정보가져오기 실패
		
		if(boardbean==null){
			System.out.println("답장 정보가져오기 실패");
			return null;
		}
		// 답장 정보가져오기 성공
		System.out.println("답장 정보가져오기 성공");
		//boardbean 저장
		request.setAttribute("boardbean", boardbean);
		
		forward.setRedirect(false);
		forward.setPath("./board/board_reply.jsp");
		return forward;
	}
	
}
