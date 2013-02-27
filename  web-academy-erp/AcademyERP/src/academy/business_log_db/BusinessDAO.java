package academy.business_log_db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BusinessDAO {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;
	
	public BusinessDAO() { //생성자
		//디비연결 이름호출
		try {
			Context init=new InitialContext();
			ds=(DataSource)init.lookup("java:comp/env/jdbc/aca");
			System.out.println("BoardDB Connected");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//생성자	

}
