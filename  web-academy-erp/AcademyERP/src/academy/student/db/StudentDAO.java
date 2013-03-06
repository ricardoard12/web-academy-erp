package academy.student.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import academy.attitude.db.AttitudeBean;
import academy.groups.db.GroupsBean;
import academy.member.db.MemberBean;

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
			pstmt.setString(8, studentbean.getMm_addr1());//주소
			pstmt.setString(9, studentbean.getMm_addr2()); //상세주소
			pstmt.setString(10, studentbean.getMm_zipcode());//회원우편번호
			pstmt.setString(11, studentbean.getMm_email()); //회원메일
			pstmt.setInt(12, 1); //학생레벨값 설정
			pstmt.executeUpdate();
			

			sql="INSERT INTO student(mm_id,st_school_name,st_school_grade,st_parent_name,st_parent_mobile,st_parent_id,st_parent_passwd,st_tuition,st_tuition_state,st_memo,st_status,gp_name) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
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
			pstmt.setString(12, studentbean.getGp_name());// 소속학급
			pstmt.executeUpdate();
					
			sql="INSERT INTO member(mm_name,mm_id,mm_passwd,mm_tel,mm_phone,mm_addr1,mm_addr2,mm_zipcode,mm_email,mm_reg_date,mm_level) VALUES(?,?,?,?,?,?,?,?,?,now(),?) ";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getSt_parent_name());  //회원이름
			pstmt.setString(2, studentbean.getSt_parent_id()); //회원아이디
			pstmt.setString(3, studentbean.getSt_parent_passwd());; //회원비번
			pstmt.setString(4, studentbean.getMm_tel());//회원전화번호
			pstmt.setString(5, studentbean.getSt_parent_mobile()); //회원핸드폰 번호
			pstmt.setString(6, studentbean.getMm_addr1());//주소
			pstmt.setString(7, studentbean.getMm_addr2()); //상세주소
			pstmt.setString(8, studentbean.getMm_zipcode());//회원우편번호
			pstmt.setString(9, studentbean.getMm_email()); //회원메일
			pstmt.setInt(10, 2); //부모레벨값 설정
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
			sql="SELECT m.mm_id,m.mm_name,s.st_school_name,s.st_school_grade,s.gp_name,s.st_tuition_state FROM member AS m,student AS s WHERE m.mm_id LIKE 's%' and m.mm_id=s.mm_id and st_status='재학'";
			// 재학생정보를 가져오는 sql
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			
			if(rs.next()){ // 정보가 있으면 저장
				studentList = new ArrayList();
				do{
					StudentBean studentbean = new StudentBean();
					studentbean.setMm_name(rs.getString("mm_name"));
					studentbean.setMm_id(rs.getString("mm_id"));
					studentbean.setSt_school_name(rs.getString("st_school_name"));
					studentbean.setSt_school_grade(rs.getString("st_school_grade"));
					studentbean.setGp_name(rs.getString("gp_name"));
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
    public void updateStudentOff(String[] st_status){
    	String sql="";
    	try {
			con=ds.getConnection();
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE student SET st_status = '휴학' WHERE mm_id =?"; //휴학처리
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, st_status[i]);
				pstmt.executeUpdate();
			}
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE member SET mm_level = 0 WHERE mm_id =?"; //레벨제로
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
    public  void updatestudentOut(String[] st_status){
    	String sql="";
    	try {
			con=ds.getConnection();
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE student SET st_status = '퇴학' WHERE mm_id =?"; //퇴출 처리
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, st_status[i]);
				pstmt.executeUpdate();
			}
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE member SET mm_level = 0 WHERE mm_id =?"; //레벨제로
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
    public List getStudentAttitudeList(String gp_name){ // 학생 출결 현황
		List SutdentAttitudeList = null;
		ResultSet rs2 = null;
		ResultSet rs3 = null;
		String sql="";
		try {
			con = ds.getConnection();
			sql = "SELECT s.mm_id,m.mm_name FROM student AS s,member AS m WHERE s.mm_id = m.mm_id AND st_status = '재학' and gp_name =?"; 
			// 학생 명단(아이디, 이름) 조회
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, gp_name);
			rs = pstmt.executeQuery();

			SutdentAttitudeList = new ArrayList();
			while (rs.next()) {
				sql = "SELECT * FROM attitude WHERE at_come_time > current_date() AND at_member_id=?";
				// 출석 시간 날짜가 오늘인 데이터 중 아이디 일치 여부 확인
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, rs.getString("mm_id"));
				rs2 = pstmt.executeQuery();
				
				if (rs2.next()) { // 오늘 출석 데이터가 있을 경우
					do { // 해당 과목 마치는 시간 입력
						AttitudeBean attitude = new AttitudeBean();
						
						attitude.setMm_name(rs.getString("mm_name")); // 학생 명단 조회 결과 중 이름 저장
						attitude.setAt_member_id(rs2.getString("at_member_id"));
						attitude.setAt_report_state(rs2.getString("at_report_state"));
						attitude.setAt_come_time(rs2.getTimestamp("at_come_time"));
						attitude.setAt_leave_time(rs2.getTimestamp("at_leave_time"));
						attitude.setAt_memo(rs2.getString("at_memo"));
						
						SutdentAttitudeList.add(attitude);
					} while (rs2.next());
				} else { // 오늘 출석 데이터가 없을 경우
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
    
    public List getStudentOffList(){
		String sql="";
    	List<StudentBean> StudentOffList = null;
    	
    	try {
			con = ds.getConnection();
			sql="SELECT m.mm_id,m.mm_name,s.st_school_name,s.st_school_grade,s.gp_name,s.st_tuition_state,st_status FROM member AS m,student AS s WHERE m.mm_id LIKE 's%' and m.mm_id=s.mm_id and st_status='휴학'"; 
			// 휴학생의 목록을 가져온다.
			pstmt =con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			
			if(rs.next()){ // 값을 가지고 있을경우 저장
				StudentOffList = new ArrayList();
				do{
					StudentBean studentbean = new StudentBean();
					studentbean.setMm_name(rs.getString("mm_name"));
					studentbean.setMm_id(rs.getString("mm_id"));
					studentbean.setSt_school_name(rs.getString("st_school_name"));
					studentbean.setSt_school_grade(rs.getString("st_school_grade"));
					studentbean.setGp_name(rs.getString("gp_name"));
					studentbean.setSt_tuition_state(rs.getString("st_tuition_state"));
					studentbean.setSt_status(rs.getString("st_status"));
					StudentOffList.add(studentbean);
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
    	
    	return StudentOffList;
    	
    }
    public List getStudentOutList(){
		String sql="";
    	List<StudentBean> studentoutlist = null;
    	
    	try {
			con = ds.getConnection();
			sql="SELECT m.mm_id,m.mm_name,s.st_school_name,s.st_school_grade,s.gp_name,s.st_tuition_state,st_status FROM member AS m,student AS s WHERE m.mm_id LIKE 's%' and m.mm_id=s.mm_id and st_status='퇴학'"; 
			// 퇴학생의 목록을 가져온다.
			pstmt =con.prepareStatement(sql);
			rs= pstmt.executeQuery();
			
			
			if(rs.next()){ // 값을 가지고 있을경우 저장
				studentoutlist = new ArrayList();
				do{
					StudentBean studentbean = new StudentBean();
					studentbean.setMm_name(rs.getString("mm_name"));
					studentbean.setMm_id(rs.getString("mm_id"));
					studentbean.setSt_school_name(rs.getString("st_school_name"));
					studentbean.setSt_school_grade(rs.getString("st_school_grade"));
					studentbean.setGp_name(rs.getString("gp_name"));
					studentbean.setSt_tuition_state(rs.getString("st_tuition_state"));
					studentbean.setSt_status(rs.getString("st_status"));
					studentoutlist.add(studentbean);
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
    	return studentoutlist;
    	
    }
    
    public  void updateStudentIn(String[] st_status){
    	String sql="";
    	try {
			con=ds.getConnection();
			for(int i=0; i <st_status.length; i++){ // st_status배열길이까지 for문 처리
				sql="UPDATE student SET st_status = '재학' WHERE mm_id =?"; //재학처리
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
    
    public StudentBean getStudentDetail(String id){
		StudentBean studentbean = null;
    	String sql="";
    	
    	try {
			con= ds.getConnection();
			sql="SELECT s.mm_id, m.mm_name,m.mm_jumin1,m.mm_jumin2,m.mm_tel,m.mm_phone,m.mm_addr1,m.mm_addr2,m.mm_email,m.mm_reg_date,m.mm_zipcode,m.mm_level,m.mm_manager_id,s.st_school_name,s.st_school_grade,s.gp_name,s.st_parent_id,s.st_parent_name,s.st_tuition, s.st_tuition_state,s.st_memo,s.st_status,st_parent_mobile,mm_manager_id FROM student as s INNER JOIN member as m WHERE s.mm_id = m.mm_id and s.mm_id=?"; // 학생 정보 가지고오는 sql 문
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs= pstmt.executeQuery();
			
			if(rs.next()){
				studentbean = new StudentBean();
				studentbean.setMm_id(rs.getString("mm_id"));
				studentbean.setMm_name(rs.getString("mm_name"));
				studentbean.setMm_jumin1(rs.getString("mm_jumin1"));
				studentbean.setMm_jumin2(rs.getString("mm_jumin2"));
				studentbean.setMm_tel(rs.getString("mm_tel"));
				studentbean.setMm_phone(rs.getString("mm_phone"));
				studentbean.setMm_addr1(rs.getString("mm_addr1"));
				studentbean.setMm_addr2(rs.getString("mm_addr2"));
				studentbean.setMm_zipcode(rs.getString("mm_zipcode"));
				studentbean.setMm_email(rs.getString("mm_email"));
				studentbean.setGp_name(rs.getString("gp_name"));
				studentbean.setSt_school_name(rs.getString("st_school_name"));
				studentbean.setSt_school_grade(rs.getString("st_school_grade"));
				studentbean.setSt_parent_id(rs.getString("st_parent_id"));
				studentbean.setSt_parent_name(rs.getString("st_parent_name"));
				studentbean.setSt_tuition(rs.getInt("st_tuition"));
				studentbean.setSt_tuition_state(rs.getString("st_tuition_state"));
				studentbean.setSt_memo(rs.getString("st_memo"));
				studentbean.setSt_status(rs.getString("st_status"));
				studentbean.setSt_parent_mobile(rs.getString("st_parent_mobile"));
				studentbean.setMm_level_id(rs.getInt("mm_level"));
				studentbean.setMm_manager_id(rs.getString("mm_manager_id"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
		
    	return studentbean;
    	
    }
    public void setStudentModify(StudentBean studentbean){
    	String sql="";
    	
    	try {
			con = ds.getConnection();
			
			// 학생 정보 업데이트
			sql="UPDATE member SET mm_name=?,mm_jumin1=?,mm_jumin2=?,mm_tel=?,mm_phone=?,mm_addr1=?,mm_addr2=?,mm_zipcode=?,mm_email=?,mm_manager_id=? WHERE mm_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getMm_name());  //회원이름
			pstmt.setString(2, studentbean.getMm_jumin1());; //주민앞
			pstmt.setString(3, studentbean.getMm_jumin2());// 주민뒤
			pstmt.setString(4, studentbean.getMm_tel());//회원전화번호
			pstmt.setString(5, studentbean.getMm_phone()); //회원핸드폰 번호
			pstmt.setString(6, studentbean.getMm_addr1());//주소
			pstmt.setString(7, studentbean.getMm_addr2()); //상세주소
			pstmt.setString(8, studentbean.getMm_zipcode());//회원우편번호
			pstmt.setString(9, studentbean.getMm_email()); //회원메일
			pstmt.setString(10, studentbean.getMm_manager_id()); //매니저 아이뒤 
			pstmt.setString(11, studentbean.getMm_id()); //회원아이디
			pstmt.executeUpdate();
			
			sql="UPDATE student SET st_school_name=?,st_school_grade=?,st_parent_name=?,st_parent_mobile=?,st_tuition=?,st_tuition_state=?,st_memo=?,gp_name=? WHERE mm_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1,studentbean.getSt_school_name()); // 학교명
			pstmt.setString(2,studentbean.getSt_school_grade()); // 학년
			pstmt.setString(3,studentbean.getSt_parent_name()); // 학부모이름
			pstmt.setString(4,studentbean.getSt_parent_mobile()); // 학부모연락처
			pstmt.setInt(5,studentbean.getSt_tuition()); // 수강료
			pstmt.setString(6,studentbean.getSt_tuition_state()); // 회비납부여부
			pstmt.setString(7,studentbean.getSt_memo()); // 메모
			pstmt.setString(8, studentbean.getGp_name());// 소속학급
			pstmt.setString(9, studentbean.getMm_id());  //회원ID
			pstmt.executeUpdate();
			
			// 부모 정보 수정된거 업데이트
			sql="UPDATE member SET mm_name=?,mm_tel=?,mm_phone=?,mm_addr1=?,mm_addr2=?,mm_zipcode=?,mm_email=?,mm_manager_id=? WHERE mm_id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, studentbean.getSt_parent_name());  //회원이름
			pstmt.setString(2, studentbean.getMm_tel());//회원전화번호
			pstmt.setString(3, studentbean.getSt_parent_mobile()); //회원핸드폰 번호
			pstmt.setString(4, studentbean.getMm_addr1());//주소
			pstmt.setString(5, studentbean.getMm_addr2()); //상세주소
			pstmt.setString(6, studentbean.getMm_zipcode());//회원우편번호
			pstmt.setString(7, studentbean.getMm_email()); //회원메일
			pstmt.setString(8, studentbean.getMm_manager_id()); //회원메일		
			pstmt.setString(9, studentbean.getSt_parent_id()); //회원아이디
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
    }
    public StudentBean getStudentinfo(String id){
		String sql="";
		StudentBean studentbean=null;
		try {
			con= ds.getConnection();
			sql="select m.mm_id, m.mm_name,m.mm_tel,m.mm_phone,m.mm_email,s.st_school_name,s.st_school_grade,s.st_parent_name,s.st_parent_mobile,s.gp_name,g.ep_id,s.st_status from member AS m, student As s, groups As g where m.mm_id=s.mm_id and s.gp_name=g.gp_name and s.st_status='재학' and m.mm_id=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				studentbean = new StudentBean();
				studentbean.setMm_id(rs.getString("mm_id")); //학생아이디
				studentbean.setMm_name(rs.getString("mm_name")); //학생이름
				studentbean.setMm_tel(rs.getString("mm_tel"));//전화번호
				studentbean.setMm_phone(rs.getString("mm_phone")); // 폰번호
				studentbean.setMm_email(rs.getString("mm_email")); // 이메일
				
				studentbean.setSt_parent_name(rs.getString("st_parent_name"));//부모이름
				studentbean.setSt_parent_mobile(rs.getString("st_parent_mobile"));// 부모 전화
				studentbean.setSt_school_name(rs.getString("st_school_name"));// 학교이름
				studentbean.setSt_school_grade(rs.getString("st_school_grade")); // 학년
				studentbean.setSt_status(rs.getString("st_status")); //상태
				studentbean.setEp_id(rs.getString("ep_id")); //담임
				studentbean.setGp_name(rs.getString("gp_name")); // 소속학과
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null)try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null)try{pstmt.close();}catch(SQLException ex){}
			if(con!=null)try{con.close();}catch(SQLException ex){}
		}
    	
    	
    	return studentbean;
    	
    }
    public List getstudentgroups(){ // 개설된과목 가져옴
		List groups=null;
		String sql="";
		GroupsBean group = null;
		try {
			con=ds.getConnection();
			sql="select gp_name from groups";  //개설된 과목을 가져옴
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			if(rs.next()){
				groups = new ArrayList<>();
				do{
					group = new GroupsBean();
					group.setGp_name(rs.getString("gp_name"));
					groups.add(group);
				}while(rs.next());
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return groups;
    	
    }
    
    public List getSMSReceiverList(String[] studentList) throws Exception {
    	List receiverList = null;
    	try {
    		con = ds.getConnection();
    		for (int i = 0; i < studentList.length; i++) {
	    		String sql = "SELECT mm_id, mm_name, mm_phone FROM member WHERE mm_id=?";
	    		pstmt = con.prepareStatement(sql);
	    		pstmt.setString(1, studentList[i]);
	    		rs = pstmt.executeQuery();
	    		
	    		receiverList = new ArrayList();
	    		if (rs.next()) {
	    			MemberBean member = new MemberBean();
	    			member.setMm_id(rs.getString("mm_id"));
	    			member.setMm_name(rs.getString("mm_name"));
	    			member.setMm_phone(rs.getString("mm_phone"));
	    			
	    			receiverList.add(member);
	    		}
    		}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return receiverList;
    }
}
