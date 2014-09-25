package com.qsd.framework.tools.xml;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.qsd.framework.tools.xml.annotation.TagType;
import com.qsd.framework.tools.xml.annotation.XMLBean;
import com.qsd.framework.tools.xml.annotation.XMLTag;
import com.qsd.framework.tools.xml.bean.XMLNode;

/**
 * XML工具
 * 
 * @author suntongwei
 */
public class XML {

	// LOG
	private static final Logger LOG = LoggerFactory.getLogger(XML.class);
	
	/**
	 * 读取XML返回XMLNode实体
	 * 
	 * @param in
	 * @return
	 */
	public static XMLNode loadXml(InputStream in){
		return XMLUtil.createXMLNode(XMLUtil.getSaxDocument(in).getRootElement());
	}
	
	public static XMLNode loadXml(URL url){
		return XMLUtil.createXMLNode(XMLUtil.getSaxDocument(url).getRootElement());
	}
	
	/**
	 * 读取XML返回实体类
	 * 
	 * @param <T> 需要转换的实体类型
	 * @param cls 实体Class
	 * @param in  XML输入流
	 * @return
	 */
	public static <T> T loadXml(Class<T> cls,InputStream in){
		
		try {
			
			XMLNode XMLNode = loadXml(in);
			
			Assert.isNull(XMLNode,"XML: XMLNode is null.");
			Assert.isNull(cls.getAnnotation(XMLBean.class), "XML:" + cls.getName() + " is not XML Class");
			
			T bean = cls.newInstance();
			
			Method[] methods = cls.getMethods();
			
			for(Method method : methods){
				if(method.getAnnotation(XMLTag.class) != null && method.getName().startsWith("set")){
					XMLTag tag = method.getAnnotation(XMLTag.class);
					
					XMLNode info = XMLUtil.queryXMLNode(XMLNode, tag.tagName());
					
					if(TagType.ATTRIBUTE.equals(tag.type())){
						method.invoke(bean,info.getAttribute());
					} else if(TagType.VALUE.equals(tag.type())){
						method.invoke(bean, info.getValue());
					}
					
				}
			}
		
			return bean;
			
		} catch (InstantiationException e) {
			LOG.error("XML:" + cls.getName() + " newInstance error");
		} catch (IllegalAccessException e) {
			LOG.error("XML:" + cls.getName() + " newInstance error");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	
}
