package cn.pzhu.vo;

public class ActivityVo {
	
	private Integer id;
	
	private String name;
	
	private String endTime;
	
	private String remark;
	
	private String uploador;
	
	private String state;

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

	public String getUploador() {
		return uploador;
	}

	public void setUploador(String uploador) {
		this.uploador = uploador;
	}	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public ActivityVo(Integer id, String name, String endTime, String remark, String uploador, String state) {
		super();
		this.id = id;
		this.name = name;
		this.endTime = endTime;
		this.remark = remark;
		this.uploador = uploador;
		this.state = state;
	}

	public ActivityVo() {
		super();
	}

}
