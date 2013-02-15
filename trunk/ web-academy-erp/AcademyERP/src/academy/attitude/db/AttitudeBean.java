package academy.attitude.db;

import java.sql.Date;

public class AttitudeBean {
	private String at_member_id;
	private String mm_name;
    private String at_report_state;
    private Date at_come_time;
    private Date at_leave_time;
    private String at_memo;
    private Date at_memo_date;
    
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
    public Date getAt_come_time() {
        return at_come_time;
    }
    public void setAt_come_time(Date at_come_time) {
        this.at_come_time = at_come_time;
    }
    public Date getAt_leave_time() {
        return at_leave_time;
    }
    public void setAt_leave_time(Date at_leave_time) {
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
    
}
