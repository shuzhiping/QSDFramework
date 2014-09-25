package com.qsd.framework.tools.xml;

import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.util.Assert;

import com.qsd.framework.tools.xml.bean.XMLNode;

public class XMLUtil {

	/**
	 * 使用Sax解析方式解析Xml
	 * 
	 * @param file
	 * @return
	 */
	public static Document getSaxDocument(InputStream in) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(in);
			return document;
		} catch (DocumentException e) {
			return null;
		}
	}
	
	public static Document getSaxDocument(URL url) {
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(url);
			return document;
		} catch (DocumentException e) {
			return null;
		}
	}
	
	public static XMLNode queryXMLNode(XMLNode info,String tagName){
		
		Assert.isNull(tagName,"XML: Query NodeInfo param tagName not null");
		
		if(tagName.equals(info.getTagName())){
			return info;
		} else {
			if(info.getNodeList() != null && !info.getNodeList().isEmpty()){
				for(XMLNode child : info.getNodeList()){
					queryXMLNode(child,tagName);
				}
			}
		}
		
		return null;
	}
	
	/**
	 * 返回一个封装的XML文件BEAN 使用递归函数
	 * 
	 * @param e
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static XMLNode createXMLNode(Element e) {

		XMLNode info = new XMLNode();

		info.setTagName(e.getName());
		if (e.attributes() != null && e.attributes().size() > 0) {
			Map<String, String> attMap = new HashMap<String, String>();
			List<Attribute> attrs = e.attributes();
			for (Attribute attr : attrs) {
				attMap.put(attr.getName(), attr.getValue());
			}
			info.setAttribute(attMap);
		}
		if (e.getTextTrim() != null && !"".equals(e.getTextTrim())) {
			info.setValue(e.getText());
		}

		if (XMLUtil.hasElement(e)) {
			List<Element> eList = e.elements();
			for (Element child : eList) {
				info.getNodeList().add(XMLUtil.createXMLNode(child));
			}
		}

		return info;
	}
	
	/**
	 * 判断是否有子节点
	 * 
	 * @param e
	 * @return
	 */
	public static boolean hasElement(Element e) {

		boolean ret = false;

		if (e.elements() != null && e.elements().size() > 0)
			return true;

		return ret;
	}
}
