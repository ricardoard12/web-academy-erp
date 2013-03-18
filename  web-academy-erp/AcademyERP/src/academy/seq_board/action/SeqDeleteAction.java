package academy.seq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeDAO;
import academy.seq_board.db.SeqDAO;

public class SeqDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		SeqDAO seqdao = new SeqDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		seqdao.DeleteNotice(num);
		forward.setRedirect(false);
		forward.setPath("./QnaList.qa");
		return forward;
	}

}
