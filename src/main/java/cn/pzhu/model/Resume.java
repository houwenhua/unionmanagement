package cn.pzhu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Resume {

	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 开始时间
	 */
	@Column(length = 20,nullable=false)
	private String startdate;
	
	/**
	 * 结束时间
	 */
	@Column(length = 20,nullable=false)
	private String enddate;
	
	/**
	 * 在职描述
	 */
	@Column(nullable=true, columnDefinition="TEXT")
	private String description;
	
	/**
	 * 用户id
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH}, optional = true)
	@JoinColumn(name="user_id")
	private SysUser user;
	
	

	public Resume() {
		super();
	}

	public Resume(Integer id, String startdate, String enddate, String description, SysUser user) {
		super();
		this.id = id;
		this.startdate = startdate;
		this.enddate = enddate;
		this.description = description;
		this.user = user;
	}

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

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}
	
	
	
}
