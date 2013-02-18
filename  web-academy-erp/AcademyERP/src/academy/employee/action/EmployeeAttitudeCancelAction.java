package academy.employee.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class EmployeeAttitudeCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeCancelAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		boolean result = attitudeDAO.employeeAttitudeCancel(id, type);
		if (result == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('취소 처리가 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
				return null;
		}
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeAttitudeListAction.em");
		return forward;
	}

}
