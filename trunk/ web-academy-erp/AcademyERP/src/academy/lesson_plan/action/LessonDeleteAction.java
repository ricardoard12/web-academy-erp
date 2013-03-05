package academy.lesson_plan.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.lesson_plan.db.LessonDAO;


public class LessonDeleteAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		
		
		LessonDAO lessondao = new LessonDAO();
		
		ActionForward forward=new ActionForward();
		
		String[] lesson_check = request.getParameterValues("lesson_check");
		
		for(int i=0 ; i< lesson_check.length ; i++){
			System.out.println(lesson_check[i]);
		}
		
		
		boolean result = lessondao.lessonDelete(lesson_check);
		
		if(result==false){
			System.out.println("삭제실패");
		}
		System.out.println("삭제성공");
		
		forward.setRedirect(false);
		forward.setPath("./LessonListAction.le");
		return forward;
	}

}
