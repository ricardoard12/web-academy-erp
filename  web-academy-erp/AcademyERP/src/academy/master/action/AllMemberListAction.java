package academy.master.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.master.db.MasterDAO;
import academy.member.db.MemberBean;

public class AllMemberListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		int toggle=0;
		String col="";
		// session 검증 소스
		HttpSession session=request.getSession();
		// MemberBean member=(MemberBean)session.getAttribute("세션 멤버이름");
		List AllList=(List)new MasterDAO().getAllMemberList(toggle,col);
		forward.setPath("./master/AllMemberList.jsp");
		forward.setRedirect(false);
		return forward;
	}
}
