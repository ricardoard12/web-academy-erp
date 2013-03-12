package academy.sms.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class SmsDAO {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	DataSource ds;

	public SmsDAO() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
			System.out.println("Sms DB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void closingDB() {
		if (con != null)
			try {
				con.close();
			} catch (Exception e) {
			}
		if (pstmt != null)
			try {
				pstmt.close();
			} catch (Exception e) {
			}
		if (rs != null)
			try {
				rs.close();
			} catch (Exception e) {
			}
		System.out.println("Sms DB Closed");
	}

	public void addSendResult(String id, String name, String receiverPhone,
			String senderPhone, String message, String resultCode,
			String autoSend) {
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

	// 전체 문자 리스트를 가져오겠다.
	public List getSmsList() {
		List list = null;
		try {
			con = ds.getConnection();
			String sql="select * from sms order by sms_idx desc";
			rs=con.prepareStatement(sql).executeQuery();
			list=new ArrayList();
			if(rs.next()){
				do{
					list.add(getSmsInfo());
				}while(rs.next());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closingDB();
		}
		return list;
	}

	private List getSmsInfo()throws Exception {
		List list =new ArrayList();
		list.add(rs.getInt("sms_idx"));//0
		list.add(rs.getString("receiver_id"));//1
		list.add(rs.getString("reveiver_name"));//2
		list.add(rs.getString("receiver_phone"));//3
		list.add(rs.getString("sender_phone"));//4
		list.add(rs.getString("message"));//5
		list.add(rs.getString("result_code"));//6
		list.add(rs.getString("auto_send"));//7
		list.add(rs.getString("send_time"));//8
		return list;
	}

}
