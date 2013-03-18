package academy.seq_board.action;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.seq_board.db.SeqBean;
import academy.seq_board.db.SeqDAO;


public class SeqWritingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		// TODO Auto-generated method stub
	
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		SeqDAO  seqdao = new SeqDAO();
		
		SeqBean seqbean = new SeqBean();
		
		seqbean.setSeq_title(request.getParameter("seq_title"));
		seqbean.setSeq_content(request.getParameter("seq_content"));
		seqbean.setQna_num(Integer.parseInt(request.getParameter("qna_num")));
		boolean check = seqdao.insertSeq(seqbean);
		
		if(check==false){
		System.out.println("성공");	
		}
		forward.setRedirect(true);
		forward.setPath("./QnaList.qa");
		
		return forward;
	}

}
