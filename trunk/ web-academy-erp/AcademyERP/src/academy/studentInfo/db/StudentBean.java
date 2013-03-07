package academy.studentInfo.db;

import java.sql.Date;

public class StudentBean {
	// 회원 공통 항목
    private String mm_name;  //'회원 이름',
    private String mm_id;  //'회원 아이디',
    private String mm_pw;  //'회원 패스워드',
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
    
    // 학생 단독 항목
    private String st_school_name; // 학교명
    private String st_school_grade; // 학년
    private String gp_name; // 소속 학급(학원)
    private String st_parent_name; // 학부모 이름
    private String st_parent_mobile; // 학부모 연락처
    private String st_parent_id; // 학부모 아이디
    private String st_parent_passwd; // 학부모 패스워드
    private int st_tuition; // 수강료
    private String st_tuition_state; // 회비 납부 여부
    private String st_memo; // 학생 메모
    private String st_status; // 재학 현황
    
    //
    private String ep_id;// 학급 담임

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

	public String getMm_pw() {
		return mm_pw;
	}

	public void setMm_pw(String mm_pw) {
		this.mm_pw = mm_pw;
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

	public String getMm_manager_id() {
		return mm_manager_id;
	}

	public void setMm_manager_id(String mm_manager_id) {
		this.mm_manager_id = mm_manager_id;
	}

	public String getSt_school_name() {
		return st_school_name;
	}

	public void setSt_school_name(String st_school_name) {
		this.st_school_name = st_school_name;
	}

	public String getSt_school_grade() {
		return st_school_grade;
	}

	public void setSt_school_grade(String st_school_grade) {
		this.st_school_grade = st_school_grade;
	}

	public String getGp_name() {
		return gp_name;
	}

	public void setGp_name(String gp_name) {
		this.gp_name = gp_name;
	}

	public String getSt_parent_name() {
		return st_parent_name;
	}

	public void setSt_parent_name(String st_parent_name) {
		this.st_parent_name = st_parent_name;
	}

	public String getSt_parent_mobile() {
		return st_parent_mobile;
	}

	public void setSt_parent_mobile(String st_parent_mobile) {
		this.st_parent_mobile = st_parent_mobile;
	}

	public String getSt_parent_id() {
		return st_parent_id;
	}

	public void setSt_parent_id(String st_parent_id) {
		this.st_parent_id = st_parent_id;
	}

	public String getSt_parent_passwd() {
		return st_parent_passwd;
	}

	public void setSt_parent_passwd(String st_parent_passwd) {
		this.st_parent_passwd = st_parent_passwd;
	}

	public int getSt_tuition() {
		return st_tuition;
	}

	public void setSt_tuition(int st_tuition) {
		this.st_tuition = st_tuition;
	}

	public String getSt_tuition_state() {
		return st_tuition_state;
	}

	public void setSt_tuition_state(String st_tuition_state) {
		this.st_tuition_state = st_tuition_state;
	}

	public String getSt_memo() {
		return st_memo;
	}

	public void setSt_memo(String st_memo) {
		this.st_memo = st_memo;
	}

	public String getSt_status() {
		return st_status;
	}

	public void setSt_status(String st_status) {
		this.st_status = st_status;
	}

	public String getEp_id() {
		return ep_id;
	}

	public void setEp_id(String ep_id) {
		this.ep_id = ep_id;
	}
    
    
    
    
}
