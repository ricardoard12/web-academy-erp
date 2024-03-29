package academy.business_log.db;

import java.sql.Date;

public class BusinessBean {

	private int business_num;
	private String business_name;
	private String business_subject;
	private String business_today;
	private String business_counsel;
	private String business_etc;
	private Date business_date;
	
	
	public String getBusiness_name() {
		return business_name;
	}
	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}
	public String getBusiness_subject() {
		return business_subject;
	}
	public void setBusiness_subject(String business_subject) {
		this.business_subject = business_subject;
	}
	public Date getBusiness_date() {
		return business_date;
	}
	public void setBusiness_date(Date business_date) {
		this.business_date = business_date;
	}
	public int getBusiness_num() {
		return business_num;
	}
	public void setBusiness_num(int business_num) {
		this.business_num = business_num;
	}
	public String getBusiness_today() {
		return business_today;
	}
	public void setBusiness_today(String business_today) {
		this.business_today = business_today;
	}
	public String getBusiness_counsel() {
		return business_counsel;
	}
	public void setBusiness_counsel(String business_counsel) {
		this.business_counsel = business_counsel;
	}
	public String getBusiness_etc() {
		return business_etc;
	}
	public void setBusiness_etc(String business_etc) {
		this.business_etc = business_etc;
	}
	
	
}
