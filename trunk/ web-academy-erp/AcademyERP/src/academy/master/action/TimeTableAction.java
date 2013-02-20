package academy.master.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TimeTableAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		/**
		 *  session 값 확인
		 *  request.setCharterset("utf-8");
		 *   HttpSession session =request.getSession();
		 */
		forward.setPath("./timetable/timetableset.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
