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

/**
 * 活动统计表
 * @author 逃离
 *
 */
@Entity
public class StatisticsActivity {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column(nullable = false)
	private String actionname;
	
	@Column(nullable = false)
	private String institute;
	
	@Column(nullable = false)
	private String statisticsname;
	
	@Column(nullable = false)
	private String number;
	
	 /**
     *  所添加附件（多对一）
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "statisticsActivity")
    private Set<Attachment> attachments = new HashSet<Attachment>();

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

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
    
    
}
