package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingOutgoingAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //지출 리스트
        List acoutgoingList = acDao.acoutgoingGetList();
        
        request.setAttribute("acoutgoingList", acoutgoingList);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_outgoing.jsp");
        return forward;
    }

}
