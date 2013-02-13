package academy.timetable.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class TimetableDAO {
    Connection con=null;
    PreparedStatement pstmt=null;
    ResultSet rs=null;
    DataSource ds;
    public TimetableDAO() {
        try {
            Context init=new InitialContext();
            ds=(DataSource)init.lookup("java:comp/env/jdbc/academy");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
