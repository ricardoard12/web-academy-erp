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

import academy.student.db.StudentBean;

public class CounselorDAO {
	  Connection con=null;
	    PreparedStatement pstmt=null;
	    ResultSet rs=null;
	    DataSource ds;
	    public CounselorDAO() {
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
				sql="select idx,mm_id,cc_subject,cc_content,cc_date,gp_name,ep_id from counsel where mm_id=? order by idx DESC limit ?,?";
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
						counselbean.setGp_name(rs.getString("gp_name"));
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
	    public StudentBean getstudentinfor(String mm_id){
			String sql="";
			StudentBean studentbean = null;
			
			try {
				con=ds.getConnection();
				sql="select m.mm_name,s.mm_id,s.st_parent_name,s.gp_name, m.mm_manager_id from member as m INNER JOIN student as s where s.mm_id=? and m.mm_id=s.mm_id";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, mm_id);
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					studentbean = new StudentBean();
					studentbean.setMm_name(rs.getString("mm_name"));
					studentbean.setMm_id(rs.getString("mm_id"));
					studentbean.setSt_parent_name(rs.getString("st_parent_name"));
					studentbean.setGp_name(rs.getString("gp_name"));
					studentbean.setMm_manager_id(rs.getString("mm_manager_id"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    	
	    	return studentbean;
	    	
	    }public void setcounselor(CounselerBean counselor){
	    	String sql="";
	    	int idx=0;
	    	try {
				con =ds.getConnection();
				sql="select max(idx) from counsel where mm_id=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, counselor.getMm_id());
				rs=pstmt.executeQuery();
				// 해당 아뒤의 상다내역의 최고 변을 구하고 그기에 1더하기
				if(rs.next()){
					idx = rs.getInt(1)+1;
				}else{
					idx = 1;
				}
				sql="insert into counsel(mm_id,cc_subject,cc_content,gp_name,ep_id,idx,cc_date) values(?,?,?,?,?,?,now())";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, counselor.getMm_id());
				pstmt.setString(2, counselor.getCc_subject());
				pstmt.setString(3, counselor.getCc_content());
				pstmt.setString(4, counselor.getGp_name());
				pstmt.setString(5, counselor.getEp_id());
				pstmt.setInt(6, idx);
				pstmt.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    }public CounselerBean getcounseler(int idx, String id){
			String sql="";
			CounselerBean counseler =null;
	    	try {
				con= ds.getConnection();
				sql="select idx,mm_id,cc_subject,cc_content, ep_id,cc_date from counsel where mm_id=? and idx=?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, id);
				pstmt.setInt(2, idx);
				rs=pstmt.executeQuery();
				if(rs.next()){
					counseler = new CounselerBean();
					counseler.setIdx(rs.getInt("idx"));
					counseler.setMm_id(rs.getString("mm_id"));
					counseler.setCc_content(rs.getString("cc_content"));
					counseler.setCc_subject(rs.getString("cc_subject"));
					counseler.setEp_id(rs.getString("ep_id"));
					counseler.setCc_date(rs.getDate("cc_date"));
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    	
	    	return counseler;
	    	
	    }public void setcounselorupdate(CounselerBean counselor){
	    	System.out.println("DB접속");
	    	System.out.println(counselor.getEp_id());
	    	System.out.println(counselor.getIdx());
	    	System.out.println(counselor.getMm_id());
	    	
	    	String sql="";
	    	try {
				con=ds.getConnection();
				sql="update counsel set cc_subject =?,cc_content =?,ep_id =? where idx=? and mm_id =?";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, counselor.getCc_subject());
				pstmt.setString(2, counselor.getCc_content());
				pstmt.setString(3, counselor.getEp_id());
				pstmt.setInt(4, counselor.getIdx());
				pstmt.setString(5, counselor.getMm_id());
				
				pstmt.executeUpdate();
				System.out.println("접속 성공");
			
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				if(rs!=null)try{rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
				if(con!=null)try{con.close();}catch(SQLException ex){}
			}
	    	
	    }
	    
}
