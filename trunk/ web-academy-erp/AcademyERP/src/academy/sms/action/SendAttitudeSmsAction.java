package academy.sms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import academy.sms.db.SmsDAO;
import academy.student.db.StudentDAO;

public class SendAttitudeSmsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendAttitudeSmsAction");
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
		SmsDAO smsDAO = new SmsDAO();
		
		String id = (String) request.getAttribute("id");
		String date = (String) request.getAttribute("date");
		String gp_name = (String) request.getAttribute("gp_name");
		String type = (String) request.getAttribute("type");
		
		String status = "";
		if (type.equals("come")) status = "입실"; // 입실 퇴실 판별
		else if (type.equals("leave")) status = "퇴실";
		
		List receiverInfo = studentDAO.getReceiverInfo(id); // 학생 이름(0), 부모 휴대폰번호(1) 가져오기
		
		String studentName = (String) receiverInfo.get(0);
		String parentPhone = (String) receiverInfo.get(1);
		String senderPhone = "0518000000"; // 발신 번호 설정
		String message = studentName + "학생이 " + status + "하였습니다. - 아이티윌부산"; // 전송 메세지 설정
		
		SMS sms = new SMS(); 
		sms.appversion("Tutorial/1.0"); // 버전 설정. 옵션 사항
		sms.charset("utf8"); // 캐릭터셋 설정
		sms.setuser("nedgold", "123123"); // 사이트 아이디, 패스워드 (전송 시엔 패스워드가 MD5 형식으로 암호화 됨)
		
		SmsMessagePdu pdu = new SmsMessagePdu();
		pdu.type = "SMS"; // 문자 전송 타입, SMS or LMS or MMS. 타입별 소스코드가 달라짐
		pdu.destinationAddress = parentPhone; // 수신 번호
		pdu.scAddress = senderPhone; // 발신 번호
		pdu.text = message; // 전송 메세지
		sms.add(pdu); // 메세지 스택에 모든 내용 삽입
		
		System.out.println(studentName + "학생이 " + status + "하였습니다. - 아이티윌부산");
		
		try {
			sms.connect(); // 서버 연결
			sms.send(); // 메세지 전송
			sms.disconnect(); // 서버 연결 끊기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List getResult = new ArrayList();
		String resultCode = "";
//		sms.printr(); // 결과 메세지 출력 함수
		
		getResult = sms.getr(); // 전송 결과 메세지 받아오기 (List 타입)
		
		// 전송 결과에 포함된 대괄호[] 없애기
		int beginIndex = getResult.toString().indexOf("[");
		int endIndex = getResult.toString().indexOf("]");
		String resultText[] = getResult.toString().substring(beginIndex + 1, endIndex).split(", ");
		
		// 전송 결과 항목 중 RESULT-CODE 찾아서 저장
		for (int i = 0; i < resultText.length; i++) {
			// 결과항목=내용 형태의 문자열 분리	
			if (resultText[i].split("=")[0].equals("RESULT-CODE")) {
				resultCode = resultText[i].split("=")[1];
			}
			/**
			 * STATUS:
			 * 0 : 전송대기
			 * 1 : 전송 후 기지국
			 * 2 : 전송완료
			 * 9 : 없는 메시지ID
			 *
			 * RESULT-CODE:
			 * 00 : 정상
			 * 20 : 아이디 혹은 패스워드 틀림
			 * 21 : 존재하지 않는 메시지 아이디
			 * 30 : 사용가능한 문자 잔량이 없음
			 * 그외 : 전송결과코드표 참조
			 */
		}
		// 전송 결과 DB에 저장
		smsDAO.addSendResult(id, studentName + "학부모", parentPhone, senderPhone, message, resultCode, "Y");
		
		sms.emptyall(); // 메세지 스택 비우기(필수!) 비우지 않으면 다음 번 문자 전송 시 이전 문자도 다시 전송됨

		forward.setRedirect(true);
		forward.setPath("./GroupsAttitudeListAction.gp?date=" + date + "&gp_name=" + gp_name);
		return forward;
	}

}
