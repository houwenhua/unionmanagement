package cn.pzhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

/**
 * 进行权限管理
 */
@Entity
@Table(name = "T_Jurisdiction")
public class Jurisdiction {

    @Id
    @GeneratedValue
    private int id;

    /**
     * 对应权限名称
     */
    @Column(length = 30, nullable = false)
    private String name;
    
    /**
     * 等级
     */
    @Column(length = 1, nullable = false)
    private String grade;
    
    /**
     * 描述
     * 
     */
    @Column(nullable = true, columnDefinition="TEXT")
    private String description;
    
    /**
     * 系统 用户
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "jurisdiction")
    private Set<SysUser> sysUsers = new HashSet<>();
    
    @ManyToMany(mappedBy = "jurisdictions")
    private Set<Tree> trees = new HashSet<>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Set<SysUser> getSysUsers() {
		return sysUsers;
	}

	public void setSysUsers(Set<SysUser> sysUsers) {
		this.sysUsers = sysUsers;
	}

	public Set<Tree> getTrees() {
		return trees;
	}

	public void setTrees(Set<Tree> trees) {
		this.trees = trees;
	}
    
}
