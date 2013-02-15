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
//		int num = board_check.length;
		//int[] num =Integer.parseInt(request.getParameterValues("board_check"));
		
//		boolean usercheck=false;
//		String passwd=request.getParameter("board_pass");
//		usercheck=boarddao.isBoardWriter(num, passwd);
//		if(usercheck==false){
//			response.setContentType("text/html;charset=utf-8");
//			PrintWriter out=response.getWriter();
//			out.println("<script>");
//			out.println("alert('삭제권한이 없습니다.');");
//			out.println("location.href='./BoardList.bo';");
//			out.println("</script>");
//			out.close();
//			return null;
//		}
		
		boolean result=boarddao.boardDelete(board_check);
		if(result==false){
			System.out.println("삭제실패");
			return null;
		}
		System.out.println("삭제성공");
		
		forward.setRedirect(true);
		forward.setPath("./BoardNotice.bo");
		return forward;
	}

}
