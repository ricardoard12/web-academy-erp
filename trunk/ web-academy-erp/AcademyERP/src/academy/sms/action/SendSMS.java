package academy.sms.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class SendSMS implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendSMS");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		StudentDAO studentDAO = new StudentDAO();
		
		String chkValue = request.getParameter("chkValue"); // 체크박스 값 결합시킨 문자열 가져오기
		String[] studentList = null; // 문자열 자른 후 저장할 배열
		
		if (chkValue != null && !chkValue.equals("")) {
			studentList = chkValue.split(","); // 구분자(,)를 기준으로 문자열 잘라서 배열에 삽입
		}
		
		List receiverList = studentDAO.getSMSReceiverList(studentList);
		
		request.setAttribute("receiverList", receiverList);
		
		forward.setRedirect(false);
		forward.setPath("./sms/smsForm.jsp");
		return forward;
	}

}
