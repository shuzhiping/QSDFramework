package com.qsd.framework.hibernate;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.jpa.JpaCallback;
import org.springframework.util.Assert;

import com.qsd.framework.hibernate.bean.DataEntity;
import com.qsd.framework.hibernate.bean.HQLEntity;
import com.qsd.framework.hibernate.bean.PagingResult;
import com.qsd.framework.hibernate.bean.QueryType;
import com.qsd.framework.hibernate.bean.SQLEntity;
import com.qsd.framework.hibernate.exception.DataRunException;
import com.qsd.framework.utils.ArrayUtil;

/**
 * 针对HqlEntity和HqlResult的数据库操作方法
 * 
 * @author suntongwei
 *
 * @param <T> 操作实体
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public abstract class HqlResultSupport<T,ID extends Serializable> 
		extends org.springframework.orm.jpa.support.JpaDaoSupport implements HqlResultTemplate<T,ID> {

	// LOG
	private static final Logger LOG = LoggerFactory.getLogger(JpaDaoSupport.class);
	
	/**
	 * 根据不同类型返回不同Query对象
	 * 
	 * @param type
	 * @return
	 * @throws DataBaseException 
	 */
	private Query getQuery(QueryType type,String queryStr,EntityManager em){
		
		try {
		
			Query query = null;
	
			if (QueryType.HQL == type) {
				query = em.createQuery(queryStr);
			} else if (QueryType.SQL == type) {
				query = em.createNativeQuery(queryStr);
			} else if (QueryType.PROCEDURE == type) {
				query = em.createNativeQuery("{call " + queryStr + "}");
			}
			
			return query;
		
		} catch (RuntimeException e) {
			LOG.error("HqlResultSupport: Query init error");
			return null;
		}
	}
	
	/**
	 * 执行函数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public int execute(final String sql, final Object... params) throws DataRunException {
		try {
			DataEntity entity = new SQLEntity();
			entity.setEntity(sql);
			entity.setParams(params);
			return execute(entity);
		} catch(DataRunException e) {
			throw e;
		}
	}
	
	/**
	 * 执行函数
	 * 
	 * @param entity
	 * @return
	 */
	public int execute(final DataEntity entity) throws DataRunException {
		try {
			return super.getJpaTemplate().execute(new JpaCallback<Integer>(){
				public Integer doInJpa(EntityManager em) throws PersistenceException {
					em.joinTransaction();
					Query query = getQuery(entity.getType(),entity.getEntity(),em);
					for (int i = 0; i < entity.getParams().length; i++) {
						query.setParameter(i + 1, entity.getParams()[i]);
					}
					return query.executeUpdate();
				}
			},true);
		} catch(DataAccessException e) {
			throw new DataRunException("HqlResultSupport Method execute error! \n Message:" + e.getMessage());
		}
	}
	
	/**
	 * 执行函数
	 * 
	 * @param sql SQL语句
	 * @return
	 */
	public int execute(final String sql) throws DataRunException {
		try {
			return super.getJpaTemplate().execute(new JpaCallback<Integer>(){
				public Integer doInJpa(EntityManager em) throws PersistenceException {
					em.joinTransaction();
					return getQuery(QueryType.SQL,sql,em).executeUpdate();
				}
			},true);
		} catch(DataAccessException e) {
			throw new DataRunException("HqlResultSupport Method execute error! \n Message:" + e.getMessage());
		}
	}
	
	/**
	 * 查询
	 * 
	 * @param <T>
	 * @param entity
	 * @return
	 */
	public List<T> queryResult(final DataEntity entity){
		return super.getJpaTemplate().execute(new JpaCallback<List<T>>(){
			public List<T> doInJpa(EntityManager em) throws PersistenceException {
				Assert.notNull(entity, "DataEntity is not null");
				Assert.notNull(entity.getEntity(), "DataEntity -- entity is not null");
				Query query = getQuery(entity.getType(),entity.getEntity(),em);
				if (entity.getParams() != null && entity.getParams().length > 0) {
					Object[] paramObj = entity.getParams();
					for (int i = 0; i < paramObj.length; i++) {
						query.setParameter(i + 1, paramObj[i]);
					}
				}
				List<T> result = null;
				if(entity.getType() == QueryType.SQL){
					SQLEntity sqlEntity = (SQLEntity) entity;
					Assert.notNull(sqlEntity.getQueryDataConver(), "QueryType -- SQL QueryDataConver is not null");
					List<Object[]> sqlRet = query.getResultList();
					if(sqlRet != null && !sqlRet.isEmpty()){
						result = new ArrayList<T>();
						if(sqlRet.get(0) instanceof Object[]){
							for(Object[] record : sqlRet){
								result.add((T) sqlEntity.getQueryDataConver().converData(record));
							}
						} else {
							for(Object record : sqlRet){
								result.add((T) sqlEntity.getQueryDataConver().converData(new Object[]{record}));
							}
						}
					}
				} else {
					result = query.getResultList();
				}
				return result;
			}
		});
	}
	
	public <E> List<E> queryResultConver(final DataEntity entity){
		return super.getJpaTemplate().execute(new JpaCallback<List<E>>(){
			public List<E> doInJpa(EntityManager em) throws PersistenceException {
				Assert.notNull(entity, "DataEntity is not null");
				Assert.notNull(entity.getEntity(), "DataEntity -- entity is not null");
				Query query = getQuery(entity.getType(),entity.getEntity(),em);
				if (entity.getParams() != null && entity.getParams().length > 0) {
					Object[] paramObj = entity.getParams();
					for (int i = 0; i < paramObj.length; i++) {
						query.setParameter(i + 1, paramObj[i]);
					}
				}
				List<E> result = null;
				if(entity.getType() == QueryType.SQL){
					SQLEntity sqlEntity = (SQLEntity) entity;
					Assert.notNull(sqlEntity.getQueryDataConver(), "QueryType -- SQL QueryDataConver is not null");
					List<Object[]> sqlRet = query.getResultList();
					if(sqlRet != null && !sqlRet.isEmpty()){
						result = new ArrayList<E>();
						if(sqlRet.get(0) instanceof Object[]){
							for(Object[] record : sqlRet){
								result.add((E) sqlEntity.getQueryDataConver().converData(record));
							}
						} else {
							for(Object record : sqlRet){
								result.add((E) sqlEntity.getQueryDataConver().converData(new Object[]{record}));
							}
						}
					}
				} else {
					HQLEntity hqlEntity = (HQLEntity) entity;
					List<T> tempResult = query.getResultList();
					if(tempResult != null && !tempResult.isEmpty()) {
						result = new ArrayList<E>();
						for(T temp : tempResult) {
							result.add((E)hqlEntity.getHqlDataConver().converData(temp));
						}
					}
				}
				return result;
			}
		});
	}
	
	/**
	 * 根据不同对象转换
	 * 
	 * @param <E>
	 * @param entity
	 */
	public <E> PagingResult<E> queryWithPagingConver(final DataEntity entity) {
		return super.getJpaTemplate().execute(new JpaCallback<PagingResult<E>>(){
			public PagingResult<E> doInJpa(EntityManager em) throws PersistenceException {
				Assert.notNull(entity, "DataEntity is not null");
				Assert.notNull(entity.getEntity(), "DataEntity -- entity is not null");
				Query query = getQuery(entity.getType(),entity.getEntity(),em);
				if (entity.getParams() != null && entity.getParams().length > 0) {
					for (int i = 0; i < entity.getParams().length; i++) {
						query.setParameter(i + 1, entity.getParams()[i]);
					}
				}
				//Long count = getDataCount(entity);
				Long count = 0l;
				List<?> resultList = query.getResultList();
				if(!ArrayUtil.isEmpty(resultList)) {
					count = Long.valueOf(resultList.size());
				}
				if (null == count || count < 1) {
					return new PagingResult<E>();
				}
				if (entity.getPaging() != null) {
					query.setFirstResult(entity.getPaging().getIndexPage());
					query.setMaxResults(entity.getPaging().getMaxRows());
				}
				List<E> rList = null;
				if(entity.getType() == QueryType.SQL){
					SQLEntity sqlEntity = (SQLEntity) entity;
					Assert.notNull(sqlEntity.getQueryDataConver(), "QueryType -- SQL QueryDataConver is not null");
					List<Object[]> sqlRet = query.getResultList();
					if(sqlRet != null && !sqlRet.isEmpty()){
						rList = new ArrayList<E>();
						for(Object[] record : sqlRet){
							rList.add((E) sqlEntity.getQueryDataConver().converData(record));
						}
//						if(sqlRet.get(0) instanceof Object[]){
//							
//						} else {
//							for(Object record : sqlRet){
//								rList.add((E) sqlEntity.getQueryDataConver().converData(new Object[]{record}));
//							}
//						}
					}
				} else {
					HQLEntity hqlEntity = (HQLEntity) entity;
					List<T> tempResult = query.getResultList();
					if(tempResult != null && !tempResult.isEmpty()) {
						rList = new ArrayList<E>();
						for(T temp : tempResult) {
							rList.add((E)hqlEntity.getHqlDataConver().converData(temp));
						}
					}
				}
				
				PagingResult<E> result = new PagingResult<E>();
				if (rList != null && !rList.isEmpty()) {
					result.setData(rList);
					if(entity.getPaging() != null){
						result.setIndexPage(entity.getPaging().setIndexPage());
						result.setMaxRows(entity.getPaging().getMaxRows());
					}
					result.setTotalData(count);
				}
				return result;
			}
		});
	}
	
	/**
	 * 执行分页查询
	 * 
	 * @param <T>
	 * @return
	 */
	public PagingResult<T> queryWithPaging(final DataEntity entity) {
		return super.getJpaTemplate().execute(new JpaCallback<PagingResult<T>>(){
			public PagingResult<T> doInJpa(EntityManager em) throws PersistenceException {
				Assert.notNull(entity, "DataEntity is not null");
				Assert.notNull(entity.getEntity(), "DataEntity -- entity is not null");
				Query query = getQuery(entity.getType(),entity.getEntity(),em);
				if (entity.getParams() != null && entity.getParams().length > 0) {
					for (int i = 0; i < entity.getParams().length; i++) {
						query.setParameter(i + 1, entity.getParams()[i]);
					}
				}
				
				//Long count = getDataCount(entity);
				Long count = 0l;
				List<?> resultList = query.getResultList();
				if(!ArrayUtil.isEmpty(resultList)) {
					count = Long.valueOf(resultList.size());
				}
				
				if (null == count || count < 1) {
					return new PagingResult<T>();
				}
		
				if (entity.getPaging() != null) {
					query.setFirstResult(entity.getPaging().getIndexPage());
					query.setMaxResults(entity.getPaging().getMaxRows());
				}
		
				List<T> rList = null;
				if(entity.getType() == QueryType.SQL){
					SQLEntity sqlEntity = (SQLEntity) entity;
					Assert.notNull(sqlEntity.getQueryDataConver(), "QueryType -- SQL QueryDataConver is not null");
					List<Object[]> sqlRet = query.getResultList();
					if(sqlRet != null && !sqlRet.isEmpty()){
						rList = new ArrayList<T>();
						for(Object[] record : sqlRet){
							rList.add((T) sqlEntity.getQueryDataConver().converData(record));
						}
//						if(sqlRet.get(0) instanceof Object[]){
//							
//						} else {
//							for(Object record : sqlRet){
//								rList.add((T) sqlEntity.getQueryDataConver().converData(new Object[]{record}));
//							}
//						}
					}
				} else {
					rList = query.getResultList();
				}
				
				PagingResult<T> result = new PagingResult<T>();
				if (rList != null && !rList.isEmpty()) {
					result.setData(rList);
					if(entity.getPaging() != null){
						result.setIndexPage(entity.getPaging().setIndexPage());
						result.setMaxRows(entity.getPaging().getMaxRows());
					}
					result.setTotalData(count);
				}
				return result;
			}
		});
	}
	
	/**
	 * 返回查询记录数
	 * 
	 * @return
	 */
	public Long getDataCount(final DataEntity entity) {
		return super.getJpaTemplate().execute(new JpaCallback<Long>(){
			public Long doInJpa(EntityManager em) throws PersistenceException {
				Query query = getQuery(entity.getType(),entity.getEntity(),em);
				if (entity.getParams() != null && entity.getParams().length > 0) {
					for (int i = 0; i < entity.getParams().length; i++) {
						query.setParameter(i + 1, entity.getParams()[i]);
					}
				}
				return Long.valueOf(query.getSingleResult().toString());
			}
		});
	}
	
	/**
	 * 使用SQL查询记录数
	 * 
	 * @param sql
	 * @param params
	 * @return
	 */
	public Long getDataCount(final String sql, final Object... params) {
		return super.getJpaTemplate().execute(new JpaCallback<Long>(){
			public Long doInJpa(EntityManager em) throws PersistenceException {
				Query query = getQuery(QueryType.SQL,sql,em);
				if (params != null && params.length > 0) {
					for (int i = 0; i < params.length; i++) {
						query.setParameter(i + 1, params[i]);
					}
				}
				return Long.valueOf(query.getSingleResult().toString());
			}
		});
	}
}
