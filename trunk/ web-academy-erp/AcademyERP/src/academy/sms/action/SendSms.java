package academy.sms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

/* 문자 전송 폼 정보 표현 클래스
 * 체크박스로 선택한 수신자 목록을 문자열로 묶어서 전송 받음
 * 받은 문자열 분리 후 수신자 정보 조회
 * 현재 남은 잔액 정보 조회
 */

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
		
		if (chkValue != null && !chkValue.equals("")) {
			for (int i = 0; i < chkValue.split(",").length; i++) { // 수신자 문자열 분리 후 
				studentList.add(chkValue.split(",")[i]); // 조회를 위해 리스트에 저장
			}
		}
		
		List receiverList = studentDAO.getSMSReceiverList(studentList); // 수신자 정보 조회 후 리스트로 받아옴
		
		CheckBalance cb = new CheckBalance(); // 문자 포인트 조회
		cb.checkBalance();
		int credits = cb.getCredits();
		
		if (credits == -1) {
            response.setContentType ("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('문자 발송 시스템 에러. 확인 요망');");
            out.println("window.close()");
            out.println("</script>");
            out.close();
            return null;
		} else if (credits == 0) {
            response.setContentType ("text/html;charset=utf-8");
            PrintWriter out = response.getWriter();
            out.println("<script>");
            out.println("alert('문자 포인트 부족. 충전 요망');");
            out.println("window.close()");
            out.println("</script>");
            out.close();
            return null;
		}

		request.setAttribute("receiverList", receiverList);
		request.setAttribute("credits", credits);
		
		forward.setRedirect(false);
		forward.setPath("./sms/smsForm.jsp");
		return forward;
	}

}
