package academy.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println("diiiiid"+num);
		/*session 검증부분*/
		HttpSession session=request.getSession();
		String gid=request.getParameter("gid");
		int glevel=Integer.parseInt(gid);
		String level;
		if(glevel<20){
			level=(String)session.getAttribute("level");
			if(level==null||level.equals("null")){
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('권한이 없습니다. 먼저 로그인 하세요.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
				return null;
			}
		}
		/*session 검증부분*/
		
		boarddao.setReadCountUpdate(num);
		boardbean = boarddao.getDetail(num);
		request.setAttribute("boardbean", boardbean);
		forward.setRedirect(false);

		forward.setPath("./board/board_detail.jsp");

		return forward;
	}

}
