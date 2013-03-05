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
			forward.setPath("./LessonListAction.le");
//			path값을 ./lesson/lesson_list.jsp로 할 경우 한글처리 값 또는 name값이 깨져서 출력된다.
//			그래서 LessonList.le로 FrontController로 돌아가게 했지만 한글은 꺠지지 않으나 물음표로 한글이 처리된다. 
			forward.setRedirect(false);
					
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
