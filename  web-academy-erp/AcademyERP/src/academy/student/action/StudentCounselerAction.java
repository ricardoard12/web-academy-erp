package academy.student.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.counsel.db.CounselorDAO;
import academy.student.db.StudentBean;
import academy.student.db.StudentDAO;

public class StudentCounselerAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		String id ="";
		
		ActionForward forward = new ActionForward();
		
		StudentBean studentbean= new StudentBean();
		
		StudentDAO studentdao = new StudentDAO(); // 학생 정보 조회
		
		CounselorDAO counseldao = new CounselorDAO(); // 
		
		if(request.getAttribute("id")!=null){
			id= (String)request.getAttribute("id");
		}else{
			 id = (String)request.getParameter("id"); // 학생 아뒤를 가지고 온다.
		}
		System.out.println("3333"+id);
		
		studentbean = studentdao.getStudentinfo(id); // 학생 기본정보를 가지고 온다.
		
		
		List counselList = null;
		int counselcount = counseldao.getcounselcount(id); //글개수
	
		
		int page=1;  //현재페이지
		int limit=3; //한페이지 글수
		//page !=null  page=
		if(request.getParameter("page")!=null){
			page=Integer.parseInt(request.getParameter("page"));
		}

		counselList = counseldao.getcounselList(id,page,limit); // 글가지고오기
		//페이징
		// 전체페이지수
		int maxpage=counselcount/limit+(counselcount%limit==0?0:1);
		maxpage=(int)((double)counselcount/limit+0.95);//올림
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
		if(counselList!=null){
			System.out.println("있음");
		}else{
			System.out.println("없음");
		}
		
		request.setAttribute("page",page);
		request.setAttribute("maxpage", maxpage);
		request.setAttribute("startpage", startpage);
		request.setAttribute("endpage",endpage);
		request.setAttribute("counselcount", counselcount);
		request.setAttribute("counselList", counselList);
		request.setAttribute("studentbean", studentbean); // 학생의 기본정보를 저장
		
		//forward 정보 ./board/qna_board_list.jsp
		forward.setRedirect(false); //값가지고 이동
		forward.setPath("/student/student_counselList.jsp");
		return forward;
		
	}

}
