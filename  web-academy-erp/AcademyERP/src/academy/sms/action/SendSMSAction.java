package academy.sms.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.sms.db.SMSDAO;

public class SendSMSAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendSMSAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		SMSDAO smsDAO = new SMSDAO();
		
		String user = request.getParameter("user");
		String password = request.getParameter("password");
		String enc = request.getParameter("enc");
		String receiverName = request.getParameter("receiverName");
		String receiverPhone = request.getParameter("receiverPhone");
		String message = request.getParameter("message");
		String senderPhone = request.getParameter("senderPhone");
		
		smsDAO.addSMSMessage(receiverName, receiverPhone, senderPhone, message);
		
		System.out.println("수신자 : " + receiverName);
		System.out.println("수신번호 : " + receiverPhone);
		System.out.println("내용 : " + message);
		System.out.println("발신자 : " + senderPhone);
		
		response.sendRedirect("http://api.coolsms.co.kr/sendmsg?user=" + user + "&password=" + password + "&enc=MD5&to=" + receiverPhone + "&from=" + senderPhone + "&text=" + message);
		return null;
	}

}
