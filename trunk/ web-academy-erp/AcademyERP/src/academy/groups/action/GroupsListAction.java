package academy.groups.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.groups.db.GroupsDAO;
import academy.master.db.ListPackage;

public class GroupsListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("id");
		String name = (String) session.getAttribute("name");
		
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
		
		GroupsDAO groups = new GroupsDAO();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = groups.getCount(id);
		List classlist = groups.getGroupsList(page, limit, id);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int pageBlock = 10;
		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)
				* pageBlock + 1;
		int endpage = startpage + pageBlock - 1;
		if (endpage > maxpage)
			endpage = maxpage;
		ListPackage pack = new ListPackage(page, maxpage, startpage, endpage,
				listcount, classlist);
		System.out.println(page + "," + maxpage + "," + startpage + ","
				+ endpage + "," + listcount);
		System.out.println("GroupsListAcion Finished");
		List roomlist = groups.getRoomList();
		request.setAttribute("ListPackage", pack);
		request.setAttribute("roomlist", roomlist);
		forward.setPath("./groups/groupsList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
