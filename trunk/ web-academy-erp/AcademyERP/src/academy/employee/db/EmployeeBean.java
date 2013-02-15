package academy.employee.db;

import java.sql.Date;

public class EmployeeBean {
    // 회원 공통 항목
    private String mm_name;  //'회원 이름',
    private String mm_id;  //'회원 아이디',
    private String mm_passwd;  //'회원 패스워드',
    private String mm_jumin1;  //'회원 주민번호 앞자리',
    private String mm_jumin2;  //'회원 주민번호 뒷자리',
    private String mm_tel;  //'회원 전화번호',
    private String mm_phone;  //'회원 핸드폰번호',
    private String mm_zipcode; // '회원 우편 번호',
    private String mm_addr1;  //'회원 주소',
    private String mm_addr2;  //'회원 상세 주소',  
    private String mm_email;  //'회원 이메일',
    private Date mm_reg_date;  //회원 등록일',
    private String mm_level;  //'등급',
    private String mm_manager_id;  //'매니저아이디',
    
    // 직원 단독 항목
    private String ep_id; // 직원ID
    private String ep_position; // 직급
    private String ep_department; // 부서
    private String ep_group_id; // 담당 학급
    private String ep_subject_name; // 담당 과목
    private String ep_bank_name; // 은행명
    private String ep_account_num; // 계좌 번호
    private String ep_account_name; // 예금주
    private int ep_salary; // 연봉
    private Date ep_in_date; // 입사일
    private Date ep_out_date; // 퇴사일
    private String ep_memo; // 퇴사 사유 등 메모
    
    public String getMm_name() {
        return mm_name;
    }
    public void setMm_name(String mm_name) {
        this.mm_name = mm_name;
    }
    public String getMm_id() {
        return mm_id;
    }
    public void setMm_id(String mm_id) {
        this.mm_id = mm_id;
    }
    public String getMm_passwd() {
        return mm_passwd;
    }
    public void setMm_passwd(String mm_passwd) {
        this.mm_passwd = mm_passwd;
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
    public String getMm_tel() {
        return mm_tel;
    }
    public void setMm_tel(String mm_tel) {
        this.mm_tel = mm_tel;
    }
    public String getMm_phone() {
        return mm_phone;
    }
    public void setMm_phone(String mm_phone) {
        this.mm_phone = mm_phone;
    }
    public String getMm_zipcode() {
        return mm_zipcode;
    }
    public void setMm_zipcode(String mm_zipcode) {
        this.mm_zipcode = mm_zipcode;
    }
    public String getMm_addr1() {
        return mm_addr1;
    }
    public void setMm_addr1(String mm_addr1) {
        this.mm_addr1 = mm_addr1;
    }
    public String getMm_addr2() {
        return mm_addr2;
    }
    public void setMm_addr2(String mm_addr2) {
        this.mm_addr2 = mm_addr2;
    }
    public String getMm_email() {
        return mm_email;
    }
    public void setMm_email(String mm_email) {
        this.mm_email = mm_email;
    }
    public Date getMm_reg_date() {
        return mm_reg_date;
    }
    public void setMm_reg_date(Date mm_reg_date) {
        this.mm_reg_date = mm_reg_date;
    }
    public String getMm_level() {
        return mm_level;
    }
    public void setMm_level(String mm_level) {
        this.mm_level = mm_level;
    }
    public void setMm_level(int mm_level) {
        this.mm_level = mm_level + "";
    }
    public String getMm_manager_id() {
        return mm_manager_id;
    }
    public void setMm_manager_id(String mm_manager_id) {
        this.mm_manager_id = mm_manager_id;
    }
    public String getEp_id() {
		return ep_id;
	}
	public void setEp_id(String ep_id) {
		this.ep_id = ep_id;
	}
	public String getEp_position() {
        return ep_position;
    }
    public void setEp_position(String ep_position) {
        this.ep_position = ep_position;
    }
    public String getEp_department() {
        return ep_department;
    }
    public void setEp_department(String ep_department) {
        this.ep_department = ep_department;
    }
    public String getEp_group_id() {
        return ep_group_id;
    }
    public void setEp_group_id(String ep_group_id) {
        this.ep_group_id = ep_group_id;
    }
    public String getEp_subject_name() {
        return ep_subject_name;
    }
    public void setEp_subject_name(String ep_subject_name) {
        this.ep_subject_name = ep_subject_name;
    }
    public String getEp_bank_name() {
		return ep_bank_name;
	}
	public void setEp_bank_name(String ep_bank_name) {
		this.ep_bank_name = ep_bank_name;
	}
	public String getEp_account_num() {
		return ep_account_num;
	}
	public void setEp_account_num(String ep_account_num) {
		this.ep_account_num = ep_account_num;
	}
	public String getEp_account_name() {
		return ep_account_name;
	}
	public void setEp_account_name(String ep_account_name) {
		this.ep_account_name = ep_account_name;
	}
	public int getEp_salary() {
        return ep_salary;
    }
    public void setEp_salary(int ep_salary) {
        this.ep_salary = ep_salary;
    }
	public Date getEp_in_date() {
		return ep_in_date;
	}
	public void setEp_in_date(Date ep_in_date) {
		this.ep_in_date = ep_in_date;
	}
	public Date getEp_out_date() {
		return ep_out_date;
	}
	public void setEp_out_date(Date ep_out_date) {
		this.ep_out_date = ep_out_date;
	}
	public String getEp_memo() {
		return ep_memo;
	}
	public void setEp_memo(String ep_memo) {
		this.ep_memo = ep_memo;
	}
    
}
