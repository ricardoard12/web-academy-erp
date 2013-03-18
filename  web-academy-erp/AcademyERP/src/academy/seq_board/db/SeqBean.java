package academy.seq_board.db;

import java.sql.Date;

public class SeqBean {
	private int qna_num;
	private int seq_num;
    private	String	seq_title;
	private String seq_name;
	private String seq_content;
	private Date seq_date;
	private int seq_count;
	public int getQna_num() {
		return qna_num;
	}
	public void setQna_num(int qna_num) {
		this.qna_num = qna_num;
	}
	public int getSeq_num() {
		return seq_num;
	}
	public void setSeq_num(int seq_num) {
		this.seq_num = seq_num;
	}
	public String getSeq_title() {
		return seq_title;
	}
	public void setSeq_title(String seq_title) {
		this.seq_title = seq_title;
	}
	public String getSeq_name() {
		return seq_name;
	}
	public void setSeq_name(String seq_name) {
		this.seq_name = seq_name;
	}
	public String getSeq_content() {
		return seq_content;
	}
	public void setSeq_content(String seq_content) {
		this.seq_content = seq_content;
	}
	public Date getSeq_date() {
		return seq_date;
	}
	public void setSeq_date(Date seq_date) {
		this.seq_date = seq_date;
	}
	public int getSeq_count() {
		return seq_count;
	}
	public void setSeq_count(int seq_count) {
		this.seq_count = seq_count;
	}
	

}
