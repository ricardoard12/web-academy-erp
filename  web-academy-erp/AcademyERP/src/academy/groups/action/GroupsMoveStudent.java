package academy.groups.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;

// 학급 학생 이동 폼 로드
public class GroupsMoveStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsMoveStudent");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (level < 3) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		GroupsDAO groupsDAO = new GroupsDAO();
		List groupsList = new ArrayList();
		
		String chkValue = request.getParameter("chkValue");
		String gp_name = request.getParameter("gp_name");
		
		groupsList = groupsDAO.getGroupsMoveList();
		
		request.setAttribute("gp_name", gp_name);
		request.setAttribute("groupsList", groupsList);
		request.setAttribute("chkValue", chkValue); // 체크 박스 값 결합시킨 채로 그대로 전달
		
		forward.setRedirect(false);
		forward.setPath("./groups/groups_move_list.jsp");
		return forward;
	}

}
