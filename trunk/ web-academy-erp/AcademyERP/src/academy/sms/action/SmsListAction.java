package academy.sms.action;import java.util.List;import javax.servlet.http.HttpServletRequest;import javax.servlet.http.HttpServletResponse;import javax.servlet.http.HttpSession;import academy.master.db.ListPackage;import academy.sms.db.SmsDAO;public class SmsListAction implements Action {	@Override	public ActionForward execute(HttpServletRequest request,			HttpServletResponse response) throws Exception {		ActionForward forward =new ActionForward();		request.setCharacterEncoding("utf-8");		HttpSession session=request.getSession();		int level=0;		if(session.getAttribute("level")!=null){			level=Integer.parseInt((String)session.getAttribute("level"));		}		/*session 검증 부분*/		if(level<3){					}		/*session 검증 부분*/		SmsDAO sms=new SmsDAO();		int page = 1;		int limit = 15;		if (request.getParameter("page") != null) {			page = Integer.parseInt(request.getParameter("page"));		}		int listcount = sms.getCount();		List smsList=sms.getSmsList(page,limit);		int maxpage = (int) ((double) listcount / limit + 0.95);		int pageBlock = 10;		int startpage = (((int) ((double) page / pageBlock + 0.9)) - 1)				* pageBlock + 1;		int endpage = startpage + pageBlock - 1;		if (endpage > maxpage)			endpage = maxpage;		ListPackage pack = new ListPackage(page, maxpage, startpage, endpage,				listcount, smsList);		System.out.println(listcount);		request.setAttribute("ListPackage", pack);		forward.setPath("./sms/smsList.jsp");		forward.setRedirect(false);		return forward;	}}