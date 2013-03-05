package academy.counsel.db;

import java.sql.Date;

public class CounselerBean {
			 private int idx; // 상담 글번호
			 private String mm_id; // 학생 아뒤
			 private String cc_subject;  // 상담제목
			 private String cc_content; // 상담 내용
			 private Date cc_date; // 상담 날짜
			 private String gp_name; // 상담 학생의 학급
			 private String ep_id; //  상담한 선생 
			public String getCc_subject() {
				return cc_subject;
			}
			public void setCc_subject(String cc_subject) {
				this.cc_subject = cc_subject;
			}
			public String getCc_content() {
				return cc_content;
			}
			public void setCc_content(String cc_content) {
				this.cc_content = cc_content;
			}
			public Date getCc_date() {
				return cc_date;
			}
			public void setCc_date(Date cc_date) {
				this.cc_date = cc_date;
			}
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

			public String getGp_name() {
				return gp_name;
			}
			public void setGp_name(String gp_name) {
				this.gp_name = gp_name;
			} 
	
	
}
