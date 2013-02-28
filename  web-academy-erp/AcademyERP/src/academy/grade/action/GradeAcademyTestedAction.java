package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyTestedAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        //학원 시험 완료된 자료가져오는 기준
        String status = "Y";
        List gradeAcademyTested = gradedao.gradeAcademyTest(status);
        
        request.setAttribute("gradeAcademyTested", gradeAcademyTested);
        forward.setRedirect(false);
        forward.setPath("./grade/grade_academy_tested.jsp");
        return forward;
    }

}
