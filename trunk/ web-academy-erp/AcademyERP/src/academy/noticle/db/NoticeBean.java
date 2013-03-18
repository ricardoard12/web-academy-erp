package academy.noticle.db;

import java.sql.Date;

public class NoticeBean {
	private int not_num;
    private	String	not_title;
	private String not_subject;
	private String not_content;
	private Date not_data;
	private int not_recont;
	
	public int getNot_num() {
		return not_num;
	}
	public void setNot_num(int not_num) {
		this.not_num = not_num;
	}
	public String getNot_title() {
		return not_title;
	}
	public void setNot_title(String not_title) {
		this.not_title = not_title;
	}
	public String getNot_subject() {
		return not_subject;
	}
	public void setNot_subject(String not_subject) {
		this.not_subject = not_subject;
	}
	public String getNot_content() {
		return not_content;
	}
	public void setNot_content(String not_content) {
		this.not_content = not_content;
	}
	public Date getNot_data() {
		return not_data;
	}
	public void setNot_data(Date not_data) {
		this.not_data = not_data;
	}
	public int getNot_recont() {
		return not_recont;
	}
	public void setNot_recont(int not_recont) {
		this.not_recont = not_recont;
	}
}
