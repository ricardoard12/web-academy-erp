package academy.grade.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeDAO;

public class GradeAcademyTestingStudentAddAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        ActionForward forward = new ActionForward();
        List st_id_list = new ArrayList();
        List gr_score_list = new ArrayList();
        
        //학생아이디 st_id
        String[] check = (String[])request.getParameterValues("check");
        for(int i=0; i<check.length; i++){
            st_id_list.add(check[i]);
        }
        //학생성적
        String[] gr_score = (String[])request.getParameterValues("gr_score");
        //그룹이름
        for(int i=0; i<gr_score.length; i++){
            gr_score_list.add(gr_score[i]);
        }
        
        String gp_name = request.getParameter("gp_name");
        String gr_code = request.getParameter("gr_code");
        
        GradeDAO gradedao = new GradeDAO();
        
        gradedao.insertTestingStudentAdd(st_id_list, gr_score_list, gr_code);
        
        forward.setRedirect(false);
        forward.setPath("./GradeAcademyTestingStudentList.gr?" +
        		"gr_code="+gr_code+"&gp_name="+gp_name);
        return forward;
    }

}
