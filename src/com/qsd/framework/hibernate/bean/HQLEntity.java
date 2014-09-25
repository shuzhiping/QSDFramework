package com.qsd.framework.hibernate.bean;

import java.util.List;

import com.qsd.framework.hibernate.HqlDataConver;

/**
 * 查询实体
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Apr 9, 2011
 */
@SuppressWarnings("unchecked")
public class HQLEntity extends DataEntity {
	
	public HQLEntity(){}
	public HQLEntity(String entity){
		super.setEntity(entity);
	}
	public HQLEntity(String entity,List<?> params){
		this(entity);
		super.setParams(params);
	}
	public HQLEntity(String entity,List<?> params, HqlDataConver<?> hqlDataConver){
		this(entity,params);
		this.hqlDataConver = hqlDataConver;
	}
	
	private HqlDataConver<?> hqlDataConver;
	
	@Override
	public QueryType getType() {
		return QueryType.HQL;
	}
	public HqlDataConver<?> getHqlDataConver() {
		return hqlDataConver;
	}
	public void setHqlDataConver(HqlDataConver<?> hqlDataConver) {
		this.hqlDataConver = hqlDataConver;
	}
}
