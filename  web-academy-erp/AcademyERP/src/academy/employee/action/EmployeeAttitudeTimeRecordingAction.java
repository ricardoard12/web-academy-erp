package academy.employee.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;
import academy.employee.db.EmployeeDAO;

public class EmployeeAttitudeTimeRecordingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAttitudeTimeRecordingAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}
		
		int result = attitudeDAO.employeeAttitudeTimeRecording(id, type, date);
		
		if (result == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('출근 시간을 먼저 등록하세요.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		} else if (result == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시간 기록을 실패하였습니다.')");
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
