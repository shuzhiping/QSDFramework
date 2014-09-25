package com.qsd.framework.spring.mvc.response;

import com.qsd.framework.hibernate.bean.PagingResult;

public class PagingResponse<T> extends BaseResponse {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4412623002009060810L;

	private PagingResult<T> result;
	
	public PagingResult<T> getResult() {
		return result;
	}
	public void setResult(PagingResult<T> result) {
		this.result = result;
	}
}
