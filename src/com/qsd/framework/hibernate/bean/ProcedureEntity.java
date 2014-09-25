package com.qsd.framework.hibernate.bean;

import java.util.List;

public class ProcedureEntity extends DataEntity {

	public ProcedureEntity(){}
	public ProcedureEntity(String entity){
		super(entity);
	}
	public ProcedureEntity(String entity,List<?> params){
		super(entity,params);
	}
	
	@Override
	public QueryType getType() {
		return QueryType.PROCEDURE;
	}

}
