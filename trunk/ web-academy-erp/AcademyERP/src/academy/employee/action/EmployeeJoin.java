package academy.employee.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.employee.db.EmployeeDAO;

// 신규 직원 등록 폼 로드
public class EmployeeJoin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeJoin");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (level < 4) {
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
		List managerList = new ArrayList(); 
		
		String mm_level = request.getParameter("mm_level");
		if (mm_level == null) {
			mm_level = "3";
		}
		
		System.out.println(mm_level);
		
		managerList = employeeDAO.getManagerList(mm_level);
		
		request.setAttribute("managerList", managerList);
		request.setAttribute("level", mm_level);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_join.jsp");
		return forward;
	}

}
