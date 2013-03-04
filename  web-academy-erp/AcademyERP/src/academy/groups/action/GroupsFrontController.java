package academy.groups.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GroupsFrontController extends HttpServlet implements Servlet {

	@Override
	protected void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// 주소값 가져오기
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		Action action = null;

		if (command.equals("/GroupsAttitudeListAction.gp")) { // 출석 현황
			action = new GroupsAttitudeListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsAddStudent.gp")) { // 학급 학생 추가
			action = new GroupsAddStudent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsAddStudentAction.gp")) { // 학급 학생 추가
			action = new GroupsAddStudentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsDelStudentAction.gp")) { // 학급 학생 삭제
			action = new GroupsDelStudentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsMoveStudent.gp")) { // 학급 이동 폼
			action = new GroupsMoveStudent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsMoveStudentAction.gp")) { // 학급 이동
			action = new GroupsMoveStudentAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsAttitudeEditTime.gp")) { // 시간 수정 폼
			 forward = new ActionForward();
			 forward.setRedirect(false);
			 forward.setPath("./groups/groups_attitude_editTime.jsp");
		} else if (command.equals("/GroupsAttitudeEditTimeAction.gp")) { // 시간 수정
			 action = new GroupsAttitudeEditTimeAction();
			 try {
				 forward= action.execute(request, response);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		}else if (command.equals("/GroupsAttitudeMemoAction.gp")) { // 메모 폼
			 action = new GroupsAttitudeMemoAction();
			 try {
				 forward = action.execute(request, response);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		}else if(command.equals("/GroupsAttitudeAddMemoAction.gp")) { // 메모 추가
			 action = new GroupsAttitudeAddMemoAction();
			 try {
				 forward = action.execute(request, response);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		}else if(command.equals("/GroupsAttitudeCancelAction.gp")) { // 시간 등록 취소
			 action = new GroupsAttitudeCancelAction();
			 try {
				 forward=action.execute(request, response);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		} else if(command.equals("/GroupsAttitudeTimeRecordingAction.gp")) { // 시간 등록
			 action = new GroupsAttitudeTimeRecordingAction();
			 try {
				 forward =action.execute(request, response);
			 } catch (Exception e) {
				 e.printStackTrace();
			 }
		} else if (command.equals("/GroupsNameList.gp")) { // 과목의 총목록을 구해오기 위해서
			action = new GroupsNameListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (command.equals("/ClassInfo.gp")) { //담당 학급 리스트를 불러온다.
			action = new GroupsListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// 이동
		if (forward != null) {
			if (forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			} else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
