package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeTsearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        request.setCharacterEncoding("UTF-8");
        
        //선생 이름 값 받기
        String ep_id = request.getParameter("ep_id");
        GradeDAO gradedao = new GradeDAO();
        List gradeTsearch = gradedao.gradeTsearch(ep_id);
        
        request.setAttribute("gradeTsearch", gradeTsearch);
        
        forward.setRedirect(false);
        forward.setPath("./grade/teacher_search.jsp");
        return forward;
    }

}
