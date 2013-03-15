package academy.faq_board.db;

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
		
			sql = "insert into board_FAQ(faq_num, faq_name, faq_subject, faq_content, faq_passwd, faq_date) values(?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, faq_boardbean.getFaq_name());
			pstmt.setString(3, faq_boardbean.getFaq_subject());
			pstmt.setString(4, faq_boardbean.getFaq_content());
			pstmt.setString(5, faq_boardbean.getFaq_passwd());
			pstmt.executeUpdate();
			
			System.out.println("faq_board Insert End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}
	
	public List getFaq_boardList(int page,int limit){
		String sql="";
		List list=null;
		int startrow=(page-1)*limit+1; //현재페이지 시작행
		try {
			System.out.println("getFaq_boardList start");
			con=ds.getConnection();
			//3 sql
			sql="select * from board_FAQ order by faq_num desc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1); //시작위치-1
			pstmt.setInt(2, limit); //개수
			//4 저장 = 실행
			rs=pstmt.executeQuery();
			//5 rs => 자바빈 저장
			if(rs.next()){
				list=new ArrayList(limit);//ArrayList객체생성
				do{
					Faq_boardbean faq_boardbean = new Faq_boardbean();
					faq_boardbean.setFaq_content(rs.getString("faq_content"));
					faq_boardbean.setFaq_name(rs.getString("faq_name"));
					faq_boardbean.setFaq_num(rs.getInt("faq_num"));
					faq_boardbean.setFaq_subject(rs.getString("faq_subject"));
					faq_boardbean.setFaq_passwd(rs.getString("faq_passwd"));
					faq_boardbean.setFaq_date(rs.getDate("faq_date"));
					list.add(faq_boardbean); //자바빈 -> 한칸
				}while(rs.next());
			}
			System.out.println("getFaq_boardList end");
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
			System.out.println("getListCount start");
			con = ds.getConnection();
			
			sql = "select count(*) from board_FAQ";
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

	public boolean faq_boardDelete(String[] num) throws Exception{
		String sql="";
		Statement stmt =null;
		boolean result = false;

        try {
        	System.out.println("Faq_boardDelete start");
            con = ds.getConnection();
            for(int i=0; i<num.length; i++){
            	sql="delete from board_FAQ where faq_num="+num[i];
            	stmt=con.createStatement();
            	stmt.executeUpdate(sql);	
            	result = true;
            	System.out.println("Faq_boardDelete end");
            }
            
        } catch (Exception e) {e.printStackTrace();} 
        finally {dbClose();}
		return false;
    }
	
	public Faq_boardbean getDetail(int num) throws Exception{
		String sql="";
		Faq_boardbean faq_boardbean = new Faq_boardbean();
		try {
			System.out.println("getDetail start");
			con=ds.getConnection();
			
			sql="select * from board_FAQ where faq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				faq_boardbean.setFaq_content(rs.getString("faq_content"));
				faq_boardbean.setFaq_name(rs.getString("faq_name"));
				faq_boardbean.setFaq_num(rs.getInt("faq_num"));
				faq_boardbean.setFaq_subject(rs.getString("faq_subject"));
				faq_boardbean.setFaq_passwd(rs.getString("faq_passwd"));
				faq_boardbean.setFaq_date(rs.getDate("faq_date"));
			}
			System.out.println("getDetail End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		return faq_boardbean;
	}
	
	public boolean userchk(int num , String password) throws Exception {
		
		String sql = "";
		boolean x = false;
		String DBPass = null;
		try {
			System.out.println("UsetCheck start");
			con = ds.getConnection();
			sql = "SELECT faq_passwd FROM board_FAQ WHERE faq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			System.out.println("Action에서 받은 num값 :"+num);
			System.out.println("Action에서 받은 password값:"+password);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				DBPass = rs.getString("faq_passwd");
				
				System.out.println("DB에서 가져온 password값:"+DBPass);
				
				if(password.equals(DBPass)){
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

	public void faq_boardModify(Faq_boardbean faq_boardbean) throws Exception {
		String sql="";
		try {
			System.out.println("FAQ_BOARD_Modify start");
			con=ds.getConnection();
			sql = "UPDATE board_FAQ SET faq_name=? , faq_subject=? , faq_content=?, faq_passwd=? WHERE faq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, faq_boardbean.getFaq_name());
			pstmt.setString(2, faq_boardbean.getFaq_subject());
			pstmt.setString(3, faq_boardbean.getFaq_content());
			pstmt.setString(4, faq_boardbean.getFaq_passwd());
			pstmt.setInt(5, faq_boardbean.getFaq_num());
			pstmt.executeUpdate();
			System.out.println("FAQ_BOARD_Modify End");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
	}

}
