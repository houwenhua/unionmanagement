package cn.pzhu.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "xt_user")
public class SysUser {

	/**
	 * 用户编号
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 姓名
	 */
	@Column(length = 50,nullable=true)
	private String name;
	
	/**
	 * 用户名
	 */
	@Column(length = 50,nullable=true)
	private String loginname;
	
	/**
	 * 性别
	 */
	@Column(length = 1,nullable = true)
	private String sex;
	
	/**
	 * 出生年月日
	 */
	@Column(length = 10,nullable = true)
	private String birthday;
	
	/**
	 * 政治面貌
	 */
	@Column(length = 20,nullable = true)
	private String policy;
	
	/**
	 * 职称
	 */
	@Column(length = 20,nullable = true)
	private String position;
	
	/**
	 * 密码
	 */
	@Column(length = 50,nullable=true)
	private String password;
	
	/**
	 * 邮箱
	 */
	@Column(length = 20,nullable=true)
	private String email;
	
	/**
	 * 用户手机号
	 */
	@Column(length = 20,nullable=true)
	private String phone;
	
	/**
	 * 状态
	 */
	@Column(length = 1,nullable=true)
	private String state;
	
	/**
	 * 家庭地址
	 */
	@Column(length = 50,nullable=true)
	private String address;

	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "sysUser")
	private Set<Notice> notices = new HashSet<>();
	
	/**
	 * 权限id
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="jurisdiction_id")
	private Jurisdiction jurisdiction;
	
	/**
	 * 与简历一对多关系
	 */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Resume> resumes = new HashSet<>();
	
	/**
	 * 与家庭成员一对多关系
	 */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<FamilyMember> familyMembers = new HashSet<>();
	
	/**
	 *与活动一对多关系
	 */
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<Activity> Activitys = new HashSet<>();
	
	/**
	 * 所属学院 多对一关系
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="Institute_id")
	private Institute institute;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "user")
	private Set<SignUpActivity> signups = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginname() {
		return loginname;
	}

	public void setLoginname(String loginname) {
		this.loginname = loginname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Set<Notice> getNotices() {
		return notices;
	}

	public void setNotices(Set<Notice> notices) {
		this.notices = notices;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Jurisdiction getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(Jurisdiction jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public Set<Resume> getResumes() {
		return resumes;
	}

	public void setResumes(Set<Resume> resumes) {
		this.resumes = resumes;
	}

	public Set<FamilyMember> getFamilyMembers() {
		return familyMembers;
	}

	public void setFamilyMembers(Set<FamilyMember> familyMembers) {
		this.familyMembers = familyMembers;
	}

	public Institute getInstitute() {
		return institute;
	}

	public void setInstitute(Institute institute) {
		this.institute = institute;
	}

	public Set<Activity> getActivitys() {
		return Activitys;
	}

	public void setActivitys(Set<Activity> activitys) {
		Activitys = activitys;
	}

	public Set<SignUpActivity> getSignups() {
		return signups;
	}

	public void setSignups(Set<SignUpActivity> signups) {
		this.signups = signups;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public SysUser(String name, String loginname, String password, String email, String phone,
			Jurisdiction jurisdiction,Institute institute,String address) {
		super();
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.jurisdiction = jurisdiction;
		this.institute = institute;
		this.address = address;
	}

	public SysUser(String name, String loginname, String sex, String birthday, String policy, String password,
			String email, String phone, String address, Jurisdiction jurisdiction, Institute institute) {
		super();
		this.name = name;
		this.loginname = loginname;
		this.sex = sex;
		this.birthday = birthday;
		this.policy = policy;
		this.password = password;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.jurisdiction = jurisdiction;
		this.institute = institute;
	}

	public SysUser() {
		super();
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
	
	
}
