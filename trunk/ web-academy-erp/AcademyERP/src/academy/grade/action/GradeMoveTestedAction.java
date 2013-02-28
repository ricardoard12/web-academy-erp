package academy.grade.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeMoveTestedAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("utf-8");
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        String[] check = request.getParameterValues("check");
        
        gradedao.gradeAcademyMoveTested(check);
        
        response.setContentType("text/html; charset=utf-8");
        PrintWriter out = response.getWriter();
        out.println("<script>");
        out.println("alert('시험완료되었습니다');");
        out.println("location.href='./GradeAcademyTesting.gr'");
        out.println("</script>");
        out.close();
        
        return null;
    }

}
