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
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
			System.out.println("Emp DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closingDB() {
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		System.out.println("Emp DB Closed");
	}

	public boolean employeeInsert(EmployeeBean employee) throws Exception {
		boolean result = false;
		try {
			con = ds.getConnection();
			sql = "INSERT INTO member VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),?,?)";
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
			pstmt.setInt(12, employee.getMm_level());
			pstmt.setString(13, employee.getMm_manager_id());
			pstmt.executeUpdate();

			sql = "INSERT INTO employee (ep_id,ep_position,ep_department,ep_group_id,ep_subject_name,ep_bank_name,ep_account_num,ep_account_name,ep_salary) VALUES(?,?,?,?,?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, employee.getMm_id());
			pstmt.setString(2, employee.getEp_position());
			pstmt.setString(3, employee.getEp_department());
			pstmt.setString(4, employee.getEp_group_id());
			pstmt.setString(5, employee.getEp_subject_name());
			pstmt.setString(6, employee.getEp_bank_name());
			pstmt.setString(7, employee.getEp_account_num());
			pstmt.setString(8, employee.getEp_account_name());
			pstmt.setInt(9, employee.getEp_salary());
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
		Vector vector = new Vector();
		try {
			con = ds.getConnection();
			sql = "SELECT * FROM employee";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			employeeList = new ArrayList();
			memberList = new ArrayList();
			ResultSet rs2 = null;
			while (rs.next()) {
				EmployeeBean employee = new EmployeeBean();
				MemberBean member = new MemberBean();

				employee.setEp_id(rs.getString("ep_id"));
				employee.setEp_department(rs.getString("ep_department"));
				employee.setEp_position(rs.getString("ep_position"));
				employee.setEp_subject_name(rs.getString("ep_subject_name"));
				employee.setEp_group_id(rs.getString("ep_group_id"));

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

	// Young-Ho source
	// get Emp List
	public Vector getEmplist(String name) {
		Vector empList = null;
		String str = "";
		if (name != null) {
			str = " AND mm_name like '%" + name + "%'";
		}
		try {
			con = ds.getConnection();
			sql = "select * from member where mm_level>=3" + str;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empList = new Vector<>();
			while (rs.next()) {
				empList.add(insertMember());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
		return empList;
	}

	private MemberBean insertMember() throws Exception {
		MemberBean member = new MemberBean();
		member.setMm_name(rs.getString("mm_name"));
		member.setMm_id(rs.getString("mm_id"));
		member.setMm_passwd(rs.getString("mm_passwd"));
		member.setMm_jumin1(rs.getString("mm_jumin1"));
		member.setMm_jumin2(rs.getString("mm_jumin2"));
		member.setMm_tel(rs.getString("mm_tel"));
		member.setMm_phone(rs.getString("mm_phone"));
		member.setMm_addr1(rs.getString("mm_addr1"));
		member.setMm_addr2(rs.getString("mm_addr2"));
		member.setMm_zipcode(rs.getString("mm_zipcode"));
		member.setMm_email(rs.getString("mm_email"));
		member.setMm_reg_date(rs.getDate("mm_reg_date"));
		member.setMm_level(rs.getInt("mm_level"));
		member.setMm_manager_id(rs.getString("mm_manager_id"));
		return member;
	}
    
    public Vector getEmployeeDetail(String id) throws Exception {
    	EmployeeBean employee = new EmployeeBean();
    	MemberBean member = new MemberBean();
    	Vector vector = new Vector();
    	try {
    		con = ds.getConnection();
    		sql = "SELECT * FROM member,employee WHERE member.mm_id=? AND employee.ep_id=?";
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
    			member.setMm_level(rs.getInt("mm_level"));
    			member.setMm_manager_id(rs.getString("mm_manager_id"));
    			
    			employee.setEp_position(rs.getString("ep_position"));
    			employee.setEp_department(rs.getString("ep_department"));
    			employee.setEp_group_id(rs.getString("ep_group_id"));
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
    
}