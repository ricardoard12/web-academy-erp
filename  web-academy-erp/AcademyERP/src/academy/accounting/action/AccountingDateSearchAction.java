package academy.accounting.action;

import java.sql.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingDateSearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //체크값 받아오기
        Date date = Date.valueOf(request.getParameter("date"));
        List searchlist = acDao.acSearchList(date);
        
        //acList로 날려서 리스트에서 중복 작업을 피하기 위해서 변경
        request.setAttribute("acList", searchlist);
        //주소값 없애기 위해
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_list.jsp");
        return forward;
    }

}
