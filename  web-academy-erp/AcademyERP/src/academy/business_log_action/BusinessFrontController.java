package academy.business_log_action;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.board.action.Action;
import academy.board.action.ActionForward;
import academy.board.action.BoardAddAction;

public class BusinessFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	String requestURI = request.getRequestURI();
	        String contextPath = request.getContextPath();
	        String command = requestURI.substring(contextPath.length());
	        ActionForward forward = null;
	        Action action = null;
	        
	        if (command.equals("/BusinessLog.bo")) {
	            forward = new ActionForward();
	            forward.setRedirect(false);
	            forward.setPath("./board/business_log_write.jsp");
	        }else if(command.equals("/BusinessAddAction.bo")){
				action = new BoardAddAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        
	}

	

}
