package academy.noticle.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class NoticeDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	 public NoticeDAO(){  //DB사용을위해 정의
		try {
//        	Class.forName("com.mysql.jdbc.Driver");
//        	String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
//        	con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
		} catch (Exception e) {
			// TODO: handle exception]
			e.printStackTrace();
		}
	}
	public int getnoticecount(){
		int count = 0;
		
		String sql="";
		
		try {
			con=ds.getConnection();
			sql="SELECT COUNT(*) FROM notice";  // 공지글 구해오기
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				count = rs.getInt(1);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
		
		return count;
		
	}
	 
	 
	public List<NoticeBean> NoticeList(int page, int limit){
		List<NoticeBean> noticeList = null; //리스트 저장 객체 생성
		String sql ="";
		int startrow=(page-1)*limit+1; //스타트페이지구하기
		
		try {
			con =ds.getConnection();
			sql="SELECT not_num,not_title,not_subject,not_recont,not_data FROM notice ORDER BY not_num DESC LIMIT ?,?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1);
			pstmt.setInt(2, limit);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){  //값이 있으면 Bean파일에 먼저 저아후 List에 저장한 값을 넘긴다.
				noticeList = new ArrayList<NoticeBean>(); 
				do{
				NoticeBean noticebean = new NoticeBean();
				noticebean.setNot_num(rs.getInt("not_num"));
				noticebean.setNot_title(rs.getString("not_title"));
				noticebean.setNot_subject(rs.getString("not_subject"));
				noticebean.setNot_recont(rs.getInt("not_recont"));
				noticebean.setNot_data(rs.getDate("not_data"));
				
				noticeList.add(noticebean);  //list에 저장
				}while(rs.next());
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
		
		return noticeList;
	}
	public boolean insertNotice(NoticeBean noticebean){
		boolean check = false;
		String sql="";
		int not_num=0;
		
		try {
			con= ds.getConnection();
			sql="SELECT MAX(not_num) FROM notice"; //글번호 구하기
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				not_num = rs.getInt(1)+1; // 최고수에서 +1
			}else{
				not_num=1; //없을경우 1을 삽입
			}
			sql="INSERT INTO notice(not_num,not_title,not_subject,not_content,not_recont,not_data) VALUES(?,?,?,?,?,now())"; //데이터를 넣어준다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, not_num);
			pstmt.setString(2, noticebean.getNot_title());
			pstmt.setString(3, "관리자"); // 관리자가 작성하므로 작성자는 관리자;
			pstmt.setString(4, noticebean.getNot_content());
			pstmt.setInt(5, 0); //조회수 는 0으로 
			pstmt.executeUpdate();
			
			check = true; // 올바르게 들어갈경우 true
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
		return check;
	}
	public void setcount(int num){
		String sql="";
		
		try {
			con=ds.getConnection();
			sql="update notice set not_recont=not_recont+1 where not_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
	}
	public NoticeBean getdetail(int num){
		NoticeBean noitce = null;
		String sql="";
		try {
			con =ds.getConnection();
			sql="select not_num,not_title,not_subject,not_content,not_data,not_recont from notice where not_num=?"; //조회
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				noitce = new NoticeBean();
				
				noitce.setNot_content(rs.getString("not_content"));
				noitce.setNot_data(rs.getDate("not_data"));
				noitce.setNot_num(rs.getInt("not_num"));
				noitce.setNot_recont(rs.getInt("not_recont"));
				noitce.setNot_subject(rs.getString("not_subject"));
				noitce.setNot_title(rs.getString("not_title"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
		return noitce;
		
	}
	public void setNotice(NoticeBean notice){
		String sql="";
		try {
			con=ds.getConnection();
			sql="update notice set not_title=?,not_content=? where not_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getNot_title());
			pstmt.setString(2, notice.getNot_content());
			pstmt.setInt(3, notice.getNot_num());
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
	}
	public void DeleteNotice(int num){
		String sql="";
		try {
			con=ds.getConnection();
			sql="delete from notice where not_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
	}
}
