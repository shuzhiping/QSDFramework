package com.qsd.framework.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.util.Assert;

import com.qsd.framework.hibernate.bean.SQLEntity;
import com.qsd.framework.hibernate.exception.DataRunException;
import com.qsd.framework.utils.ArrayUtil;

/**
 * 数据库持久层基础操作
 *
 * @author suntongwei
 * @version 1.0
 * @create at Oct 6, 2011
 */
@SuppressWarnings("unchecked")
public abstract class JpaDaoBase<T,ID extends Serializable> extends HqlResultSupport<T,ID> implements DatabaseTemplate<T,ID> {

	// LOG
	private static final Logger LOG = LoggerFactory.getLogger(JpaDaoBase.class);
	
	// 获取类的泛型
	protected Class<T> persistentClass = (Class<T>) DaoUtil.getTypeArguments(JpaDaoBase.class, this.getClass()).get(0);
	
	/**
	 * 根据实体保存
	 * 
	 * @param t 实体类
	 * @return
	 * @throws DataRunException
	 */
	public void save(final T t) throws DataRunException {
		try {
			Assert.notNull(t,"save entity is not null");
			super.getJpaTemplate().persist(t);
		} catch (DataAccessException e) {
			LOG.error("save entity exception： " + t.getClass(),e);
			throw new DataRunException("save entity exception： " + t.getClass());
		}
	}
	
	/**
	 * 保存所有集合实体
	 * 
	 * @param collection 实体集合
	 * @throws DataRunException 如果插入失败则抛出该异常
	 */
	public void saveAll(final Collection<T> collection) throws DataRunException {
		try {
			for(T t : collection)
				save(t);
		} catch (DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 更新实体
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public T merge(final T t) throws DataRunException {
		try {
			return super.getJpaTemplate().merge(t);
		} catch (DataAccessException e) {
			LOG.error("merge entity exception： " + t.getClass(),e);
			throw new DataRunException("save entity exception： " + t.getClass());
		}
	}
	
	/**
	 * 更新实体集合
	 * 
	 * @param collection
	 * @throws DataRunException
	 */
	public void mergeAll(final Collection<T> collection) throws DataRunException {
		try {
			for(T t : collection)
				merge(t);
		} catch (DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 根据实体更新
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void update(final T t) throws DataRunException {
		try {
			Assert.notNull(t,"update entity is not null");
			super.getJpaTemplate().merge(t);
		} catch (DataAccessException e) {
			LOG.error("merge entity exception： " + t.getClass(),e);
			throw new DataRunException("merge entity exception： " + t.getClass());
		}
	}
	
	/**
	 * 更新所有集合实体
	 * 
	 * @param t
	 * @throws DataRunException
	 */
	public void updateAll(final Collection<T> collection) throws DataRunException {
		try {
			for(T t : collection)
				update(t);
		} catch (DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 根据实体删除
	 * 
	 * @param t 实体类
	 * @return
	 * @throws DataRunException
	 */
	public void delete(final T t) throws DataRunException {
		try {
			Assert.notNull(t,"delete entity is not null");
			super.getJpaTemplate().remove(t);
		} catch (DataAccessException e) {
			LOG.error("delete entity exception： " + t.getClass(),e);
			throw new DataRunException("delete entity exception： " + t.getClass());
		}
	}
	
	/**
	 * 删除所有集合实体
	 * 
	 * @param collection 实体集合
	 * @throws DataRunException 如果删除失败则抛出该异常
	 */
	public void deleteAll(final Collection<T> collection) throws DataRunException {
		try {
			for(T t : collection)
				delete(t);
		} catch (DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 根据主键删除对象
	 * 
	 * @param id 主键ID
	 */
	public void deleteById(final ID id) throws DataRunException {
		try {
			super.getJpaTemplate().remove(findById(id));
		} catch (DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 根据多个主键删除对象
	 * 
	 * @param ids 主键ID集合
	 */
	public void deleteByIds(final ID... ids) throws DataRunException {
		try {
			for(ID id : ids){
				deleteById(id);
			}
		} catch (DataRunException e){
			throw e;
		}
	}
	
	/**
	 * 根据HQL语句查询
	 * 
	 * @param queryString HQL语句
	 * @return
	 */
	public List<T> find(final String queryString) {
		return findByParams(queryString);
	}
	
	/**
	 * 根据SQL语句查询
	 * 
	 * @param queryString SQL语句
	 * @param conver SQL查询数据转换器
	 * @return
	 */
	public List<T> find(final String queryString,final QueryDataConver<T> conver){
		return this.findByParams(queryString, conver);
	}
	
	/**
	 * 根据HQL语句和参数查询一条数据结果<br/>
	 * 只返回数据的第一条
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public T findFirstByParams(final String queryString,final Object... params){
		List<T> ret = this.findByParams(queryString,params);
		return ret != null && !ret.isEmpty() ? ret.get(0) : null;
	}
	
	/**
	 * 根据HQL语句查询一条数据结果<br/>
	 * 只返回数据的第一条
	 * 
	 * @param queryString
	 * @return
	 */
	public T findFirst(final String queryString) {
		List<T> ret = this.find(queryString);
		return ret != null && !ret.isEmpty() ? ret.get(0) : null;
	}
	
	/**
	 * 根据SQL语句和参数查询一条数据结果<br/>
	 * 只返回数据的第一条
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public <E> E findFirstConverByParams(final String queryString, final QueryDataConver<E> conver, final Object... params){
		List<E> ret = this.findConverByParams(queryString, conver, params);
		return ret != null && !ret.isEmpty() ? ret.get(0) : null;
	}
	
	/**
	 * 根据HQL语句和参数查询
	 * 
	 * @param queryString
	 * @param params
	 * @return
	 */
	public List<T> findByParams(final String queryString,final Object... params) {
		return super.getJpaTemplate().find(queryString,params);
	}
	
	/**
	 * 根据SQL语句和参数查询
	 * 
	 * @param queryString SQL语句
	 * @param conver SQL查询数据转换器
	 * @param params 查询参数
	 * @return
	 */
	public List<T> findByParams(final String queryString,final QueryDataConver<T> conver,final Object... params) {
		SQLEntity entity = new SQLEntity();
		entity.setEntity(queryString);
		entity.setQueryDataConver(conver);
		entity.setParams(params);
		return super.queryResult(entity);
	}
	
	/**
	 * 根据SQL语句和参数查询第一条记录
	 * 
	 * @param queryString
	 * @param conver
	 * @param params
	 * @return
	 */
	public T findFirstByParams(final String queryString,final QueryDataConver<T> conver,final Object... params) {
		List<T> result = findByParams(queryString,conver,params);
		return ArrayUtil.isEmpty(result) ? null : result.get(0);
	}
	
	/**
	 * 根据SQL语句和参数查询
	 * 
	 * @param <E> 自定义类型
	 * @param queryString SQL语句
	 * @param conver SQL查询数据转换器
	 * @param params 查询参数
	 * @return
	 */
	public <E> List<E> findConverByParams(final String queryString,final QueryDataConver<E> conver,final Object... params) {
		SQLEntity entity = new SQLEntity();
		entity.setEntity(queryString);
		entity.setQueryDataConver(conver);
		entity.setParams(params);
		return super.queryResultConver(entity);
	}
	
	/**
	 * 返回所有记录
	 * 无分页
	 * 
	 * @return
	 */
	public List<T> findAll() {
		return findByParams("From " + this.persistentClass.getName());
	}
	
	/**
	 * 根据主键查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	public T findById(final ID id){
		return super.getJpaTemplate().find(this.persistentClass, id);
	}
}
