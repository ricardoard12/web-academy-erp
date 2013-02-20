package academy.groups.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;

public class GroupsAttitudeEditTimeAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("EmployeeAttitudeEditTimeAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeBean attitude = new AttitudeBean();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		
		String id = request.getParameter("id");
		String type = request.getParameter("type");
		String hour = request.getParameter("hour");
		String minute = request.getParameter("minute");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = sdf.format(Calendar.getInstance().getTime());
		
		String editTime = date + " " + hour + ":" + minute;
//		System.out.println("editTime : " + editTime);
		
		boolean result = attitudeDAO.employeeAttitudeEditTime(id,editTime,type);
		
		if (result == true) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시간 변경이 완료되었습니다.')");
			out.println("opener.location.reload()"); //부모창 새로고침 후 자식창 닫기
			out.println("window.close()");
			out.println("</script>");
			out.close();
			return null;
		} else {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('시간 변경이 실패하였습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
	}

}
