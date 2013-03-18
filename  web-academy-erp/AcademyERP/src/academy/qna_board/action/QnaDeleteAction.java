package academy.qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaDAO;

public class QnaDeleteAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		QnaDAO qnadao = new QnaDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		qnadao.DeleteNotice(num);
		forward.setRedirect(false);
		forward.setPath("./QnaList.qa");
		return forward;
	}

}
