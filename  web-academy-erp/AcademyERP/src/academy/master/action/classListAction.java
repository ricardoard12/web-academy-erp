package academy.master.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsDAO;
import academy.master.db.ListPackage;
import academy.master.db.MasterDAO;

public class classListAction implements Action {
	/* by Young-Ho ver 0.5 */
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		/**
		 * session 처리 로그인 객체확인후 진행
		 */

		List classlist=null;
		MasterDAO master = new MasterDAO();
		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listcount = master.getCount();
		classlist = master.getClassList(page, limit);
		int maxpage = (int) ((double) listcount / limit + 0.95);
		int pageBlock = 10;
		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)
				* pageBlock + 1;
		int endpage = startpage + pageBlock - 1;
		if (endpage > maxpage)
			endpage = maxpage;
		ListPackage pack = new ListPackage(page, maxpage, startpage, endpage,
				listcount, classlist);
		List roomlist=new GroupsDAO().getRoomList();
		System.out.println(page+","+maxpage+","+startpage+","+endpage+","+listcount);
		System.out.println("ClassListAcion Finished");
		request.setAttribute("roomlist", roomlist);
		request.setAttribute("ListPackage", pack);
		forward.setPath("./master/ListClass.jsp");
		forward.setRedirect(false);

		return forward;
	}
}
