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
			String sql = "select * from timetable where gp_idx='" + gp_idx
					+ "'";
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
		list.add(rs.getString("ep_id"));// 1
		list.add(rs.getString("gp_idx"));// 2
		list.add(rs.getString("ti_idx"));// 3
		list.add(rs.getString("ti_lesson"));// 4
		list.add(rs.getInt("room_idx"));// 5
		return list;
	}

	private List getSbInfo() throws Exception {
		List list = new ArrayList();
		list.add(rs.getInt("sub_idx"));
		list.add(rs.getString("sub_name"));
		list.add(rs.getString("sub_code"));
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

}
