package academy.grade.db;

import java.sql.Date;

public class GradeBean {
    private String gr_code; // 과목 코드
    private String gr_subject; // 과목명
    private String gr_memo; // 시험내용
    private String mm_id; // 학생ID
    private int gr_score; // 과목 점수
    private Date gr_exam_date; // 시험 일자
    private String ep_id; // 담당 강사
    private String gr_place; // 시험 장소 분류(학교 or 학원)
    private String gr_period;  //학교시험종류
    private String gr_school_name; //학교
    
    private String mm_name; //학생 선생 검색시 이름값 가져오기
    private String mm_jumin1; //학생 선생 검색시 주민값 가져오기
    private String mm_jumin2; //학생 선생 검색시 주민값 가져오기
    
    
    public String getGr_school_name() {
        return gr_school_name;
    }
    public void setGr_school_name(String gr_school_name) {
        this.gr_school_name = gr_school_name;
    }
    public String getMm_name() {
        return mm_name;
    }
    public void setMm_name(String mm_name) {
        this.mm_name = mm_name;
    }
    public String getMm_jumin1() {
        return mm_jumin1;
    }
    public void setMm_jumin1(String mm_jumin1) {
        this.mm_jumin1 = mm_jumin1;
    }
    public String getMm_jumin2() {
        return mm_jumin2;
    }
    public void setMm_jumin2(String mm_jumin2) {
        this.mm_jumin2 = mm_jumin2;
    }
    
    /////////////////////////////////////////////////
    public String getGr_code() {
        return gr_code;
    }
    public void setGr_code(String gr_code) {
        this.gr_code = gr_code;
    }
    public String getGr_subject() {
        return gr_subject;
    }
    public void setGr_subject(String gr_subject) {
        this.gr_subject = gr_subject;
    }
    public String getGr_memo() {
        return gr_memo;
    }
    public void setGr_memo(String gr_memo) {
        this.gr_memo = gr_memo;
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
    public String getGr_place() {
        return gr_place;
    }
    public void setGr_place(String gr_place) {
        this.gr_place = gr_place;
    }
    public String getGr_period() {
        return gr_period;
    }
    public void setGr_period(String gr_period) {
        this.gr_period = gr_period;
    }
    
}
