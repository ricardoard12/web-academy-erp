package academy.sms.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		/* 권한 확인 */
		HttpSession session = request.getSession();
		String sid = (String) session.getAttribute("id");
		int level = Integer.parseInt((String) session.getAttribute("level"));
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
		StudentDAO studentDAO = new StudentDAO();
		
		// 수신자 아이디를 체크박스 값으로 선택해서 문자열로 묶고(구분자는 콤마(,)) 전송받은 상태.
		String chkValue = request.getParameter("chkValue"); // 체크박스 값 결합시킨 수신자 목록 문자열 가져오기
		List studentList = new ArrayList(); // 문자열 자른 후 저장할 배열
		
		if (chkValue != null && !chkValue.equals("")) {
			for (int i = 0; i < chkValue.split(",").length; i++) { // 수신자 문자열 분리 후 
				studentList.add(chkValue.split(",")[i]); // 조회를 위해 리스트에 저장
			}
		}
		
		// 수신자 정보 조회 후 리스트로 받아옴 (이름, 전화번호 등등)
		List receiverList = studentDAO.getSMSReceiverList(studentList); 
		
		request.setAttribute("receiverList", receiverList);
		
		
		// 문자 메세지 잔여 포인트 조회 부분
		SMS sms = new SMS();
		
		sms.setuser("scandea", "123456"); // 아이디, 패스워드 설정
	
		// 발송 가능 건수 조회
		SmsBalanceInfo sbi = null;
		try {
			sms.connect(); // 서버 연결
			sbi = sms.getBalanceInfo(); // 메소드 호출해서 정보 조회 후 저장
			sms.disconnect(); // 서버 연결 종료
		} catch (IOException e) {
			System.out.println(e.toString());
		}
	
		if (sbi.resultCode.equals("00")) // 조회 결과가 정상일 때
		{
			if (sbi.credits == 0) { // 잔액 부족이면 경고창
//				System.out.println("캐쉬: " + sbi.cash);
//				System.out.println("포인트: " + sbi.point);
//				System.out.println("문자방울: " + sbi.mdrop);
//				System.out.println("발송가능 SMS 건수: " + sbi.credits);
				response.setContentType ("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('문자 포인트 잔액 부족. 충전 요망.');");
				out.println("window.close()");
				out.println("</script>");
				out.close();
				return null;
			} else { // 잔액이 있으면
				request.setAttribute("credits", sbi.credits); // 현재 문자 발송 가능 건수 전달
			}
		} else { // 조회 결과에 이상이 있을 때
			response.setContentType ("text/html;charset=utf-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        out.println("alert('" + sbi.resultMessage + "(Code : " + sbi.resultCode + ")');"); // 조회 불가 메세지 출력
	        out.println("window.close()");
	        out.println("</script>");
	        out.close();
	        return null;
//			System.out.println("Result Code: " + sbi.resultCode);
//			System.out.println("Result Message: " + sbi.resultMessage);
		}
		
		// 문자 메세지 작성 폼으로 이동
		forward.setRedirect(false);
		forward.setPath("./sms/smsForm.jsp");
		return forward;
	}

}
