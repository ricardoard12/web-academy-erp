package academy.qna_board.db;

import java.sql.Date;

public class QnaBean {
	private int qna_num;
    private	String	qna_title;
	private String qna_subject;
	private String qna_content;
	private Date qna_data;
	private int qna_recont;
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public String getQna_title() {
		return qna_title;
	}
	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}
	public String getQna_subject() {
		return qna_subject;
	}
	public void setQna_subject(String qna_subject) {
		this.qna_subject = qna_subject;
	}
	public String getQna_content() {
		return qna_content;
	}
	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}
	public Date getQna_data() {
		return qna_data;
	}
	public void setQna_data(Date qna_data) {
		this.qna_data = qna_data;
	}
	public int getQna_recont() {
		return qna_recont;
	}
	public void setQna_recont(int qna_recont) {
		this.qna_recont = qna_recont;
	}
	
	
}
