package academy.employee.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.employee.db.EmployeeDAO;

// 퇴직자 목록 조회
public class EmployeeOutgoingListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeListAction");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (sid == null || sid.equals("") || level < 4) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		Vector vector = new Vector();
		List employeeList = new ArrayList();
		List memberList = new ArrayList();
		
		vector = employeeDAO.getEmployeeOutgoingList();
		employeeList = (List)vector.get(0);
		memberList = (List)vector.get(1);
		
		request.setAttribute("employeeList", employeeList);
		request.setAttribute("memberList", memberList);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_outgoing_list.jsp");
		return forward;
	}

}
