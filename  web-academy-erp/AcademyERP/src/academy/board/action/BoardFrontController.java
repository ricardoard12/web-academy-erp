package academy.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BoardFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if (command.equals("/BoardWrite.bo")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./board/board_write.jsp");
        }else if(command.equals("/BoardAddAction.bo")){
			action = new BoardAddAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardNotice.bo")){
			action = new BoardNoticeAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDetailAction.bo")){
			action = new BoardDetailAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModify.bo")){
			action=new BoardModifyView();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardModifyAction.bo")){
			action=new BoardModifyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardDelete.bo")){
			forward=new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./board/board_delete.jsp");
		}else if(command.equals("/BoardDeleteAction.bo")){
			action=new BoardDeleteAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReply.bo")){
			//  BoardReplyView()
			action=new BoardReplyView();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else if(command.equals("/BoardReplyAction.bo")){
			action=new BoardReplyAction();
			try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
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
