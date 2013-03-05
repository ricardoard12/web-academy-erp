package academy.member.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.member.db.MemberDAO;


public class ZipcodeAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("MemberZipcodeAction!");
		
		ActionForward forward = new ActionForward();
		MemberDAO memberDAO = new MemberDAO();
		List zipcodeList = new ArrayList();
		
		String searchDong = request.getParameter("dong");
		
		zipcodeList = memberDAO.searchZipcode(searchDong);
		
		request.setAttribute("zipcodeList", zipcodeList);
		
		forward.setRedirect(false);
		forward.setPath("./member/zipcode.jsp");
		return forward;
	}

	
}
