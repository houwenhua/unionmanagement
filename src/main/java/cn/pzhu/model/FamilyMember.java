package cn.pzhu.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 家庭成员信息
 * @author 逃离
 *
 */
@Entity
public class FamilyMember {

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 姓名
	 */
	@Column(length = 20,nullable=false)
	private String name;
	
	/**
	 * 与本人关系
	 */
	@Column(length = 20,nullable=false)
	private String relationship;
	
	/**
	 * 所在单位
	 */
	@Column(length = 50,nullable=false)
	private String unit;
	
	/**
	 * 职位
	 */
	@Column(length = 20,nullable=true)
	private String position;
	
	/**
	 * 政治面貌
	 */
	@Column(length = 20,nullable=false)
	private String policy;
	
	/**
	 * 用户id
	 */
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name="user_id")
	private SysUser user;

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

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getPolicy() {
		return policy;
	}

	public void setPolicy(String policy) {
		this.policy = policy;
	}

	public SysUser getUser() {
		return user;
	}

	public void setUser(SysUser user) {
		this.user = user;
	}

	public FamilyMember(Integer id, String name, String relationship,String policy, String unit, String position,
			SysUser user) {
		super();
		this.id = id;
		this.name = name;
		this.relationship = relationship;
		this.policy = policy;
		this.unit = unit;
		this.position = position;
		this.user = user;
	}

	public FamilyMember() {
		super();
	}
	
	
}
