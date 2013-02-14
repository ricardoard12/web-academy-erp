package academy.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;


public class BoardModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		BoardDAO boarddao=new BoardDAO();
		BoardBean boardbean=new BoardBean();
		ActionForward forward=new ActionForward();
		int num = Integer.parseInt(request.getParameter("board_num"));
		String passwd = request.getParameter("board_pass");
		boolean userCheck=boarddao.isBoardWriter(num,passwd);
		System.out.println(userCheck);
		
		if(userCheck==false){//비밀번호틀림
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다');");
			out.println("location.href='./BoardList.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		boardbean.setBoard_num(Integer.parseInt(request.getParameter("board_num")));
		boardbean.setBoard_subject(request.getParameter("board_subject"));
		boardbean.setBoard_content(request.getParameter("board_content"));
	
		boarddao.boardModify(boardbean);
		
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+boardbean.getBoard_num());
		return forward;
	}

}
