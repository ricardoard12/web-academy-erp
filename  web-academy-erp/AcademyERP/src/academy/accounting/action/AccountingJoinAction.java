package academy.accounting.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingBean;

public class AccountingJoinAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        ActionForward forward = new ActionForward();
        AccountingBean acBean = new AccountingBean();
        
        acBean.setAc_io_type(request.getParameter("ac_io_type"));
        acBean.setMm_id(request.getParameter("mm_id"));
        acBean.setAc_price("request.getParameter("ac_price"));
        request.getParameter("ac_cc_type");
        request.getParameter("ac_date");
        request.getParameter("ac_manager_name");
        request.getParameter("ac_memo");
        
        
        forward.setRedirect(false);
        forward.setPath("./AccountingList.ac");
        return forward;
    }

}
