package academy.business_log_action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BusinessFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	String requestURI = request.getRequestURI();
	        String contextPath = request.getContextPath();
	        String command = requestURI.substring(contextPath.length());
	        ActionForward forward = null;
	        Action action = null;
	        
	        //업무일지 입력 폼
	        if (command.equals("/BusinessWrite.bl")) {
	            forward = new ActionForward();
	            forward.setRedirect(false);
	            forward.setPath("./business_log/business_write.jsp");
	        //업무일지 입력 기능
	        }else if(command.equals("/BusinessAddAction.bl")){
				action = new BusinessAddAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/BusinessNotice.bl")){
				action = new BusinessNoticeAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else if(command.equals("/BusinessDetailAction.bo")){
				action = new BusinessDetailAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//	        else if(command.equals("/BusinessModify.bo")){
//				action=new BusinessModifyView();
//				try {
//					forward=action.execute(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}else if(command.equals("/BusinessModifyAction.bo")){
//				action=new BusinessModifyAction();
//				try {
//					forward=action.execute(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}else if(command.equals("/BusinessDelete.bo")){
//				forward=new ActionForward();
//				forward.setRedirect(false);
//				forward.setPath("./business_log/business_delete.jsp");
//			}else if(command.equals("/BusinessDeleteAction.bo")){
//				action=new BusinessDeleteAction();
//				try {
//					forward=action.execute(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//
//			}
	        
	//이동
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
