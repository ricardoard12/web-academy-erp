package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.accounting.db.AccountingBean;
import academy.accounting.db.AccountingDAO;

public class AccountingStuentdAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		
		AccountingDAO accountingdao = new AccountingDAO();
		
		AccountingBean  accountbean = new AccountingBean(); //회비 내역을 가지고온 것을 저장한다.
		
		//로그인된 세션값 처리
		HttpSession session = request.getSession();
		String sid = (String)session.getAttribute("id");
		
       
        String memberid="";
        String result1=sid.substring(0,1) ; //첫자리 잘라내기
        String result2 =sid.substring(1) ; // 나머지 뒷자리 잘라내기
            if(result1.equals("p") || result1.equals("P")){
                result1="S"; //p로 시작하는 부보 아이뒤일경우 S로 바꿔준다.
                memberid = result1+result2; //다시 합치기
            }else{
                memberid = result1+result2;
            }
       List acounting =null;
		
       int accountselcount = accountingdao.getAccountcount(memberid); //글개수
		
		
		int page=1;  //현재페이지
		int limit=10; //한페이지 글수
		//page !=null  page=
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}

		 acounting= accountingdao.getAccountingStudent(memberid,page,limit);
		//페이징
		// 전체페이지수
		int maxpage=accountselcount/limit+(accountselcount%limit==0?0:1);
		maxpage=(int)((double)accountselcount/limit+0.95);//올림
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
		request.setAttribute("page",page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage",endpage);
		request.setAttribute("accountselcount", accountselcount); // 해당아이뒤에 해당하는 수강료 정보 갯수
		request.setAttribute("acounting", acounting); // 수강료를 낸 현황
		request.setAttribute("memberid", memberid);//아뒤갑 넘김
		//forward 정보 ./board/qna_board_list.jsp
		forward.setRedirect(false); //값가지고 이동
		forward.setPath("/accounting/accounting_student_list.jsp");
		return forward;
       
       
      
            
	}

}
