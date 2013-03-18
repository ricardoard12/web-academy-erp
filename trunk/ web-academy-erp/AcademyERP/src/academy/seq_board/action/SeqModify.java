package academy.seq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.seq_board.db.SeqBean;
import academy.seq_board.db.SeqDAO;

public class SeqModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		SeqBean seq = null;
		
		SeqDAO seqdao = new SeqDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		seq = seqdao.getdetail(num);
		
		request.setAttribute("seq", seq);
		forward.setRedirect(false);
		forward.setPath("./seq_board/seq_board_modify.jsp");
		return forward;
	}

}
