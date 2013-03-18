package academy.qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaBean;
import academy.qna_board.db.QnaDAO;

public class QnaModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		QnaBean qna = new QnaBean();
		
		QnaDAO qnadao = new QnaDAO();
		
		qna.setQna_content(request.getParameter("qna_content"));
		qna.setQna_num(Integer.parseInt(request.getParameter("num")));
		qna.setQna_title(request.getParameter("qna_title"));
		
		qnadao.setNotice(qna);
		request.setAttribute("num",Integer.parseInt(request.getParameter("num")));
		forward.setRedirect(false);
		forward.setPath("./QnaDetail.qa");
		return forward;
	}

}
