package academy.sms.action;import java.util.List;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import academy.sms.db.SmsDAO;public class SmsDetail implements Action {	@Override	public ActionForward execute(HttpServletRequest request,			HttpServletResponse response) throws Exception {		ActionForward forward = new ActionForward();		request.setCharacterEncoding("utf-8");		String sms_idx=request.getParameter("sms_idx");		List sms=new SmsDAO().getSmsInfo(sms_idx);		request.setAttribute("sms", sms);		forward.setPath("./sms/smsDetail.jsp");		forward.setRedirect(false);		return forward;	}}