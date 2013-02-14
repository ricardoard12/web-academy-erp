package academy.accounting.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.accounting.db.AccountingDAO;

public class AccountingDeleteAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        AccountingDAO acDao = new AccountingDAO();
        
        //체크값 받아오기
        String[] check = request.getParameterValues("check");
        

        //회계 리스트 삭제
        acDao.acDeleteList(check);
        
        forward.setRedirect(false);
        forward.setPath("./AccountingList.ac");
        return forward;
    }

}
