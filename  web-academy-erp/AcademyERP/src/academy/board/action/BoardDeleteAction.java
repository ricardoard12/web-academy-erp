package academy.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardDAO;



public class BoardDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		BoardDAO boarddao=new BoardDAO();
		ActionForward forward=new ActionForward();
		String[] board_check = request.getParameterValues("board_check");
		for(int i=0 ; i< board_check.length ; i++){
			System.out.println(board_check[i]);
		}
		
		boolean result=boarddao.boardDelete(board_check);
		
		if(result==false){
			System.out.println("삭제실패");
		}
		System.out.println("삭제성공");
		
		forward.setRedirect(false);
		forward.setPath("./BoardNotice.bo");
		return forward;
	}

}
