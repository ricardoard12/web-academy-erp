package academy.qna_board.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaBean;
import academy.qna_board.db.QnaDAO;


public class QnaWritingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		QnaDAO  qnadao = new QnaDAO();
		
		QnaBean qnabean = new QnaBean();
		
		qnabean.setQna_title(request.getParameter("qna_title"));
		qnabean.setQna_content(request.getParameter("qna_content"));
		qnabean.setQna_subject(request.getParameter("qna_name"));
		
		boolean check = qnadao.insertqna(qnabean);
		
		if(check==false){
		System.out.println("성공");	
		}
		HttpSession session = request.getSession();
		session.setAttribute("qnaid",request.getParameter("qna_name"));
		forward.setRedirect(true);
		forward.setPath("./QnaList.qa");
		
		return forward;
	}

}
