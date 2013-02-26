package academy.grade.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeIDSearchAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        
        request.setCharacterEncoding("UTF-8");
        
        String id = "";
        //학생 이름 or 선생 이름 값 받기
        id = request.getParameter("id");
        System.out.println(id);
        GradeDAO gradedao = new GradeDAO();
        List searchlist = gradedao.IDSearch(id);
        
        if(searchlist ==null){
        System.out.println("값없음");
        }
        request.setAttribute("searchlist", searchlist);
        
        forward.setRedirect(false);
        forward.setPath("./grade/student_id_search.jsp");
        return forward;
    }

}
