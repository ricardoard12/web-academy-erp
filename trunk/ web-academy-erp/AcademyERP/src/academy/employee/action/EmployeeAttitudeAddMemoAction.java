package academy.employee.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class EmployeeAttitudeAddMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeMemoAddAction");
		request.setCharacterEncoding("UTF-8");
		
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String at_memo = request.getParameter("at_memo");
		
		boolean result = attitudeDAO.employeeAttitudeAddMemo(id, at_memo);
		
		if (result == true) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메모 작성이 완료되었습니다.')");
			out.println("window.close()");
			out.println("</script>");
			out.close();
			return null;
		} else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메모 작성이 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
