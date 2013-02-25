package academy.grade.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if(command.equals("/GradeJoin.gr")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./grade/grade_register.jsp");
        }else if(command.equals("/GradeJoinAction.gr")) {
            action = new GradeJoinAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        }else if(command.equals("/GradeAcademyList.gr")) {
            action = new GradeAcademyListAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        }else if(command.equals("/GradeSchoolList.gr")) {
            action = new GradeSchoolListAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        }else if(command.equals("/GradeStudentSearch.gr")) {
            action = new GradeStudentSearchAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        }
        
        // 이동
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }
    }

}
