package academy.accounting.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingListAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acdao = new AccountingDAO();
        
        String kind = request.getParameter("kind");
        String date = request.getParameter("date");
        List aclist = null;
        
        //날짜 검색을 제외한 수강료,수입,지출 검색
        if(date == null){
            aclist = acdao.ackindList(kind);
        //날짜 검색
        }else{
            //날짜값 변환
            Date searchDate = Date.valueOf(date);
            aclist = acdao.acSearchList(searchDate);
        }
        request.setAttribute("aclist", aclist);
        request.setAttribute("kind", kind);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_list.jsp");
        return forward;
    }

}
