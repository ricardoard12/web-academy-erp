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
        //삭제후 돌아갈 페이지 정하기
        String kind = request.getParameter("kind");
        
        //회계 리스트 삭제
        acDao.acDeleteList(check);
        
        //페이지 현샹유지 true
        forward.setRedirect(true);
        forward.setPath("./AccountingList.ac?kind="+kind);
        return forward;
    }

}
