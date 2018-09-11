package cn.pzhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 学院信息
 * @author 逃离
 *
 */
@Entity
@Table(name = "t_institute")
public class Institute {

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	/**
	 * 名称
	 */
	@Column(length=50,nullable = false)
	private String name;
	
	/**
	 * 描述
	 */
	@Column(length=255,nullable = true)
	private String description;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "institute")
	private Set<SysUser> SysUser = new HashSet<>();

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SysUser> getSysUser() {
		return SysUser;
	}

	public void setSysUser(Set<SysUser> sysUser) {
		SysUser = sysUser;
	}
}
