package academy.employee.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.groups.db.GroupsBean;
import academy.member.db.MemberBean;

public class EmployeeDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = "";

	public EmployeeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("Emp DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closingDB() {
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {	}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {	}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {	}
		System.out.println("Emp DB Closed");
	}

	public boolean employeeInsert(EmployeeBean employee) throws Exception { // 직원 등록
		boolean result = false;
		try {
			con = ds.getConnection();
			sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),?,?,null)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getMm_name());
			pstmt.setString(2, employee.getMm_id());
			pstmt.setString(3, employee.getMm_passwd());
			pstmt.setString(4, employee.getMm_jumin1());
			pstmt.setString(5, employee.getMm_jumin2());
			pstmt.setString(6, employee.getMm_tel());
			pstmt.setString(7, employee.getMm_phone());
			pstmt.setString(8, employee.getMm_addr1());
			pstmt.setString(9, employee.getMm_addr2());
			pstmt.setString(10, employee.getMm_zipcode());
			pstmt.setString(11, employee.getMm_email());
			pstmt.setString(12, employee.getMm_level());
			pstmt.setString(13, employee.getMm_manager_id());
			pstmt.executeUpdate();

			sql = "INSERT INTO employee (ep_id,ep_position,ep_department,ep_subject_name,ep_bank_name,ep_account_num,ep_account_name,ep_salary,ep_in_date) VALUES(?,?,?,?,?,?,?,?,now())";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getMm_id());
			pstmt.setString(2, employee.getEp_position());
			pstmt.setString(3, employee.getEp_department());
			pstmt.setString(4, employee.getEp_subject_name());
			pstmt.setString(5, employee.getEp_bank_name());
			pstmt.setString(6, employee.getEp_account_num());
			pstmt.setString(7, employee.getEp_account_name());
			pstmt.setInt(8, employee.getEp_salary());
			pstmt.executeUpdate();

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}

		return result;
	}

	public Vector getEmployeeList() throws Exception {
		List employeeList = null;
		List memberList = null;
		List groupsList = null;
		Vector vector = new Vector();
		try {
			con = ds.getConnection();
			sql = "SELECT * FROM employee WHERE ep_status='재직'";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			employeeList = new ArrayList();
			memberList = new ArrayList();
			groupsList = new ArrayList();
			ResultSet rs2 = null;
			while (rs.next()) { // ep_status = 재직인 사람이 있을 경우
				EmployeeBean employee = new EmployeeBean();
				MemberBean member = new MemberBean();
				GroupsBean groups = new GroupsBean();

				employee.setEp_id(rs.getString("ep_id"));
				employee.setEp_department(rs.getString("ep_department"));
				employee.setEp_position(rs.getString("ep_position"));
				employee.setEp_subject_name(rs.getString("ep_subject_name"));

//				System.out.println("EP_ID : " + rs.getString("ep_id"));
				
				sql = "SELECT mm_name FROM member WHERE mm_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("ep_id"));
				rs2 = pstmt.executeQuery();

				if (rs2.next()) { // member 테이블의 id와 일치
					member.setMm_name(rs2.getString("mm_name")); // 회원 이름 가져옴
				}

				ResultSet rs3 = null;
				
				sql = "SELECT gp_room, gp_name FROM groups WHERE ep_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("ep_id"));
				rs3 = pstmt.executeQuery();
				
				if (rs3.next()) { // 해당 직원이 담당하는 학급 정보를 가져옴
					groups.setGp_room(rs3.getString("gp_room"));
					groups.setGp_name(rs3.getString("gp_name"));
				}
				
				employeeList.add(employee);
				memberList.add(member);
				groupsList.add(groups);
			}

			vector.add(employeeList);
			vector.add(memberList);
			vector.add(groupsList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}

		return vector;
	}    
	
    public Vector getEmployeeDetail(String id) throws Exception { // 직원 상세 정보 조회
    	EmployeeBean employee = new EmployeeBean();
    	MemberBean member = new MemberBean();
    	Vector vector = new Vector();
    	try {
    		con = ds.getConnection();
    		sql = "SELECT * FROM member,employee WHERE member.mm_id=? AND employee.ep_id=?";
    		// member 와 employee 테이블에서 일치하는 아이디가 있는지 확인
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		pstmt.setString(2, id);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			member.setMm_id(rs.getString("mm_id"));
    			member.setMm_name(rs.getString("mm_name"));
    			member.setMm_jumin1(rs.getString("mm_jumin1"));
    			member.setMm_jumin2(rs.getString("mm_jumin2"));
    			member.setMm_tel(rs.getString("mm_tel"));
    			member.setMm_phone(rs.getString("mm_phone"));
    			member.setMm_addr1(rs.getString("mm_addr1"));
    			member.setMm_addr2(rs.getString("mm_addr2"));
    			member.setMm_zipcode(rs.getString("mm_zipcode"));
    			member.setMm_email(rs.getString("mm_email"));
    			member.setMm_reg_date(rs.getDate("mm_reg_date"));
    			member.setMm_level(rs.getString("mm_level"));
    			member.setMm_manager_id(rs.getString("mm_manager_id"));
    			
    			employee.setEp_position(rs.getString("ep_position"));
    			employee.setEp_department(rs.getString("ep_department"));
    			employee.setEp_subject_name(rs.getString("ep_subject_name"));
    			employee.setEp_bank_name(rs.getString("ep_bank_name"));
    			employee.setEp_account_num(rs.getString("ep_account_num"));
    			employee.setEp_account_name(rs.getString("ep_account_name"));
    			employee.setEp_salary(rs.getInt("ep_salary"));

    			vector.add(member);
    			vector.add(employee);
    		}
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		closingDB();
    	}
    	
    	return vector;
    }
    
    public void employeeOutgoing(List outgoingList) throws Exception { // 직원 퇴직 처리
    	try {
    		con = ds.getConnection();
    		
    		for (int i = 0; i < outgoingList.size(); i++) {
    			String ep_id = (String) outgoingList.get(i);
	    		sql = "UPDATE employee SET ep_status='퇴직',ep_out_date=now() WHERE ep_id=?";
	    		// 해당 직원의 근무 현황을 '퇴직'으로 바꾸고 퇴사일을 현재 날짜로 설정 
	    		pstmt = con.prepareStatement(sql);
	    		pstmt.setString(1, ep_id);
	    		pstmt.executeUpdate();
	    		
	    		sql = "UPDATE member SET mm_level=0 WHERE mm_id=?";
	    		// 멤버 레벨을 0으로 설정 (로그인 불가능 하도록)
				pstmt = con.prepareStatement(sql);
	    		pstmt.setString(1, ep_id);
	    		pstmt.executeUpdate();
    		}
    		
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		closingDB();
    	}
    }
    
    public Vector getEmployeeOutgoingList() throws Exception { // 퇴직자 목록
		List employeeList = null;
		List memberList = null;
		Vector vector = new Vector();
		try {
			con = ds.getConnection();
			sql = "SELECT * FROM employee WHERE ep_status='퇴직'"; // '퇴직'인 사람 조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			employeeList = new ArrayList();
			memberList = new ArrayList();
			ResultSet rs2 = null;
			while (rs.next()) { // 해당 회원 정보 저장
				EmployeeBean employee = new EmployeeBean();
				MemberBean member = new MemberBean();

				employee.setEp_id(rs.getString("ep_id"));
				employee.setEp_department(rs.getString("ep_department"));
				employee.setEp_position(rs.getString("ep_position"));
				employee.setEp_subject_name(rs.getString("ep_subject_name"));
				employee.setEp_in_date(rs.getDate("ep_in_date"));
				employee.setEp_out_date(rs.getDate("ep_out_date"));
				employee.setEp_memo(rs.getString("ep_memo"));

				sql = "SELECT mm_name FROM member WHERE mm_id=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("ep_id"));
				rs2 = pstmt.executeQuery();

				if (rs2.next()) {
					member.setMm_name(rs2.getString("mm_name"));
				}

				employeeList.add(employee);
				memberList.add(member);
			}

			vector.add(employeeList);
			vector.add(memberList);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}

		return vector;
	}
    
    public List getManagerList(String mm_level) throws Exception { // 회원 가입 폼에서 상위 관리자 목록 출력
    	List managerList = null;
    	try {
    		con = ds.getConnection();
    		sql = "SELECT mm_name,mm_id,mm_level FROM member WHERE mm_level > ?";
    		// 설정할 레벨(lv.3 or 4)보다 상위 레벨(lv.4 or 5)의 회원 정보를 가져옴
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, mm_level);
    		rs = pstmt.executeQuery();
    		
    		managerList = new ArrayList();
    		while (rs.next()) {
    			MemberBean member = new MemberBean();
    			
    			member.setMm_name(rs.getString("mm_name"));
    			member.setMm_manager_id(rs.getString("mm_id"));
    			member.setMm_level(rs.getString("mm_level"));
    			
    			managerList.add(member);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return managerList;
    }
    
    public boolean employeeOutgoingAddMemo(String id, String ep_memo) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
			sql = "UPDATE employee SET ep_memo=? WHERE ep_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ep_memo);
			pstmt.setString(2, id);
			pstmt.executeUpdate();

			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }
}
