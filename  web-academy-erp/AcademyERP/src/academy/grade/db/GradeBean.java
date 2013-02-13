package academy.grade.db;

import java.sql.Date;

public class GradeBean {
    private String gr_id; // 과목 아이디
    private String gr_name; // 과목명
    private String gr_contents; // 시험내용
    private String mm_id; // 학생ID
    private int gr_score; // 과목 점수
    private Date gr_exam_date; // 시험 일자
    private String ep_id; // 담당 강사
    private String gr_where_test; // 시험 장소 분류(학교 or 학원)
    
    public String getGr_id() {
        return gr_id;
    }
    public void setGr_id(String gr_id) {
        this.gr_id = gr_id;
    }
    public String getGr_name() {
        return gr_name;
    }
    public void setGr_name(String gr_name) {
        this.gr_name = gr_name;
    }
    public String getGr_contents() {
        return gr_contents;
    }
    public void setGr_contents(String gr_contents) {
        this.gr_contents = gr_contents;
    }
    public String getMm_id() {
        return mm_id;
    }
    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }
    public int getGr_score() {
        return gr_score;
    }
    public void setGr_score(int gr_score) {
        this.gr_score = gr_score;
    }
    public Date getGr_exam_date() {
        return gr_exam_date;
    }
    public void setGr_exam_date(Date gr_exam_date) {
        this.gr_exam_date = gr_exam_date;
    }
    public String getEp_id() {
        return ep_id;
    }
    public void setEp_id(String ep_id) {
        this.ep_id = ep_id;
    }
    public String getGr_where_test() {
        return gr_where_test;
    }
    public void setGr_where_test(String gr_where_test) {
        this.gr_where_test = gr_where_test;
    }
    
}
