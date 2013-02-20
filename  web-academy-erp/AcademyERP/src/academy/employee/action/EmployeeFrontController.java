package academy.employee.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 주소값 가져오기
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/EmployeeJoin.em")) {
			action = new EmployeeJoin();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeJoinAction.em")) {
			action = new EmployeeJoinAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeListAction.em")) {
			action = new EmployeeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeListAction.em")) {
			action = new EmployeeAttitudeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeDetailAction.em")) {
			action = new EmployeeDetailAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeOutgoingAction.em")) {
			action = new EmployeeOutgoingAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeOutgoingListAction.em")) {
			action = new EmployeeOutgoingListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeTimeRecordingAction.em")) {
			action = new EmployeeAttitudeTimeRecordingAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeMemoAction.em")) {
			action = new EmployeeAttitudeMemoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeAddMemoAction.em")) {
			action = new EmployeeAttitudeAddMemoAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeCancelAction.em")) {
			action = new EmployeeAttitudeCancelAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/EmployeeAttitudeEditTime.em")) {
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("./employee/employee_attitude_editTime.jsp");
		} else if (command.equals("/EmployeeAttitudeEditTimeAction.em")) {
			action = new EmployeeAttitudeEditTimeAction();
			try {
				forward = action.execute(request, response);
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
