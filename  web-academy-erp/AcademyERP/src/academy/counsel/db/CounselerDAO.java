package academy.counsel.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class CounselerDAO {
	  Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    DataSource ds;
	    public CounselerDAO() {
	        try {
	            Context init=new InitialContext();
	            ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }
	    public int getcounselcount(String id){
			int counselcount =0;
			String sql="";
			
			try {
				con= ds.getConnection();
				sql="select count(*) from counsel where mm_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					counselcount = rs.getInt(1);
				}
			
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    	
	    	
	    	return counselcount;
	    	
	    }
	    

	    public List getcounselList(String id, int  page,  int limit){
			List counselList =null;
			int startrow=(page-1)*limit+1; //현재페이지 시작행
	    	String sql="";
	    	
	    	try {
				con= ds.getConnection();
				sql="select idx,mm_id,cc_subject,cc_content,cc_date,gp_id,ep_id from counsel where mm_id=? order by idx DESC limit ?,?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, startrow-1);// 시작계수
				pstmt.setInt(3, limit);
				rs= pstmt.executeQuery();
				
				if(rs.next()){
					counselList = new ArrayList();
					do{
						CounselerBean counselbean = new CounselerBean(); // counselbean에 조회한 데이터 저장
						counselbean.setCc_content(rs.getString("cc_content"));
						counselbean.setCc_date(rs.getDate("cc_date"));
						counselbean.setCc_subject(rs.getString("cc_subject"));
						counselbean.setEp_id(rs.getString("ep_id"));
						counselbean.setIdx(rs.getInt("idx"));
						counselbean.setMm_id(rs.getString("mm_id"));
						
						counselList.add(counselbean);
						
					}while(rs.next());
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    	
	    	return counselList;
	    	
	    }
}
