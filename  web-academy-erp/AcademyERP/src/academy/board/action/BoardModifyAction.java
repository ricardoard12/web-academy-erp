package academy.board.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		
		String uploadfolder = "board/board_upload";
		String realFolder = request.getRealPath(uploadfolder);
		int fileSize = 5 * 1024 * 1024;
		MultipartRequest multi = null;
		multi=new MultipartRequest(request,realFolder,fileSize,"utf-8",new DefaultFileRenamePolicy());
		
		int num = Integer.parseInt(multi.getParameter("board_num"));
		String passwd = multi.getParameter("board_pass");
		boolean userCheck=boarddao.isBoardWriter(num,passwd);
		System.out.println(userCheck);
		
		if(userCheck==false){//비밀번호틀림
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out=response.getWriter();
			out.println("<script>");
			out.println("alert('수정할 권한이 없습니다');");
			out.println("location.href='./BoardNotice.bo';");
			out.println("</script>");
			out.close();
			return null;
		}
		
		boardbean.setBoard_num(Integer.parseInt(multi.getParameter("board_num")));
		boardbean.setBoard_subject(multi.getParameter("board_subject"));
		boardbean.setBoard_content(multi.getParameter("board_content"));
	
		boarddao.boardModify(boardbean);
		
		forward.setRedirect(true);
		forward.setPath("./BoardDetailAction.bo?num="+boardbean.getBoard_num());
		return forward;
	}

}
