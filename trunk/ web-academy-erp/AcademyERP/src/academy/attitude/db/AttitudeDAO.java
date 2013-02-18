package academy.attitude.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

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
	
	public List getEmployeeAttitudeList() throws Exception { // 직원 출결 현황
		List attitudeList = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		try {
			con = ds.getConnection();
			sql = "SELECT employee.ep_id,member.mm_name FROM employee,member WHERE employee.ep_id = member.mm_id AND employee.ep_status='재직'"; 
			// 직원 명단(아이디, 이름) 조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			attitudeList = new ArrayList();
			while (rs.next()) {
				sql = "SELECT * FROM attitude WHERE at_member_id=? AND at_come_time > current_date()";
				// 출근 시간 날짜가 오늘인 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("ep_id"));
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 오늘 출근 데이터가 있을 경우
					do { // 해당 출 퇴근 시간 전달
						AttitudeBean attitude = new AttitudeBean();
						
						attitude.setMm_name(rs.getString("mm_name")); // 직원 명단 조회 결과 중 이름 저장
						attitude.setAt_member_id(rs2.getString("at_member_id"));
						attitude.setAt_report_state(rs2.getString("at_report_state"));
						attitude.setAt_come_time(rs2.getTimestamp("at_come_time"));
						attitude.setAt_leave_time(rs2.getTimestamp("at_leave_time"));
						attitude.setAt_memo(rs2.getString("at_memo"));
						attitude.setAt_idx(rs2.getInt("at_idx"));
						
						attitudeList.add(attitude);
					} while (rs2.next());
				} else { // 오늘 출근 데이터가 없을 경우
					AttitudeBean attitude = new AttitudeBean();
					
					attitude.setMm_name(rs.getString("mm_name"));
					attitude.setAt_member_id(rs.getString(1)); // ID 받아옴
					attitude.setAt_report_state("N"); // 출근 상태 N(미출근) 으로 설정
					
					sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
					// 미출근 상태에서 오늘 날짜의 메모(사유)가 있는지 확인
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString(1));
					rs3 = pstmt.executeQuery();
					
					if (rs3.next()) { // 메모(사유)가 있을 경우
						attitude.setAt_memo(rs3.getString("at_memo"));
					} 
					
					// 메모조차 없으면 전부 자동으로 NULL로 설정됨
					
					attitudeList.add(attitude);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
		
		return attitudeList;
	}
	
    public int employeeAttitudeTimeRecording(String id, String type) throws Exception {
    	int result = 0;
    	ResultSet rs2 = null;
    	try {
    		System.out.println("기록 준비");
    		con = ds.getConnection();
    		sql = "SELECT at_member_id,at_come_time FROM attitude WHERE at_member_id=? AND at_come_time > current_date()";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			System.out.println("이전 시간 등록 내역 있음");
    				System.out.println("출근 내역 있음");
    				sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_come_time > current_date()";
    	    		pstmt = con.prepareStatement(sql);
    	    		pstmt.setString(1, id);
    	    		pstmt.executeUpdate();
    	    		result = 1;
    		} else {
    			System.out.println("이전 시간 등록 내역 없음");
    			if (!type.equals("leave")) {
    				sql = "SELECT at_memo,at_memo_date FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setString(1, id);
    				rs2 = pstmt.executeQuery();
    				
    				if (rs2.next()) {
    					sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_memo_date > current_date()";
        	    		pstmt = con.prepareStatement(sql);
        	    		pstmt.setString(1, id);
        	    		pstmt.executeUpdate();
			    		result = 1;
    				} else {
    					sql = "INSERT INTO attitude (at_member_id, at_report_state, at_" + type + "_time) VALUES(?,'Y',now())";
		    			pstmt = con.prepareStatement(sql);
			    		pstmt.setString(1, id);
			    		pstmt.executeUpdate();
        	    		result = 1;
    				}
    			} else {
    				result = -1;
    			}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	System.out.println(result);
    	return result;
    }
   
    public boolean employeeAttitudeAddMemo(String id, String at_memo) throws Exception {
    	boolean result = false;
    	try {
    		System.out.println(id);
    		con = ds.getConnection();
    		sql = "SELECT at_idx, at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			System.out.println("메모 추가");
    			sql = "UPDATE attitude SET at_memo=?,at_memo_date=now() WHERE at_idx=?";
    			pstmt = con.prepareStatement(sql);
    			pstmt.setString(1, at_memo);
    			pstmt.setInt(2, rs.getInt("at_idx"));
    			pstmt.executeUpdate();
    		} else {
    			System.out.println("새 메모 작성");
    			sql = "INSERT INTO attitude (at_member_id,at_report_state,at_memo,at_memo_date) VALUES(?,'N',?,now())";
    			pstmt = con.prepareStatement(sql);
    			pstmt.setString(1, id);
    			pstmt.setString(2, at_memo);
    			pstmt.executeUpdate();
    		}
    		result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }

    public boolean employeeAttitudeCancel(String id, String type) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		rs = pstmt.executeQuery();
	    		
    		if (rs.next()) { // 메모 있을 경우
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
		    		sql = "UPDATE attitude SET at_come_time=?, at_leave_time=?,at_report_state=? WHERE at_member_id=? AND at_come_time > current_date()";
		    		pstmt = con.prepareStatement(sql);
		    		pstmt.setDate(1, null);
		    		pstmt.setDate(2, null);
		    		pstmt.setString(3, "N");
		    		pstmt.setString(4, id);
		    		pstmt.executeUpdate();
    			} else if (type.equals("leave")) { // 퇴근 시간 취소 버튼 클릭시
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time > current_date()";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
    				pstmt.setString(2, id);
    				pstmt.executeUpdate();
    			}
    		} else { // 메모 없을 경우
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
	    			sql = "DELETE FROM attitude WHERE  at_member_id=? AND at_come_time > current_date()";
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setString(1, id);
	    			pstmt.executeUpdate();
    			} else { // 퇴근 시간 취소 버튼 클릭시
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time > current_date()";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
    				pstmt.setString(2, id);
	    			pstmt.executeUpdate();
    			}
    		}
    		
    		result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }
    
    public boolean employeeAttitudeEditTime(String id, String editTime, String type) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "UPDATE attitude SET at_" + type + "_time=? WHERE at_member_id=? AND at_come_time > current_date()";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, editTime);
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
