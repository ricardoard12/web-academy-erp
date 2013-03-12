package academy.employee.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;
import academy.employee.db.EmployeeDAO;

// 직원 출결 현황
public class EmployeeAttitudeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeListAction");
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
		AttitudeBean attitude = new AttitudeBean();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}
		System.out.println(date);
		
		List attitudeList = new ArrayList();
		attitudeList = attitudeDAO.getEmployeeAttitudeList(date);
		
		request.setAttribute("attitudeList", attitudeList);
		request.setAttribute("date", date);
		
		forward.setRedirect(false);
		forward.setPath("./employee/employee_attitude_list.jsp");
		return forward;
	}
	
}
