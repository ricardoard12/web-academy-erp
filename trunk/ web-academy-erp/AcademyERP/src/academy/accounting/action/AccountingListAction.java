package academy.accounting.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountingListAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_list.jsp");
        return forward;
    }

}
