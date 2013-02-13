package academy.accounting.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AccountingDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public AccountingDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void closingDB() {
        if (con != null) try {con.close();} catch (Exception e) {}
        if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
        if (rs != null) try {rs.close();} catch (Exception e) {}
    }
    
    public void acInsert(AccountingBean acBean){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String sql="";
        int num=0;
        String acnum = ""; //회계ID
        try {
            //회계ID 번호 구하기
            con=ds.getConnection();
            sql="select max(ac_idx) from accounting";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                num = rs.getInt(1);
            }            
            //회계ID 날짜-acnum
            acnum=sdf.format(cal.getTime()).toString()+"-"+(num+1);

            con = ds.getConnection();
            sql = "insert into accounting(ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo)" +
            		"values(?,?,?,?,?,?,?,?)";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, acnum);
            pstmt.setString(2, acBean.getMm_id());
            pstmt.setInt(3, acBean.getAc_price());
            pstmt.setString(4, acBean.getAc_cc_type());
            pstmt.setString(5, acBean.getAc_io_type());
            pstmt.setDate(6, acBean.getAc_date());
            pstmt.setString(7, acBean.getAc_manager_name());
            pstmt.setString(8, acBean.getAc_memo());
            
            pstmt.executeUpdate();
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
    }
}
