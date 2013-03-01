package academy.grade.db;

import java.sql.Connection;
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

import academy.accounting.db.AccountingBean;

public class GradeDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public GradeDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
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
        
        try {
            con=ds.getConnection();
            sql = "insert into grade(gr_code, gr_subject, gr_memo, gr_exam_date, ep_id, gr_place, gr_period, gr_status, gr_school_name)" +
                    "values(?,?,?,?,?,?,?,?,?)";
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
            
            pstmt.setString(9, gradebean.getGr_school_name());
            
            
            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return result;
    }
    
    public List gradeTsearch(String ep_id){
        List gradeTsearch = null;
        String sql = "";
        GradeBean gradebean = null;
        try {
            con=ds.getConnection();
            sql = "select mm_name, mm_id, mm_jumin1, mm_jumin2 from member where mm_name like ? and mm_id like 't%'";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, "%"+ep_id+"%");
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeTsearch = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setMm_name(rs.getString("mm_name"));
                    gradebean.setMm_id(rs.getString("mm_id"));
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
            sql = "select gr_code, gr_subject, gr_memo, gr_exam_date, ep_id" +
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
                    
                    gradeAcademyList.add(gradebean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return gradeAcademyList;
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
    
    public List gradeSchoolList(){
        List gradeSchoolList = null;
        GradeBean gradebean = null;
        String sql="";
        
        try {
            con=ds.getConnection();
            sql = "select gr_subject, gr_memo, mm_id, gr_score, gr_period from grade where gr_place = '학교'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                gradeSchoolList = new ArrayList();
                do{
                    gradebean = new GradeBean();
                    gradebean.setGr_period(rs.getString("gr_period"));
                    gradebean.setGr_subject(rs.getString("gr_subject"));
                    gradebean.setGr_memo(rs.getString("gr_memo"));
                    gradebean.setMm_id(rs.getString("mm_id"));
                    gradebean.setGr_score(rs.getInt("gr_score"));
                    
                    gradeSchoolList.add(gradebean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return gradeSchoolList;
    }
    
}
