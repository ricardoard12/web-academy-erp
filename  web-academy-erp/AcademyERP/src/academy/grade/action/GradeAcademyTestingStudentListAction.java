package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyTestingStudentListAction implements Action {

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        GradeDAO gradedao = new GradeDAO();
        
        String gp_name = request.getParameter("gp_name");
        List gradeAcademyTestingStudentList = gradedao.gradeAcademyTestingStudentList(gp_name);
        
        request.setAttribute("gradeAcademyTestingStudentList", gradeAcademyTestingStudentList);
        
        forward.setRedirect(false);
        forward.setPath("./grade/grade_academy_testing_student_list.jsp");
        return forward;
    }

}
