package cn.pzhu.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 公告
 * @author 逃离
 *
 */
@Entity
@Table(name = "t_notice")
public class Notice {

    /**
     * 公告id
     */
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 发布者id （一对多）
     */
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="user_Id")
    private SysUser sysUser;

    /**
     * 公告标题
     */
    @Column(nullable = false)
    private String title;

    /**
     *  所添加附件（多对一）
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER, mappedBy = "notice")
    private Set<Attachment> attachments = new HashSet<Attachment>();

    /**
     * 发布日期
     */
    @Column(length = 20,nullable = false)
    private String date;

    /**
     * 公告类型
     */
    @Column(nullable = false)
    private String type;
    
    /**
     * 内容
     */
    @Column(nullable = false, columnDefinition="TEXT")
    private String content;

    public Notice() {
		super();
	}

	public Notice(SysUser sysUser, String title, String date, String type) {
		super();
		this.sysUser = sysUser;
		this.title = title;
		this.date = date;
		this.type = type;
	}
	

	public Notice(SysUser sysUser, String title, String date, String type,
			String content) {
		super();
		this.sysUser = sysUser;
		this.title = title;
		this.date = date;
		this.type = type;
		this.content = content;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SysUser getSysUser() {
        return sysUser;
    }

    public void setSysUser(SysUser sysUser) {
        this.sysUser = sysUser;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Set<Attachment> attachments) {
        this.attachments = attachments;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
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
    
}