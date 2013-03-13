package academy.master.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsBean;
import academy.master.db.MasterDAO;

public class classSetAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");

		request.setCharacterEncoding("utf-8");
		GroupsBean groups = new GroupsBean();
		groups.setEp_id(request.getParameter("teacher_sel"));
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");		

		groups.setGp_startdate(request.getParameter("startdate"));
		groups.setGp_enddate(request.getParameter("enddate"));
		groups.setGp_lev(request.getParameter("level"));
		groups.setGp_half(request.getParameter("half"));
		groups.setGp_status("0");
		groups.setGp_name(request.getParameter("level2")+request.getParameter("sub_name"));
		new MasterDAO().setClass(groups);
		forward.setPath("./ClassList.master");
		forward.setRedirect(true);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("opener.location.reload();");
		out.println("window.close();");
		out.println("</script>");
		out.close();
		return null;
	}
}
