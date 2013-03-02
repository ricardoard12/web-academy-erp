package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeSsearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        request.setCharacterEncoding("UTF-8");
        
        //학생 이름 값 받기
        String mm_name = request.getParameter("mm_name");
        GradeDAO gradedao = new GradeDAO();
        List gradeSsearch = gradedao.gradeSsearch(mm_name);
        
        request.setAttribute("gradeSsearch", gradeSsearch);
        
        forward.setRedirect(false);
        forward.setPath("./grade/student_search.jsp");
        return forward;
    }

}
