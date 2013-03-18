package academy.student.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.member.db.MemberDAO;
import academy.student.db.StudentDAO;

public class StudentGroupsAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		List  groups = new ArrayList();
		
		StudentDAO studentDAO = new StudentDAO();
		SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");
		groups = studentDAO.getstudentgroups();
		MemberDAO memberDAO = new MemberDAO();
		String newID = memberDAO.getNewEmployeeID("S" + sdf.format(System.currentTimeMillis()));
		
		   String memberid=""; //주소합칠대사용
	        String result1=newID.substring(0,1) ; //첫자리 잘라내기
	        String result2 =newID.substring(1) ; // 나머지 뒷자리 잘라내기
	            if(result1.equals("s") || result1.equals("S")){
	                result1="P"; //부모아이뒤로 바꿔주기 
	                memberid = result1+result2; //다시 합치기
	           }
		request.setAttribute("groups", groups);
		forward.setRedirect(false);
		request.setAttribute("newID", newID);
		request.setAttribute("memberid", memberid);
		forward.setPath("./StudentJoin.st");
		return forward;
	}

}
