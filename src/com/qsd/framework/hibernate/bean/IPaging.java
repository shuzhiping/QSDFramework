package com.qsd.framework.hibernate.bean;

/**
 * 分页参数接口
 *
 * @author suntongwei
 */
public interface IPaging {

	/**
	 * 设置当前页数
	 * 
	 * @param indexPage
	 */
	public int setIndexPage();
	
	/**
	 * 返回起始页数
	 * 
	 * @return
	 */
	public Integer getIndexPage();
	
	/**
	 * 每页最大记录数
	 * 
	 * @return
	 */
	public Integer getMaxRows();
}
