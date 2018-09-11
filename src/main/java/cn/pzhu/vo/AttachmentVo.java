package cn.pzhu.vo;

import javax.persistence.Column;

public class AttachmentVo {

	private Integer id;

    private String originallyname;

    private  String  servername;

	public AttachmentVo(Integer id, String originallyname, String servername) {
		super();
		this.id = id;
		this.originallyname = originallyname;
		this.servername = servername;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
    
}
