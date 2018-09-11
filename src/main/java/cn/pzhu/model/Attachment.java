package cn.pzhu.model;

import javax.persistence.*;

/**
 * 附件
 * @author 逃离
 *
 */
@Entity
@Table(name = "t_attachment")
public class Attachment {
    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 原名
     */
    @Column(nullable = false)
    private String originallyname;

    /**
     * 服务器保存名
     */
    @Column(nullable = false)
    private  String  servername;

    /**
     * 公告id
     */
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
    @JoinColumn(name="notice_id")
    private Notice notice;
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
	@JoinColumn(name = "signup_id")
	private SignUpActivity signups;
    
    @ManyToOne(cascade = { CascadeType.REFRESH, CascadeType.MERGE }, optional = true)
   	@JoinColumn(name = "statisticsActivity_id")
   	private StatisticsActivity statisticsActivity;

    public Attachment() {
		super();
	}

	public Attachment(String originallyname, String servername, StatisticsActivity statisticsActivity) {
		super();
		this.originallyname = originallyname;
		this.servername = servername;
		this.statisticsActivity = statisticsActivity;
	}

	public Attachment(String originallyname, String servername, SignUpActivity signups) {
		super();
		this.originallyname = originallyname;
		this.servername = servername;
		this.signups = signups;
	}

	public Attachment(String originallyname, String servername, Notice notice) {
		super();
		this.originallyname = originallyname;
		this.servername = servername;
		this.notice = notice;
	}

	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Notice getNotice() {
        return notice;
    }

    public void setNotice(Notice notice) {
        this.notice = notice;
    }

	public String getOriginallyname() {
		return originallyname;
	}

	public void setOriginallyname(String originallyname) {
		this.originallyname = originallyname;
	}

	public String getServername() {
		return servername;
	}

	public void setServername(String servername) {
		this.servername = servername;
	}

	public SignUpActivity getSignups() {
		return signups;
	}

	public void setSignups(SignUpActivity signups) {
		this.signups = signups;
	}
	
    
}
