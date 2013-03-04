package academy.groups.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class GroupsAttitudeCancelAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsAttitudeCancelAction");
		request.setCharacterEncoding("UTF-8");
		
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
		
		boolean result = attitudeDAO.studentAttitudeCancel(id, type, date);
		if (result == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('취소 처리가 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
				return null;
		}
		
		request.setAttribute("gp_name", gp_name);
		request.setAttribute("date", date);
		
		forward.setRedirect(false);
		forward.setPath("./GroupsAttitudeListAction.gp");
		return forward;
	}

}
