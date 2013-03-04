package academy.lesson_plan.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.lesson_plan.db.LessonDAO;

public class LessonListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("Lesson_Plan Start");
		ActionForward forward = new ActionForward();
		request.setCharacterEncoding("utf-8");
		String level = request.getParameter("level");
		System.out.println(level);
		HttpSession session = request.getSession();
			
		LessonDAO lessondao = new LessonDAO();

		List lessonlist = new ArrayList();
		int page = 1;
		int limit = 10;

		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}

		int listcount = lessondao.getListCount();


		lessonlist = lessondao.getLessonList(page, limit);

		int maxpage = (int) ((double) listcount / limit + 0.95);

		int pageBlock = 10;

		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)
				* pageBlock + 1;

		int endpage = startpage + pageBlock - 1;
		if (endpage > maxpage)
			endpage = maxpage;

		// 값저장
		request.setAttribute("page", page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage", endpage);
		request.setAttribute("listcount", listcount);
		request.setAttribute("lessonlist", lessonlist);

		forward.setRedirect(false);
		forward.setPath("./lesson/lesson_list.jsp");
		return forward;
	}

}
