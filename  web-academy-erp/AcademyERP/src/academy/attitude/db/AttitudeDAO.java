package academy.attitude.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.employee.db.EmployeeBean;

public class AttitudeDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = "";

	public AttitudeDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closingDB() {
		if (con != null) try {con.close();} catch (Exception e) {}
		if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
		if (rs != null) try {rs.close();} catch (Exception e) {}
	}
	
	public List getEmployeeAttitudeList() throws Exception {
		List attitudeList = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			con = ds.getConnection();
			sql = "SELECT ep_id FROM employee"; // 직원 명단 조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			attitudeList = new ArrayList();
			while (rs.next()) {
				sql = "SELECT * FROM attitude WHERE at_open_time > current_date() AND at_member_id=?";
				// 출근 시간 날짜가 오늘인 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString(1));
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 오늘 출근 데이터가 있을 경우
					do { // 해당 출 퇴근 시간 입력
						AttitudeBean attitude = new AttitudeBean();
						
						attitude.setAt_member_id(rs2.getString("at_member_id"));
						attitude.setAt_report_state(rs2.getString("at_report_state"));
						attitude.setAt_open_time(rs2.getDate("at_open_time"));
						attitude.setAt_close_time(rs2.getDate("at_close_time"));
						attitude.setAt_memo(rs2.getString("at_memo"));
						
						attitudeList.add(attitude);
					} while (rs2.next());
				} else { // 오늘 출근 데이터가 없을 경우
					AttitudeBean attitude = new AttitudeBean();
					
					attitude.setAt_member_id(rs.getString(1));
					attitude.setAt_report_state("N");
					
					sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString(1));
					rs3 = pstmt.executeQuery();
					
					if (rs.next()) {
						attitude.setAt_memo(rs.getString(1));
					} 
//					attitude.setAt_open_time(rs.getDate("at_open_time")); // 출근시간 NULL
//					attitude.setAt_close_time(rs.getDate("at_close_time")); // 퇴근시간 NULL
//					attitude.setAt_memo(rs.getString(1)); // 메모 NULL
					
					attitudeList.add(attitude);
				}
			}
//			con = ds.getConnection();			
//			sql = "SELECT * FROM attitude WHERE at_open_time > current_date() AND at_member_id LIKE 'T%'";
//			pstmt = con.prepareStatement(sql);
//			rs2 = pstmt.executeQuery();
//			
//			attitudeList = new ArrayList();
//			while (rs2.next()) {
//				AttitudeBean attitude = new AttitudeBean();
//				
//				attitude.setAt_member_id(rs.getString("at_member_id"));
//				attitude.setAt_report_state(rs.getString("at_report_state"));
//				attitude.setAt_open_time(rs.getDate("at_open_time"));
//				attitude.setAt_close_time(rs.getDate("at_close_time"));
//				attitude.setAt_memo(rs.getString("at_memo"));
//				
//				attitudeList.add(attitude);
//			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
		
		return attitudeList;
	}

}
