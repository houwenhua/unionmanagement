package cn.pzhu.model;

import java.util.List;

public class Pagination {

	/**
	 * 总记录数
	 */
	private Long total;
	
	/**
	 * 当前页
	 */
	private Integer page;
	
	/**
	 * 当前页中的记录
	 */
	private List rows;

	public Pagination() {
		super();
	}

	public Pagination(Long total, Integer page, List rows) {
		super();
		this.total = total;
		this.page = page;
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}
	
}
