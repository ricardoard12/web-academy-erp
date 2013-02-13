package academy.attitude.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class AttitudeDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public AttitudeDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/academy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
