package academy.sms.action;import java.io.IOException;import java.io.PrintWriter;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;public class MessagePoint implements Action {	@Override	public ActionForward execute(HttpServletRequest request,			HttpServletResponse response) throws Exception {		// 문자 메세지 포인트 조회		SMS sms = new SMS();		sms.setuser("nedgold", "123123"); // 아이디, 패스워드 설정		// 발송 가능 건수 조회		SmsBalanceInfo sbi = null;		try {			sms.connect();			sbi = sms.getBalanceInfo(); // 메소드 호출해서 정보 조회 후 저장			sms.disconnect();		} catch (IOException e) {			System.out.println(e.toString());		}				if (sbi.resultCode.equals("00")) // 조회 결과가 정상일 때		{			if (sbi.credits == 0) { // 잔액 부족이면 경고창			 System.out.println("캐쉬: " + sbi.cash);			 System.out.println("포인트: " + sbi.point);			 System.out.println("문자방울: " + sbi.mdrop);			 System.out.println("발송가능 SMS 건수: " + sbi.credits);				response.setContentType("text/html;charset=utf-8");				PrintWriter out = response.getWriter();				out.println("<script>");				out.println("alert('문자 포인트 잔액 부족. 충전 요망.');");				//out.println("window.close()");				out.println("</script>");				out.close();				return null;			} else { // 잔액이 있으면				request.setAttribute("credits", sbi.credits); // 현재 문자 발송 가능 건수																// 전달			}		} else { // 조회 결과에 이상이 있을 때			response.setContentType("text/html;charset=utf-8");			PrintWriter out = response.getWriter();			out.println("<script>");			out.println("alert('" + sbi.resultMessage + "(Code : "					+ sbi.resultCode + ")');");			out.println("window.close()");			out.println("</script>");			out.close();			return null;			// System.out.println("Result Code: " + sbi.resultCode);			// System.out.println("Result Message: " + sbi.resultMessage);		}		return null;	}}