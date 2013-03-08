package academy.grade.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GradeFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if(command.equals("/GradeJoin.gr")) {
            forward = new ActionForward();
            forward.setRedirect(false);
            forward.setPath("./grade/grade_register.jsp");
            
            //학교/학원 정보등록
        }else if(command.equals("/GradeJoinAction.gr")) {
            action = new GradeJoinAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
         
        }else if(command.equals("/GradeTsearch.gr")) {
            action = new GradeTsearchAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        
        // 학생 정보
        }else if(command.equals("/GradeSsearch.gr")) {
            action = new GradeSsearchAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        
        //반그룹 정보
        }else if(command.equals("/GradeGsearch.gr")) {
            action = new GradeGsearchAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        
            
            //학원 시험중인 정보
        }else if(command.equals("/GradeAcademyTesting.gr")) {
            action = new GradeAcademyTestingAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
            
            //과목별 학생 리스트
        }else if(command.equals("/GradeAcademyTestingStudentList.gr")) {
            action = new GradeAcademyTestingStudentListAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        
            //과목별 학생 점수 입력
        }else if(command.equals("/GradeAcademyTestingStudentAdd.gr")) {
            action = new GradeAcademyTestingStudentAddAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        
            //그룹별  학생 찾기
        }else if(command.equals("/GradeSGsearch.gr")) {
            action = new GradeSGsearchAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}    
            
            //학원 시험완료된 정보            
        }else if(command.equals("/GradeAcademyTested.gr")) {
            action = new GradeAcademyTestedAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
            
            //학원 시험중인 정보 완료변경
        }else if(command.equals("/GradeMoveTested.gr")) {
            action = new GradeMoveTestedAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
            
        }else if(command.equals("/GradeSchoolTested.gr")) {
            action = new GradeSchoolTestedAction();
            try {forward = action.execute(request, response);
            } catch (Exception e) {e.printStackTrace();}
        }
        
        
        // 이동
        if (forward != null) {
            if (forward.isRedirect()) {
                response.sendRedirect(forward.getPath());
            } else {
                RequestDispatcher dispatcher = request
                        .getRequestDispatcher(forward.getPath());
                dispatcher.forward(request, response);
            }
        }
    }

}
