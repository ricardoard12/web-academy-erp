package academy.timetable.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.timetable.db.TimetableDAO;

public class InsertTimeTable implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		List<String> list = new ArrayList<String>();
		list.add(request.getParameter("ep_id"));
		list.add(request.getParameter("gp_idx"));
		list.add(request.getParameter("day"));
		list.add(request.getParameter("lesson"));
		list.add(request.getParameter("sub_name"));
		TimetableDAO timetable = new TimetableDAO();
		if (!timetable.checkTimetable(list)) {
			// 뒤로 보내고 중복 알림 메세지와 함께 샤바샤바
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('강사의 시간배치가 중복 됩니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		timetable.updateTimetable(list);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("opener.location.reload();");
		out.println("window.close();");
		out.println("</script>");
		out.close();
		return null;
	}

}
