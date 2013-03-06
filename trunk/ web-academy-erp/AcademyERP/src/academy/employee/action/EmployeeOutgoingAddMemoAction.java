package academy.employee.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.employee.db.EmployeeDAO;

public class EmployeeOutgoingAddMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeOutgoingAddMemoAction");
		request.setCharacterEncoding("UTF-8");
		
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		String id = request.getParameter("id");
		String ep_memo = request.getParameter("ep_memo");
		
		System.out.println(ep_memo);
		
		boolean result = employeeDAO.employeeOutgoingAddMemo(id, ep_memo);
		
		if (result == true) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('메모 작성이 완료되었습니다.')");
			out.println("opener.location.reload()"); //부모창 새로고침 후 자식창 닫기
			out.println("window.close()");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html;charset=UTF-8");
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