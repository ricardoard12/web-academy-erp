package academy.qna_board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.swing.internal.plaf.basic.resources.basic;

public class QnaDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	 public QnaDAO(){  //DB사용을위해 정의
		try {
			Context init = new InitialContext();
	           ds=(DataSource)init.lookup("java:comp/env/jdbc/p4_learntime_kr");
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
			sql="SELECT COUNT(*) FROM board_qna";  // 공지글 구해오기
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
	 
	 
	public List<QnaBean> QnaList(int page, int limit){
		List<QnaBean> QnaList = null; //리스트 저장 객체 생성
		String sql ="";
		int startrow=(page-1)*limit+1; //스타트페이지구하기
		
		try {
			con =ds.getConnection();
			sql="SELECT qna_num,qna_title,qna_subject,qna_recont,qna_data FROM board_qna ORDER BY qna_num DESC LIMIT ?,?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1);
			pstmt.setInt(2, limit);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){  //값이 있으면 Bean파일에 먼저 저아후 List에 저장한 값을 넘긴다.
				QnaList = new ArrayList<QnaBean>(); 
				do{
				QnaBean qnabean = new QnaBean();
				qnabean.setQna_num(rs.getInt("qna_num"));
				qnabean.setQna_title(rs.getString("qna_title"));
				qnabean.setQna_subject(rs.getString("qna_subject"));
				qnabean.setQna_recont(rs.getInt("qna_recont"));
				qnabean.setQna_data(rs.getDate("qna_data"));
				
				QnaList.add(qnabean);  //list에 저장
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
		
		return QnaList;
	}
	public boolean insertqna(QnaBean qnabean){
		boolean check = false;
		String sql="";
		int qna_num=0;
		
		try {
			con= ds.getConnection();
			sql="SELECT MAX(qna_num) FROM board_qna"; //글번호 구하기
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				qna_num = rs.getInt(1)+1; // 최고수에서 +1
			}else{
				qna_num=1; //없을경우 1을 삽입
			}
			sql="INSERT INTO board_qna(qna_num,qna_title,qna_subject,qna_content,qna_recont,qna_data) VALUES(?,?,?,?,?,now())"; //데이터를 넣어준다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, qna_num);
			pstmt.setString(2, qnabean.getQna_title());
			pstmt.setString(3, qnabean.getQna_subject()); // 관리자가 작성하므로 작성자는 관리자;
			pstmt.setString(4, qnabean.getQna_content());
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
			sql="update board_qna set qna_recont=qna_recont+1 where qna_num=?";
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
	public QnaBean getdetail(int num){
		QnaBean qna = null;
		String sql="";
		try {
			con =ds.getConnection();
			sql="select qna_num,qna_title,qna_subject,qna_content,qna_data,qna_recont from board_qna where qna_num=?"; //조회
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				qna = new QnaBean();
				
				qna.setQna_content(rs.getString("qna_content"));
				qna.setQna_data(rs.getDate("qna_data"));
				qna.setQna_num(rs.getInt("qna_num"));
				qna.setQna_recont(rs.getInt("qna_recont"));
				qna.setQna_subject(rs.getString("qna_subject"));
				qna.setQna_title(rs.getString("qna_title"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {  // 사용후 DB접속 종료.
			if(rs!=null)try{rs.close();}catch(Exception e){};
			if(pstmt!=null)try{pstmt.close();}catch(Exception e){};
			if(con!=null)try{con.close();}catch(Exception e){};
		}
		return qna;
		
	}
	public void setNotice(QnaBean notice){
		String sql="";
		try {
			con=ds.getConnection();
			sql="update board_qna set qna_title=?,qna_content=? where qna_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getQna_title());
			pstmt.setString(2, notice.getQna_content());
			pstmt.setInt(3, notice.getQna_num());
			
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
			sql="delete from board_qna where qna_num=?";
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
