package academy.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentOutListAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		// 퇴출된 학생 조회
				ActionForward forward = new ActionForward();
				
				StudentDAO studentdao = new StudentDAO();
				
				List<StudentBean> StudentOutList  = null;
				int studentcountout = studentdao.studentcountout(); //글개수
				
				
				int page=1;  //현재페이지
				int limit=10; //한페이지 글수
				//page !=null  page=
				if(request.getParameter("page")!=null){
					page=Integer.parseInt(request.getParameter("page"));
				}
				StudentOutList =  studentdao.getStudentOutList(page,limit);//퇴학생조회
				//페이징
				// 전체페이지수
				int maxpage=studentcountout/limit+(studentcountout%limit==0?0:1);
				maxpage=(int)((double)studentcountout/limit+0.95);//올림
				//시작페이지번호 1  11  21  31
				int pageblock=3; //1 -10 페이지 보여주는 개수
				int startpage=((int)(page/pageblock)-(page%pageblock==0?1:0))*pageblock+1;
				startpage=(((int)((double)page/pageblock+0.9))-1)*pageblock+1;
				//끝페이지 10 20 30 ..
				int endpage=startpage+pageblock-1;
				if(endpage>maxpage){
					endpage=maxpage;
				}
				//request.setAttribute(이름,값) //정보저장
				if(StudentOutList!=null){
					System.out.println("있음");
				}else{
					System.out.println("없음");
				}
				request.setAttribute("page",page);
				request.setAttribute("maxpage", maxpage);
				request.setAttribute("startpage", startpage);
				request.setAttribute("endpage",endpage);
				request.setAttribute("studentcount", studentcountout);
				request.setAttribute("StudentOutList", StudentOutList); // LeaveofabsenceList정보를 저장한다.
				forward.setRedirect(false);
				forward.setPath("./student/student_out_list.jsp");//student_leaveofabsencelist폼으로 이동
				
				return forward;
	}

}
