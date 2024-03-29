package academy.member.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class MemberDAO {
    Connection con = null;
    DataSource ds = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    
    public MemberDAO() {
        try {
        	Class.forName("com.mysql.jdbc.Driver");
        	String URL = "jdbc:mysql://localhost:3306/p4_learntime_kr?useUnicode=true&amp; characterEncoding=utf8";
        	con = DriverManager.getConnection(URL , "p4.learntime" , "0909");
//            Context init = new InitialContext();
//            ds = (DataSource) init.lookup("java:comp/env/jdbc/p4_learntime_kr");
            System.out.println("Master DB Connected");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Master DB Connecting failed");
        }
    }

    private void dbClose() {
        if (rs != null) try {rs.close();} catch (SQLException e) {e.printStackTrace();}
        if (pstmt != null) try {pstmt.close();} catch (SQLException e) {e.printStackTrace();}
        if (con != null) try {con.close();} catch (SQLException e) {e.printStackTrace();}
        System.out.println("Master DB Closed");
    }
    
    public Vector isMember(MemberBean member){
        // 확인되면 이름을 가져와야 되어서 Vector 사용
        // 0번째는 인증 번호 1번째는 사용자 이름
        int x = -1;
        String sql="";
        Vector vector = new Vector();
        
        try {
//            con = ds.getConnection();
            sql = "select mm_passwd, mm_name, mm_level from member where mm_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMm_id());
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                if(rs.getString("mm_passwd").equals(member.getMm_passwd())){
                    //비밀번호 맞음
                    x=1;
                    vector.add(0, x);
                    
                    //이름 받기
                    String name = rs.getString("mm_name");
                    vector.add(1, name);
                    
                    //레벨값 받기
                    String level = rs.getString("mm_level");
                    vector.add(2, level);
                    
                    
                }else{
                    //비밀번호 틀림
                    x=0;
                    vector.add(0, x);
                }
            }else{
                //아이디없음
                x = -1;
                vector.add(0, x);
            }
            
        } catch (SQLException e) {  
            e.printStackTrace();
        }finally{   dbClose(); }
        
        return vector;
    }
    
    public List searchZipcode(String searchDong) throws Exception { // 우편번호 찾기
		List zipcodeList = new ArrayList();
		try {
//			con = ds.getConnection();
			String sql = "SELECT * FROM zipcode WHERE dong LIKE ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "%"+searchDong+"%");
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				String zipcode = rs.getString("zipcode");
				String sido = rs.getString("sido");
				String gugun = rs.getString("gugun");
				String dong = rs.getString("dong");
				String bunji = rs.getString("bunji");
				if (bunji == null) bunji = "";
				String addr = sido + " " + gugun + " " + dong + " " + bunji;
				zipcodeList.add(zipcode + "," + addr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
		
		return zipcodeList;
	}
    
    // ID 자동 부여를 위한 오늘 날짜에 가입한 아이디 확인 및 새 ID 부여
    public String getNewEmployeeID(String searchID) throws Exception {
    	String id = "";
    	try {
//    		con = ds.getConnection();
    		String sql = "SELECT * FROM member WHERE mm_id LIKE '" + searchID + "%' ORDER BY mm_id DESC LIMIT 0,1";
    		pstmt = con.prepareStatement(sql);
    		rs = pstmt.executeQuery();
    		
    		if (rs.next()) {
    			int idNum = Integer.parseInt(rs.getString("mm_id").substring(7));
    			if (idNum < 10) {
    				id = searchID + "0" + (idNum + 1);
    			} else {
    				id = searchID + (idNum + 1);
    			}
    		} else {
    			id = searchID + "01";
    		}
    		
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbClose();
		}
    	
		return id;
    }
    
    /*public List getMemberList(){
        List memberList=null;
        String sql;
        
        try {
            con = ds.getConnection();
            sql = "select * from member2";
            pstmt = con.prepareStatement(sql);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                memberList = new ArrayList();
                do{
                    MemberBean db = new MemberBean();
                    db.setMember_id(rs.getString("member_id"));
                    db.setMember_pw(rs.getString("member_pw"));
                    db.setMember_name(rs.getString("member_name"));
                    db.setMember_age(rs.getInt("member_age"));
                    db.setMember_email(rs.getString("member_email"));
                    db.setMember_gender(rs.getString("member_gender"));
                    
                    memberList.add(db);
                }while(rs.next());
                
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(rs != null){try {rs.close();} catch (SQLException e) {}}
            if(pstmt != null){try {pstmt.close();} catch (SQLException e) {}}
            if(con != null){try {con.close();} catch (SQLException e) {}}
        }
        
        return memberList;
    }
    
    public MemberBean getDetailMember(String viewid){

        MemberBean member = new MemberBean();
        String sql = "";
        
        try {
            con = ds.getConnection();
            sql = "select * from member2 where member_id = ?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, viewid);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                member.setMember_id(rs.getString("member_id"));
                member.setMember_pw(rs.getString("member_pw"));
                member.setMember_name(rs.getString("member_name"));
                member.setMember_age(rs.getInt("member_age"));
                member.setMember_gender(rs.getString("member_gender"));
                member.setMember_email(rs.getString("member_email"));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            if(rs != null){try {rs.close();} catch (SQLException e) {}}
            if(pstmt != null){try {pstmt.close();} catch (SQLException e) {}}
            if(con != null){try {con.close();} catch (SQLException e) {}}
        }
        
        return member;
    }
    
    public boolean deleteMember(String deleteid){
        boolean x = false;
        String sql="";
        
        try {
            con = ds.getConnection();
            sql = "delete from member2 where member_id=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, deleteid);
            pstmt.executeUpdate();
            x = true;
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            if(pstmt != null){try {pstmt.close();} catch (SQLException e) {}}
            if(con != null){try {con.close();} catch (SQLException e) {}}
        }
        
        
        return x;
        
    }*/
}
