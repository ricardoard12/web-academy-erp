package academy.grade.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
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
            sql = "insert into grade(gr_code, gr_subject, gr_memo, mm_id, gr_score, gr_exam_date, ep_id, gr_place, gr_period)" +
                    "values(?,?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, gradebean.getGr_code());
            pstmt.setString(2, gradebean.getGr_subject());
            pstmt.setString(3, gradebean.getGr_memo());
            pstmt.setString(4, gradebean.getMm_id());
            pstmt.setInt(5, gradebean.getGr_score());
            pstmt.setDate(6, gradebean.getGr_exam_date());
            pstmt.setString(7, gradebean.getEp_id());
            pstmt.setString(8, gradebean.getGr_place());
            pstmt.setString(9, gradebean.getGr_period());
            
            
            pstmt.executeUpdate();
            result = true;
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return result;
    }
}
