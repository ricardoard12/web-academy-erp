package academy.attitude.db;

import java.sql.Date;
import java.sql.Timestamp;

public class AttitudeBean {
	private String at_member_id;
	private String mm_name;
    private String at_report_state;
    private Timestamp at_come_time;
    private Timestamp at_leave_time;
    private String at_memo;
    private Date at_memo_date;
    private int at_idx;
    private String st_school_name;
    private String st_school_grade;
    
    public String getAt_member_id() {
		return at_member_id;
	}
	public void setAt_member_id(String at_member_id) {
		this.at_member_id = at_member_id;
	}
	public String getMm_name() {
		return mm_name;
	}
	public void setMm_name(String mm_name) {
		this.mm_name = mm_name;
	}
    public String getAt_report_state() {
        return at_report_state;
    }
    public void setAt_report_state(String at_report_state) {
        this.at_report_state = at_report_state;
    }
    public Timestamp getAt_come_time() {
        return at_come_time;
    }
    public void setAt_come_time(Timestamp at_come_time) {
        this.at_come_time = at_come_time;
    }
    public Timestamp getAt_leave_time() {
        return at_leave_time;
    }
    public void setAt_leave_time(Timestamp at_leave_time) {
        this.at_leave_time = at_leave_time;
    }
    public String getAt_memo() {
        return at_memo;
    }
    public void setAt_memo(String at_memo) {
        this.at_memo = at_memo;
    }
	public Date getAt_memo_date() {
		return at_memo_date;
	}
	public void setAt_memo_date(Date at_memo_date) {
		this.at_memo_date = at_memo_date;
	}
	public int getAt_idx() {
		return at_idx;
	}
	public void setAt_idx(int at_idx) {
		this.at_idx = at_idx;
	}
	public String getSt_school_name() {
		return st_school_name;
	}
	public void setSt_school_name(String st_school_name) {
		this.st_school_name = st_school_name;
	}
	public String getSt_school_grade() {
		return st_school_grade;
	}
	public void setSt_school_grade(String st_school_grade) {
		this.st_school_grade = st_school_grade;
	}
    
}
