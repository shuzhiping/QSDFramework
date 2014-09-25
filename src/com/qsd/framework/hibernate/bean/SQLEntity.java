package com.qsd.framework.hibernate.bean;

import java.util.List;

import com.qsd.framework.hibernate.QueryDataConver;

/**
 * SQL查询实体
 * 
 * @author suntongwei
 * @version 1.0
 * @create 2011-10-26
 */
public class SQLEntity extends DataEntity {

	public SQLEntity(){}
	public SQLEntity(String entity){
		super(entity);
	}
	public SQLEntity(String entity,QueryDataConver<?> queryDataConver){
		super(entity);
		this.queryDataConver = queryDataConver;
	}
	public SQLEntity(String entity,List<?> params,QueryDataConver<?> queryDataConver){
		super(entity,params);
		this.queryDataConver = queryDataConver;
	}
	
	/** 当使用SQL查询的时候，数据转换器 */
	private QueryDataConver<?> queryDataConver;
	
	@Override
	public QueryType getType() {
		return QueryType.SQL;
	}
	
	public QueryDataConver<?> getQueryDataConver() {
		return queryDataConver;
	}
	public void setQueryDataConver(QueryDataConver<?> queryDataConver) {
		this.queryDataConver = queryDataConver;
	}
}
