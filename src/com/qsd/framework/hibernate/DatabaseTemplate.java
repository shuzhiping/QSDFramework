package com.qsd.framework.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import com.qsd.framework.hibernate.exception.DataRunException;

/**
 * 数据库基本方法接口
 *
 * @author suntongwei
 * @version 1.0
 * @create at Oct 6, 2011
 */
public interface DatabaseTemplate<T,ID extends Serializable> {

	/**
	 * 根据实体保存
	 * 
	 * @param t 实体类
	 * @return
	 * @throws DataRunException
	 */
	public void save(final T t) throws DataRunException;
	
	/**
	 * 保存所有集合实体
	 * 
	 * @param collection 实体集合
	 * @throws DataRunException 如果插入失败则抛出该异常
	 */
	public void saveAll(final Collection<T> collection) throws DataRunException;
	
	/**
	 * 更新实体
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public T merge(final T t) throws DataRunException;
	
	/**
	 * 更新实体集合
	 * 
	 * @param collection
	 * @throws DataRunException
	 */
	public void mergeAll(final Collection<T> collection) throws DataRunException;
	
	/**
	 * 根据实体更新
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void update(final T t) throws DataRunException;
	
	/**
	 * 更新所有集合实体
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void updateAll(final Collection<T> collection) throws DataRunException;
	
	/**
	 * 根据实体删除
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void delete(final T t) throws DataRunException;
	
	/**
	 * 删除所有集合实体
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void deleteAll(final Collection<T> collection) throws DataRunException;
	
	/**
	 * 根据主键删除对象
	 * 
	 * @param id 主键ID
	 */
	public void deleteById(final ID id) throws DataRunException;
	
	/**
	 * 根据多个主键删除对象
	 * 
	 * @param id 主键ID
	 */
	public void deleteByIds(final ID... ids) throws DataRunException;
	
	/**
	 * 根据HQL语句查询
	 * 
	 * @param queryString HQL语句
	 * @return
	 */
	public List<T> find(final String queryString);
	
	/**
	 * 根据SQL语句查询
	 * 
	 * @param queryString SQL语句
	 * @param conver SQL查询数据转换器
	 * @return
	 */
	public List<T> find(final String queryString,final QueryDataConver<T> conver);
	
	/**
	 * 根据HQL语句和参数查询一条数据结果<br/>
	 * 只返回数据的第一条
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public T findFirstByParams(final String queryString,final Object... params);
	
	/**
	 * 根据HQL语句和参数查询
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public List<T> findByParams(final String queryString,final Object... params);
	
	/**
	 * 根据SQL语句和参数查询
	 * 
	 * @param queryString SQL语句
	 * @param conver SQL查询数据转换器
	 * @param params 查询参数
	 * @return
	 */
	public List<T> findByParams(final String queryString,final QueryDataConver<T> conver,final Object... params);
	
	/**
	 * 返回所有记录
	 * 无分页
	 * 
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 根据主键查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public T findById(final ID id);
}
