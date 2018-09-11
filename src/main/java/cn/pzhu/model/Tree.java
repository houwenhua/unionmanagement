package cn.pzhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * 菜单
 * @author 逃离
 *
 */
@Entity
@Table(name="t_nav")
public class Tree {

	/**
	 * 编号
	 */
	@Id
	@Column(nullable=true)
	private Integer id;
	
	/**
	 * 节点名
	 */
	@Column(length = 20,nullable=true)
	private String text;

	/**
	 * 状态
	 */
	@Column(length = 10,nullable=true)
	private String state;
	
	/**
	 * 图标
	 */
	@Column(length = 20,nullable=true)
	private String iconCls;
	
	/**
	 * 页面路径
	 */
	@Column(length = 50,nullable=true)
	private String url;
	
	/**
	 * 上级节点编号
	 */
	@Column(nullable=true)
	private Integer tid;
	
	/**
	 * 权限
	 */
	@ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "Tree_Jurisdiction", joinColumns = {
            @JoinColumn(name = "Tree_id", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "Jurisdiction_id", referencedColumnName = "ID")})
	private Set<Jurisdiction> jurisdictions = new HashSet<>();
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getIconCls() {
		return iconCls;
	}
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	public Integer getTid() {
		return tid;
	}
	public void setTid(Integer tid) {
		this.tid = tid;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Set<Jurisdiction> getJurisdictions() {
		return jurisdictions;
	}
	public void setJurisdictions(Set<Jurisdiction> jurisdictions) {
		this.jurisdictions = jurisdictions;
	}
	
}
