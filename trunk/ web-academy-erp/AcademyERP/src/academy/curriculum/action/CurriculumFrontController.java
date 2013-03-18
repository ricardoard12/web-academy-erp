package academy.curriculum.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import academy.employee.action.EmployeeAttitudeTimeRecordingAction;

public class CurriculumFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 주소값 가져오기
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;
		
		if(command.equals("/Curriculumkor.cr")){
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("./curriculum/curriculumkor.jsp");
		}
		else if(command.equals("/Curriculummath.cr")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./curriculum/curriculummath.jsp");
		}
		else if(command.equals("/Curriculumeng.cr")){
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./curriculum/curriculumeng.jsp");
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
