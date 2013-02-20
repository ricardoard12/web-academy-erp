package academy.groups.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsDAO;
import academy.student.db.StudentDAO;

public class GroupsNmaeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		ActionForward forward = new ActionForward();
		GroupsDAO groupsdao = new GroupsDAO(); // 계설된 과목의 리스트를 가지고오기 위해 선언
		
		List Gp_nameList = null;
		
		Gp_nameList = groupsdao.getGpList();
		request.setAttribute("Gp_nameList", Gp_nameList); // 가지고온 과목명을 저장후 넘김
		
		forward.setRedirect(false);
		forward.setPath("./group/group_attitude_list.jsp");
		return forward;
	}

}
