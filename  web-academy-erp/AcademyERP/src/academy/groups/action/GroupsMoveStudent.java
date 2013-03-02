package academy.groups.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsDAO;

public class GroupsMoveStudent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("GroupsMoveStudent");
		request.setCharacterEncoding("UTF-8");
		
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
