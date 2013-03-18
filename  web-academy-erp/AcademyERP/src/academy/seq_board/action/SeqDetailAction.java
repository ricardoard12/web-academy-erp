package academy.seq_board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.seq_board.db.SeqBean;
import academy.seq_board.db.SeqDAO;

public class SeqDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		int num=0;
		ActionForward forward = new ActionForward();
		SeqBean seq = null;
		SeqDAO seqdao = new SeqDAO();
		
		if( request.getParameter("num")!= null){
			num = Integer.parseInt(request.getParameter("num"));
		}else{
			num=(int)request.getAttribute("num");
		}
		
		seqdao.setcount(num);
		
		seq=seqdao.getdetail(num);
		
		request.setAttribute("seq", seq);
		forward.setRedirect(false);
		forward.setPath("./seq_board/seq_board_detail.jsp");
		return forward;
	}

}
