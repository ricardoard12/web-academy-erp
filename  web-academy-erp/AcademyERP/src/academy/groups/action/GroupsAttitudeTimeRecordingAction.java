package academy.groups.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.attitude.db.AttitudeDAO;

// 학급 학생 출결 시간 등록
public class GroupsAttitudeTimeRecordingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsAttitudeTimeRecordingAction");
		request.setCharacterEncoding("UTF-8");

		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (sid == null || sid.equals("") || level < 3) {
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
		String type = request.getParameter("type");
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

		String gp_name = request.getParameter("gp_name");
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}

		int result = attitudeDAO.studentAttitudeTimeRecording(id, type, date);

		if (result == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('출석 시간을 먼저 등록하세요.')");
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
	
		request.setAttribute("id", id);	
		request.setAttribute("date", date);
		request.setAttribute("gp_name", gp_name);
		request.setAttribute("type", type);

		
		forward.setRedirect(false);
		forward.setPath("./SendAttitudeSmsAction.sms");
		return forward;
	}

}
