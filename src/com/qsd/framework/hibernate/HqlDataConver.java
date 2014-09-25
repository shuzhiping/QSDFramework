package com.qsd.framework.hibernate;

/**
 * 
 * 
 * @author suntongwei
 *
 * @param <E>
 */
public interface HqlDataConver<E> {

	/**
	 * 转换数据
	 * 
	 * @param row SQL查询数据结果
	 * @return 封装后数据结果
	 */
	public <T> E converData(T t);
}
