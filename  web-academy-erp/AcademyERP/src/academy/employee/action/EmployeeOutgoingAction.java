package academy.employee.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.employee.db.EmployeeDAO;

// 직원 퇴직 처리
public class EmployeeOutgoingAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeDeleteAction");
		request.setCharacterEncoding("UTF-8");
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = Integer.parseInt((String) session.getAttribute("level"));
		if (sid == null || sid.equals("") || level < 4) {
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
		EmployeeDAO employeeDAO = new EmployeeDAO();
		List outgoingList = new ArrayList();
		
		String[] ep_id = (String[])request.getParameterValues("employeeSelect");
		for (int i = 0; i < ep_id.length; i++) {
			outgoingList.add(ep_id[i]); // 체크 박스 값 받아와서 리스트에 저장
		}
		
		employeeDAO.employeeOutgoing(outgoingList);
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeOutgoingListAction.em");
		return forward;
	}

}
