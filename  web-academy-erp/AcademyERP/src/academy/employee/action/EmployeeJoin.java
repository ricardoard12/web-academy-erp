package academy.employee.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.employee.db.EmployeeDAO;
import academy.member.db.MemberDAO;

// 신규 직원 등록 폼 로드
public class EmployeeJoin implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeJoin");
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
		MemberDAO memberDAO = new MemberDAO();
		List managerList = new ArrayList(); 
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		
		String mm_level = request.getParameter("mm_level");
		if (mm_level == null) {
			mm_level = "3";
		}
		
		managerList = employeeDAO.getManagerList(mm_level);
		
		// ID 자동 부여를 위한 오늘 날짜에 가입한 아이디 확인 및 새 ID 부여
		String newID = memberDAO.getNewEmployeeID("T" + sdf.format(System.currentTimeMillis()));
		
		request.setAttribute("newID", newID);
		request.setAttribute("managerList", managerList);
		request.setAttribute("level", mm_level);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_join.jsp");
		return forward;
	}

}
