package academy.master.action;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsBean;

public class classSetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

		request.setCharacterEncoding("utf-8");
		GroupsBean groups = new GroupsBean();
		groups.setEp_id(request.getParameter("teacher_sel"));
		
		sdfDate.format(Calendar.getInstance().getTime());

		forward.setPath("./classList.master");
		forward.setRedirect(true);
		return forward;
	}

}
