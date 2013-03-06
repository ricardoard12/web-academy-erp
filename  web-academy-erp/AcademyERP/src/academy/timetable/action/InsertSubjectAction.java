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
		forward.setPath("./timetable/InsertSubject.jsp");
		forward.setRedirect(false);
		request.setCharacterEncoding("utf-8");
		String sub_name = request.getParameter("sub_name");
		String day = request.getParameter("day");
		String lesson = request.getParameter("lesson");
		TimetableDAO timetable = new TimetableDAO();
		List subList = timetable.getSubject();
		request.setAttribute("subject_list", subList);
		request.setAttribute("sub_name", sub_name);
		forward.setPath("./timetable/insertSubject.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
