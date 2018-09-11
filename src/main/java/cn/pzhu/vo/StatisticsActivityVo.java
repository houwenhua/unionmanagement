package cn.pzhu.vo;

public class StatisticsActivityVo {

    private Integer id;
	
	private String actionname;
	
	private String institute;
	
	private String statisticsname;
	
	private String number;

	public StatisticsActivityVo(Integer id, String actionname, String institute, String statisticsname, String number) {
		super();
		this.id = id;
		this.actionname = actionname;
		this.institute = institute;
		this.statisticsname = statisticsname;
		this.number = number;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getActionname() {
		return actionname;
	}

	public void setActionname(String actionname) {
		this.actionname = actionname;
	}

	public String getInstitute() {
		return institute;
	}

	public void setInstitute(String institute) {
		this.institute = institute;
	}

	public String getStatisticsname() {
		return statisticsname;
	}

	public void setStatisticsname(String statisticsname) {
		this.statisticsname = statisticsname;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
	
	
	
}
