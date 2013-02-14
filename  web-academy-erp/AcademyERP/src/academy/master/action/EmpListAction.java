package academy.master.action;

import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class EmpListAction implements Action {

	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		/*
		 * 세션값 확인 level 값으로 판단한다. HttpSession session = request.getSession();
		 * MemberBean member = (MemberBean) session.getAttribute("세션값");
		 * if(member.getMm_level()!=5){ response.sendRedirect(""); }
		 */
		Vector emplist = new MasterDAO().getEmplist(name);
		request.setAttribute("empList", emplist);
		forward.setPath("./master/employee_list.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
