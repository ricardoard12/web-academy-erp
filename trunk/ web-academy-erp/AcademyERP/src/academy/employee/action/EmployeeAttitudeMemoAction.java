package academy.employee.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.attitude.db.AttitudeDAO;

// 직원 출,퇴근 메모창 로드
public class EmployeeAttitudeMemoAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeMemoAction");
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
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String at_memo = request.getParameter("at_memo");
		String date = null;
		date = request.getParameter("date");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		if (date == null || date.equals("")) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}
		
		System.out.println("EmployeeAttitudeMemoAction의 Date : " + date);
		request.setAttribute("id", id);
		request.setAttribute("at_memo", at_memo);
		request.setAttribute("date", date);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_memo.jsp");
		return forward;
	}

}
