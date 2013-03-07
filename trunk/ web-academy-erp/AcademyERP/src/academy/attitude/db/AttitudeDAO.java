package academy.attitude.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closingDB() {
		if (con != null) try {con.close();} catch (Exception e) {}
		if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
		if (rs != null) try {rs.close();} catch (Exception e) {}
	}
	
	public List getEmployeeAttitudeList(String date) throws Exception { // 직원 출근 현황
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
				sql = "SELECT * FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
				// 검색한 날짜의 출근 시간(00:00:00부터 23:59:59까지) 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("ep_id"));
				pstmt.setString(2, date + " 00:00:00");
				pstmt.setString(3, date + " 23:59:59");
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 출근 데이터가 있을 경우
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
				} else { // 출근 데이터가 없을 경우
					AttitudeBean attitude = new AttitudeBean();
					
					attitude.setMm_name(rs.getString("mm_name"));
					attitude.setAt_member_id(rs.getString(1)); // ID 받아옴
					attitude.setAt_report_state("N"); // 출근 상태 N(미출근) 으로 설정
					
					sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date >= ? AND at_memo_date <= ?";
					// 미출근 상태에서 해당 날짜(00:00:00부터 23:59:59까지)의 메모(사유)가 있는지 확인
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString(1));
					pstmt.setString(2, date + " 00:00:00");
//					System.out.println("출근 전인 사람 메모 날짜 : " + date);
					pstmt.setString(3, date + " 23:59:59");
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
	
    public int employeeAttitudeTimeRecording(String id, String type, String date) throws Exception {
    	int result = 0;
    	ResultSet rs2 = null;
    	try {
//    		System.out.println("기록 준비");
    		con = ds.getConnection();
    		sql = "SELECT at_member_id,at_come_time FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
			pstmt.setString(2, date + " 00:00:00");
			pstmt.setString(3, date + " 23:59:59");
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			System.out.println("이전 시간 등록 내역 있음");
    				System.out.println("출근 내역 있음");
    				sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    	    		pstmt = con.prepareStatement(sql);
    	    		pstmt.setString(1, id);
					pstmt.setString(2, date + " 00:00:00");
					pstmt.setString(3, date + " 23:59:59");
    	    		pstmt.executeUpdate();
    	    		result = 1;
    		} else {
    			System.out.println("이전 시간 등록 내역 없음");
    			if (!type.equals("leave")) {
    				sql = "SELECT at_memo,at_memo_date FROM attitude WHERE at_member_id=?AND at_memo_date >= ? AND at_memo_date <= ?";
    				// 출근 시간 등록 시 기존 메모 기록되어 있는지 확인
    				pstmt = con.prepareStatement(sql);
    				pstmt.setString(1, id);
					pstmt.setString(2, date + " 00:00:00");
					pstmt.setString(3, date + " 23:59:59");
    				rs2 = pstmt.executeQuery();
    				
    				if (rs2.next()) {
    					System.out.println("이전 시간 등록 내역 없음 출근시간 등록 Date : " + date);
    					sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_memo_date >= ? AND at_memo_date <= ?";
        	    		pstmt = con.prepareStatement(sql);
        	    		pstmt.setString(1, id);
    					pstmt.setString(2, date + " 00:00:00");
    					pstmt.setString(3, date + " 23:59:59");
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
    	return result;
    }
   
    public boolean employeeAttitudeAddMemo(String id, String at_memo, String date) throws Exception {
    	boolean result = false;
    	try {
//    		System.out.println("DAO Date : " + date);
    		con = ds.getConnection();
    		sql = "SELECT at_idx, at_memo FROM attitude WHERE at_member_id=? AND at_memo_date >= ? AND at_memo_date <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
			pstmt.setString(2, date + " 00:00:00");
			pstmt.setString(3, date + " 23:59:59");
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) { // 기존 메모 있을 경우
    			System.out.println("메모 추가");
    			sql = "UPDATE attitude SET at_memo=?,at_memo_date=now() WHERE at_idx=?";
    			pstmt = con.prepareStatement(sql);
    			pstmt.setString(1, at_memo);
    			pstmt.setInt(2, rs.getInt("at_idx"));
    			pstmt.executeUpdate();
    		} else { // 기존 메모 없을 경우
    			System.out.println("기존 메모 없을 경우");
    			ResultSet rs2 = null;
    			sql = "SELECT at_idx FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
        		pstmt = con.prepareStatement(sql);
        		pstmt.setString(1, id);
    			pstmt.setString(2, date + " 00:00:00");
    			pstmt.setString(3, date + " 23:59:59");
        		rs2 = pstmt.executeQuery();
        		
        		if (rs2.next()) {
        				System.out.println("출근 내역 있음");
        				sql = "UPDATE attitude SET at_memo=?,at_memo_date=now() WHERE at_idx=?";
        	    		pstmt = con.prepareStatement(sql);
        	    		pstmt.setString(1, at_memo);
        	    		pstmt.setInt(2, rs2.getInt("at_idx"));
        	    		pstmt.executeUpdate();
        		} else {
	    			System.out.println("새 메모 작성");
	    			sql = "INSERT INTO attitude (at_member_id,at_report_state,at_memo,at_memo_date) VALUES(?,'N',?,now())";
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setString(1, id);
	    			pstmt.setString(2, at_memo);
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

    public boolean employeeAttitudeCancel(String id, String type, String date) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
			pstmt.setString(2, date + " 00:00:00");
			pstmt.setString(3, date + " 23:59:59");
    		rs = pstmt.executeQuery();
	    		
    		if (rs.next()) { // 메모 있을 경우
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
		    		sql = "UPDATE attitude SET at_come_time=?, at_leave_time=?,at_report_state=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
		    		pstmt = con.prepareStatement(sql);
		    		pstmt.setDate(1, null);
		    		pstmt.setDate(2, null);
		    		pstmt.setString(3, "N");
		    		pstmt.setString(4, id);
					pstmt.setString(5, date + " 00:00:00");
					pstmt.setString(6, date + " 23:59:59");
		    		pstmt.executeUpdate();
    			} else if (type.equals("leave")) { // 퇴근 시간 취소 버튼 클릭시
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
    				pstmt.setString(2, id);
    				pstmt.setString(3, date + " 00:00:00");
					pstmt.setString(4, date + " 23:59:59");
    				pstmt.executeUpdate();
    			}
    		} else { // 메모 없을 경우
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
	    			sql = "DELETE FROM attitude WHERE  at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
	    			pstmt = con.prepareStatement(sql);
	    			pstmt.setString(1, id);
	    			pstmt.setString(2, date + " 00:00:00");
					pstmt.setString(3, date + " 23:59:59");
	    			pstmt.executeUpdate();
    			} else { // 퇴근 시간 취소 버튼 클릭시
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
    				pstmt.setString(2, id);
					pstmt.setString(3, date + " 00:00:00");
					pstmt.setString(4, date + " 23:59:59");
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
    
    public boolean employeeAttitudeEditTime(String id, String editTime, String type, String date) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "UPDATE attitude SET at_" + type + "_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, editTime);
    		pstmt.setString(2, id);
			pstmt.setString(3, date + " 00:00:00");
			pstmt.setString(4, date + " 23:59:59");
    		pstmt.executeUpdate();
    		
    		result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }
    
    
    
    
    // 여기서부터 Student
    public List getGroupsStudentAttitudeList(String date, int page, int limit, String gp_name) throws Exception { // 학급 출석 현황
		List attitudeList = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		
		int startRow = (page - 1) * limit;
		
		try {
			con = ds.getConnection();
			sql = "SELECT student.mm_id, member.mm_name FROM student, member WHERE student.mm_id = member.mm_id AND student.st_status='재학' AND gp_name=? LIMIT ?,?"; 
			// 학급 명단(아이디, 이름) 조회
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gp_name);
			pstmt.setInt(2, startRow);
			pstmt.setInt(3, limit);
			rs = pstmt.executeQuery();

			attitudeList = new ArrayList();
			while (rs.next()) {
				sql = "SELECT * FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ? LIMIT ?,?";
				// 검색한 날짜의 출석 시간(00:00:00부터 23:59:59까지) 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("mm_id"));
				pstmt.setString(2, date + " 00:00:00");
				pstmt.setString(3, date + " 23:59:59");
				pstmt.setInt(4, startRow);
				pstmt.setInt(5, limit);
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 출근 데이터가 있을 경우
					do { // 해당 출 퇴근 시간 전달
						AttitudeBean attitude = new AttitudeBean();
						
						attitude.setMm_name(rs.getString("mm_name")); // 학급 명단 조회 결과 중 이름 저장
						attitude.setAt_member_id(rs2.getString("at_member_id"));
						attitude.setAt_report_state(rs2.getString("at_report_state"));
						attitude.setAt_come_time(rs2.getTimestamp("at_come_time"));
						attitude.setAt_leave_time(rs2.getTimestamp("at_leave_time"));
						attitude.setAt_memo(rs2.getString("at_memo"));
						attitude.setAt_idx(rs2.getInt("at_idx"));
						
						attitudeList.add(attitude);
					} while (rs2.next());
				} else { // 출근 데이터가 없을 경우
					AttitudeBean attitude = new AttitudeBean();
					
					attitude.setMm_name(rs.getString("mm_name"));
					attitude.setAt_member_id(rs.getString(1)); // ID 받아옴
					attitude.setAt_report_state("N"); // 출근 상태 N(미출근) 으로 설정
					
					sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date >= ? AND at_memo_date <= ? LIMIT ?,?";
					// 미출근 상태에서 해당 날짜(00:00:00부터 23:59:59까지)의 메모(사유)가 있는지 확인
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString(1));
					pstmt.setString(2, date + " 00:00:00");
//					System.out.println("출근 전인 사람 메모 날짜 : " + date);
					pstmt.setString(3, date + " 23:59:59");
					pstmt.setInt(4, startRow);
					pstmt.setInt(5, limit);
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
    
    public int getGroupsStudentCount(String gp_name) throws Exception {
    	int listCount = 0;
    	try {
    		con = ds.getConnection();
    		sql = "SELECT COUNT(st_idx) FROM student WHERE gp_name = ? AND st_status='재학'";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, gp_name);
    		rs = pstmt.executeQuery();
    		if (rs.next()) {
    			listCount = rs.getInt(1);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
		
		return listCount;
    }
    
    public boolean studentAttitudeEditTime(String id, String editTime, String type, String date) throws Exception { // 학생 출결 시간 수정
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "UPDATE attitude SET at_" + type + "_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, editTime);
    		pstmt.setString(2, id);
			pstmt.setString(3, date + " 00:00:00");
			pstmt.setString(4, date + " 23:59:59");
    		pstmt.executeUpdate();
    		
    		result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }
    
    public boolean studentAttitudeAddMemo(String id, String at_memo) throws Exception {
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
    public int studentAttitudeTimeRecording(String id, String type, String date) throws Exception { // 학생 출결 시간 기록
        	int result = 0;
        	ResultSet rs2 = null;
        	try {
//        		System.out.println("기록 준비");
        		con = ds.getConnection();
        		sql = "SELECT at_member_id,at_come_time FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
        		pstmt = con.prepareStatement(sql);
        		pstmt.setString(1, id);
    			pstmt.setString(2, date + " 00:00:00");
    			pstmt.setString(3, date + " 23:59:59");
        		rs = pstmt.executeQuery();
        		
        		if (rs.next()) {
//        			System.out.println("이전 시간 등록 내역 있음");
//        				System.out.println("출근 내역 있음");
        				sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
        	    		pstmt = con.prepareStatement(sql);
        	    		pstmt.setString(1, id);
    					pstmt.setString(2, date + " 00:00:00");
    					pstmt.setString(3, date + " 23:59:59");
        	    		pstmt.executeUpdate();
        	    		result = 1;
        		} else {
//        			System.out.println("이전 시간 등록 내역 없음");
        			if (!type.equals("leave")) {
        				sql = "SELECT at_memo,at_memo_date FROM attitude WHERE at_member_id=?AND at_memo_date >= ? AND at_memo_date <= ?";
        				// 출근 시간 등록 시 기존 메모 기록되어 있는지 확인
        				pstmt = con.prepareStatement(sql);
        				pstmt.setString(1, id);
    					pstmt.setString(2, date + " 00:00:00");
    					pstmt.setString(3, date + " 23:59:59");
        				rs2 = pstmt.executeQuery();
        				
        				if (rs2.next()) {
//        					System.out.println("이전 시간 등록 내역 없음 메모 있음");
        					sql = "UPDATE attitude SET at_report_state='Y',at_" + type + "_time=now() WHERE at_member_id=? AND at_memo_date >= ? AND at_memo_date <= ?";
            	    		pstmt = con.prepareStatement(sql);
            	    		pstmt.setString(1, id);
        					pstmt.setString(2, date + " 00:00:00");
        					pstmt.setString(3, date + " 23:59:59");
            	    		pstmt.executeUpdate();
    			    		result = 1;
        				} else {
//        					System.out.println("이전 시간 등록 내역 없음 메모 없음");
        					sql = "INSERT INTO attitude (at_member_id, at_report_state, at_" + type + "_time) VALUES(?,'Y',now())";
    		    			pstmt = con.prepareStatement(sql);
    			    		pstmt.setString(1, id);
    			    		pstmt.executeUpdate();
            	    		result = 1;
        				}
        			} else {
        				System.out.println("type = leave");
        				result = -1;
        			}
        		}
    		} catch (Exception e) {
    			e.printStackTrace();
    		} finally {
    			closingDB();
    		}
        	return result;
    }
    
    public boolean studentAttitudeCancel(String id, String type, String date) throws Exception {
    	boolean result = false;
    	try {
    		con = ds.getConnection();
    		sql = "SELECT at_idx, at_memo FROM attitude WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
			pstmt.setString(2, date + " 00:00:00");
			pstmt.setString(3, date + " 23:59:59");
    		rs = pstmt.executeQuery();
	    		
    		if (rs.next()) { // 메모 있을 경우
    			System.out.println("메모 있음");
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
    				System.out.println("결근 처리 or 출근 시간 취소 버튼 클릭 시");
		    		sql = "UPDATE attitude SET at_come_time=?, at_leave_time=?,at_report_state=? WHERE at_idx=?";
		    		pstmt = con.prepareStatement(sql);
		    		pstmt.setDate(1, null);
		    		pstmt.setDate(2, null);
		    		pstmt.setString(3, "N");
		    		pstmt.setInt(4, rs.getInt("at_idx"));
		    		pstmt.executeUpdate();
    			} else if (type.equals("leave")) { // 퇴근 시간 취소 버튼 클릭시
    				System.out.println("퇴근 시간 취소 버튼 클릭 시");
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_idx=?";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
		    		pstmt.setInt(2, rs.getInt("at_idx"));
    				pstmt.executeUpdate();
    			}
    		} else { // 메모 없을 경우
    			System.out.println("메모 없음");
    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
    				System.out.println("결근 처리 or 출근 시간 취소 버튼 클릭 시");
	    			sql = "DELETE FROM attitude WHERE at_idx=?";
	    			pstmt = con.prepareStatement(sql);
		    		pstmt.setInt(1, rs.getInt("at_idx"));
	    			pstmt.executeUpdate();
    			} else { // 퇴근 시간 취소 버튼 클릭시
    				System.out.println("퇴근 시간 취소 버튼 클릭 시");
    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_idx=?";
    				pstmt = con.prepareStatement(sql);
    				pstmt.setDate(1, null);
		    		pstmt.setInt(2, rs.getInt("at_idx"));
	    			pstmt.executeUpdate();
    			}
    		}
//    		if (rs.next()) { // 메모 있을 경우
//    			System.out.println("메모 있음");
//    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
//    				System.out.println("결근 처리 or 출근 시간 취소 버튼 클릭 시");
//		    		sql = "UPDATE attitude SET at_come_time=?, at_leave_time=?,at_report_state=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
//		    		pstmt = con.prepareStatement(sql);
//		    		pstmt.setDate(1, null);
//		    		pstmt.setDate(2, null);
//		    		pstmt.setString(3, "N");
//		    		pstmt.setString(4, id);
//					pstmt.setString(5, date + " 00:00:00");
//					pstmt.setString(6, date + " 23:59:59");
//		    		pstmt.executeUpdate();
//    			} else if (type.equals("leave")) { // 퇴근 시간 취소 버튼 클릭시
//    				System.out.println("퇴근 시간 취소 버튼 클릭 시");
//    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
//    				pstmt = con.prepareStatement(sql);
//    				pstmt.setDate(1, null);
//    				pstmt.setString(2, id);
//    				pstmt.setString(3, date + " 00:00:00");
//					pstmt.setString(4, date + " 23:59:59");
//    				pstmt.executeUpdate();
//    			}
//    		} else { // 메모 없을 경우
//    			System.out.println("메모 있음");
//    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
//    				System.out.println("결근 처리 or 출근 시간 취소 버튼 클릭 시");
//	    			sql = "DELETE FROM attitude WHERE  at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
//	    			pstmt = con.prepareStatement(sql);
//	    			pstmt.setString(1, id);
//	    			pstmt.setString(2, date + " 00:00:00");
//					pstmt.setString(3, date + " 23:59:59");
//	    			pstmt.executeUpdate();
//    			} else { // 퇴근 시간 취소 버튼 클릭시
//    				System.out.println("퇴근 시간 취소 버튼 클릭 시");
//    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time >= ? AND at_come_time <= ?";
//    				pstmt = con.prepareStatement(sql);
//    				pstmt.setDate(1, null);
//    				pstmt.setString(2, id);
//					pstmt.setString(3, date + " 00:00:00");
//					pstmt.setString(4, date + " 23:59:59");
//	    			pstmt.executeUpdate();
//    			}
//    		}
    		
    		result = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
    	
    	return result;
    }
//    public boolean studentAttitudeCancel(String id, String type) throws Exception {
//    	boolean result = false;
//    	try {
//    		con = ds.getConnection();
//    		sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
//    		pstmt = con.prepareStatement(sql);
//    		pstmt.setString(1, id);
//    		rs = pstmt.executeQuery();
//	    		
//    		if (rs.next()) { // 메모 있을 경우
//    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
//		    		sql = "UPDATE attitude SET at_come_time=?, at_leave_time=?,at_report_state=? WHERE at_member_id=? AND at_come_time > current_date()";
//		    		pstmt = con.prepareStatement(sql);
//		    		pstmt.setDate(1, null);
//		    		pstmt.setDate(2, null);
//		    		pstmt.setString(3, "N");
//		    		pstmt.setString(4, id);
//		    		pstmt.executeUpdate();
//    			} else if (type.equals("leave")) { // 퇴근 시간 취소 버튼 클릭시
//    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time > current_date()";
//    				pstmt = con.prepareStatement(sql);
//    				pstmt.setDate(1, null);
//    				pstmt.setString(2, id);
//    				pstmt.executeUpdate();
//    			}
//    		} else { // 메모 없을 경우
//    			if (type.equals("all") || type.equals("come")) { // 결근 처리 or 출근 시간 취소 버튼 클릭 시
//	    			sql = "DELETE FROM attitude WHERE  at_member_id=? AND at_come_time > current_date()";
//	    			pstmt = con.prepareStatement(sql);
//	    			pstmt.setString(1, id);
//	    			pstmt.executeUpdate();
//    			} else { // 퇴근 시간 취소 버튼 클릭시
//    				sql = "UPDATE attitude SET at_leave_time=? WHERE at_member_id=? AND at_come_time > current_date()";
//    				pstmt = con.prepareStatement(sql);
//    				pstmt.setDate(1, null);
//    				pstmt.setString(2, id);
//	    			pstmt.executeUpdate();
//    			}
//    		}
//    		
//    		result = true;
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			closingDB();
//		}
//    	
//    	return result;
//    }
    
    public String getstudentAttitudeGpid(String id){
    	String sql="";
		String gp_id=null;
		
		try {
			con =ds.getConnection();
			sql="select gp_id from student As s, " +
					"attitude As a where s.mm_id = a.at_member_id  and " +
					"at_member_id =? and a. at_idx = (select  max(at_idx) from attitude where at_member_id=?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, id);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				gp_id = rs.getString("gp_id");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closingDB();
		}
    	
    	return gp_id;
    }
    // 해당아이디의 출석한 갯수를 구한다.
    public int getAttitudecount(String memberid){
		String sql="";
		int attitudecount =0;
		try {
			con= ds.getConnection();
			sql="select count(*) from attitude where at_member_id =?"; // 구해오는 sql문장
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberid);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				attitudecount = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closingDB();
		}
    	return attitudecount;
    	
    }
    public List getAttitudeStudent(String memberid,int page,int limit){
    	int startrow=(page-1)*limit+1; //현재페이지 시작행
    	List attitude = null;
    	String sql="";
    	String state=""; //상태를 한글로 바꾸기 
    	try {
			con=ds.getConnection();
			sql="select at_report_state,at_come_time,at_leave_time,at_memo from attitude where at_member_id =? order by at_come_time desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, memberid);
			pstmt.setInt(2, startrow-1);// 시작계수
			pstmt.setInt(3, limit);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				attitude = new ArrayList();
				do{
					AttitudeBean attitudebean = new AttitudeBean();
					if(rs.getString("at_report_state").equals("Y") ||rs.getString("at_report_state").equals("y") ){ //at_report_state상태가 Y이면 출석으로 해준다.
						state = "출석";
					}else{
						state ="결석";
					}
					attitudebean.setAt_report_state(state);
					attitudebean.setAt_come_time(rs.getTimestamp("at_come_time"));
					attitudebean.setAt_leave_time(rs.getTimestamp("at_leave_time"));
					attitudebean.setAt_memo(rs.getString("at_memo"));
					
					attitude.add(attitudebean);
					
				}while(rs.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			closingDB();
		}
    	return attitude;
    	
    }
}
