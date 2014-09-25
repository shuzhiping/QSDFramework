package com.qsd.framework.hibernate.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 
 * @author suntongwei
 */
@SuppressWarnings("unchecked")
public abstract class DataEntity {
	
	/** 查询语句 */
	private String entity;
	/** 查询参数 */
	private List<?> params;
	/** 分页参数 */
	private IPaging paging;
	
	public DataEntity(){}
	public DataEntity(String entity){
		this.entity = entity;
	}
	public DataEntity(String entity,List<?> params){
		this.entity = entity;
		this.params = params;
	}
	
	/**
	 * 返回类型
	 * 
	 * @return
	 */
	public abstract QueryType getType();
	
	public Object[] getParams(){
		if(null == this.params || this.params.size() < 1)
			return new Object[0];
		return this.params.toArray();
	}
	public List setParams() {
		if(null == this.params)
			this.params = new ArrayList();
		return params;
	}
	public void setParam(Object obj) {
		setParams().add(obj);
	}
	public void setParams(Object... params){
		for(Object param : params)
			this.setParam(param);
	}
	public IPaging getPaging() {
		return this.paging;
	}
	public void setPaging(IPaging p) {
		this.paging = p;
	}
	public String getEntity() {
		return entity;
	}
	public void setEntity(String entity) {
		this.entity = entity;
	}
	public void setEntity(StringBuffer entity) {
		this.entity = entity.toString();
	}
}
