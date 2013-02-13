package academy.attitude.db;

import java.sql.Date;

public class AttitudeBean {
	private String at_member_id;
    private String at_report_state;
    private Date at_open_time;
    private Date at_close_time;
    private String at_memo;
    
    public String getAt_member_id() {
		return at_member_id;
	}
	public void setAt_member_id(String at_member_id) {
		this.at_member_id = at_member_id;
	}
    public String getAt_report_state() {
        return at_report_state;
    }
    public void setAt_report_state(String at_report_state) {
        this.at_report_state = at_report_state;
    }
    public Date getAt_open_time() {
        return at_open_time;
    }
    public void setAt_open_time(Date at_open_time) {
        this.at_open_time = at_open_time;
    }
    public Date getAt_close_time() {
        return at_close_time;
    }
    public void setAt_close_time(Date at_close_time) {
        this.at_close_time = at_close_time;
    }
    public String getAt_memo() {
        return at_memo;
    }
    public void setAt_memo(String at_memo) {
        this.at_memo = at_memo;
    }
    
}
