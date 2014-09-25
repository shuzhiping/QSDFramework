package com.qsd.framework.tools.property;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 系统配置文件工具类
 * 
 * @author suntongwei
 * @version 1.0
 * @create at Aug 9, 2011
 */
public final class PropertyUtil {

	private static final Logger log = LoggerFactory.getLogger(PropertyUtil.class);
	
	private PropertyUtil(){}
	
	/**
	 * 根据路径读取
	 * /resources/systemconfig.properties
	 * 
	 * @param propsPath
	 * @return
	 */
	public static Properties load(String propsPath){
		try {
			Properties properties = new Properties();
			InputStream in = PropertyUtil.class.getResourceAsStream(propsPath);
			properties.load(in);
			return properties;
		} catch (IOException e) {
			log.debug("读取配置文件异常");
			return null;
		}
	}
}
