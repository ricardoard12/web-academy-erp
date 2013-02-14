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
		if (rs != null || con != null || pstmt != null)
			try {
				rs.close();
				con.close();
				pstmt.close();
				System.out.println("Master DB Closed");
			} catch (SQLException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}

	}

	// 직원들의 목록과 권한 설정하기
	public List<List> getEmplist(String name) {
		List<List> empList = null;
		String str = "";
		if (name != null) {
			str = " AND LIKE '%" + name + "%'";
		}
		try {
			con = ds.getConnection();
			String sql = "select mm_id,mm_name,ep_position,ep_department,mm_manager_id,mm_level"
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
		return list;
	}

}
