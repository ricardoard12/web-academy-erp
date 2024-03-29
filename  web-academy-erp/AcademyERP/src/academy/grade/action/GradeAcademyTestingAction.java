package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyTestingAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        //학원 시험중인 자료가져오는 기준
        String status = "N";
        String gp_name = request.getParameter("gp_name");
        List gradeAcademyTesting = gradedao.gradeAcademyTest(status);
        
        request.setAttribute("gradeAcademyTesting", gradeAcademyTesting);
        forward.setRedirect(false);
        forward.setPath("./grade/grade_academy_testing.jsp");
        return forward;
    }

}
