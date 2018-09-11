package cn.pzhu.vo;

import javax.persistence.Column;
import javax.persistence.Id;

public class TreeVo {

	private Integer id;
	
	private String text;

	private String state;
	
	private String iconCls;
	
	private String url;
	
	private Integer tid;
	
	

	public TreeVo(Integer id, String text, String state, String iconCls, String url, Integer tid) {
		super();
		this.id = id;
		this.text = text;
		this.state = state;
		this.iconCls = iconCls;
		this.url = url;
		this.tid = tid;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getTid() {
		return tid;
	}

	public void setTid(Integer tid) {
		this.tid = tid;
	}
	
	
}
