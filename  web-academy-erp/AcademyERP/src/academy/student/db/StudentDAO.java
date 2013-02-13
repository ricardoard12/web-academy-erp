package academy.student.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class StudentDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public StudentDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void insertStudent(StudentBean studentbean){
    	String sql="";
    	try {
			con=ds.getConnection();
			System.out.println("성공");
			sql="INSERT INTO member(mm_name,mm_id,mm_passwd,mm_jumin1,mm_jumin2,mm_tel,mm_phone,mm_addr1,mm_addr2,mm_zipcode,mm_email,mm_reg_date,mm_level) VALUES(?,?,?,?,?,?,?,?,?,?,?,now(),?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getMm_name());  //회원이름
			pstmt.setString(2, studentbean.getMm_id()); //회원아이디
			pstmt.setString(3, studentbean.getMm_pw());; //회원비번
			pstmt.setString(4, studentbean.getMm_jumin1());; //주민앞
			pstmt.setString(5, studentbean.getMm_jumin2());// 주민뒤
			pstmt.setString(6, studentbean.getMm_tel());//회원전화번호
			pstmt.setString(7, studentbean.getMm_phone()); //회원핸드폰 번호
			pstmt.setString(8, studentbean.getMm_zipcode());//회원우편번호
			pstmt.setString(9, studentbean.getMm_addr1());//주소
			pstmt.setString(10, studentbean.getMm_addr2()); //상세주소
			pstmt.setString(11, studentbean.getMm_email()); //회원메일
			pstmt.setInt(12, 1); //학생레벨값 설정
			pstmt.executeUpdate();
			

			sql="INSERT INTO student(mm_id,st_school_name,st_school_grade,st_parent_name,st_parent_mobile,st_parent_id,st_parent_passwd,st_tuition,st_tuition_state,st_memo) VALUES(?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getMm_name());  //회원이름
			pstmt.setString(2,studentbean.getSt_school_name()); // 학교명
			pstmt.setString(3,studentbean.getSt_school_grade()); // 학년
			pstmt.setString(4,studentbean.getSt_parent_name()); // 학부모이름
			pstmt.setString(5,studentbean.getSt_parent_mobile()); // 학부모연락처
			pstmt.setString(6,studentbean.getSt_parent_id()); // 학부모아이디
			pstmt.setString(7,studentbean.getSt_parent_passwd()); // 학부모패스워드
			pstmt.setInt(8,studentbean.getSt_tuition()); // 수강료
			pstmt.setString(9,studentbean.getSt_tuition_state()); // 회비납부여부
			pstmt.setString(10,studentbean.getSt_memo()); // 메모		
			pstmt.executeUpdate();
					
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
    }
}
