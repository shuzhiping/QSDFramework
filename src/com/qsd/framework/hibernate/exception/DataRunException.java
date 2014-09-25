package com.qsd.framework.hibernate.exception;

import com.qsd.framework.exception.SystemException;

/**
 * 数据库运行异常
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Aug 9, 2011
 */
public class DataRunException extends SystemException {
	
	private static final long serialVersionUID = 8323735385984164246L;
	
	private Integer errorCode;
	private String message;
	
	public DataRunException(int errorCode) {
		super();
		this.errorCode = errorCode;
	}
	
	public DataRunException(String message) {
		super(message);
		this.message = message;
	}
	public DataRunException(int errorCode, String message) {
		super(message);
		this.message = message;
		this.errorCode = errorCode;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Integer getErrorCode() {
		return this.errorCode;
	}
}
