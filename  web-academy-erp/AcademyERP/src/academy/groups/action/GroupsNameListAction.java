package academy.groups.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;
import academy.student.db.StudentDAO;

public class GroupsNameListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		int level = Integer.parseInt((String) session.getAttribute("level"));
		/* 권한 확인 */
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
		GroupsDAO groupsdao = new GroupsDAO(); // 계설된 과목의 리스트를 가지고오기 위해 선언
		
		List Gp_nameList = null;
		
		Gp_nameList = groupsdao.getGpList();
		request.setAttribute("Gp_nameList", Gp_nameList); // 가지고온 과목명을 저장후 넘김
		
		forward.setRedirect(false);
		forward.setPath("./group/group_attitude_list.jsp");
		return forward;
	}

}
