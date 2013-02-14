package academy.student.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentFrontController extends HttpServlet implements Servlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 주소값 가져오기
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String command = requestURI.substring(contextPath.length());
        ActionForward forward = null;
        Action action = null;

        if (command.equals("/StudentJoinAction.st")) { //회원가입 액션
        	action = new StudentJoinAction();
        	try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentJoin.st")){ //회원가입 폼
        	forward = new ActionForward();
        	forward.setRedirect(false);
        	forward.setPath("./student/student_join.jsp");
        }else if(command.equals("/StudentList.st")){ // 학생 목록
        	action= new StudentListAction();
        	
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentOff.st")){ // 휴학생처리
        	action = new StudentOffAction();
        	
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentOut.st")){ // 퇴학 처리
        	action = new StudentOutAction();
        	
        	try {
				forward =action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentAttitudeList.st")){ // 출석 현황
        	action = new StudentAttitudeListAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentOffList.st")){ //휴원생관리
        	action = new StudentOffListAction();
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
        }else if(command.equals("/StudentIn.st")){ // 휴원생을 다시 재수강시킨다.
        	action = new StudentInAction();
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentDetail.st")){ // 상세정보
        	action =new StudentDetailAction();
        	 try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentModifyAction.st")){ // 회원수정
        	action = new StudentModifyAction();
        	try {
				forward=action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
        }else if(command.equals("/StudentOutList.st")){ //휴학생 정보
        	action = new StudentOutListAction();
        	try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
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
