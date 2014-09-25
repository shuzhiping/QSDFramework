package com.qsd.framework.html.tags;

/**
 * HTML TAG
 * 
 * @author suntongwei
 */
public abstract class Tag {
	
	private String className;
	
	private String style;
	
	private String onClickEvent;
	
	public abstract String getTagName();

	public String getOnClickEvent() {
		return onClickEvent;
	}

	public void setOnClickEvent(String onClickEvent) {
		this.onClickEvent = onClickEvent;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}
}
