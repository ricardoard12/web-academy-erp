package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeSchoolListAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        List gradeSchoolList = gradedao.gradeSchoolList();
        
        request.setAttribute("gradeSchoolList", gradeSchoolList);
        forward.setRedirect(false);
        forward.setPath("./grade/grade_school_list.jsp");
        return forward;
    }

}
