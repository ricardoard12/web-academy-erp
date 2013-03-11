package academy.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentDetailAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		ActionForward forward = new ActionForward();
		
		StudentDAO studentdao = new StudentDAO();
		
		StudentBean studentbean = null;
		
		String check=request.getParameter("check"); //수강생(1),휴원생(2),퇴원생(3)에서의 목록인지 판단  
		
		String id  = request.getParameter("id");  // 아이디값 저정
				
        String memberid=""; //주소합칠대사용
        String result1=id.substring(0,1) ; //첫자리 잘라내기
        String result2 =id.substring(1) ; // 나머지 뒷자리 잘라내기
            if(result1.equals("p") || result1.equals("P")){
                result1="S"; //p로 시작하는 부보 아이뒤일경우 S로 바꿔준다.
                memberid = result1+result2; //다시 합치기
            }else{
                memberid = result1+result2;
            }
		
		studentbean=  studentdao.getStudentDetail(memberid); // DB에서 조회
		List  groups = studentdao.getstudentgroups();
		request.setAttribute("studentbean", studentbean);
		request.setAttribute("groups", groups);//소속학급 수정시 전체 소속학급 조회
		request.setAttribute("check", check);//수강생(1),휴원생(2),퇴원생(3)에서의 목록인지 판단후 저장된값 전송  
		
		if(!check.equals("4")){
			forward.setRedirect(false);
			forward.setPath("./student/student_detail.jsp");
		}
		else{
			forward.setRedirect(false);
			forward.setPath("./student/student_detail2.jsp");
		}
		return forward;
	}

}
