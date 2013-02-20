package academy.groups.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.groups.db.GroupsDAO;
import academy.student.db.StudentDAO;

public class GroupsAttitudeListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		StudentDAO studentdao = new StudentDAO();
		GroupsDAO groupsdao = new GroupsDAO(); // 계설된 과목의 리스트를 가지고오기 위해 선언
		
		List Gp_nameList = null;
		Gp_nameList = groupsdao.getGpList();
		request.setAttribute("Gp_nameList", Gp_nameList); // 가지고온 과목명을 저장후 넘김
		
		String gp_name="";
		
		if(request.getAttribute("gp_id")!=null){
			 gp_name = (String)request.getAttribute("gp_id");
		}else{
			 gp_name = request.getParameter("gp_name");
			System.out.println(gp_name);
				
		}
		
		System.out.println("gp_name"+gp_name);
		
		if(request.getParameter("gp_name")!= null){
			
			List StudentAttitudeList = null;
			StudentAttitudeList = studentdao.getStudentAttitudeList(gp_name);  //DB에서 출석 관련정보를 가져온다.
			
			request.setAttribute("GroupsAttitudeList", StudentAttitudeList); // 가지고온 StudentAttitudeList 정보를 넘긴다.
			request.setAttribute("gp_name", gp_name);
			forward.setRedirect(false);
			forward.setPath("./group/group_attitude_list.jsp");// student_attitude_list 폼으로 이동
			
		}
		return forward;
	}

}
