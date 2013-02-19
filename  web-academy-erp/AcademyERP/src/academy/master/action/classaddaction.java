package academy.master.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class classaddaction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward =new ActionForward();
		request.setCharacterEncoding("utf-8");
		
		/*
		 * 세션 설정 
		 * */
		MasterDAO master=new MasterDAO();
		List tlist =master.getTeachserList();		
		request.setAttribute("tList", tlist);
		request.setAttribute("sub", request.getParameter("subject_sel"));
		forward.setPath("./master/addClass.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
