package academy.qna_board.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;
import academy.qna_board.db.QnaBean;
import academy.qna_board.db.QnaDAO;
import academy.seq_board.db.SeqBean;
import academy.seq_board.db.SeqDAO;


public class QnaListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		QnaDAO qnadao = new QnaDAO(); // DAO정의
		SeqDAO seqdao = new SeqDAO();
		//MemberDAO memberdao = new MemberDAO(); //회원정보 디비
		
		QnaBean qnabean = new QnaBean(); //Bean 정의
		
		List<QnaBean> qnaList = null; // 리스트에 내용이 저장되기 때문에 LIST를 정의한다.
		List<SeqBean> seqList =null;// 답글 리스트에 잇는 내용 가지고오기
		int qnacount = qnadao.getnoticecount(); //글개수
		int seqcount = seqdao.getseqcount(); //답글개수
		
		int page =1;
		int limit=10;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		qnaList = qnadao.QnaList(page, limit); //DB에서 리스트를 가져온다.
		seqList = seqdao.seqList(page, limit); //DB에서 리스트를 가져온다.
		
		//페이징
				// 전체페이지수
				int maxpage=qnacount/limit+(qnacount%limit==0?0:1);
				maxpage=(int)((double)qnacount/limit+0.95);//올림
				//시작페이지번호 1  11  21  31
				int pageblock=3; //1 -10 페이지 보여주는 개수
				int startpage=((int)(page/pageblock)-(page%pageblock==0?1:0))*pageblock+1;
				startpage=(((int)((double)page/pageblock+0.9))-1)*pageblock+1;
				//끝페이지 10 20 30 ..
				int endpage=startpage+pageblock-1;
				if(endpage>maxpage){
					endpage=maxpage;
				}
				//request.setAttribute(이름,값) //정보저장
				if(qnaList!=null){
					System.out.println("있음");
				}else{
					System.out.println("없음");
				}
				
				request.setAttribute("page",page);
				request.setAttribute("maxpage", maxpage);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage",endpage);
				request.setAttribute("qnacount", qnacount);
		
		
				request.setAttribute("qnaList", qnaList);
				request.setAttribute("seqList", seqList);	
		
		forward.setRedirect(false);
		forward.setPath("./qna_board/qna_board_list.jsp");
		
		return forward;
	}

}
