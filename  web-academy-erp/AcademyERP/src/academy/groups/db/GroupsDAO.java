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

	public List getGpList() { // 전체 과목 가지고오기
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
	public int getCount(String ep_id) {
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
	public List getGroupsList(int page, int limit, String ep_id) {
		List list = null;
		int startrow=(page-1)*limit+1;
		try {
			con = ds.getConnection();
			String sql = "SELECT * FROM groups where ep_id='" + ep_id + "' order by gp_idx desc limit "+(startrow-1)+","+limit;
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

}
