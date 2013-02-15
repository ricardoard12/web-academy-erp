package academy.accounting.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingBean;
import academy.accounting.db.AccountingDAO;

public class AccountingJoinAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        ActionForward forward = new ActionForward();
        AccountingBean acBean = new AccountingBean();
        
        acBean.setAc_io_type(request.getParameter("ac_io_type"));
        acBean.setMm_id(request.getParameter("mm_id"));
        acBean.setAc_price(Integer.parseInt(request.getParameter("ac_price")));
        acBean.setAc_cc_type(request.getParameter("ac_cc_type"));
        acBean.setAc_date(Date.valueOf(request.getParameter("ac_date")));
        acBean.setAc_manager_name(request.getParameter("ac_manager_name"));
        acBean.setAc_memo(request.getParameter("ac_memo"));
        
        AccountingDAO acDao = new AccountingDAO();
        //수입지출 입력
        boolean result = acDao.acJoin(acBean);
        
        if(result){
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('성공적으로 가입하였습니다');");
            out.println("location.href='./AccountingJoin.ac'");
            out.println("</script>");
            out.close();
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('실패하였습니다');");
            out.println("history.go(-1);");
            out.println("</script>");
            out.close();
        }
        
        return null;
    }

}
