package academy.seq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.seq_board.db.SeqBean;
import academy.seq_board.db.SeqDAO;

public class SeqModifyAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		SeqBean  seq = new SeqBean();
		
		SeqDAO seqdao = new SeqDAO();
		
		seq.setSeq_content(request.getParameter("seq_content"));
		seq.setSeq_num(Integer.parseInt(request.getParameter("num")));
		seq.setSeq_title(request.getParameter("seq_title"));
		
		seqdao.setNotice(seq);
		request.setAttribute("num",Integer.parseInt(request.getParameter("num")));
		forward.setRedirect(false);
		forward.setPath("./SeqDetail.sq");
		return forward;
	}

}
