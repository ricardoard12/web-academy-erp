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
				// TODO: handle exception
				e.printStackTrace();
			}
		} else if (command.equals("/GroupsAddStudent.gp")) { // 학급 학생 추가
			action = new GroupsAddStudent();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			// }else if(command.equals("/GroupsAttitudeEditTime.gp")){ // 출근 시간
			// 등록 폼
			// forward = new ActionForward();
			// forward.setRedirect(false);
			// forward.setPath("./group/group_attitude_editTime.jsp");
			//
			// }else if(command.equals("/GroupsAttitudeEditTimeAction.gp")){
			// action = new GroupsAttitudeEditTimeAction();
			// try {
			// forward= action.execute(request, response);
			// } catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }
			// }else if(command.equals("/GroupsAttitudeMemoAction.gp")){
			// action = new GroupsAttitudeMemoAction();
			// try {
			// forward = action.execute(request, response);
			// } catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }
			// }else if(command.equals("/GroupsAttitudeAddMemoAction.gp")){
			// action = new GroupsAttitudeAddMemoAction();
			// try {
			// forward = action.execute(request, response);
			// } catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }
			// }else if(command.equals("/GroupsAttitudeCancelAction.gp")){
			// action = new GroupsAttitudeCancelAction();
			// try {
			// forward=action.execute(request, response);
			// } catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }
			// }else
			// if(command.equals("/GroupsAttitudeTimeRecordingAction.gp")){
			// action = new GroupsAttitudeTimeRecordingAction();
			//
			// try {
			// forward =action.execute(request, response);
			// } catch (Exception e) {
			// // TODO: handle exception
			// e.printStackTrace();
			// }
		} else if (command.equals("/GroupsNameList.gp")) { // 과목의 총목록을 구해오기 위해서
			action = new GroupsNameListAction();
			try {
				forward = action.execute(request, response);
			} catch (Exception e) {
				// TODO: handle exception
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
				RequestDispatcher dispatcher = request
						.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
