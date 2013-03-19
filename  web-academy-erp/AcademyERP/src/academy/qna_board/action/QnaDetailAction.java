package academy.qna_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaBean;
import academy.qna_board.db.QnaDAO;

public class QnaDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num=0;
		ActionForward forward = new ActionForward();
		QnaBean qna = null;
		QnaDAO qnadao = new QnaDAO();
		
		if( request.getParameter("num")!= null){
			num = Integer.parseInt(request.getParameter("num"));
		}else{
			num=Integer.parseInt((String)request.getAttribute("num"));
		}
		
		qnadao.setcount(num);
		
		qna=qnadao.getdetail(num);
		
		request.setAttribute("qna", qna);
		forward.setRedirect(false);
		forward.setPath("./qna_board/qna_board_detail.jsp");
		return forward;
	}

}
