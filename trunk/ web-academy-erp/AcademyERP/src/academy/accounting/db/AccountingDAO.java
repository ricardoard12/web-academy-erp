package academy.accounting.db;

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
    
    //전체리스트
    public List acGetList(){
        String sql="";
        List acList = null;
        AccountingBean acBean = null;
        try {
            con=ds.getConnection();
            sql="select ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo from accounting " +
            		"order by ac_id desc";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                acList = new ArrayList();
                do{
                    acBean = new AccountingBean();
                    acBean.setAc_id(rs.getString("ac_id"));
                    acBean.setMm_id(rs.getString("mm_id"));
                    acBean.setAc_price(rs.getInt("ac_price"));
                    acBean.setAc_cc_type(rs.getString("ac_cc_type"));
                    acBean.setAc_io_type(rs.getString("ac_io_type"));
                    acBean.setAc_date(rs.getDate("ac_date"));
                    acBean.setAc_manager_name(rs.getString("ac_manager_name"));
                    acBean.setAc_memo(rs.getString("ac_memo"));
                    
                    acList.add(acBean);
                }while(rs.next());
            }            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return acList;
    }
    
    //회비리스트
    public List acfeeGetList(){
        String sql="";
        List acfeeList = null;
        AccountingBean acBean = null;
        try {
            con=ds.getConnection();
            sql="select ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo from accounting " +
                    "where ac_io_type='수강료' order by ac_id desc";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                acfeeList = new ArrayList();
                do{
                    acBean = new AccountingBean();
                    acBean.setAc_id(rs.getString("ac_id"));
                    acBean.setMm_id(rs.getString("mm_id"));
                    acBean.setAc_price(rs.getInt("ac_price"));
                    acBean.setAc_cc_type(rs.getString("ac_cc_type"));
                    acBean.setAc_io_type(rs.getString("ac_io_type"));
                    acBean.setAc_date(rs.getDate("ac_date"));
                    acBean.setAc_manager_name(rs.getString("ac_manager_name"));
                    acBean.setAc_memo(rs.getString("ac_memo"));
                    
                    acfeeList.add(acBean);
                }while(rs.next());
            }            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return acfeeList;
    }
    
    //수입 리스트
    public List acincomingGetList(){
        String sql="";
        List acincomingList = null;
        AccountingBean acBean = null;
        try {
            con=ds.getConnection();
            sql="select ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo from accounting " +
                    "where ac_io_type='수입' order by ac_id desc";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                acincomingList = new ArrayList();
                do{
                    acBean = new AccountingBean();
                    acBean.setAc_id(rs.getString("ac_id"));
                    acBean.setMm_id(rs.getString("mm_id"));
                    acBean.setAc_price(rs.getInt("ac_price"));
                    acBean.setAc_cc_type(rs.getString("ac_cc_type"));
                    acBean.setAc_io_type(rs.getString("ac_io_type"));
                    acBean.setAc_date(rs.getDate("ac_date"));
                    acBean.setAc_manager_name(rs.getString("ac_manager_name"));
                    acBean.setAc_memo(rs.getString("ac_memo"));
                    
                    acincomingList.add(acBean);
                }while(rs.next());
            }            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return acincomingList;
    }
    
    //지출 리스트
    public List acoutgoingGetList(){
        String sql="";
        List acoutgoingList = null;
        AccountingBean acBean = null;
        try {
            con=ds.getConnection();
            sql="select ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo from accounting " +
                    "where ac_io_type='지출' order by ac_id desc";
            pstmt=con.prepareStatement(sql);
            rs=pstmt.executeQuery();
            if(rs.next()){
                acoutgoingList = new ArrayList();
                do{
                    acBean = new AccountingBean();
                    acBean.setAc_id(rs.getString("ac_id"));
                    acBean.setMm_id(rs.getString("mm_id"));
                    acBean.setAc_price(rs.getInt("ac_price"));
                    acBean.setAc_cc_type(rs.getString("ac_cc_type"));
                    acBean.setAc_io_type(rs.getString("ac_io_type"));
                    acBean.setAc_date(rs.getDate("ac_date"));
                    acBean.setAc_manager_name(rs.getString("ac_manager_name"));
                    acBean.setAc_memo(rs.getString("ac_memo"));
                    
                    acoutgoingList.add(acBean);
                }while(rs.next());
            }            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return acoutgoingList;
    }
}
