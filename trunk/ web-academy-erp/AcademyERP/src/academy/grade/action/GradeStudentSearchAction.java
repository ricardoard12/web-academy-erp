package academy.grade.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeStudentSearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        forward.setRedirect(false);
        forward.setPath("./grade/student_id_search.jsp");
        return forward;
    }

}
