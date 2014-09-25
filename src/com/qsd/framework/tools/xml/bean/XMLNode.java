package com.qsd.framework.tools.xml.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 解析XML实体绑定类
 * 
 * @author suntongwei
 * @version 1.1
 * @update 2010.7.13 add annotation variable
 * @create at 2010-4-28
 */
public class XMLNode {

	/** 参数名和值 */
	private Map<String, String> attribute;
	/** 注释 */
	private String annotaction;
	/** 标签名 */
	private String tagName;
	/** 内容 */
	private String value;
	/** 子节点 */
	private List<XMLNode> nodeList;

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public String getTagName() {
		return tagName;
	}

	public void setTagName(String tagName) {
		this.tagName = tagName;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public List<XMLNode> getNodeList() {
		if (null == this.nodeList)
			this.nodeList = new ArrayList<XMLNode>();
		return this.nodeList;
	}

	public void setNodeList(List<XMLNode> nodeList) {
		this.nodeList = nodeList;
	}

	public String getAnnotaction() {
		return annotaction;
	}

	public void setAnnotaction(String annotaction) {
		this.annotaction = annotaction;
	}
}
