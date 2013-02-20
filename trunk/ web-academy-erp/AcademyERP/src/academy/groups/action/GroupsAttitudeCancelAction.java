package academy.groups.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;

public class GroupsAttitudeCancelAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println("SudentAttitudeCancelAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		
		boolean result = attitudeDAO.studentAttitudeCancel(id, type);
		if (result == false) {
				response.setContentType("text/html;charset=UTF-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('취소 처리가 실패하였습니다.')");
				out.println("history.back()");
				out.println("</script>");
				out.close();
				return null;
		}
		
		String gp_id ="";
		gp_id= attitudeDAO.getstudentAttitudeGpid(id);
		
		request.setAttribute("gp_id", gp_id);
		forward.setRedirect(false);
		forward.setPath("./GroupsAttitudeList.gp");
		return forward;
	}

}
