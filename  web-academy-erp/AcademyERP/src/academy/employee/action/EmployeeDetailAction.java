package academy.employee.action;

import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.employee.db.EmployeeBean;
import academy.employee.db.EmployeeDAO;
import academy.member.db.MemberBean;

// 직원 정보 상세 조회
public class EmployeeDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeDetailAction");
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
		EmployeeBean employee = new EmployeeBean();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		MemberBean member = new MemberBean();
		Vector vector = null;
		
		String id = request.getParameter("id"); // 조회할 직원 ID 값 받아오기
		
		vector = employeeDAO.getEmployeeDetail(id);
		member = (MemberBean)vector.get(0);
		employee = (EmployeeBean)vector.get(1);
		
		if (member == null || employee == null) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('조회할 직원이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
		}
		
		request.setAttribute("member", member);
		request.setAttribute("employee", employee);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_detail.jsp");
		return forward;
	}

}
