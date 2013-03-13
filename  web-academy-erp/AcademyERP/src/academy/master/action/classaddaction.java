package academy.master.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.master.db.MasterDAO;

public class classaddaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String sel = request.getParameter("subject_sel");
		int level = (Integer.parseInt((String) session.getAttribute("level")));
		/* 세션 검증 부분 */
		if (level < 4) {
			// 뒤로 보넴
		}
		MasterDAO master = new MasterDAO();
		List tlist = master.getTeachserList();
		request.setAttribute("tList", tlist);
		request.setAttribute("sub", sel);
		forward.setPath("./master/addClass.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
