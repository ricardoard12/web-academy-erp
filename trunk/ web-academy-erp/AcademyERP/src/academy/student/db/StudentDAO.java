package academy.student.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.attitude.db.AttitudeBean;

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
			

			sql="INSERT INTO student(mm_id,st_school_name,st_school_grade,st_parent_name,st_parent_mobile,st_parent_id,st_parent_passwd,st_tuition,st_tuition_state,st_memo,st_status) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getMm_id());  //회원이름 => // 회원ID(mm_id) 로 수정하세요
			pstmt.setString(2,studentbean.getSt_school_name()); // 학교명
			pstmt.setString(3,studentbean.getSt_school_grade()); // 학년
			pstmt.setString(4,studentbean.getSt_parent_name()); // 학부모이름
			pstmt.setString(5,studentbean.getSt_parent_mobile()); // 학부모연락처
			pstmt.setString(6,studentbean.getSt_parent_id()); // 학부모아이디
			pstmt.setString(7,studentbean.getSt_parent_passwd()); // 학부모패스워드
			pstmt.setInt(8,studentbean.getSt_tuition()); // 수강료
			pstmt.setString(9,studentbean.getSt_tuition_state()); // 회비납부여부
			pstmt.setString(10,studentbean.getSt_memo()); // 메모
			pstmt.setString(11, "재학");
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
    
    public List studentList(){
		List studentList =null;
		
    	String sql=""; //조회
    	
    	try {
			con = ds.getConnection();
			sql="SELECT m.mm_id,m.mm_name,s.st_school_name,s.st_school_grade,s.gp_id,s.st_tuition_state FROM member AS m,student AS s WHERE m.mm_id LIKE 's%' and m.mm_id=s.mm_id and st_status='재학'";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				studentList = new ArrayList();
				do{
					StudentBean studentbean = new StudentBean();
					studentbean.setMm_name(rs.getString("mm_name"));
					studentbean.setMm_id(rs.getString("mm_id"));
					studentbean.setSt_school_name(rs.getString("st_school_name"));
					studentbean.setSt_school_grade(rs.getString("st_school_grade"));
					studentbean.setGp_id(rs.getString("gp_id"));
					studentbean.setSt_tuition_state(rs.getString("st_tuition_state"));
					studentList.add(studentbean);
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
    	
    	return studentList;
    	
    }
    public void updateStudentLeaveofabsence(String[] st_status){
    	String sql="";
    	try {
			con=ds.getConnection();
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE student SET st_status = '휴학' WHERE mm_id =?"; //휴학처리
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, st_status[i]);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
    }
    public  void updateStatusExpel(String[] st_status){
    	String sql="";
    	try {
			con=ds.getConnection();
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE student SET st_status = '퇴출' WHERE mm_id =?"; //휴학처리
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, st_status[i]);
				pstmt.executeUpdate();
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
    }
    public List getStudentAttitudeList(){ // 학생 출결 현황
		List SutdentAttitudeList = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		try {
			con = ds.getConnection();
			sql = "SELECT s.mm_id,m.mm_name FROM student AS s,member AS m WHERE s.mm_id = m.mm_id AND st_status = '재학'"; 
			// 학생 명단(아이디, 이름) 조회
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();

			SutdentAttitudeList = new ArrayList();
			while (rs.next()) {
				sql = "SELECT * FROM attitude WHERE at_open_time > current_date() AND at_member_id=?";
				// 출석 시간 날짜가 오늘인 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("mm_id"));
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 오늘 출석 데이터가 있을 경우
					do { // 해당 과목 마치는 시간 입력
						AttitudeBean attitude = new AttitudeBean();
						
						attitude.setMm_name(rs.getString("mm_name")); // 직원 명단 조회 결과 중 이름 저장
						attitude.setAt_member_id(rs2.getString("at_member_id"));
						attitude.setAt_report_state(rs2.getString("at_report_state"));
						attitude.setAt_open_time(rs2.getDate("at_open_time"));
						attitude.setAt_close_time(rs2.getDate("at_close_time"));
						attitude.setAt_memo(rs2.getString("at_memo"));
						
						SutdentAttitudeList.add(attitude);
					} while (rs2.next());
				} else { // 오늘 출근 데이터가 없을 경우
					AttitudeBean attitude = new AttitudeBean();
					
					attitude.setMm_name(rs.getString("mm_name"));
					attitude.setAt_member_id(rs.getString(1)); // ID 받아옴
					attitude.setAt_report_state("N"); // 출근 상태 N(미출근) 으로 설정
					
					sql = "SELECT at_memo FROM attitude WHERE at_member_id=? AND at_memo_date > current_date()";
					// 미출근 상태에서 오늘 날짜의 메모(사유)가 있는지 확인
					pstmt = con.prepareStatement(sql);
					pstmt.setString(1, rs.getString(1));
					rs3 = pstmt.executeQuery();
					
					if (rs3.next()) { // 메모(사유)가 있을 경우
						attitude.setAt_memo(rs3.getString("at_memo"));
					} 
					
					// 메모조차 없으면 전부 자동으로 NULL로 설정됨
					
					SutdentAttitudeList.add(attitude);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		return SutdentAttitudeList;
	}
}