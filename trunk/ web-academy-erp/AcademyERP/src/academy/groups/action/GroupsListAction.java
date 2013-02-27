package academy.groups.action;

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
		if (level <= 3) {
			// 레벨보다 낮으면 접근 금지

		}
		GroupsDAO groups=new GroupsDAO();
		int page=1;
		int limit=10;
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}
		int listcount=groups.getCount(id);
		List groupslist=groups.getGroupsList(page,limit,id);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int pageBlock = 10;
		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)
				* pageBlock + 1;
		int endpage = startpage + pageBlock - 1;
		if (endpage > maxpage)
			endpage = maxpage;
		ListPackage pack = new ListPackage(page, maxpage, startpage, endpage,
				listcount, groupslist);
		System.out.println(page+","+maxpage+","+startpage+","+endpage+","+listcount);

		request.setAttribute("groupslist", pack);

		forward.setPath("./groups/groupsList.jsp");
		forward.setRedirect(false);
		return forward;
	}

}
