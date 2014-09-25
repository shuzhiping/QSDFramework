package com.qsd.framework.spring.mvc;

import javax.servlet.http.HttpServletRequest;

/**
 * spring mvc 支持类
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Jul 30, 2011
 */
public abstract class ControllerSupport {

	public static final String SUCCESS = "SUCCESS";
	public static final String ERROR = "ERROR";
	public static final String FAIL = "FAIL";
	// 权限异常
	public static final String SEC = "SEC";

	/**
	 * 获取用户IP地址
	 * 
	 * @param request
	 * @return
	 */
	protected String getIp(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
