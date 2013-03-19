package academy.grade.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


public class GradeDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public GradeDAO() {
        try {
//        	Class.forName("com.mysql.jdbc.Driver");
//        	String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
//        	con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
            Context init = new InitialContext();
            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closingDB() {
        if (con != null) try {con.close();} catch (Exception e) {}
        if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
        if (rs != null) try {rs.close();} catch (Exception e) {}
    }
    
    public boolean gradeJoin(GradeBean gradebean){
        String sql="";
        boolean result = false;  //정상 성적입력확인 여부
        System.out.println(gradebean.getGr_school());
        try {
                        con=ds.getConnection();
            
            // 성적테이블 생성
            sql = "insert into grade(gr_code, gr_subject, gr_memo, gr_exam_date, ep_id, gr_place, " +
            		"gr_period, gr_status, st_school_name, gr_school, st_id, gp_name) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, gradebean.getGr_code());
            pstmt.setString(2, gradebean.getGr_subject());
            pstmt.setString(3, gradebean.getGr_memo());
            pstmt.setDate(4, gradebean.getGr_exam_date());
            pstmt.setString(5, gradebean.getEp_id());
            pstmt.setString(6, gradebean.getGr_place());
            pstmt.setString(7, gradebean.getGr_period());
            
            if(gradebean.getGr_place().equals("학원")){
                pstmt.setString(8, "N"); //무조건 시험진행중 N 시험완료 Y
            }else{
                pstmt.setString(8, ""); //무조건 시험진행중 N 시험완료 Y
            }
            
            pstmt.setString(9, gradebean.getSt_school_name());
            pstmt.setString(10, gradebean.getGr_school());
            pstmt.setString(11, gradebean.getSt_id());
            pstmt.setString(12, gradebean.getGp_name());
            
            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return result;
    }
    
    public List gradeTsearch(){
        List gradeTsearch = null;
        String sql = "";
        GradeBean gradebean = null;
        try {
                        con=ds.getConnection();
            sql = "select employee.ep_account_name, employee.ep_id, member.mm_jumin1, member.mm_jumin2 " +
            		"from employee inner join member where employee.ep_id = member.mm_id";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeTsearch = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setMm_name(rs.getString("ep_account_name"));
                    gradebean.setEp_id(rs.getString("ep_id"));
                    gradebean.setMm_jumin1(rs.getString("mm_jumin1"));
                    gradebean.setMm_jumin2(rs.getString("mm_jumin2"));
                    
                    gradeTsearch.add(gradebean);
                    
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
       
        return gradeTsearch;
    }
    //시험중 자료
    public List gradeAcademyTest(String status){
        List gradeAcademyList = null;
        GradeBean gradebean = null;
        String sql="";
        try {
                        con=ds.getConnection();
            sql = "select gr_code, gr_subject, gr_memo, gr_exam_date, ep_id, gp_name" +
            		" from grade where gr_place = '학원' and gr_status=? ";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, status);
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeAcademyList = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setGr_code(rs.getString("gr_code"));
                    gradebean.setGr_subject(rs.getString("gr_subject"));
                    gradebean.setGr_memo(rs.getString("gr_memo"));
                    gradebean.setGr_exam_date(rs.getDate("gr_exam_date"));
                    gradebean.setEp_id(rs.getString("ep_id"));
                    gradebean.setGp_name(rs.getString("gp_name"));
                    
                    gradeAcademyList.add(gradebean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return gradeAcademyList;
    }
    
  // 학생정보 찾기
    public List gradeSsearch(){
        List gradeSsearch = null;
        String sql = "";
        GradeBean gradebean = null;
        try {
                        con=ds.getConnection();
            sql = "select member.mm_name, member.mm_id, member.mm_jumin1, member.mm_jumin2, student.st_school_name " +
            		"from member inner join student where member.mm_id = student.mm_id";
        	pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){  
                gradeSsearch = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setMm_name(rs.getString("mm_name"));
                    gradebean.setSt_id(rs.getString("mm_id"));
                    gradebean.setMm_jumin1(rs.getString("mm_jumin1"));
                    gradebean.setMm_jumin2(rs.getString("mm_jumin2"));
                    gradebean.setSt_school_name(rs.getString("st_school_name"));
                    gradeSsearch.add(gradebean);
                    
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
       
        return gradeSsearch;
    }
    
 // 그룹정보 찾기
    public List gradeGsearch(){
        List gradeGsearch = null;
        String sql = "";
        GradeBean gradebean = null;
        try {
                        con=ds.getConnection();
            sql = "select groups.gp_name, employee.ep_account_name, employee.ep_subject_name " +
            		"from groups inner join employee where groups.ep_id = employee.ep_id;";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){  
                gradeGsearch = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setGp_name(rs.getString("gp_name"));
                    gradebean.setMm_name(rs.getString("ep_account_name"));
                    gradebean.setGr_subject(rs.getString("ep_subject_name"));
                    gradeGsearch.add(gradebean);
                    
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
       
        return gradeGsearch;
    }
    
    //시험에 그룹 학생정보 찾기
    public List gradeSGsearch(String mm_name){
        List gradeSsearch = null;
        String sql = "";
        GradeBean gradebean = null;
        try {
                        con=ds.getConnection();
            sql = "select mm_name, mm_id, mm_jumin1, mm_jumin2 from member where mm_name like ? and mm_id like 's%'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%"+mm_name+"%");
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeSsearch = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setMm_name(rs.getString("mm_name"));
                    gradebean.setEp_id(rs.getString("mm_id"));
                    gradebean.setMm_jumin1(rs.getString("mm_jumin1"));
                    gradebean.setMm_jumin2(rs.getString("mm_jumin2"));
                    
                    gradeSsearch.add(gradebean);
                    
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
       
        return gradeSsearch;
    }
    
    public void gradeAcademyTestingCancel(String score, String id){
        String sql = "";
        
        try {
                        con = ds.getConnection();
            
            sql = "delete from exam where gr_score = ? and st_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, score);
            pstmt.setString(2, id);
            pstmt.executeUpdate();
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
    }
    
    public void gradeAcademyMoveTested(String[] check){
        StringBuffer sql = new StringBuffer("update grade set gr_status='Y' where gr_code in (");
        // 기본 쿼리문만을 StringBuffer로 생성한다.
        for(int i=0; i<check.length; i++){
            sql.append("'"+check[i]+"'");
            if(i<check.length-1){   
              //만약 check의 길이가 i보다 크다면 ,를 붙인다.
                sql.append(",");
            }else if(i==check.length-1){    
              //위의조건 만족시 만약 i와 check가 같다면 ) 으로써 쿼리를 완성한다
                sql.append(")");
            }
        }
        
        try {
                        con=ds.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.executeUpdate();
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
    }
    
    public void insertTestingStudentAdd(List st_id_list, List gr_score_list, String gr_code){
        String sql = "";
   
        try {
                        con = ds.getConnection();
            
            for(int i=0; i<st_id_list.size(); i++){
                    
                String st_id = (String)st_id_list.get(i);
                String gr_score = (String)gr_score_list.get(i);
                
                sql = "insert into exam(gr_code, st_id, gr_score) values(?,?,?)";
                pstmt = con.prepareStatement(sql);
                pstmt.setString(1, gr_code);
                pstmt.setString(2, st_id);
                pstmt.setString(3, gr_score);
                pstmt.executeUpdate();
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
    }
    
    
    public List gradeAcademyTestingStudentList(String gp_name, String gr_code){
        List gradeAcademyTestingStudentList = null;
        GradeBean gradebean = null;
        String sql="";
        ResultSet rs2=null;
        
        try {
                        con=ds.getConnection();
            sql = "select member.mm_name, student.mm_id, student.st_school_name, student.gp_name " +
            		"from student inner join member where student.mm_id = member.mm_id and student.gp_name = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, gp_name);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                gradeAcademyTestingStudentList = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setMm_name(rs.getString("mm_name"));
                    
                    gradebean.setSt_id(rs.getString("mm_id"));
                    
                    sql = "select exam.gr_score from exam where st_id = ? and exam.gr_code=?";
                    pstmt = con.prepareStatement(sql);
                    pstmt.setString(1, rs.getString("mm_id"));
                    pstmt.setString(2, gr_code);
                    rs2 = pstmt.executeQuery();

                    if (rs2.next()) { // member 테이블의 id와 일치
                       gradebean.setGr_score(rs2.getString("gr_score")); // 회원 이름 가져옴
                    }
                    
                    gradebean.setSt_school_name(rs.getString("st_school_name"));
                    gradebean.setGp_name(rs.getString("gp_name"));
                    
                    gradeAcademyTestingStudentList.add(gradebean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return gradeAcademyTestingStudentList;
    }
    
    public List gradeSchoolList(){
        List gradeSchoolList = null;
        GradeBean gradebean = null;
        String sql="";
        
        try {
                        con=ds.getConnection();
            sql = "select gr_subject, gr_memo, gr_period, st_school_name, gr_school from grade where gr_place = '학교'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeSchoolList = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setGr_period(rs.getString("gr_period"));
                    gradebean.setGr_subject(rs.getString("gr_subject"));
                    gradebean.setGr_memo(rs.getString("gr_memo"));
                    gradebean.setSt_school_name(rs.getString("st_school_name"));
                    gradebean.setGr_school(rs.getString("gr_school"));
                    
                    gradeSchoolList.add(gradebean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return gradeSchoolList;
    }
    
}
