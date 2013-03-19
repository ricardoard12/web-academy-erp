package academy.root.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class RootDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public RootDAO() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
        	con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
//            Context init = new InitialContext();
//            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
