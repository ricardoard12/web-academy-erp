package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyListAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        List gradeAcademyList = gradedao.gradeAcademyList();
        
        request.setAttribute("gradeAcademyList", gradeAcademyList);
        forward.setRedirect(false);
        forward.setPath("./grade/grade_academy_list.jsp");
        return forward;
    }

}
