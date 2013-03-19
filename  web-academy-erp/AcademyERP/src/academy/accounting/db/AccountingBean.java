package academy.accounting.db;

import java.sql.Date;

public class AccountingBean {
    private String ac_id; // 회계 ID
    private String mm_id; // 회원ID
    private int ac_price; // 금액
    private String ac_cc_type; // 결제유형(현금(cach)/카드(card))
    private String ac_io_type; // 항목유형(수강료/수입(imcome)/지출(outgoing))
    private Date ac_date; // 결제일
    private String ac_manager_name; // 담당자명
    private String ac_memo; // 메모
    private String mm_name;
    private int mm_jumin1;
    private int mm_jumin2;
    
    
    public int getMm_jumin1() {
        return mm_jumin1;
    }
    public void setMm_jumin1(int mm_jumin1) {
        this.mm_jumin1 = mm_jumin1;
    }
    public int getMm_jumin2() {
        return mm_jumin2;
    }
    public void setMm_jumin2(int mm_jumin2) {
        this.mm_jumin2 = mm_jumin2;
    }
    public String getMm_name() {
        return mm_name;
    }
    public void setMm_name(String mm_name) {
        this.mm_name = mm_name;
    }
    public String getAc_id() {
        return ac_id;
    }
    public void setAc_id(String ac_id) {
        this.ac_id = ac_id;
    }
    public String getMm_id() {
        return mm_id;
    }
    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }
    public int getAc_price() {
        return ac_price;
    }
    public void setAc_price(int ac_price) {
        this.ac_price = ac_price;
    }
    public String getAc_cc_type() {
        return ac_cc_type;
    }
    public void setAc_cc_type(String ac_cc_type) {
        this.ac_cc_type = ac_cc_type;
    }
    public String getAc_io_type() {
        return ac_io_type;
    }
    public void setAc_io_type(String ac_io_type) {
        this.ac_io_type = ac_io_type;
    }
    public Date getAc_date() {
        return ac_date;
    }
    public void setAc_date(Date ac_date) {
        this.ac_date = ac_date;
    }
    public String getAc_manager_name() {
        return ac_manager_name;
    }
    public void setAc_manager_name(String ac_manager_name) {
        this.ac_manager_name = ac_manager_name;
    }
    public String getAc_memo() {
        return ac_memo;
    }
    public void setAc_memo(String ac_memo) {
        this.ac_memo = ac_memo;
    }
   
}
