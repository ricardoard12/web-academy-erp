package academy.master.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class classstatusupdate implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		/**
		 * 자격 검증
		 */
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String id=request.getParameter("id");
		String status=request.getParameter("status");
		new MasterDAO().updateStatus(id,status);
		request.setAttribute("page", page);
		forward.setPath("./ClassList.master");
		forward.setRedirect(true);
		return forward;
	}

}
