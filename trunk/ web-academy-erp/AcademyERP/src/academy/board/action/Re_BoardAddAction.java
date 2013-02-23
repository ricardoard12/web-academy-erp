package academy.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.db.Re_BoardBean;
import academy.board.db.Re_BoardDAO;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;


public class Re_BoardAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		Re_BoardBean re_boardbean = new Re_BoardBean();
		Re_BoardDAO re_boarddao = new Re_BoardDAO();
		ActionForward forward = new ActionForward();
		
		try{			
			re_boardbean.setRe_board_num(Integer.parseInt(request.getParameter("re_board_num")));
			re_boardbean.setRe_board_ref(Integer.parseInt(request.getParameter("re_board_ref")));
			re_boardbean.setRe_board_lev(Integer.parseInt(request.getParameter("re_board_lev")));
			re_boardbean.setRe_board_seq(Integer.parseInt(request.getParameter("re_board_seq")));
			re_boardbean.setRe_board_name(request.getParameter("re_board_name"));
			re_boardbean.setRe_board_content(request.getParameter("re_board_content"));	
			re_boarddao.re_boardinsert(re_boardbean);
			forward.setRedirect(true);
			forward.setPath("./BoardNotice.bo");
		}catch(Exception e){
			e.printStackTrace();
		}
			return forward;
	}

}
