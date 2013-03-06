package academy.timetable.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.timetable.db.TimetableDAO;

public class InsertSubjectAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String sub_name = "";
		String ep_id = "";
		String gp_idx=request.getParameter("gp_idx");
		String day = request.getParameter("day");
		String lesson = request.getParameter("lesson");
		if (request.getParameter("sub_name") != null) {
			sub_name = request.getParameter("sub_name");
		}

		if (request.getParameter("ep_id") != null) {
			ep_id = request.getParameter("ep_id");
		}

		TimetableDAO timetable = new TimetableDAO();
		List subList = timetable.getSubject();
		List ep_list = timetable.getEpList(sub_name);
		request.setAttribute("sub_list", subList);
		request.setAttribute("ep_list", ep_list);
		request.setAttribute("sub_name", sub_name);
		request.setAttribute("gp_idx", gp_idx);
		forward.setPath("./timetable/InsertSubject.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
