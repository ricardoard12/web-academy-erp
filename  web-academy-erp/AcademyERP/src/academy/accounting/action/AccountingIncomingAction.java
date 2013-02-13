package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingIncomingAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //수입 리스트
        List acincomingList = acDao.acincomingGetList();
        
        request.setAttribute("acincomingList", acincomingList);
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_incoming.jsp");
        return forward;
    }

}