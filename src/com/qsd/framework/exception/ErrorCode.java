package com.qsd.framework.exception;

/**
 * 系统错误码
 * 
 * @author suntongwei
 *
 */
public interface ErrorCode {

	/**
	 * 系统级错误
	 */
	public static final int SYSTEM_ERROR = 0x1;
	
	/**
	 * 数据库执行错误
	 */
	public static final int DB_EXECUTE_ERROR = 0x3E8;
	
	/**
	 * 权限异常错误
	 */
	public static final int SECURITY_ERROR = 0x7D0;
	
}
