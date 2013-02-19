package academy.master.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class classdeleteaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		MasterDAO master = new MasterDAO();
		String[] arr = (String[]) request.getParameterValues("classlistchk");
		for (int i = 0; i < arr.length; i++) {
			master.deleteclass(arr[i]);
		}
		request.setAttribute("page", page);
		forward.setPath("./ClassList.master");
		forward.setRedirect(false);
		return forward;
	}

}
