package academy.groups.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;
import academy.employee.db.EmployeeDAO;
import academy.master.db.ListPackage;
import academy.student.db.StudentDAO;

public class GroupsAttitudeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 직원 출결 현황
		System.out.println("GroupsAttitudeListAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeBean attitude = new AttitudeBean();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}
//		System.out.println(date);

		int page = 1;
		int limit = 10;
		if (request.getParameter("page") != null) {
			page = Integer.parseInt(request.getParameter("page"));
		}
		int listCount = attitudeDAO.getStudentCount();
		int maxPage = (int) ((double) listCount / limit + 0.95);
		int pageBlock = 10;
		int startPage = (((int) ((double) page / pageBlock + 0.9)) - 1)	* pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		if (endPage > maxPage) endPage = maxPage;
		
		List attitudeList = new ArrayList();
		String gp_name = request.getParameter("gp_name");
//		attitudeList = attitudeDAO.getStudentAttitudeList(gp_name, date);
		attitudeList = attitudeDAO.getStudentAttitudeList(date, page, limit);
		
		request.setAttribute("attitudeList", attitudeList);
		request.setAttribute("date", date);
		
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		
		forward.setRedirect(false);
		forward.setPath("./groups/groups_attitude_list.jsp");
		return forward;
	}
	
}
