package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingIDSearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        AccountingDAO acdao = new AccountingDAO();
        
        List idlist = null;
        
        idlist = acdao.accountingIDsearch();
        
        request.setAttribute("idlist", idlist);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/.jsp");
        return forward;
    }

}
