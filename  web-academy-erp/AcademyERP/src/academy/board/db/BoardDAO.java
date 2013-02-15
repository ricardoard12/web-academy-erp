package academy.board.db;

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

public class BoardDAO {
	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public BoardDAO() { //생성자
		//디비연결 이름호출
		try {
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자	
	
	
	public void boardinsert(BoardBean boardbean){
		int num=0;
		String sql="";
		try {
			con=ds.getConnection();
			sql="select max(board_num) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num=1;
			}
					
			sql="insert into board(board_num , board_name, board_pass, board_subject , board_content, board_file, board_re_ref , board_re_lev, board_re_seq, board_readcount, board_date) values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, boardbean.getBoard_name());
			pstmt.setString(3, boardbean.getBoard_pass());
			pstmt.setString(4, boardbean.getBoard_subject());
			pstmt.setString(5, boardbean.getBoard_content());
			pstmt.setString(6, boardbean.getBoard_file());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	
	public int getListCount() throws Exception{
		String sql="";
		int x=0;
		try {
			con=ds.getConnection();
			
			sql="select count(*) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				x=rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return x;
	}
	
	public List getBoardList(int page,int limit){
		String sql="";
		List list=null;
		int startrow=(page-1)*limit+1; 
		try {
			con=ds.getConnection();
			
			sql="select * from board order by board_re_ref desc, board_re_seq asc limit ?,?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, startrow-1); 
			pstmt.setInt(2, limit); 
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				list=new ArrayList(limit);
				do{
					BoardBean boardbean=new BoardBean();
					boardbean.setBoard_num(rs.getInt("board_num"));
					boardbean.setBoard_name(rs.getString("board_name"));
					boardbean.setBoard_subject(rs.getString("board_subject"));
					boardbean.setBoard_content(rs.getString("board_content"));
					boardbean.setBoard_file(rs.getString("board_file"));
					boardbean.setBoard_re_ref(rs.getInt("board_re_ref"));
					boardbean.setBoard_re_lev(rs.getInt("board_re_lev"));
					boardbean.setBoard_re_seq(rs.getInt("board_re_seq"));
					boardbean.setBoard_readcount(rs.getInt("board_readcount"));
					boardbean.setBoard_date(rs.getDate("board_date"));
					list.add(boardbean); 
				}while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return list;
	}
	public void setReadCountUpdate(int num) throws Exception{
		String sql="";
		try {
			
			con=ds.getConnection();
			
			sql="update board set board_readcount=board_readcount+1 where board_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	public BoardBean getDetail(int num) throws Exception{
		String sql="";
		BoardBean boardbean=null;
		try {
			
			con=ds.getConnection();
			
			sql="select * from board where board_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs=pstmt.executeQuery();
		
			if(rs.next()){
				boardbean = new BoardBean();
				boardbean.setBoard_num(rs.getInt("board_num"));
				boardbean.setBoard_name(rs.getString("board_name"));
				boardbean.setBoard_subject(rs.getString("board_subject"));
				boardbean.setBoard_content(rs.getString("board_content"));
				boardbean.setBoard_file(rs.getString("board_file"));
				boardbean.setBoard_re_ref(rs.getInt("board_re_ref"));
				boardbean.setBoard_re_lev(rs.getInt("board_re_lev"));
				boardbean.setBoard_re_seq(rs.getInt("board_re_seq"));
				boardbean.setBoard_readcount(rs.getInt("board_readcount"));
				boardbean.setBoard_date(rs.getDate("board_date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return boardbean;
	}
	public boolean isBoardWriter(int num , String passwd){
		String sql="";
		boolean x=false;
		try {
			
			con=ds.getConnection();
			
			sql="select board_pass from board where board_num=? ";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				String dbPasswd=rs.getString("board_pass");
				if(passwd.equals(dbPasswd)){
					x=true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return x;
	}
	public void boardModify(BoardBean boardbean) throws Exception{
		String sql="";
		try {
			
			con=ds.getConnection();
			sql="update board set board_subject=?, board_content=? where board_num=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, boardbean.getBoard_subject());
			pstmt.setString(2, boardbean.getBoard_content());
			pstmt.setInt(3, boardbean.getBoard_num());
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
	}
	public boolean boardDelete(String[] num) throws Exception{
		String sql="";
		int num2;
		Statement stmt =null;
		boolean result=false;
//		StringBuffer sql = new StringBuffer("delete from board where board_num=?");
//        // 기본 쿼리문만을 StringBuffer로 생성한다.
//        for(int i=0; i<num.length; i++){
//            sql.append("'"+num[i]+"'");
//            if(i<num.length-1){   
//              //만약 check의 길이가 i보다 크다면 ,를 붙인다.
//                sql.append(",");
//            }else if(i==num.length-1){    
//              //위의조건 만족시 만약 i와 check가 같다면 ) 으로써 쿼리를 완성한다
//                sql.append(")");
//            }
//        }
        try {
            con = ds.getConnection();
            for(int i=0; i<num.length; i++){
            	sql="delete from board where board_num="+num[i];
            	stmt=con.createStatement();
            	stmt.executeUpdate(sql);	
            	result = true;
            }
            
        } catch (Exception e) {e.printStackTrace();} 
        finally {if(rs!=null)try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		if(con!=null)try{con.close();}catch(SQLException ex){}}
		return false;
    }
	public int boardReply(BoardBean boardbean){
		String sql="";
		int x=0;
		int ref=boardbean.getBoard_re_ref();
		int lev=boardbean.getBoard_re_lev();
		int seq=boardbean.getBoard_re_seq();
		int num=0;
		try {
			
			con=ds.getConnection();
			sql="select max(board_num) from board";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				num=rs.getInt(1)+1;
			}else{
				num=1;
			}
			
			sql="update board set board_re_seq=board_re_seq+1 where board_re_ref=? and board_re_seq>?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, ref);
			pstmt.setInt(2, seq);
			
			pstmt.executeUpdate();
		
			seq=seq+1;
			lev=lev+1;
			
			sql="insert into board values(?,?,?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, boardbean.getBoard_name());
			pstmt.setString(3, boardbean.getBoard_pass());
			pstmt.setString(4, boardbean.getBoard_subject());
			pstmt.setString(5, boardbean.getBoard_content());
			pstmt.setString(6, "");
			pstmt.setInt(7, ref);
			pstmt.setInt(8, lev);
			pstmt.setInt(9, seq);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			x = num;
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return x;
	}
	
}


