package academy.counsel.db;

import java.sql.Date;

public class CounselerBean {
			 private int idx; // 상담 글번호
			 private String mm_id; // 학생 아뒤
			 private String co_subject;  // 상담제목
			 private String co_content; // 상담 내용
			 private Date co_date; // 상담 날짜
			 private String gp_id; // 상담 학생의 학급
			 private String ep_id; //  상담한 선생 
			public String getEp_id() {
				return ep_id;
			}
			public void setEp_id(String ep_id) {
				this.ep_id = ep_id;
			}
			public int getIdx() {
				return idx;
			}
			public void setIdx(int idx) {
				this.idx = idx;
			}
			public String getMm_id() {
				return mm_id;
			}
			public void setMm_id(String mm_id) {
				this.mm_id = mm_id;
			}
			public String getCo_subject() {
				return co_subject;
			}
			public void setCo_subject(String co_subject) {
				this.co_subject = co_subject;
			}
			public String getCo_content() {
				return co_content;
			}
			public void setCo_content(String co_content) {
				this.co_content = co_content;
			}
			public Date getCo_date() {
				return co_date;
			}
			public void setCo_date(Date co_date) {
				this.co_date = co_date;
			}
			public String getGp_id() {
				return gp_id;
			}
			public void setGp_id(String gp_id) {
				this.gp_id = gp_id;
			} 
	
	
}
