package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		String uploadfolder = "boardupload";
		String realFolder = request.getRealPath(uploadfolder);
		int fileSize = 5 * 1024 * 1024;
		
		try{						
			boardbean.setBoard_name(request.getParameter("st_parent_id"));
			boardbean.setBoard_pass(request.getParameter("st_parent_pass"));
			boardbean.setBoard_content(request.getParameter("st_memo"));
			boardbean.setBoard_subject(request.getParameter("temp_input"));
			boarddao.boardinsert(boardbean);
			forward.setRedirect(true);
			forward.setPath("./BoardNotice.bo");
		}catch(Exception e){
			e.printStackTrace();
		}
			return forward;
	}

}
