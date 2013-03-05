package academy.lesson_plan.db;

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



public class LessonDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds;
	
	
	public LessonDAO() {
		try {
			Context init = new InitialContext();
			System.out.println("Lesson DB Connected");
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void dbClose(){
		if(rs!=null)try{rs.close();}catch(SQLException ex){}
		if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
		if(con!=null)try{con.close();}catch(SQLException ex){}
		System.out.println("Lesson_Plan_DB Closed");
	}
	


	public void lessoninsert(LessonBean lessonbean) throws Exception{
		int num = 0;
		String sql = "";
		try {
			
			System.out.println("Lesson_Plan_Insert Start");
			con = ds.getConnection();
			sql = "SELECT max(lesson_num) FROM lesson_plan;";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				num = rs.getInt(1)+1;
				
			}else {
				num = 1;
			}
			
			sql = "INSERT INTO lesson_plan(lesson_num, lesson_subject, lesson_teacher, lesson_goal, lesson_book, lesson_cost, lesson_time, lesson_content ,lesson_date) values(?,?,?,?,?,?,?,?,now())";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, lessonbean.getLesson_subject());
			pstmt.setString(3, lessonbean.getLesson_teacher());
			pstmt.setString(4, lessonbean.getLesson_goal());
			pstmt.setString(5, lessonbean.getLesson_book());
			pstmt.setString(6, lessonbean.getLesson_cost());
			pstmt.setString(7, lessonbean.getLesson_time());
			pstmt.setString(8, lessonbean.getLesson_content());
			pstmt.executeUpdate();
			System.out.println("Lesson_Plan_Insert End");
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dbClose();
		}
		
	}

	public List getLessonList(int page, int limit) {
	String sql = "";
	List list = null;
	int startrow=(page-1)*limit+1; //현재페이지 시작행
	try {
		System.out.println("getLessonList start");
		con=ds.getConnection();
		//3 sql
		sql="SELECT * FROM lesson_plan ORDER BY lesson_num DESC LIMIT ?,?";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, startrow-1); //시작위치-1
		pstmt.setInt(2, limit); //개수
		//4 저장 = 실행
		rs=pstmt.executeQuery();
		//5 rs => 자바빈 저장
		if(rs.next()){
			list = new ArrayList(limit);//ArrayList객체생성
			do{
				LessonBean lessonbean = new LessonBean();
				lessonbean.setLesson_num(rs.getInt("lesson_num"));
				lessonbean.setLesson_teacher(rs.getString("lesson_teacher"));
				lessonbean.setLesson_subject(rs.getString("lesson_subject"));
				lessonbean.setLesson_goal(rs.getString("lesson_goal"));
				lessonbean.setLesson_cost(rs.getString("lesson_cost"));
				lessonbean.setLesson_book(rs.getString("lesson_book"));
				lessonbean.setLesson_content(rs.getString("lesson_content"));
				lessonbean.setLesson_time(rs.getString("lesson_time"));
				lessonbean.setLesson_date(rs.getString("lesson_date"));
				list.add(lessonbean); //자바빈 -> 한칸
			}while(rs.next());
		}
		System.out.println("getlessonList end");
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
		System.out.println("getLessonListCount start");
		con = ds.getConnection();
		
		sql = "select count(*) from lesson_plan";
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

public boolean lessonDelete(String[] num) throws Exception{
	String sql="";
	Statement stmt =null;
	boolean result = false;

    try {
    	System.out.println("lessonDelete start");
        con = ds.getConnection();
        for(int i=0; i<num.length; i++){
        	sql="DELETE FROM lesson_plan WHERE lesson_num="+num[i];
        	stmt=con.createStatement();
        	stmt.executeUpdate(sql);	
        	result = true;
        	System.out.println("lessonDelete end");
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
		
		sql="SELECT lesson_teacher FROM lesson_plan WHERE lesson_num=? ";
		pstmt=con.prepareStatement(sql);
		pstmt.setInt(1, num);
		
		rs=pstmt.executeQuery();
		
		if(rs.next()){
			String dbName=rs.getString("lesson_teacher");
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
	
}
