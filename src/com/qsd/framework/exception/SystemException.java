package com.qsd.framework.exception;

/**
 * 系统异常类
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Aug 7, 2011
 */
public class SystemException extends RuntimeException {

	private static final long serialVersionUID = -7217605938452020324L;
	
	private int code;

	public SystemException(){}
	
	public SystemException(String message){
		super(message);
	}
	
	public SystemException(int code,String message) {
		super(message);
		this.code = code;
	}
	
	public SystemException(String message, Throwable cause){
		super(message,cause);
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
}
