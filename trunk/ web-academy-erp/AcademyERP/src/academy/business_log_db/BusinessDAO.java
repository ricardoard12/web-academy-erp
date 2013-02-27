package academy.business_log_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BusinessDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	private void dbClose(){
		if(rs!=null)try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		if(con!=null)try{con.close();}catch(SQLException ex){}
		System.out.println("BusinessDB Closed");
	}
	
	public BusinessDAO() { //생성자
		//디비연결 이름호출
		try {
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
			System.out.println("Business_Log_DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자	

	public void businessinsert(BusinessBean businessbean) {
		int num = 0;
		String sql = "";
		
		try {
			System.out.println("BusinessInsert start");
			con = ds.getConnection();
			sql = "select max(business_num) from business_log;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
				
			}else {
				num = 1;
			}
			
			sql = "insert into business_log(business_num,business_today,business_counsel,business_etc ,business_date) values(?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, businessbean.getBusiness_today());
			pstmt.setString(3, businessbean.getBusiness_counsel());
			pstmt.setString(4, businessbean.getBusiness_etc());
			pstmt.executeUpdate();
			
			System.out.println("BusinessInsert End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}
	
	

}
