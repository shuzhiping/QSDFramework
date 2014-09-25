package com.qsd.framework.hibernate;

/**
 * SQL查询自动数据注入接口
 * 
 * @author suntongwei
 * @version 1.0
 * @since 2011-10-12
 * @param <T>
 */
public interface QueryDataConver<T> {

	/**
	 * 转换数据
	 * 
	 * @param row SQL查询数据结果
	 * @return 封装后数据结果
	 */
	public T converData(Object[] row);
}
