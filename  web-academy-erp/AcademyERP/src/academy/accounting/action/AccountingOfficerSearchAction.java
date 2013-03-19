package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingOfficerSearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {

        ActionForward forward = new ActionForward();
        AccountingDAO acdao = new AccountingDAO();
        
        List officerlist = null;
        
        officerlist = acdao.accountingOfficerSearch();
        
        request.setAttribute("officerlist", officerlist);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/officer_search.jsp");
        return forward;

    }

}
