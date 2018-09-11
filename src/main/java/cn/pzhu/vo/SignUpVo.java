package cn.pzhu.vo;

public class SignUpVo {

	private Integer id;
	
	private String name;
	
	private String signupdate;
	
	private String username;
	
	private String checkactivity;
	
	

	public SignUpVo() {
		super();
	}

	public SignUpVo(Integer id, String name, String signupdate, String username, String checkactivity) {
		super();
		this.id = id;
		this.name = name;
		this.signupdate = signupdate;
		this.username = username;
		this.checkactivity = checkactivity;
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

	public String getSignupdate() {
		return signupdate;
	}

	public void setSignupdate(String signupdate) {
		this.signupdate = signupdate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCheckactivity() {
		return checkactivity;
	}

	public void setCheckactivity(String checkactivity) {
		this.checkactivity = checkactivity;
	}
	
	
}
