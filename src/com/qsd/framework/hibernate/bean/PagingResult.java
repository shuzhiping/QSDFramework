package com.qsd.framework.hibernate.bean;

import java.util.List;

/**
 * HQL结果实体
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Apr 9, 2011
 * @param <T>
 */
public class PagingResult<T> {

	private List<T> data; 
	private Integer indexPage = 1;
	@SuppressWarnings("unused")
	private Integer totalPage; 
	private Long totalData; 
	private Integer maxRows; 
	
	/**
	 * 判断是否有下一页
	 * 
	 * @return
	 */
	public boolean getIsNext() {
		if (this.getIndexPage() == this.getTotalPage())
			return true;
		else
			return false;
	}

	/**
	 * 判断是否有上一页
	 * 
	 * @return
	 */
	public boolean getIsPrev() {
		if (this.getIndexPage() == 1)
			return true;
		else
			return false;
	}

	/**
	 * 返回一页显示最大数据
	 * 
	 * @return
	 */
	public Integer getMaxRows() {
		return maxRows != null && maxRows > 0 ? maxRows : 0;
	}

	public void setMaxRows(Integer maxRows) {
		this.maxRows = maxRows;
	}

	/**
	 * 返回当前页数
	 * 
	 * @return
	 */
	public Integer getIndexPage() {
		return this.indexPage;
	}

	/**
	 * 返回总共页数
	 * 
	 * @return
	 */
	public Integer getTotalPage() {
		if(null == this.totalData) return 0;
		return (this.totalData.intValue() - 1) / this.getMaxRows() + 1;
	}

	/**
	 * 返回总共数据数
	 * 
	 * @return
	 */
	public Long getTotalData() {
		if(null == this.totalData) return 0l;
		return totalData;
	}

	public void setTotalData(Long totalData) {
		this.totalData = totalData;
	}
	
	public void setIndexPage(Integer indexPage) {
		this.indexPage = indexPage;
	}

	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	
	/**
	 * 返回当前数据索引
	 */
	public int getRowIndex() {
		return getMaxRows() * (getIndexPage() - 1);
	}

	/**
	 * 返回数据
	 * 
	 * @return
	 */
	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}
}
