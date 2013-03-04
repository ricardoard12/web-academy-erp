package academy.lesson_plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.lesson_plan.db.LessonBean;
import academy.lesson_plan.db.LessonDAO;

public class LessonAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		LessonBean lessonbean = new LessonBean();
		LessonDAO lessondao = new LessonDAO();
		ActionForward forward = new ActionForward();
		
		try {

			lessonbean.setLesson_subject(request.getParameter("lesson_subject"));
			lessonbean.setLesson_teacher(request.getParameter("lesson_teacher"));
			lessonbean.setLesson_book(request.getParameter("lesson_book"));
			lessonbean.setLesson_goal(request.getParameter("lesson_goal"));
			lessonbean.setLesson_cost(request.getParameter("lesson_cost"));
			lessonbean.setLesson_time(request.getParameter("lesson_time"));
			lessonbean.setLesson_content(request.getParameter("lesson_content"));
			
			lessondao.lessoninsert(lessonbean);
			forward.setPath("./lesson/lesson_write.jsp");
			forward.setRedirect(false);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
