package academy.faq_board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FaqBoardFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 	String requestURI = request.getRequestURI();
	        String contextPath = request.getContextPath();
	        String command = requestURI.substring(contextPath.length());
	        ActionForward forward = null;
	        Action action = null;
	        
	        if (command.equals("/Faq_boardWrite.fb")) {
	            forward = new ActionForward();
	            forward.setRedirect(false);
	            forward.setPath("./faq_board/faq_board_write.jsp");
	        //업무일지 입력 기능
	        }else if(command.equals("/Faq_BoardAddAction.fb")){
				action = new Faq_BoardAddAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Faq_boardList.fb")){
				action = new Faq_BoardListAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Faq_BoardDeleteAction.fb")){
				action = new Faq_BoardDeleteAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Faq_BoardDetailAction.fb")){
				action = new Faq_BoardDetailAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}else if(command.equals("/Faq_BoardModifyAction.fb")){
				action = new Faq_BoardModifyAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
	        
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

