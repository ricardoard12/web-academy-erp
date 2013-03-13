package academy.business_log.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.board.db.BoardBean;



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
			ds=(DataSource)init.lookup("java:comp/env/jdbc/p4_learntime_kr");
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
			
			sql = "insert into business_log(business_num,business_name,business_subject,business_today,business_counsel,business_etc ,business_date) values(?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, businessbean.getBusiness_name());
			pstmt.setString(3, businessbean.getBusiness_subject());
			pstmt.setString(4, businessbean.getBusiness_today());
			pstmt.setString(5, businessbean.getBusiness_counsel());
			pstmt.setString(6, businessbean.getBusiness_etc());
			pstmt.executeUpdate();
			
			System.out.println("BusinessInsert End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}
	
	public List getBusinessList(int page,int limit){
		String sql="";
		List list=null;
		int startrow=(page-1)*limit+1; //현재페이지 시작행
		try {
			System.out.println("getBusinessList start");
			con=ds.getConnection();
			//3 sql
			sql="select * from business_log order by business_num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1); //시작위치-1
			pstmt.setInt(2, limit); //개수
			//4 저장 = 실행
			rs=pstmt.executeQuery();
			//5 rs => 자바빈 저장
			if(rs.next()){
				list=new ArrayList(limit);//ArrayList객체생성
				do{
					BusinessBean Business=new BusinessBean();//자바빈객체
					Business.setBusiness_num(rs.getInt("business_num"));
					Business.setBusiness_name(rs.getString("business_name"));
					Business.setBusiness_subject(rs.getString("business_subject"));
					Business.setBusiness_today(rs.getString("business_today"));
					Business.setBusiness_counsel(rs.getString("business_counsel"));
					Business.setBusiness_etc(rs.getString("business_etc"));
					Business.setBusiness_date(rs.getDate("business_date"));
					
					list.add(Business); //자바빈 -> 한칸
				}while(rs.next());
			}
			System.out.println("getBusinessList end");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
		dbClose();
		}
		return list;
	}

	public int getListCount() throws Exception{
		String sql = "";
		int x = 0;
		try {
			System.out.println("getReListCount start");
			con = ds.getConnection();
			
			sql = "select count(*) from business_log";
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

	public BusinessBean getDetail(int num) throws Exception{
		String sql="";
		BusinessBean businessbean = new BusinessBean();//자바빈객체
		try {
			System.out.println("getDetail start");
			con=ds.getConnection();
			
			sql="select * from business_log where business_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				businessbean.setBusiness_num(rs.getInt("business_num"));
				businessbean.setBusiness_name(rs.getString("business_name"));
				businessbean.setBusiness_subject(rs.getString("business_subject"));
				businessbean.setBusiness_today(rs.getString("business_today"));
				businessbean.setBusiness_counsel(rs.getString("business_counsel"));
				businessbean.setBusiness_etc(rs.getString("business_etc"));
				businessbean.setBusiness_date(rs.getDate("business_date"));
			}
			System.out.println("getDetail End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return businessbean;
	}

	public boolean busienssDelete(String[] num) throws Exception{
		String sql="";
		Statement stmt =null;
		boolean result = false;

        try {
        	System.out.println("businessDelete start");
            con = ds.getConnection();
            for(int i=0; i<num.length; i++){
            	sql="delete from business_log where business_num="+num[i];
            	stmt=con.createStatement();
            	stmt.executeUpdate(sql);	
            	result = true;
            	System.out.println("businessDelete end");
            }
            
        } catch (Exception e) {e.printStackTrace();} 
        finally {dbClose();}
		return false;
    }
	
	public boolean isBoardWriter(int num , String name){
		String sql="";
		boolean x = false;
		try {
			System.out.println("isBoardWriter start");
			con=ds.getConnection();
			
			sql="select business_name from board where business_num=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				String dbName=rs.getString("business_name");
				if(name.equals(dbName)){
					x=true;
				}
			}
			System.out.println("isBoardWriter End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return x;
	}

	public void businessModify(BusinessBean businessbean) throws Exception{
		String sql="";
		try {
			System.out.println("businessModify start");
			con=ds.getConnection();
			sql = "UPDATE business_log SET business_name=?,business_subject=? , business_today=? , business_counsel=?, business_etc=? WHERE business_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, businessbean.getBusiness_name());
			pstmt.setString(2, businessbean.getBusiness_subject());
			pstmt.setString(3, businessbean.getBusiness_today());
			pstmt.setString(4, businessbean.getBusiness_counsel());
			pstmt.setString(5, businessbean.getBusiness_etc());
			pstmt.setInt(6, businessbean.getBusiness_num());
			pstmt.executeUpdate();
			System.out.println("businessModify End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}

	public boolean userchk(int num , String name) throws Exception {
		String sql = "";
		boolean x = false;
		String DBName = null;
		try {
			System.out.println("UsetCheck start");
			con = ds.getConnection();
			sql = "SELECT business_name FROM business_log WHERE business_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			System.out.println("DBNum:"+num);
			System.out.println("DBName1:"+name);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				DBName = rs.getString("business_name");
				
				System.out.println("DBName2:"+DBName);
				
				if(name.equals(DBName)){
					x = true;
				}else{
					x = false;
				}
			}
			System.out.println("UserCheck End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		System.out.println("return x : " + x);
		return x;
	}

	

}
