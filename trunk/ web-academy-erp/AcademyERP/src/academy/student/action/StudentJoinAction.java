package academy.student.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentJoinAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
			request.setCharacterEncoding("utf-8");
		
			ActionForward forward = new ActionForward();
		
			StudentBean studentbean = new StudentBean();
		
			studentbean.setMm_name(request.getParameter("mm_name"));  //회원이름
			studentbean.setMm_id(request.getParameter("mm_id")); //회원아이디
			studentbean.setMm_pw(request.getParameter("mm_pw")); //회원비번
			studentbean.setMm_jumin1(request.getParameter("mm_jumin1")); //주민앞
			studentbean.setMm_jumin2(request.getParameter("mm_jumin2")); // 주민뒤
			studentbean.setMm_tel(request.getParameter("mm_telDDD")+request.getParameter("mm_tel")); //회원전화번호 
			studentbean.setMm_phone(request.getParameter("mm_phoneDDD")+request.getParameter("mm_phone")); //회원핸드폰번호
			studentbean.setMm_zipcode(request.getParameter("mm_zipcode1")+request.getParameter("mm_zipcode2")); //회원우편번호
			studentbean.setMm_addr1(request.getParameter("mm_addr1"));  //회원주소
			studentbean.setMm_addr2(request.getParameter("mm_addr2"));  //상세주소    
			studentbean.setMm_email(request.getParameter("mm_email1")+request.getParameter("mm_email2")); //회원이메일
		    
		    
		    
			studentbean.setSt_school_name(request.getParameter("st_school_name")); //학교명
			studentbean.setSt_school_grade(request.getParameter("st_school_grade")); //학년
			studentbean.setSt_parent_name(request.getParameter("st_parent_name")); //학부모이름
			studentbean.setSt_parent_mobile(request.getParameter("st_parent_mobileDDD")+request.getParameter("st_parent_mobile"));//학부모연락처 
			studentbean.setSt_parent_id(request.getParameter("st_parent_id")); //학부도아이디
			studentbean.setSt_parent_passwd(request.getParameter("st_parent_passwd")); //학부모패스워드
			studentbean.setSt_tuition(Integer.parseInt(request.getParameter("st_tuition"))); //수강료
			studentbean.setSt_tuition_state(request.getParameter("st_tuition_state")); //회비납부여부
			studentbean.setSt_memo(request.getParameter("st_memo")); //메모
		
			StudentDAO studentdao = new StudentDAO(); //DB 생성
			
			studentdao.insertStudent(studentbean); //회원정보 DB에 전송
			
			//System.out.println("성공");
		return null;
	}

}
