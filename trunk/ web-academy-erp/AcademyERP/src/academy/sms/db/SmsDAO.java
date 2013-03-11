package academy.sms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SmsDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public SmsDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/p4_learntime_kr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void closingDB() {
		if (con != null) try {con.close();} catch (Exception e) {}
		if (pstmt != null) try {pstmt.close();} catch (Exception e) {}
		if (rs != null) try {rs.close();} catch (Exception e) {}
	}
    
	public void addSendResult(String id, String name, String receiverPhone, String senderPhone, String message, String resultCode, String autoSend) {
    	try {
    		con = ds.getConnection();
    		String sql = "INSERT INTO sms (receiver_id,receiver_name,receiver_phone,sender_phone,message,result_code,auto_send) VALUES (?,?,?,?,?,?,?)";
    		pstmt = con.prepareStatement(sql);
    		pstmt.setString(1, id);
    		pstmt.setString(2, name);
    		pstmt.setString(3, receiverPhone);
    		pstmt.setString(4, senderPhone);
    		pstmt.setString(5, message);
    		pstmt.setString(6, resultCode);
    		pstmt.setString(7, autoSend);
    		pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	closingDB();
        }
    }
    
}
