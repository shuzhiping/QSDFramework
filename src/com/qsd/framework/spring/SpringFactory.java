package com.qsd.framework.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 获取spring在环境中的对象
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Aug 14, 2011
 */
public class SpringFactory implements ServletContextListener {
	
	/**
	 * servlet上下文
	 */
	private static ServletContext servletContext = null;
	/**
	 * spring上下文
	 */
	private static WebApplicationContext context = null;
	
	/**
	 * 根据Bean名获取对象
	 * 
	 * @param beanId Spring Bean Id
	 * @return
	 */
	public static Object getBean(String beanId) {
		return context.getBean(beanId);
	}
	
	/**
	 * 根据实体对象获取Bean
	 * 
	 * @param <T> Bean Class Type
	 * @param cls 实体对象
	 * @return
	 */
	public static <T> T getBean(Class<T> cls){
		return context.getBean(cls);
	}
	
	/**
	 * 项目启动加载Spring上下文
	 */
	public void contextInitialized(ServletContextEvent sce) {
		servletContext = sce.getServletContext();
		context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
	}
		
	public void contextDestroyed(ServletContextEvent sce) {}
}
