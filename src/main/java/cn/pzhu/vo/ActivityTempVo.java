package cn.pzhu.vo;

public class ActivityTempVo {

	private Integer id;

	private String name;

	private String endTime;

	private String remark;
	
	private String state;
	
	private String username;

	private String grade;
	
	private String iname;

	public ActivityTempVo(Integer id, String name, String endTime, String remark, String state, String username,
			String grade, String iname) {
		super();
		this.id = id;
		this.name = name;
		this.endTime = endTime;
		this.remark = remark;
		this.state = state;
		this.username = username;
		this.grade = grade;
		this.iname = iname;
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

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getIname() {
		return iname;
	}

	public void setIname(String iname) {
		this.iname = iname;
	}

	
}
