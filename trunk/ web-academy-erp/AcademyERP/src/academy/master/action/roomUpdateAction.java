package academy.master.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class roomUpdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String page = request.getParameter("page");
		String id = request.getParameter("id");
		String room = request.getParameter("room");
		int ea=Integer.parseInt(request.getParameter("ea"));
		new MasterDAO().updateRoom(id, room,ea);
		request.setAttribute("page", page);
		forward.setPath("./ClassList.master");
		forward.setRedirect(true);
		return forward;
	}

}
