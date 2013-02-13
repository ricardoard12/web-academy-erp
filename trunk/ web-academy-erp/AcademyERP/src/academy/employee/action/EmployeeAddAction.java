package academy.employee.action;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import academy.employee.db.EmployeeBean;
import academy.employee.db.EmployeeDAO;

public class EmployeeAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		System.out.println("EmployeeAddAction");
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		EmployeeBean employee = new EmployeeBean();
		EmployeeDAO employeeDAO = new EmployeeDAO();
		
		employee.setMm_name(request.getParameter("mm_name")); // 이름
		employee.setMm_id(request.getParameter("mm_id")); // 아이디 (직원 아이디도 동일)
		employee.setMm_passwd(request.getParameter("mm_passwd")); // 패스워드
		employee.setMm_jumin1(request.getParameter("mm_jumin1")); // 주민번호 앞
		employee.setMm_jumin2(request.getParameter("mm_jumin2")); // 주민번호 뒤
		employee.setMm_tel(request.getParameter("mm_telDDD") + "-" + request.getParameter("mm_tel")); // 전화번호
		employee.setMm_phone(request.getParameter("mm_phoneDDD") + "-" + request.getParameter("mm_phone")); // 휴대폰
		employee.setMm_addr1(request.getParameter("mm_addr1")); // 주소
		employee.setMm_addr2(request.getParameter("mm_addr2")); // 상세주소
		employee.setMm_zipcode(request.getParameter("mm_zipcode1") + "-" + request.getParameter("mm_zipcode2")); // 우편번호
		employee.setMm_email(request.getParameter("mm_email1") + "@" + request.getParameter("mm_email2")); // 이메일
		employee.setMm_level(Integer.parseInt(request.getParameter("mm_level"))); // 회원 등급
		employee.setMm_manager_id(request.getParameter("mm_manager_id")); // 상위 관리자
				
		employee.setEp_position(request.getParameter("ep_position")); // 직급
		employee.setEp_department(request.getParameter("ep_department")); // 부서
		employee.setEp_group_id(request.getParameter("ep_group_id")); // 담당 학급
		employee.setEp_subject_name(request.getParameter("ep_subject_name")); // 담당 과목
		employee.setEp_bank_name(request.getParameter("ep_bank_name")); // 계좌 은행
		employee.setEp_account_num(request.getParameter("ep_account_num")); // 계좌번호
		employee.setEp_account_name(request.getParameter("ep_account_name")); // 예금주
		employee.setEp_salary(Integer.parseInt(request.getParameter("ep_salary"))); // 연봉
		
		boolean result = employeeDAO.employeeInsert(employee);
		
		if (result == false) {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('회원가입 실패!')");
			out.println("history.back()");
			out.println("</script>");
			out.close();
			return null;
		}
		
		System.out.println("직원 등록 성공");
		
		forward.setRedirect(true);
		forward.setPath("./EmployeeListAction.em");
		return forward;
	}

}
