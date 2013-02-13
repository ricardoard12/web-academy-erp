package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingListAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //전체리스트
        List acList = acDao.acGetList();

        request.setAttribute("acList", acList);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_list.jsp");
        return forward;
    }

}
