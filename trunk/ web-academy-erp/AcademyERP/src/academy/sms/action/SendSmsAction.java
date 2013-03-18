package academy.sms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.sms.db.SmsDAO;

/* 문자 전송 Action 페이지
 * 개인, 단체 문자 전송 가능
 * SMS 전용(LMS, MMS 구현 X)
 */
public class SendSmsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendSMSAction");
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
		SmsDAO smsDAO = new SmsDAO();
		
		// 메세지 수신자 정보 결합시킨 문자열 받아오기 
		String receiverID = request.getParameter("receiverID");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		
		// 전송 메세지, 발신자 전화번호 받아오기
		String message = request.getParameter("message");
		String senderPhone = request.getParameter("senderPhone");
		
		// 메세지 수신자 정보 저장 리스트
		List receiverIDList = new ArrayList();
		List receiverNameList = new ArrayList();
		List receiverPhoneList = new ArrayList();
		
		// 각 수신자 별 전송 결과 저장 리스트
		List resultMsgList = new ArrayList();
		
		// 결합된 수신자 정보 콤마(,)를 구분자로 사용하여 분리 후 리스트 저장
		for (int i = 0; i < receiverID.split(",").length; i++) {
			receiverIDList.add(receiverID.split(",")[i]); // 수신자 회원 ID 분리
			receiverNameList.add(receiverName.split(",")[i]); // 수신자 이름 분리
			receiverPhoneList.add(receiverPhone.split(",")[i]); // 수신 번호 분리
		}
		
		SMS sms = new SMS(); // 문자 발송 메인 클래스인 SMS.class 객체 생성
		sms.appversion("Tutorial/1.0"); // 버전 설정. 옵션 사항
		sms.charset("utf8"); // 캐릭터셋 설정
		sms.setuser("academytest", "dkzkepal1234"); // 사이트 아이디, 패스워드 (전송 시엔 패스워드가 MD5 형식으로 암호화 됨)
		
		for (int i = 0; i < receiverPhoneList.size(); i++) { // 수신자 인원만큼 반복
			SmsMessagePdu pdu = new SmsMessagePdu(); // 문자 전송 건별 객체 생성
			pdu.type = "SMS"; // 문자 전송 타입, SMS or LMS or MMS. 타입별 소스코드가 달라짐
			pdu.destinationAddress = (String) receiverPhoneList.get(i); // 수신번호
			pdu.scAddress = senderPhone; // 발신번호
			pdu.text = message; // 전송 메세지
			sms.add(pdu); // 메세지 스택에 모든 내용 삽입
		}
		
		try { 
			sms.connect(); // 문자 전송 서버 연결
			sms.send(); // 메세지 스택의 내용 모두 전송
			sms.disconnect(); // 문자 전송 서버 연결 종료
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List getResult = new ArrayList();
		List messageIDList = new ArrayList(); // 26자리 MESSAGE-ID. 결과 조회용
		String resultCode = "";
		sms.printr(); // 결과 메세지 출력 함수
		
		getResult = sms.getr(); // 전송 결과 메세지 받아오기 (List 타입)
		
		// 전송 결과에 포함된 대괄호[] 없애기
		int beginIndex = getResult.toString().indexOf("[");
		int endIndex = getResult.toString().indexOf("]");
		String resultText[] = getResult.toString().substring(beginIndex + 1, endIndex).split(", ");
		
		// 전송 결과 항목 중 RESULT-CODE 찾아서 저장
		int count = 0;
		for (int i = 0; i < resultText.length; i++) {
			if (resultText[i].split("=")[0].equals("RESULT-CODE")) { // 결과항목=내용 형태의 문자열 분리
				resultCode = resultText[i].split("=")[1]; 
				
				// 전송 결과 디비 저장
				smsDAO.addSendResult(receiverIDList.get(count).toString(), (String)receiverNameList.get(count), (String)receiverPhoneList.get(count), senderPhone, message, resultCode, "N");
				resultMsgList.add(resultCode); // result code값 저장
				count++;
			}
//			/**
//			 * STATUS:
//			 * 0 : 전송대기
//			 * 1 : 전송 후 기지국
//			 * 2 : 전송완료
//			 * 9 : 없는 메시지ID
//			 *
//			 * RESULT-CODE:
//			 * 00 : 정상
//			 * 20 : 아이디 혹은 패스워드 틀림
//			 * 21 : 존재하지 않는 메시지 아이디
//			 * 30 : 사용가능한 문자 잔량이 없음
//			 * 그외 : 전송결과코드표 참조
//			 */
		}
		
		sms.emptyall(); // 메세지 스택 비우기(필수!) 비우지 않으면 다음 번 문자 전송 시 이전 문자도 다시 전송됨
		
		request.setAttribute("receiverIDList", receiverIDList);
		request.setAttribute("receiverNameList", receiverNameList);
		request.setAttribute("receiverPhoneList", receiverPhoneList);
		request.setAttribute("resultMsgList", resultMsgList);
		request.setAttribute("senderPhone", senderPhone);
		request.setAttribute("message", message);
		
		forward.setRedirect(false);
		forward.setPath("./sms/smsSendResult.jsp");
		return forward;
	}

}