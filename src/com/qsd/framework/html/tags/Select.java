package com.qsd.framework.html.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import com.qsd.framework.utils.StringUtil;

/**
 * 
 * 
 * @author suntongwei
 */
public class Select extends TagSupport {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7608080192134395492L;

	private String name;
	private Integer value;
	private Map<Integer,String> items;
	
	@Override
	public int doStartTag() throws JspException {
		StringBuilder sb = new StringBuilder("<select " + (StringUtil.isNull(name) ? "" : "name=\"" + name + "\"") + ">");
		if(items != null && !items.isEmpty()) {
			for(Iterator<Integer> it = items.keySet().iterator(); it.hasNext();) {
				Integer key = it.next();
				String isSelect = "";
				if(value != null && value.intValue() == key.intValue()) {
					isSelect = "selected=\"selected\"";
				}
				sb.append("<option value=\"" + key + "\"" + isSelect + ">");
				sb.append(items.get(key));
				sb.append("</option>");
			}
		}
		sb.append("</select>");
		
		try {
			super.pageContext.getOut().write(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return super.doStartTag();
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public Map<Integer, String> getItems() {
		return items;
	}
	public void setItems(Map<Integer, String> items) {
		this.items = items;
	}
}
