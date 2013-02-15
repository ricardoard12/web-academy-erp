package academy.master.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.master.db.MasterDAO;

public class LevelupdateAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String findname = request.getParameter("findname");
		/*
		 * session 검증 부분 HttpSession session=request.getSession(); MemberBean
		 * member=(MemberBean)session.getParameter("세션 멤버명");
		 * if(!member.getMm_level.equals("5")){ 뒤로 보내던지 에러 메세지 보내던지 로그인 화면으로
		 * 보내던지 ㅎㅎ }
		 */
		System.out.println("updateEvent");
		new MasterDAO().updateLevel(request.getParameter("id"),
				request.getParameter("level"));
		request.setAttribute("findname", findname);
		forward.setPath("./LevelList.master");
		forward.setRedirect(true);
		return forward;
	}

}
