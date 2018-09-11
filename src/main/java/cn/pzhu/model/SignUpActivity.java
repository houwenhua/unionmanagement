package cn.pzhu.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "signupactivity")
public class SignUpActivity {

	/**
	 * 编号
	 */
	@Id
	@GeneratedValue
	private Integer id;
	
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "user_id")
	private SysUser user;
	
	
	@ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "activity_id")
	private Activity activity;
	
	@OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "signups")
	private Set<Attachment> attachments = new HashSet<>();
	
	@Column(length = 50,nullable=true)
	private String signupdate;
	
	@Column(length = 1,nullable=true)
	private String checkactivity;
	
	

	public SignUpActivity() {
		super();
	}

	public SignUpActivity(SysUser user, Activity activity, String signupdate, String checkactivity) {
		super();
		this.user = user;
		this.activity = activity;
		this.signupdate = signupdate;
		this.checkactivity = checkactivity;
	}

	public SignUpActivity(Integer id, SysUser user, Activity activity, String signupdate, String checkactivity) {
		super();
		this.id = id;
		this.user = user;
		this.activity = activity;
		this.signupdate = signupdate;
		this.checkactivity = checkactivity;
	}

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

	public Activity getActivity() {
		return activity;
	}

	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	public String getSignupdate() {
		return signupdate;
	}

	public void setSignupdate(String signupdate) {
		this.signupdate = signupdate;
	}

	public String getCheckactivity() {
		return checkactivity;
	}

	public void setCheckactivity(String checkactivity) {
		this.checkactivity = checkactivity;
	}

	public Set<Attachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(Set<Attachment> attachments) {
		this.attachments = attachments;
	}
	
}
