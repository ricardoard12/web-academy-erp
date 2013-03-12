package academy.employee.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// 퇴직자 사유 메모창 로드
public class EmployeeOutgoingMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeOutgoingMemoAction");
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
		
		String id = request.getParameter("id");
		String ep_memo = request.getParameter("ep_memo");
		
		request.setAttribute("id", id);
		request.setAttribute("ep_memo", ep_memo);
		
		System.out.println("Memo Action memo : " + ep_memo);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_outgoing_memo.jsp");
		return forward;
	}

}
