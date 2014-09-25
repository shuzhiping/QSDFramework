package com.qsd.framework.html.tags;

import java.util.List;

/**
 * 
 * 
 * @author suntongwei
 */
public abstract class EffectTag extends Tag {

	/**
	 * ID
	 */
	private String id;
	/**
	 * NAME
	 */
	private String name;
	
	public abstract void addChildTag(Tag tag);
	
	public abstract List<Tag> getChildTag();
	
	public abstract boolean hasChild();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
