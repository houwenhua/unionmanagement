package cn.pzhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 工会活动
 * 
 * @author 逃离
 *
 */
@Entity
@Table(name = "activity")
public class Activity {

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue
	private Integer id;

	/**
	 * 用户id
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "user_id")
	private SysUser user;
	
	/**
	 * 名称
	 */
	@Column(length = 50, nullable = false)
	private String name;

	/**
	 * 报名截止时间
	 */
	@Column(length = 20, nullable = false)
	private String endTime;
	
	/**
	 * 是否截止
	 */
	@Column(length = 1,nullable = false)
	private String state;

	/**
	 * 备注
	 */
	@Column(nullable = true, columnDefinition = "TEXT")
	private String remark;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "activity")
	private Set<SignUpActivity> signups = new HashSet<>();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
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
	

	public Set<SignUpActivity> getSignups() {
		return signups;
	}

	public void setSignups(Set<SignUpActivity> signups) {
		this.signups = signups;
	}

	public Activity(Integer id, SysUser user, String name, String endTime, String state, String remark) {
		super();
		this.id = id;
		this.user = user;
		this.name = name;
		this.endTime = endTime;
		this.state = state;
		this.remark = remark;
	}

	public Activity() {
		super();
	}
}
