package academy.timetable.db;

import java.sql.Date;

public class TimetableBean {
    private String ep_id;// 강사ID
    private String mm_name;// 강사명
    private String gp_id;// 학급ID
    private String gp_name;// 학급명
    private Date ti_day;// 요일
    private int ti_lesson;// 시간(교시)
    
    public String getEp_id() {
        return ep_id;
    }
    public void setEp_id(String ep_id) {
        this.ep_id = ep_id;
    }
    public String getMm_name() {
        return mm_name;
    }
    public void setMm_name(String mm_name) {
        this.mm_name = mm_name;
    }
    public String getGp_id() {
        return gp_id;
    }
    public void setGp_id(String gp_id) {
        this.gp_id = gp_id;
    }
    public String getGp_name() {
        return gp_name;
    }
    public void setGp_name(String gp_name) {
        this.gp_name = gp_name;
    }
    public Date getTi_day() {
        return ti_day;
    }
    public void setTi_day(Date ti_day) {
        this.ti_day = ti_day;
    }
    public int getTi_lesson() {
        return ti_lesson;
    }
    public void setTi_lesson(int ti_lesson) {
        this.ti_lesson = ti_lesson;
    }
    
}
