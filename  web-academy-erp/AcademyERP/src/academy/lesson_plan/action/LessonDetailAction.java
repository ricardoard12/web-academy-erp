package academy.lesson_plan.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.lesson_plan.db.LessonBean;
import academy.lesson_plan.db.LessonDAO;




public class LessonDetailAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		LessonBean lessonbean = new LessonBean();
		LessonDAO lessondao = new LessonDAO();
		
		
		ActionForward forward=new ActionForward();
		int num = Integer.parseInt(request.getParameter("num"));
		System.out.println("현재 num값 : "+num);
//		String name = (String) request.getParameter("name");
		HttpSession session = request.getSession();
		String name = (String) session.getAttribute("name");
		
		System.out.println("현재 name값 : " + name);
		
		
		lessonbean = lessondao.getDetail(num);
		boolean usercheck = lessondao.userchk(num, name);

		if(usercheck == false){
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('본인의 게시물이 아닙니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		request.setAttribute("lessonbean", lessonbean);
		forward.setRedirect(false);
		forward.setPath("./lesson/lesson_detail.jsp");
		return forward;
	}

}
