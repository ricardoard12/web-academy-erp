package academy.sms.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentDAO;

public class SendAttitudeSmsAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("SendAttitudeSmsAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		StudentDAO studentDAO = new StudentDAO();
		
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
		
		SMS sms = new SMS();
		sms.appversion("Tutorial/1.0");
		sms.charset("utf8");
		sms.setuser("academytest", "dkzkepal");
		
		SmsMessagePdu pdu = new SmsMessagePdu();
		pdu.type = "SMS";
		pdu.destinationAddress = parentPhone; // 수신번호
		pdu.scAddress = "0518000000"; // 발신번호
		pdu.text = studentName + "학생이 " + status + "하였습니다. - 아이티윌부산"; // 전송 메세지
		sms.add(pdu);
		
//		System.out.println(studentName + "학생이 " + status + "하였습니다. - 아이티윌부산");
		
		try {
			sms.connect();
			sms.send();
			sms.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List getResult = new ArrayList();
		List result = new ArrayList();
		String resultCode = "";
		
		getResult = sms.getr(); // 전송 결과 메세지 받아오기 (List 타입)
//		System.out.println(getResult);
		for (int i = 0; i < getResult.size(); i++) {
			for (int j = 0; j < getResult.toString().split(",").length; j++) {
				result.add(getResult.toString().split(",")[j]);
			}
		}
		
//		for (int i = 0; i < result.size(); i++) {
//			System.out.println(result.get(i));
//			if (result.get(i).toString().split("=")[0].equals("RESULT-CODE")) {
//				if (result.get(i).toString().split("=")[1].equals("20")) {
//					response.setContentType ("text/html;charset=utf-8");
//		            PrintWriter out = response.getWriter();
//		            out.println("<script>");
//		            out.println("alert('SMS 아이디/비밀번호 틀림');");
//		            out.println("history.back();");
//		            out.println("</script>");
//		            out.close();
//				}
//			}
//		}
		sms.emptyall(); // 메세지 스택 비우기(필수!)

		forward.setRedirect(true);
		forward.setPath("./GroupsAttitudeListAction.gp?date=" + date + "&gp_name=" + gp_name);
		return forward;
	}

}
