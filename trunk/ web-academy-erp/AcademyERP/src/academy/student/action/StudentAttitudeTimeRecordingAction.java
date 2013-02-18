package academy.student.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeDAO;

public class StudentAttitudeTimeRecordingAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("SudentAttitudeTimeRecordingAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		int result = attitudeDAO.studentAttitudeTimeRecording(id, type);
		
		if (result == -1) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('출근 시간을 먼저 등록하세요.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		} else if (result == 0) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시간 기록을 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		System.out.println(result+"result");
		String gp_id ="";
		gp_id= attitudeDAO.getstudentAttitudeGpid(id);
		
		request.setAttribute("gp_id", gp_id);
		System.out.println("GP_id"+gp_id);
		forward.setRedirect(false);
		forward.setPath("./StudentAttitudeList.st");
		return forward;
	}

}
