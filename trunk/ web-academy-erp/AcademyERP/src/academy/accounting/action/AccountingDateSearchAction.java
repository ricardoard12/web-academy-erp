package academy.accounting.action;

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
        String date = request.getParameter("date");
        
        System.out.println(date);
        /*acDao.acSearchList(date);*/
        
        forward.setRedirect(false);
        forward.setPath("./AccountingList.ac");
        return forward;
    }

}
