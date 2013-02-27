package academy.groups.db;

import java.sql.Date;

public class GroupsBean {
	private int gp_idx; // 인덱스
	private String gp_room; // 강의실 번호
	private String gp_name; // 학급명
	private String ep_id; // 담당강사 ID
	private String gp_lev; // 학년
	private String gp_half; // 학기
	private int gp_ea; // 총 인원
	private String gp_status; // 개설 여부
	private String gp_startdate; // 시작일
	private String gp_enddate; // 종료일
	
	public int getGp_idx() {
		return gp_idx;
	}
	public void setGp_idx(int gp_idx) {
		this.gp_idx = gp_idx;
	}
	public String getGp_room() {
		return gp_room;
	}
	public void setGp_room(String gp_room) {
		this.gp_room = gp_room;
	}
	public String getGp_name() {
		return gp_name;
	}
	public void setGp_name(String gp_name) {
		this.gp_name = gp_name;
	}
	public String getEp_id() {
		return ep_id;
	}
	public void setEp_id(String ep_id) {
		this.ep_id = ep_id;
	}
	public String getGp_lev() {
		return gp_lev;
	}
	public void setGp_lev(String gp_lev) {
		this.gp_lev = gp_lev;
	}
	public String getGp_half() {
		return gp_half;
	}
	public void setGp_half(String gp_half) {
		this.gp_half = gp_half;
	}
	public int getGp_ea() {
		return gp_ea;
	}
	public void setGp_ea(int gp_ea) {
		this.gp_ea = gp_ea;
	}
	public String getGp_status() {
		return gp_status;
	}
	public void setGp_status(String gp_status) {
		this.gp_status = gp_status;
	}
	public String getGp_startdate() {
		return gp_startdate;
	}
	public void setGp_startdate(String gp_startdate) {
		this.gp_startdate = gp_startdate;
	}
	public String getGp_enddate() {
		return gp_enddate;
	}
	public void setGp_enddate(String gp_enddate) {
		this.gp_enddate = gp_enddate;
	}
	
}
