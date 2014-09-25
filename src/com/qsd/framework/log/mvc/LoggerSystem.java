/**
 * Copyright 2013 Yxkj Co.,Ltd. All rights reserved.
 */
package com.qsd.framework.log.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 基于SPRING MVC拦截器模式的日志记录系统
 *
 * @author suntongwei
 * @since 2013-3-21
 * @version 1.0
 */
public class LoggerSystem implements HandlerInterceptor {

	// log
//	private final Logger log = LoggerFactory.getLogger(LoggerSystem.class);
	
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		
	}

	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		
	}

	public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2) throws Exception {
		return false;
	}

}
