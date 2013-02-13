package academy.student.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if (command.equals("/StudentJoinAction.st")) {
        	action = new StudentJoinAction();
        	try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentJoin.st")){
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./student/student_join.jsp");
        }else if(command.equals("/StudentListAction.st")){
        	action= new StudentListAction();
        	
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentLeaveofabsence.st")){
        	action = new StudentLeaveofabsenceAction();
        	
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentExpel.st")){
        	action = new StudentExpelAction();
        	
        	try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if( command.equals("/StudentAttitudeList.st")){
        	action = new StudentAttitudeListAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
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
