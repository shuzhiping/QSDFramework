package com.qsd.framework.html.tags;

import java.util.ArrayList;
import java.util.List;

/**
 * HTML DIV TAG
 * 
 * @author suntongwei
 */
public class DivTag extends EffectTag {

	public static final String TAG = "Div";
	
	private List<Tag> childTag = new ArrayList<Tag>();
	
	

	@Override
	public String getTagName() {
		return TAG;
	}

	@Override
	public void addChildTag(Tag tag) {
		this.childTag.add(tag);
	}
	@Override
	public List<Tag> getChildTag() {
		return childTag;
	}
	@Override
	public boolean hasChild() {
		return childTag.isEmpty();
	}

	
}
