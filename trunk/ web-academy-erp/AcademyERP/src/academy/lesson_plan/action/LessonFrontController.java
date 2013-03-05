package academy.lesson_plan.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LessonFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
			request.setCharacterEncoding("utf-8");
			String requestURI = request.getRequestURI();
	        String contextPath = request.getContextPath();
	        String command = requestURI.substring(contextPath.length());
	        ActionForward forward = null;
	        Action action = null;
	        
	        //강의계획서 입력 폼
	        if (command.equals("/LessonWrite.le")) {
	            forward = new ActionForward();
	            forward.setRedirect(false);
	            forward.setPath("./lesson/lesson_write.jsp");
	        //강의계획서 입력 기능
	        }else if(command.equals("/LessonList.le")){
	        	forward = new ActionForward();
	            forward.setRedirect(false);
	            forward.setPath("./lesson/lesson_list.jsp");
	        }else if(command.equals("/LessonAddAction.le")){
	        	action = new LessonAddAction();
	        	try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
	        }else if(command.equals("/LessonListAction.le")){
				action = new LessonNoticeAction();
				try {
					forward=action.execute(request, response);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
//	        else if(command.equals("/BusinessDetailAction.bl")){
//				action = new BusinessDetailAction();
//				try {
//					forward=action.execute(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			else if(command.equals("/BusinessModifyAction.bl")){
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
//			}else if(command.equals("/BusinessDeleteAction.bl")){
//				action=new BusinessDeleteAction();
//				try {
//					forward=action.execute(request, response);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
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
