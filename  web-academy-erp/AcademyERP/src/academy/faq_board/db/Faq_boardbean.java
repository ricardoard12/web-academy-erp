package academy.faq_board.db;

import java.sql.Date;

public class Faq_boardbean {

	private int faq_num;
	private String faq_name;
	private String faq_subject;
	private String faq_content;
	private String faq_passwd;
	private Date faq_date;
	
	
	public String getFaq_passwd() {
		return faq_passwd;
	}
	public void setFaq_passwd(String faq_passwd) {
		this.faq_passwd = faq_passwd;
	}
	public int getFaq_num() {
		return faq_num;
	}
	public void setFaq_num(int faq_num) {
		this.faq_num = faq_num;
	}
	public String getFaq_name() {
		return faq_name;
	}
	public void setFaq_name(String faq_name) {
		this.faq_name = faq_name;
	}
	public String getFaq_subject() {
		return faq_subject;
	}
	public void setFaq_subject(String faq_subject) {
		this.faq_subject = faq_subject;
	}
	public String getFaq_content() {
		return faq_content;
	}
	public void setFaq_content(String faq_content) {
		this.faq_content = faq_content;
	}
	public Date getFaq_date() {
		return faq_date;
	}
	public void setFaq_date(Date faq_date) {
		this.faq_date = faq_date;
	}
	
	
}
