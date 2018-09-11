package cn.pzhu.vo;

public class ResumeVo {
	
	private Integer id;
	
	private String startdate;
	
	private String enddate;
	
	private String description;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStartdate() {
		return startdate;
	}

	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}

	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ResumeVo(String startdate, String enddate, String description) {
		super();
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
	}

	public ResumeVo() {
		super();
	}

	public ResumeVo(Integer id, String startdate, String enddate, String description) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
	}
	
	

}
