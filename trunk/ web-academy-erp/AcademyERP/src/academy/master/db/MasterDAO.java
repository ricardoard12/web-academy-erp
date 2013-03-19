package academy.master.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.groups.db.GroupsBean;
import academy.member.db.MemberBean;

public class MasterDAO {

	Connection con = null;
	DataSource ds = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MasterDAO() {
		try {
//        	Class.forName("com.mysql.jdbc.Driver");
//        	String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
//        	con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("Master DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Master DB Connecting failed");
		}
	}

	private void dbClose() {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		System.out.println("Master DB Closed");

	}

	// 직원들의 목록과 권한 가져오기
	public List<List> getEmplist(String name) {
		List<List> empList = null;
		String str = "";
		System.out.println("검색어 : " + name);
		if (name != null) {
			str = " AND mm_name LIKE '%" + name + "%'";
		} else {
			str = "";
		}
		try {
			con = ds.getConnection();
			String sql = "select mm_id,mm_name,ep_position,ep_department,mm_manager_id,mm_level,ep_status"
					+ " from member,employee where mm_id=ep_id" + str;
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			empList = new ArrayList();
			while (rs.next()) {
				empList.add(insertLevellist());
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return empList;
	}

	private List<String> insertLevellist() throws Exception {
		List<String> list = new ArrayList();
		list.add(rs.getString(1));
		list.add(rs.getString(2));
		list.add(rs.getString(3));
		list.add(rs.getString(4));
		list.add(rs.getString(5));
		list.add(rs.getString(6));
		list.add(rs.getString(7));
		return list;
	}

	// level update 부분
	public void updateLevel(String id, String level) {
		try {
			con = ds.getConnection();
			String sql = "update member set mm_level=" + level
					+ " where mm_id=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			System.out.println("update 성공");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("update 실패");
		} finally {
			dbClose();
		}
	}

	public List getAllMemberList(int toggle, String col) {
		MemberBean member = null;
		String str = "";

		try {

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return null;
	}

	// 전체 학급의 갯수를 구한다.
	public int getCount() {
		int x = 0;
		try {
			con = ds.getConnection();
			String sql = "select count(gp_idx) from groups";
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				x = rs.getInt(1);
			}
			System.out.println("학급갯수는 : " + x);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return x;
	}

	// 리스트별로 클래스의 수를 가져온다.
	public List getClassList(int page, int limit) {
		List list = null;
		int startrow = (page - 1) * limit + 1;
		try {
			System.out.println("->");
			con = ds.getConnection();
			String sql = "select * from groups "
					+ "order by groups.gp_idx desc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow - 1);
			pstmt.setInt(2, limit);
			rs = pstmt.executeQuery();
			System.out.println("<-");
			if (rs.next()) {
				list = new ArrayList(limit);
				do {
					list.add(insertClass());
				} while (rs.next());
			}
			System.out.println("리스트 가져감");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	private List insertClass() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("gp_idx"));
		list.add(rs.getString("gp_name"));
		list.add(rs.getString("ep_id"));
		// list.add(rs.getString("mm_name"));
		list.add(rs.getString("gp_lev"));
		list.add(rs.getString("gp_half"));
		list.add(rs.getInt("gp_ea"));
		list.add(rs.getString("gp_status"));
		list.add(rs.getDate("gp_startdate"));
		list.add(rs.getDate("gp_enddate"));
		list.add(rs.getInt("gp_room"));
		System.out.println(list.get(0));
		return list;
	}

	// 상태 변경 ^^
	public void updateStatus(String id, String status) {

		try {
			con = ds.getConnection();
			String sql = "update groups set gp_status='" + status
					+ "' where gp_idx=" + Integer.parseInt(id);
			con.prepareStatement(sql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	// 지울꺼다...
	public void deleteclass(String string) {
		try {
			con = ds.getConnection();
			String sql = "delete from groups where gp_idx='" + string + "'";
			con.prepareStatement(sql).executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

	public List getTeachserList() {
		List tList = null;
		String str = "재직";
		try {
			con = ds.getConnection();
			String sql = "select employee.ep_id,member.mm_name,employee.ep_subject_name "
					+ "from employee,member "
					+ "where mm_id=ep_id AND ep_status='" + str + "'";
			System.out.println("선생 리스트 ->");
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				tList = new ArrayList();
				do {
					tList.add(getTeacherInfo());

				} while (rs.next());
			}
			System.out.println("<-선생 리스트");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return tList;
	}

	private List getTeacherInfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getString("ep_id"));
		list.add(rs.getString("mm_name"));
		list.add(rs.getString("ep_subject_name"));
		return list;
	}

	// 반생
	public void setClass(GroupsBean groups) {
		try {
			con = ds.getConnection();
			String sql = "insert into groups (gp_name,ep_id,gp_lev,gp_half,gp_status,gp_startdate,gp_enddate) values(?,?,?,?,?,?,?)";
			System.out.println("학급삽입 --->");
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, groups.getGp_name());
			pstmt.setString(2, groups.getEp_id());
			pstmt.setString(3, groups.getGp_lev());
			pstmt.setString(4, groups.getGp_half());
			pstmt.setString(5, groups.getGp_status());
			pstmt.setString(6, groups.getGp_startdate());
			pstmt.setString(7, groups.getGp_enddate());
			pstmt.executeUpdate();
			sql = "select max(gp_idx) from groups";
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				String gp_idx = rs.getInt(1) + "";
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 7; j++) {
						sql = "insert into timetable (gp_idx,ti_day,ti_lesson,ti_room) values('"
								+ gp_idx
								+ "','"
								+ (i + "")
								+ "','"
								+ (j + "")
								+ "','')";
						con.prepareStatement(sql).executeUpdate();
					}
				}

			}

			System.out.println("학급 삽입 완료 --->");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("!!!!!!!!!!!!학급 삽입 실패!!!!!!!!!!!!");
		} finally {
			dbClose();
		}
	}

	public void updateRoom(String id, String room, int ea) {
		try {
			con = ds.getConnection();
			String sql = "update groups SET groups.gp_room='" + room
					+ "',groups.gp_ea=" + ea + " where gp_idx="
					+ Integer.parseInt(id);
			con.prepareStatement(sql).executeUpdate();
			sql = "select * from room_list where room_list_idx="
					+ Integer.parseInt(room);
			rs = con.prepareStatement(sql).executeQuery();
			String roomname = "";
			if (rs.next()) {
				roomname = rs.getString("room_list_name");
				System.out.println(roomname);
				sql = "update timetable set ti_room='" + roomname
						+ "' where gp_idx='" + id + "'";
				con.prepareStatement(sql).executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

}
