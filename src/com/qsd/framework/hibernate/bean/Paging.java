package com.qsd.framework.hibernate.bean;

/**
 * 分页查询
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Apr 9, 2011
 */
public class Paging implements IPaging {
	
	/** 当前页 */
	private Integer indexPage;
	/** 每页最大记录数 */
	private Integer maxRows;
	
	public Paging(int indexPage, int maxRows) {
		this.indexPage = indexPage;
		this.maxRows = maxRows;
	}
	
	public Integer getIndexPage() {
		if(indexPage > 0)
			return (indexPage - 1) * maxRows;
		return indexPage;
	}
	public void setIndexPage(Integer indexPage) {
		this.indexPage = indexPage;
	}
	public Integer getMaxRows() {
		return maxRows;
	}
	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}
	
	public int setIndexPage() {
		return getIndexPage() / getMaxRows() + 1;
	}
}
