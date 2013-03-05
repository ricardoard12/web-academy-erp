package academy.groups.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;
import academy.groups.db.GroupsDAO;

public class GroupsAttitudeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 학생 출결 현황
		System.out.println("GroupsAttitudeListAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		AttitudeBean attitude = new AttitudeBean();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		GroupsDAO groupDAO = new GroupsDAO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}
//		System.out.println(sdfDate.format(Calendar.getInstance().getTime()));

		int page = 1;
		int limit = 10; // 페이지 당 표시 줄 수
		if (request.getParameter("page") != null) { // 현재 페이지 번호
			page = Integer.parseInt(request.getParameter("page"));
		}
		String gp_name = request.getParameter("gp_name"); // 학급 이름
//		if (gp_name == null) {
//			gp_name = "2C";
//		}
		int listCount = attitudeDAO.getGroupsStudentCount(gp_name); // 학생 수
		int maxPage = (int) ((double) listCount / limit + 0.95); // 최대 페이지
		int pageBlock = 10; // 한 블록당 페이지 수
		int startPage = (((int) ((double) page / pageBlock + 0.9)) - 1)	* pageBlock + 1; // 시작 페이지
		int endPage = startPage + pageBlock - 1; // 끝 페이지
		if (endPage > maxPage) endPage = maxPage;
		
		List attitudeList = new ArrayList();
//		attitudeList = attitudeDAO.getStudentAttitudeList(gp_name, date);
		attitudeList = attitudeDAO.getGroupsStudentAttitudeList(date, page, limit, gp_name);
		List gpList = groupDAO.getGpList();
		
		request.setAttribute("attitudeList", attitudeList);
		request.setAttribute("gpList", gpList);
		request.setAttribute("date", date);
		request.setAttribute("page", page);
		request.setAttribute("maxPage", maxPage);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("listCount", listCount);
		request.setAttribute("gp_name", gp_name);
		
		System.out.println("리스트 넘어간다");
		
		forward.setRedirect(false);
		forward.setPath("./groups/groups_attitude_list.jsp");
		return forward;
	}
	
}
