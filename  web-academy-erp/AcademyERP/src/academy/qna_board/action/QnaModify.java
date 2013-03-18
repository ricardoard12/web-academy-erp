package academy.qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.taglibs.standard.lang.jstl.test.Bean1;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaBean;
import academy.qna_board.db.QnaDAO;

public class QnaModify implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		QnaBean qna = null;
		
		QnaDAO qnadao = new QnaDAO();
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		qna = qnadao.getdetail(num);
		
		request.setAttribute("qna", qna);
		forward.setRedirect(false);
		forward.setPath("./qna_board/qna_board_modify.jsp");
		return forward;
	}

}
