package academy.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class Re_BoardDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

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
		System.out.println("RE_BoardDB Closed");
	}

	public Re_BoardDAO() { // 생성자
		// 디비연결 이름호출
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("RE_BoardDB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}// 생성자
	
	
	// 덧글 DB입력
	public void re_boardinsert(Re_BoardBean re_boardbean) throws Exception{
		BoardBean boardbean = new BoardBean();
		int num = 0;
		String sql  = "";
		try {
			System.out.println("re_boardinsert start");
			con = ds.getConnection();
			sql = "select max(re_board_num) FROM re_board where re_board_num = ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, re_boardbean.getRe_board_num());
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
				
			}else{
				num = 1;
			}
			sql = "insert into re_board(re_board_num, re_board_ref, re_board_name , re_board_content) value(?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_boardbean.getRe_board_num());
			pstmt.setInt(2, num);
			//pstmt.setInt(3, re_boardbean.getRe_board_lev());
			//pstmt.setInt(4, re_boardbean.getRe_board_seq());
			pstmt.setString(3, re_boardbean.getRe_board_name());
			pstmt.setString(4, re_boardbean.getRe_board_content());
			pstmt.executeUpdate();
			
			System.out.println("re_boardinsert End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{dbClose();}
	}

	
	// 덧글 리스트 출력
	public List getReBoardList(int page, int limit) throws Exception {
		String sql = "";
		List list = null;
		int startrow = (page - 1) * limit + 1;
		try {
			System.out.println("getReBoardList start");
			con = ds.getConnection();

			sql = "select * from re_board order by re_board_ref desc, re_board_seq asc limit ?,?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, startrow - 1); // 시작위치-1
			pstmt.setInt(2, limit); // 개수
			pstmt.executeQuery();

			if (rs.next()) {
				list = new ArrayList(limit);
				do {
					Re_BoardBean re_boardbean = new Re_BoardBean();
					re_boardbean.setRe_board_num(rs.getInt("re_board_num"));
					re_boardbean.setRe_board_ref(rs.getInt("re_board_ref"));
					re_boardbean.setRe_board_seq(rs.getInt("re_board_seq"));
					re_boardbean.setRe_board_lev(rs.getInt("re_board_lev"));
					re_boardbean.setRe_board_name(rs
							.getString("re_board_name"));
					re_boardbean.setRe_board_content(rs
							.getString("re_board_content"));
					list.add(re_boardbean);

				} while (rs.next());
			}
			System.out.println("getReBoardList End");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		return list;

	}
	
	public int getReListCount() throws Exception{
		String sql = "";
		int x = 0;
		try {
			System.out.println("getReListCount start");
			con = ds.getConnection();
			
			sql = "select count(*) from re_board";
			pstmt = con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				x = rs.getInt(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			dbClose();
		}
		return x;
		
	}

}
