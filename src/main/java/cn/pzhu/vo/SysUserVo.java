package cn.pzhu.vo;

public class SysUserVo {

	
	private Integer id;

	private String name;
	
	private String loginname;
	
	private String password;
	
	private String email;

	private String jurisdiction;
	
	private String state;
	
	private String phone;
	
	private String institute;
	
	private String address;
	
	private String sex;
	
	private String birthday;
	
	private String policy;
	
	private String position;

	
	public SysUserVo() {
		super();
	}

	public SysUserVo(Integer id, String name, String loginname, String password, String email, String jurisdiction,
			String state,String phone,String institute) {
		super();
		this.id = id;
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.jurisdiction = jurisdiction;
		this.state = state;
		this.phone = phone;
		this.institute = institute;
	}
	
	

	public SysUserVo(Integer id, String name, String loginname, String password, String email, String jurisdiction,
			String state, String phone, String institute, String address) {
		super();
		this.id = id;
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.jurisdiction = jurisdiction;
		this.state = state;
		this.phone = phone;
		this.institute = institute;
		this.address = address;
	}

	public SysUserVo(Integer id, String name, String loginname, String password, String email, String jurisdiction,
			String state, String phone, String institute, String address, String sex, String birthday, String policy,
			String position) {
		super();
		this.id = id;
		this.name = name;
		this.loginname = loginname;
		this.password = password;
		this.email = email;
		this.jurisdiction = jurisdiction;
		this.state = state;
		this.phone = phone;
		this.institute = institute;
		this.address = address;
		this.sex = sex;
		this.birthday = birthday;
		this.policy = policy;
		this.position = position;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
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

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}
	

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}
	
}
