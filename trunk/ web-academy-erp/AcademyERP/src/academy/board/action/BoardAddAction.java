package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import academy.board.db.BoardBean;
import academy.board.db.BoardDAO;

public class BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		BoardBean boardbean = new BoardBean();
		BoardDAO boarddao = new BoardDAO();
		ActionForward forward = new ActionForward();
		String uploadfolder = "board/board_upload";
		String realFolder = request.getRealPath(uploadfolder);
		int fileSize = 5 * 1024 * 1024;
		
		try{			
			MultipartRequest multi = null;
			multi=new MultipartRequest(request,realFolder,fileSize,"utf-8",new DefaultFileRenamePolicy());
			boardbean.setBoard_name(multi.getParameter("board_name"));
			boardbean.setBoard_pass(multi.getParameter("board_pass"));
			boardbean.setBoard_content(multi.getParameter("board_content"));
			boardbean.setBoard_subject(multi.getParameter("board_subject"));
			boardbean.setBoard_file(multi.getFilesystemName("board_file"));
			boarddao.boardinsert(boardbean);
			forward.setRedirect(true);
			forward.setPath("./BoardNotice.bo");
		}catch(Exception e){
			e.printStackTrace();
		}
			return forward;
	}

}
