package cn.pzhu.vo;

public class FamilyMemberVo {
	private Integer id;
	
	/**
	 * 姓名
	 */
	private String name;
	
	/**
	 * 与本人关系
	 */
	private String relationship;
	/**
	 * 政治面貌
	 */
	private String policy;
	
	/**
	 * 所在单位
	 */
	private String unit;
	
	/**
	 * 职位
	 */
	private String position;
	
	public FamilyMemberVo(Integer id, String name, String relationship,String policy ,String unit, String position) {
		super();
		this.id = id;
		this.name = name;
		this.relationship = relationship;
		this.policy = policy;
		this.unit = unit;
		this.position = position;

	}

	public FamilyMemberVo() {
		super();
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
	
	
}
