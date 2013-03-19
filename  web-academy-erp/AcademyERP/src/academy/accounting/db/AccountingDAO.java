package academy.accounting.db;

import java.sql.Connection;
import java.sql.Date;
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

public class AccountingDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public AccountingDAO() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
            con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
//            Context init = new InitialContext();
//            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
        } catch (Exception e) {e.printStackTrace();}
    }
    
    public void closingDB() {
        if (con != null) try {con.close();} catch (Exception e) {}
        if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
        if (rs != null) try {rs.close();} catch (Exception e) {}
    }
    
    public boolean acJoin(AccountingBean acBean){
        Calendar cal=Calendar.getInstance();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
        String sql="";
        int num=0;
        String acnum = ""; //회계ID
        boolean result = false;  //정상가입확인 여부
        
        try {
            //회계ID 번호 구하기
            //con=ds.getConnection();
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
            result = true;
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return result;
    }
    
    //전체리스트
    public List ackindList(String kind){
        StringBuffer sql = new StringBuffer("select accounting.ac_id,accounting.mm_id," +
                "accounting.ac_price, accounting.ac_cc_type,accounting.ac_io_type," +
                "accounting.ac_date, accounting.ac_manager_name,accounting.ac_memo, " +
                "member.mm_name from accounting ");
        List acList = null;
        AccountingBean acBean = null;
        try {
            //con=ds.getConnection();
            if(kind.equals("fee")){
                sql.append("where ac_io_type = '수강료' ");
            }else if(kind.equals("in")){
                sql.append("where ac_io_type = '수입' ");
            }else if(kind.equals("out")){
                sql.append("where ac_io_type = '지출' ");
            }else if(kind.equals("list")){}
            
            sql.append("and member.mm_id = accounting.mm_id order by ac_id desc");
            
            pstmt=con.prepareStatement(sql.toString());
            rs=pstmt.executeQuery();
            if(rs.next()){
                acList = new ArrayList();
                do{
                    acBean = new AccountingBean();
                    acBean.setAc_id(rs.getString("ac_id"));
                    acBean.setMm_id(rs.getString("mm_id"));
                    acBean.setMm_name(rs.getString("mm_name"));
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
    
    //날짜검색 리스트
    public List acSearchList(Date date){
        String sql="";
        List searchlist = null;
        AccountingBean acBean = null;
        try {
            //con=ds.getConnection();
            sql="select ac_id,mm_id,ac_price,ac_cc_type,ac_io_type,ac_date,ac_manager_name,ac_memo from accounting " +
                    "where ac_date = ? order by ac_id desc";
            pstmt=con.prepareStatement(sql);
            pstmt.setDate(1, date);
            rs=pstmt.executeQuery();
            if(rs.next()){
                searchlist = new ArrayList();
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
                    
                    searchlist.add(acBean);
                }while(rs.next());
            }            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return searchlist;
    }
    
    
    public void acDeleteList(String[] check){
        StringBuffer sql = new StringBuffer("delete from accounting where ac_id in (");
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
//            con = ds.getConnection();
            pstmt = con.prepareStatement(sql.toString());
            pstmt.executeUpdate();
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
    }
    
    public int getAccountcount(String memberid){ // 해당아이뒤의 수강료를 낸 정보의 갯수를 구해온다.
        int accountcount =0;
        String  sql="";
        try {
            //con=ds.getConnection();
            sql="select count(*) from accounting where mm_id =? and ac_io_type='수강료'"; // 해당아이뒤의 수강료를 낸 정보의 갯수를 구해온다.
            pstmt=con.prepareStatement(sql  );
            pstmt.setString(1, memberid);
            rs= pstmt.executeQuery();
            if(rs.next()){
                accountcount = rs.getInt(1); // 조회한 값을 accountcount를 집어 넣는다.
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {closingDB();}
        
        return accountcount;
        
    }
    
    public List getAccountingStudent(String memberid,int page,int limit){
        List account = null; //가지고온 정보를 저장할 list를 가지고옴
        int startrow=(page-1)*limit+1; //현재페이지 시작행
        String sql="";
        
        try {
//            con = ds.getConnection();
            sql="select ac_price,ac_cc_type,ac_date,ac_memo from accounting where mm_id =? and ac_io_type ='수강료' order by ac_date desc limit ?,?";
            pstmt=con.prepareStatement(sql);
            pstmt.setString(1, memberid);
            pstmt.setInt(2, startrow-1);// 시작계수
            pstmt.setInt(3, limit);
            rs= pstmt.executeQuery();
            
            if(rs.next()){
                account = new ArrayList();
                do{
                    AccountingBean accounting = new AccountingBean();
                    accounting.setAc_cc_type(rs.getString("ac_cc_type"));
                    accounting.setAc_price(rs.getInt("ac_price"));
                    accounting.setAc_date(rs.getDate("ac_date"));
                    accounting.setAc_memo(rs.getString("ac_memo"));
                    
                    account.add(accounting);
                }while(rs.next());
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally {closingDB();}
        return account;
    }
    
    public List accountingIDsearch(){
        List accountingIDlList = null;
        AccountingBean acbean = null;
        String sql="";
        
        try {
            //con=ds.getConnection();
            sql = "SELECT mm_id, mm_name, mm_jumin1, mm_jumin2 FROM member where mm_id like 's%' or mm_id like 't%'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                accountingIDlList = new ArrayList();
                do{
                    acbean = new AccountingBean();
                    acbean.setMm_id(rs.getString("mm_id"));
                    acbean.setMm_name(rs.getString("mm_name"));
                    acbean.setMm_jumin1(rs.getInt("mm_jumin1"));
                    acbean.setMm_jumin2(rs.getInt("mm_jumin2"));
                    accountingIDlList.add(acbean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return accountingIDlList;
    }
    
    public List accountingOfficerSearch(){
        List accountingOfficerlList = null;
        AccountingBean acbean = null;
        String sql="";
        
        try {
            //con=ds.getConnection();
            sql = "SELECT mm_id, mm_name, mm_jumin1, mm_jumin2 FROM member where mm_id like 't%'";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if(rs.next()){
                accountingOfficerlList = new ArrayList();
                do{
                    acbean = new AccountingBean();
                    acbean.setMm_id(rs.getString("mm_id"));
                    acbean.setMm_name(rs.getString("mm_name"));
                    acbean.setMm_jumin1(rs.getInt("mm_jumin1"));
                    acbean.setMm_jumin2(rs.getInt("mm_jumin2"));
                    accountingOfficerlList.add(acbean);
                }while(rs.next());
            }
            
        } catch (Exception e) {e.printStackTrace();} finally {closingDB();}
        return accountingOfficerlList;
    }
}
