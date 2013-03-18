package academy.seq_board.db;

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

public class SeqDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	 public SeqDAO(){  //DB사용을위해 정의
		try {
			Context init = new InitialContext();
	           ds=(DataSource)init.lookup("java:comp/env/jdbc/p4_learntime_kr");
		} catch (Exception e) {
			// TODO: handle exception]
			e.printStackTrace();
		}
	}
	public int getseqcount(){
		int count = 0;
		
		String sql="";
		
		try {
			con=ds.getConnection();
			sql="SELECT COUNT(*) FROM board_seq";  // 공지글 구해오기
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
	 
	 
	public List<SeqBean> seqList(int page, int limit){
		List<SeqBean> noticeList = null; //리스트 저장 객체 생성
		String sql ="";
		int startrow=(page-1)*limit+1; //스타트페이지구하기
		
		try {
			con =ds.getConnection();
			sql="SELECT s.seq_num,s.seq_title,s.seq_name,s.seq_count,s.seq_date FROM board_seq as s, board_qna as q where s.qna_num=q.qna_num ORDER BY s.seq_num DESC LIMIT ?,?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1);
			pstmt.setInt(2, limit);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){  //값이 있으면 Bean파일에 먼저 저아후 List에 저장한 값을 넘긴다.
				noticeList = new ArrayList<SeqBean>(); 
				do{
				SeqBean noticebean = new SeqBean();
				noticebean.setSeq_num(rs.getInt("seq_num"));
				noticebean.setSeq_title(rs.getString("seq_title"));
				noticebean.setSeq_name(rs.getString("seq_name"));
				noticebean.setSeq_count(rs.getInt("seq_count"));
				noticebean.setSeq_date(rs.getDate("seq_date"));
				
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
	public boolean insertSeq(SeqBean seqbean){
		boolean check = false;
		String sql="";
		int seq_num=0;
		
		try {
			con= ds.getConnection();
			sql="SELECT MAX(seq_num) FROM board_seq"; //글번호 구하기
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				seq_num = rs.getInt(1)+1; // 최고수에서 +1
			}else{
				seq_num=1; //없을경우 1을 삽입
			}
			sql="INSERT INTO board_seq(seq_num,seq_title,seq_name,seq_content,seq_count,seq_date,qna_num) VALUES(?,?,?,?,?,now(),?)"; //데이터를 넣어준다.
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, seq_num);
			pstmt.setString(2, seqbean.getSeq_title());
			pstmt.setString(3, "관리자"); // 관리자가 작성하므로 작성자는 관리자;
			pstmt.setString(4, seqbean.getSeq_content());
			pstmt.setInt(5, 0); //조회수 는 0으로 
			pstmt.setInt(6, seqbean.getQna_num());
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
			sql="update board_seq set seq_count=seq_count+1 where seq_num=?";
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
	public SeqBean getdetail(int num){
		SeqBean noitce = null;
		String sql="";
		try {
			con =ds.getConnection();
			sql="select seq_num,seq_title,seq_name,seq_content,seq_date,seq_count from board_seq where seq_num=?"; //조회
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				noitce = new SeqBean();
				
				noitce.setSeq_content(rs.getString("seq_content"));
				noitce.setSeq_date(rs.getDate("seq_date"));
				noitce.setSeq_num(rs.getInt("seq_num"));
				noitce.setSeq_count(rs.getInt("seq_count"));
				noitce.setSeq_name(rs.getString("seq_name"));
				noitce.setSeq_title(rs.getString("seq_title"));
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
	public void setNotice(SeqBean notice){
		String sql="";
		try {
			con=ds.getConnection();
			sql="update board_seq set seq_title=?,seq_content=? where seq_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, notice.getSeq_title());
			pstmt.setString(2, notice.getSeq_content());
			pstmt.setInt(3, notice.getSeq_num());
			
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
			sql="delete from board_seq where seq_num=?";
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
