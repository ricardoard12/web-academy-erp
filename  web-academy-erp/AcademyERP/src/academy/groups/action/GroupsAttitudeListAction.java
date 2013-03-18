package academy.groups.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.attitude.db.AttitudeBean;
import academy.attitude.db.AttitudeDAO;
import academy.groups.db.GroupsDAO;

// 학급별 학생 목록 조회
public class GroupsAttitudeListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 학생 출결 현황
		System.out.println("GroupsAttitudeListAction");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = (Integer) session.getAttribute("level");
		if (sid == null || sid.equals("") || level < 3) {
			response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('권한이 없습니다.')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		ActionForward forward = new ActionForward();
		AttitudeDAO attitudeDAO = new AttitudeDAO();
		GroupsDAO groupsDAO = new GroupsDAO();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
		
		String date = request.getParameter("date");
		if (date == null) {
			date = sdfDate.format(Calendar.getInstance().getTime());
		}

		int page = 1;
		int limit = 10; // 페이지 당 표시 줄 수
		if (request.getParameter("page") != null) { // 현재 페이지 번호
			page = Integer.parseInt(request.getParameter("page"));
		}
		String gp_name = request.getParameter("gp_name"); // 학급 이름

		int listCount = attitudeDAO.getGroupsStudentCount(gp_name); // 학생 수
		int maxPage = (int) ((double) listCount / limit + 0.95); // 최대 페이지
		int pageBlock = 10; // 한 블록당 페이지 수
		int startPage = (((int) ((double) page / pageBlock + 0.9)) - 1)	* pageBlock + 1; // 시작 페이지
		int endPage = startPage + pageBlock - 1; // 끝 페이지
		if (endPage > maxPage) endPage = maxPage;
		
		List attitudeList = new ArrayList();
		attitudeList = attitudeDAO.getGroupsStudentAttitudeList(date, page, limit, gp_name);
		
		List gpList = new ArrayList();
		if (level > 3) {
			gpList = groupsDAO.getGpList();
		} else if (level == 3) {
			List list2 = groupsDAO.getGroupsList(page, limit, sid);
			for (int i = 0; i < list2.size(); i++) {
				List list1 = (List) list2.get(i);
				gpList.add(list1.get(1));
			}
		}
		
		request.setAttribute("gpList", gpList);
		request.setAttribute("attitudeList", attitudeList);
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
