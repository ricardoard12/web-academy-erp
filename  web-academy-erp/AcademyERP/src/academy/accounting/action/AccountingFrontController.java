package academy.accounting.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AccountingFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;
        
        //수입지출 폼 
        if(command.equals("/AccountingJoin.ac")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./accounting/accounting_join.jsp");
        //수입지출 등록            
        }else if(command.equals("/AccountingJoinAction.ac")) {
            action = new AccountingJoinAction();
            try {forward = action.execute(request, response);} 
            catch (Exception e) {e.printStackTrace();}
        //kind별 리스트(list,in,out,date)       
        }else if(command.equals("/AccountingList.ac")) {
            action = new AccountingListAction();
            try {forward = action.execute(request, response);} 
            catch (Exception e) {e.printStackTrace();}
        // 회계 리스트 삭제
        }else if(command.equals("/AccountingDelete.ac")) {
                action = new AccountingDeleteAction();
                try {forward = action.execute(request, response);} 
                catch (Exception e) {e.printStackTrace();}
        }//학생/학부모 회계내용조회
        else if(command.equals("/AccountingStuentd.ac")){
        	action= new AccountingStuentdAction();
        	try {forward=action.execute(request, response);}
            catch (Exception e) {e.printStackTrace();}
        	//수입 지출 수강자 선택 리스트
        } else if(command.equals("/AccountingIDSearch.ac")){
            action= new AccountingIDSearchAction();
            try {forward=action.execute(request, response);}
            catch (Exception e) {e.printStackTrace();}
            //담당자 선택 리스트
        } else if(command.equals("/AccountingOfficerSearch.ac")){
            action= new AccountingOfficerSearchAction();
            try {forward=action.execute(request, response);}
            catch (Exception e) {e.printStackTrace();}
        }
        
        
        // 이동
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }
    }

}
