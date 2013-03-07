package academy.sms.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class SendSms implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendSms");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		StudentDAO studentDAO = new StudentDAO();
		
		String chkValue = request.getParameter("chkValue"); // 체크박스 값 결합시킨 수신자 목록 문자열 가져오기
		List studentList = new ArrayList(); // 문자열 자른 후 저장할 배열
		
		System.out.println("chkValue : " + chkValue);
		if (chkValue != null && !chkValue.equals("")) {
			System.out.println("Length : " + chkValue.split(",").length);
			for (int i = 0; i < chkValue.split(",").length; i++) {
				System.out.println("chkValue.split[" + i + "] : " + chkValue.split(",")[i]);
				studentList.add(chkValue.split(",")[i]);
			}
		}
		
		List receiverList = studentDAO.getSMSReceiverList(studentList);
		
		request.setAttribute("receiverList", receiverList);
		System.out.println("receiverList Size : " + receiverList.size());
		
		forward.setRedirect(false);
		forward.setPath("./sms/smsForm.jsp");
		return forward;
	}

}
