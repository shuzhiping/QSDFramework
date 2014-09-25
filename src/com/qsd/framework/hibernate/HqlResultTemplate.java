package com.qsd.framework.hibernate;

import java.io.Serializable;
import java.util.List;

import com.qsd.framework.hibernate.bean.DataEntity;
import com.qsd.framework.hibernate.bean.PagingResult;
import com.qsd.framework.hibernate.exception.DataRunException;

/**
 * 针对HqlEntity和HqlResult的数据库操作方法
 * 
 * @author suntongwei
 *
 * @param <T> 实体对象
 * @param <ID>
 */
public interface HqlResultTemplate<T,ID extends Serializable> {

	/**
	 * 执行函数
	 * 
	 * @param entity
	 * @return
	 */
	public int execute(final DataEntity entity) throws DataRunException;
	
	/**
	 * 执行SQL语句
	 * 
	 * @param sql
	 * @return
	 * @throws DataRunException
	 */
	public int execute(String sql) throws DataRunException;
	
	/**
	 * 查询
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public List<T> queryResult(final DataEntity entity);
	
	/**
	 * 执行分页查询
	 * 
	 * @param <T>
	 * @return
	 */
	public PagingResult<T> queryWithPaging(final DataEntity entity);
	
	/**
	 * 返回查询记录数
	 * 
	 * @return
	 */
	public Long getDataCount(final DataEntity entity);
}
