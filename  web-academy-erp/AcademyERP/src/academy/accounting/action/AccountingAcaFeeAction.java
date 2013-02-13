package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingAcaFeeAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //회비 리스트
        List acfeeList = acDao.acfeeGetList();
        
        request.setAttribute("acfeeList", acfeeList);
        
        forward.setRedirect(false);
        forward.setPath("./accounting/accounting_academyFee.jsp");
        return forward;
    }

}