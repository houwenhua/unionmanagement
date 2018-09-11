package cn.pzhu.vo;

public class NoticeTempVo {

	 private Integer id;
	 
	 private String title;
	 
	 private String date;
	 
	 private String type;
	 
	 private String content;
	 
	 private Integer userid;
	 
	 private String grade;
	 
	 private String name;
	 
	 private String username;

	public NoticeTempVo(Integer id, String title, String date, String type, String content, Integer userid,
			String grade, String name, String username) {
		super();
		this.id = id;
		this.title = title;
		this.date = date;
		this.type = type;
		this.content = content;
		this.userid = userid;
		this.grade = grade;
		this.name = name;
		this.username = username;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	 
	 
}
