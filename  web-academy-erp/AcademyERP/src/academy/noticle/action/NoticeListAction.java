package academy.noticle.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.noticle.db.NoticeBean;
import academy.noticle.db.NoticeDAO;


public class NoticeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		NoticeDAO noticedao = new NoticeDAO(); // DAO정의
		
		//MemberDAO memberdao = new MemberDAO(); //회원정보 디비
		
		NoticeBean noticebean = new NoticeBean(); //Bean 정의
		
		List<NoticeBean> noticeList = null; // 리스트에 내용이 저장되기 때문에 LIST를 정의한다.
		int noticecount = noticedao.getnoticecount(); //글개수
		
		
		int page =1;
		int limit=10;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		
		noticeList = noticedao.NoticeList(page, limit); //DB에서 리스트를 가져온다.
		
		//페이징
				// 전체페이지수
				int maxpage=noticecount/limit+(noticecount%limit==0?0:1);
				maxpage=(int)((double)noticecount/limit+0.95);//올림
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
				if(noticeList!=null){
					System.out.println("있음");
				}else{
					System.out.println("없음");
				}
				
				request.setAttribute("page",page);
				request.setAttribute("maxpage", maxpage);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage",endpage);
				request.setAttribute("noticecount", noticecount);
		
		
				request.setAttribute("noticeList", noticeList);

		
		forward.setRedirect(false);
		forward.setPath("./notice/noticelist.jsp");
		
		return forward;
	}

}
