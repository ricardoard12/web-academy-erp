package academy.grade.action;

import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.grade.db.GradeBean;
import academy.grade.db.GradeDAO;

public class GradeJoinAction implements Action{

    @Override
    public ActionForward execute(HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        ActionForward forward = new ActionForward();
        
        request.setCharacterEncoding("utf-8");
        
        String gr_code = ""; // 과목 코드
        String gr_subject = ""; // 과목명
        String gr_memo = ""; // 시험내용
        Date gr_exam_date = null; // 시험 일자
        String ep_id = ""; // 담당 강사
        String gr_place = ""; // 시험 장소 분류(학교 or 학원)
        String gr_period = "";  //학교시험종류
        String st_school_name = ""; //학교이름
        
        
        gr_place = request.getParameter("gr_place");
        
        if(gr_place.equals("학원")){
            gr_code = request.getParameter("gr_code");
            gr_exam_date = Date.valueOf(request.getParameter("gr_exam_date"));
            ep_id = request.getParameter("ep_id");
        }else{
            //학교
            gr_period = request.getParameter("gr_period");
            st_school_name = request.getParameter("st_school_name");
        }
        
        //공통
        gr_subject = request.getParameter("gr_subject");
        gr_memo = request.getParameter("gr_memo");
        
        //bean 저장 , 학원*학교에 따라 저장 다름
        GradeBean gradebean = new GradeBean();
        if(gr_place.equals("학원")){
            gradebean.setGr_code(gr_code);
            gradebean.setGr_exam_date(gr_exam_date);
            gradebean.setEp_id(ep_id);
        }else{
            gradebean.setGr_period(gr_period);
            gradebean.setSt_school_name(st_school_name);
        }

        gradebean.setGr_memo(gr_memo);
        gradebean.setGr_place(gr_place);
        gradebean.setGr_subject(gr_subject);
       
        
        //dao넣기
        GradeDAO gradedao = new GradeDAO();
        boolean result = gradedao.gradeJoin(gradebean);
        
        if(result){
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('성공적으로 등록하였습니다');");
            out.println("location.href='./GradeJoin.gr'");
            out.println("</script>");
            out.close();
        }else{
            response.setContentType("text/html; charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('실패하였습니다');");
            out.println("history.back();");
            out.println("</script>");
            out.close();
        }
        return null;
    }

}
