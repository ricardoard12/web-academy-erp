package academy.groups.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.student.db.StudentBean;

public class GroupsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds;

	public GroupsDAO() {
		try {
			Context init = new InitialContext();
			System.out.println("Groups DB Connected");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List getGpList() throws Exception { // 전체 과목 가지고오기
		List gpList = null;
		String sql = "";
		try {
			con = ds.getConnection();
			sql = "select gp_name from groups"; // 전체 과목조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				gpList = new ArrayList();
				do {
					gpList.add(rs.getString("gp_name")); // 조회한 정보를 list 저장

				} while (rs.next());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return gpList;

	}

	// 해당 선생의 총 담당 학급수
	public int getCount(String ep_id) throws Exception {
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "SELECT COUNT(gp_idx) FROM groups where ep_id='"
					+ ep_id + "'";
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return x;
	}

	// 해당 선생의 담당 학급 가져오기
	public List getGroupsList(int page, int limit, String ep_id) throws Exception {
		List list = null;
		int startrow = (page - 1) * limit + 1;
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM groups where ep_id='" + ep_id
					+ "' order by gp_idx desc limit " + (startrow - 1) + ","
					+ limit;
			System.out.println("getlist Start-->");
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				list = new ArrayList();
				do {
					list.add(insertList());
				} while (rs.next());
			}
			System.out.println("getlist finished-->");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	public List getAddStudentList() throws Exception {
		List studentList = null;

		try {
			con = ds.getConnection();
			String sql = "SELECT mm_id, st_school_name, st_school_grade FROM student WHERE gp_name IS NULL"; // 학급에 소속되어 있지 않은 학생만 검색
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			studentList = new ArrayList();
			while (rs.next()) {
				StudentBean student = new StudentBean();
				ResultSet rs2 = null;
				sql = "SELECT mm_name FROM member WHERE mm_id=?"; // 해당 ㅈ생의 이름 가져오기
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("mm_id"));
				rs2 = pstmt.executeQuery();

				if (rs2.next()) {
					student.setMm_name(rs2.getString("mm_name"));
				}
				student.setMm_id(rs.getString("mm_id"));
				student.setSt_school_name(rs.getString("st_school_name"));
				student.setSt_school_grade(rs.getString("st_school_grade"));

				studentList.add(student);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}

		return studentList;
	}

	public boolean groupsAddStudent(String gp_name, List studentList) throws Exception { // 학급 학생 추가
		boolean result = false;
		try {
			con = ds.getConnection();
			for (int i = 0; i < studentList.size(); i++) {
				String mm_id = (String) studentList.get(i);
				String sql = "UPDATE student SET gp_name=? WHERE mm_id=?"; // 학생 정보의 gp_name만 변경
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gp_name);
				pstmt.setString(2, mm_id);
				pstmt.executeUpdate();
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	public boolean groupsDelStudent(List studentList) throws Exception { // 학급 학생 제외
		boolean result = false;
		try {
			con = ds.getConnection();
			for (int i = 0; i < studentList.size(); i++) {
				String mm_id = (String) studentList.get(i);
				String sql = "UPDATE student SET gp_name=? WHERE mm_id=?"; // 학생 정보의 gp_name만 변경
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, null);
				pstmt.setString(2, mm_id);
				pstmt.executeUpdate();
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
	
	public List getGroupsMoveList() throws Exception { // 이동시킬 학급 목록 가져오기
		List groupsList = null;
		try {
			con = ds.getConnection();
			String sql = "SELECT gp_name FROM groups WHERE gp_status=1 ORDER BY gp_name ASC"; // gp_status=1(개강중)인 학급 목록만 대상에 추가
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			groupsList = new ArrayList();
			while(rs.next()) {
				groupsList.add(rs.getString(1));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return groupsList;
	}
	
	public boolean groupsMoveStudent(String gp_name, String[] studentList) throws Exception { // 학급 학생 이동
		boolean result = false;
		try {
			con = ds.getConnection();
			
			for (int i = 0; i < studentList.length; i++) { // 배열 크기만큼 반복
				String sql = "UPDATE student SET gp_name=? WHERE mm_id=?"; // 학생 정보의 gp_name만 변경
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, gp_name);
				pstmt.setString(2, studentList[i]);
				pstmt.executeUpdate();
				
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return result;
	}
	 
	
	
	private List insertList() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("gp_idx"));// 0
		list.add(rs.getString("gp_name"));// 1
		list.add(rs.getString("ep_id"));// 2
		list.add(rs.getString("gp_lev"));// 3
		list.add(rs.getString("gp_half"));// 4
		list.add(rs.getInt("gp_ea"));// 5
		list.add(rs.getString("gp_status"));// 6
		list.add(rs.getDate("gp_startdate"));// 7
		list.add(rs.getDate("gp_enddate"));// 8
		list.add(rs.getString("gp_room"));// 9
		return list;
	}

	private void dbClose() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException ex) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException ex) {
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException ex) {
			}
		System.out.println("Groups DB Closed");
	}

	public List getRoomList() {
		List list = null;
		try {
			con = ds.getConnection();
			String sql = "select * from room_list where room_status=1";
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				list = new ArrayList();
				do {
					list.add(insertRoomList());
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	private List insertRoomList() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("room_list_idx"));// 0
		list.add(rs.getInt("room_list_name"));// 1
		list.add(rs.getInt("room_status"));// 2
		list.add(rs.getInt("room_ea"));// 3
		return list;
	}

}
