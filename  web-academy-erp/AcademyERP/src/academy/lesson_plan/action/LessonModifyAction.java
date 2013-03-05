package academy.lesson_plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.lesson_plan.db.LessonBean;
import academy.lesson_plan.db.LessonDAO;

public class LessonModifyAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		request.setCharacterEncoding("utf-8");
		ActionForward forward = new ActionForward();
		
		
		LessonBean lessonbean = new LessonBean();
		lessonbean.setLesson_num(Integer.parseInt(request.getParameter("lesson_num")));
		lessonbean.setLesson_teacher(request.getParameter("lesson_teacher"));
		lessonbean.setLesson_subject(request.getParameter("lesson_subject"));
		lessonbean.setLesson_book(request.getParameter("lesson_book"));
		lessonbean.setLesson_time(request.getParameter("lesson_time"));
		lessonbean.setLesson_cost(request.getParameter("lesson_cost"));
		lessonbean.setLesson_content(request.getParameter("lesson_content"));
		lessonbean.setLesson_goal(request.getParameter("lesson_goal"));
		
		LessonDAO lessondao = new LessonDAO();
		lessondao.lessonModify(lessonbean);
				
		forward.setRedirect(true);
		forward.setPath("./LessonListAction.le");
		return forward;
				
	}

}
