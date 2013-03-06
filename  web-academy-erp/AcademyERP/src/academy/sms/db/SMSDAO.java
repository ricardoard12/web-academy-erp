package academy.sms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SMSDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds = null;
	String sql = "";

	public SMSDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/aca");
			System.out.println("sms DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void closingDB() {
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {	}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {	}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {	}
		System.out.println("sms DB Closed");
	}
	
	public void addSMSMessage(String receiverName, String receiverPhone, String senderPhone, String message) throws Exception {
		try {
			con = ds.getConnection();
			sql = "INSERT INTO sms (receiver_name, receiver_phone, sender_phone, message) VALUES (?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, receiverName);
			pstmt.setString(2, receiverPhone);
			pstmt.setString(3, senderPhone);
			pstmt.setString(4, message);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}

	}
}
