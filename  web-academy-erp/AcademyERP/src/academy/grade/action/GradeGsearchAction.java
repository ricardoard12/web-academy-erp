package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeGsearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        GradeDAO gradedao = new GradeDAO();
        List gradeGsearch = gradedao.gradeGsearch();
        
        request.setAttribute("gradeGsearch", gradeGsearch);
        
        forward.setRedirect(false);
        forward.setPath("./grade/group_search.jsp");
        return forward;
    }

}
