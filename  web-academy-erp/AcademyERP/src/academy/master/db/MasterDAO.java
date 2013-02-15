package academy.master.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MasterDAO {

	Connection con = null;
	DataSource ds = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	public MasterDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
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

}
