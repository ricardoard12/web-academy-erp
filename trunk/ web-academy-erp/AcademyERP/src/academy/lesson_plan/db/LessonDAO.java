package academy.lesson_plan.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
	
}
