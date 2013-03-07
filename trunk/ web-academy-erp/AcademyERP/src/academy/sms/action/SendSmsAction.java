
package academy.sms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.sms.db.SmsDAO;

public class SendSmsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendSMSAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		SmsDAO smsDAO = new SmsDAO();
		
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String message = request.getParameter("message");
		String senderPhone = request.getParameter("senderPhone");
		
//		smsDAO.addSMSMessage(receiverName, receiverPhone, senderPhone, message);
		
		System.out.println("수신자 : " + receiverName);
		System.out.println("수신번호 : " + receiverPhone);
		System.out.println("내용 : " + message);
		System.out.println("발신자 : " + senderPhone);
		
		
		SMS sms = new SMS();
		sms.appversion("Tutorial/1.0");
		sms.charset("utf8");
		sms.setuser("academytest", "dkzkepal1234");
		
		SmsMessagePdu pdu = new SmsMessagePdu();
		pdu.type = "SMS";
		pdu.destinationAddress = receiverPhone; // 수신번호
		pdu.scAddress = senderPhone; // 발신번호
		pdu.text = message; // 전송 메세지
		sms.add(pdu);
		
		try {
			sms.connect();
			sms.send();
			sms.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		String result = sms.printr();
		List getResult = new ArrayList();
		List result = new ArrayList();
		String resultCode = "";
		
		getResult = sms.getr(); // 전송 결과 메세지 받아오기 (List 타입)
		for (int i = 0; i < getResult.size(); i++) {
			for (int j = 0; j < getResult.toString().split(",").length; j++) {
				result.add(getResult.toString().split(",")[j]);
//				if ()
			}
		}
		
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
			if (result.get(i).toString().split("=")[0].equals("RESULT-CODE")) {
				if (result.get(i).toString().split("=")[1].equals("20")) {
					response.setContentType ("text/html;charset=utf-8");
		            PrintWriter out = response.getWriter();
		            out.println("<script>");
		            out.println("alert('SMS 아이디/비밀번호 틀림');");
		            out.println("history.back();");
		            out.println("</script>");
		            out.close();
				}
			}
		}
		sms.emptyall();
		
		return null;
	}

}