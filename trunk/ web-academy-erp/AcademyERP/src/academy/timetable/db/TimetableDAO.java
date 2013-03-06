package academy.timetable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TimetableDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds;

	public TimetableDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("timetable DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// dbClose
	private void dbClose() {
		if (con != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		if (pstmt != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		if (rs != null)
			try {
				con.close();
			} catch (SQLException e) {
			}
		System.out.println("timetable DB Closed");
	}

	// 해당 학급의 시간표 가져오기
	public List getTimetable(String gp_idx) {
		List list = null;
		try {
			con = ds.getConnection();
			String sql = "select * from timetable,member where gp_idx='" + gp_idx
					+ "' AND ep_id=mm_id";
			rs = con.prepareStatement(sql).executeQuery();
			list = new ArrayList();
			if (rs.next()) {
				do {
					list.add(insertTimetableinfo());
				} while (rs.next());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	// 그룹 - 반 리스트 가져오기
	public List getGpList() {
		List list = null;
		try {
			con = ds.getConnection();
			String sql = "select * from groups";
			rs = con.prepareStatement(sql).executeQuery();
			list = new ArrayList();
			if (rs.next()) {
				do {
					list.add(getGpInfo());
				} while (rs.next());
			}
			System.out.println(list.size());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	private List getGpInfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("gp_idx"));// 0
		list.add(rs.getString("gp_name"));// 1
		list.add(rs.getString("ep_id"));// 2
		list.add(rs.getString("gp_lev"));// 3
		list.add(rs.getString("gp_half"));// 4
		list.add(rs.getInt("gp_ea"));// 5
		list.add(rs.getString("gp_status"));// 6
		list.add(rs.getInt("gp_room"));// 7
		return list;
	}

	private List insertTimetableinfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("ti_idx"));// 0
		list.add(rs.getString("mm_name"));// 1
		list.add(rs.getString("ti_day"));// 2
		list.add(rs.getString("ti_lesson"));// 3
		list.add(rs.getString("ti_subject"));// 4
		return list;
	}

	private List getSbInfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("sub_idx"));// 0
		list.add(rs.getString("sub_name"));// 1
		list.add(rs.getString("sub_code"));// 2
		return list;
	}

	// 과목 목록 가져가기
	public List getSubject() {
		List list = null;
		try {
			con = ds.getConnection();
			String sql = "select * from subject";
			rs = con.prepareStatement(sql).executeQuery();
			if (rs.next()) {
				list = new ArrayList();
				do {
					list.add(getSbInfo());
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	public List getEpList(String subject) {
		List list = null;
		try {
			con = ds.getConnection();
			String sql = "select * from employee,member where mm_id=ep_id AND ep_subject_name='"
					+ subject + "'";
			rs = con.prepareStatement(sql).executeQuery();
			list = new ArrayList();
			if (rs.next()) {
				do {
					list.add(getEpInfo());
				} while (rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;
	}

	private List getEpInfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getString("ep_id"));
		list.add(rs.getString("mm_name"));
		list.add(rs.getString("ep_subject_name"));
		return list;
	}

	public boolean checkTimetable(List<String> list) {
		boolean x = true;
		// list.add(request.getParameter("ep_id"));
		// list.add(request.getParameter("gp_idx"));
		// list.add(request.getParameter("day"));
		// list.add(request.getParameter("lesson"));
		// list.add(request.getParameter("room_idx"));
		// list.add(request.getParameter("sub_name"));
		try {
			con = ds.getConnection();
			String sql = "select * from timetable where ep_id=? AND ti_day=? AND ti_lesson=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, list.get(0).toString());
			pstmt.setString(2, list.get(2).toString());
			pstmt.setString(3, list.get(3).toString());
			rs = pstmt.executeQuery();
			if (rs.next()) {
				x = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return x;
	}

	public void updateTimetable(List<String> list) {
		// list.add(request.getParameter("ep_id"));
		// list.add(request.getParameter("gp_idx"));
		// list.add(request.getParameter("day"));
		// list.add(request.getParameter("lesson"));
		// list.add(request.getParameter("room_idx"));
		// list.add(request.getParameter("sub_name"));
		String ep_id = list.get(0);
		String gp_idx = list.get(1);
		String ti_day = list.get(2);
		String ti_lesson = list.get(3);
		String ti_subject = list.get(4);
		try {
			con = ds.getConnection();
			String sql = "update timetable set ep_id=?,ti_subject=? where gp_idx=? AND ti_day=? AND ti_lesson=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, ep_id);
			pstmt.setString(2, ti_subject);
			pstmt.setString(3, gp_idx);
			pstmt.setString(4, ti_day);
			pstmt.setString(5, ti_lesson);
			pstmt.executeUpdate();
			System.out.println(ep_id + "," + ti_subject + "," + gp_idx + ","
					+ ti_day + "," + ti_lesson);
			System.out.println("update schedule");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
	}

}
