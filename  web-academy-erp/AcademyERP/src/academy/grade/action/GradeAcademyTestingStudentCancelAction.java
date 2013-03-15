package academy.grade.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyTestingStudentCancelAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        String score = request.getParameter("score");
        String id = request.getParameter("id");
        
        
        String gr_code = request.getParameter("gr_code");
        String gp_name = request.getParameter("gp_name");
        
        System.out.println(gr_code + gp_name);
        GradeDAO gradedao = new GradeDAO();
        
        gradedao.gradeAcademyTestingCancel(score, id);
        
        response.sendRedirect("./GradeAcademyTestingStudentList.gr?gr_code="+gr_code+"&gp_name="+gp_name);
        
        return null;
    }

}
