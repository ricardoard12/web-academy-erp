package academy.faq_board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.business_log.db.BusinessBean;

public class Faq_boardDAO {
	
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private void dbClose(){
		if(rs!=null)try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		if(con!=null)try{con.close();}catch(SQLException ex){}
		System.out.println("FAQ_Board DB Closed");
	}
	
	public Faq_boardDAO(){
		try {
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("FAQ_Board DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자 종료
	
	public void faqboardinsert(Faq_boardbean faq_boardbean) {
		int num = 0;
		String sql = "";
		
		try {
			System.out.println("faq_board Insert start");
			con = ds.getConnection();
			sql = "select max(faq_num) from board_FAQ;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
				
			}else {
				num = 1;
			}
			
			sql = "insert into board_FAQ(faq_num, faq_name, faq_subject, faq_content, faq_date) values(?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, faq_boardbean.getFaq_name());
			pstmt.setString(3, faq_boardbean.getFaq_subject());
			pstmt.setString(4, faq_boardbean.getFaq_content());
			pstmt.executeUpdate();
			
			System.out.println("faq_board Insert End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}

}
